package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.shared.NewtonRunnable;
import hr.fer.zemris.java.fractals.util.ArgParser;
import hr.fer.zemris.java.fractals.util.RootPolynomialInput;
import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonParallel {
    public static void main(String[] args) {
        System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
        System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");

        ComplexRootedPolynomial crp = RootPolynomialInput.loadFromInput();
        ArgParser ap = new ArgParser(args);

        FractalViewer.show(new MultiWorkerProducer(crp, ap.getWorkers(), ap.getTracks()));
    }

    private static class MultiWorkerProducer implements IFractalProducer {
        private final int workers;
        private int tracks;
        private final ComplexRootedPolynomial crp;
        private final BlockingQueue<NewtonRunnable> queue = new LinkedBlockingQueue<>();

        public MultiWorkerProducer(ComplexRootedPolynomial crp, int workers, int tracks) {
            this.workers = workers;
            this.tracks = tracks;
            this.crp = crp;
        }

        private interface InterruptedThrowableRunnable {
            void call() throws InterruptedException;
        }

        private void waitTillQueueExecuted(InterruptedThrowableRunnable runnable) {
            while (true) {
                try {
                    runnable.call();
                    break;
                } catch (InterruptedException ignored) {
                }
            }
        }

        private void addToQueue(NewtonRunnable job) {
            this.waitTillQueueExecuted(() -> this.queue.put(job));
        }

        @Override
        public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height, long requestNo,
                            IFractalResultObserver observer, AtomicBoolean cancel) {
            long start = System.currentTimeMillis();
            short[] image = new short[width * height];

            if (this.tracks > height) {
                this.tracks = height;
            }

            int countPerTrack = height / this.tracks;

            System.out.printf("Tracks %d, Workers %d\n", this.tracks, this.workers);
            Thread[] threads = new Thread[this.workers];

            for (int i = 0; i < this.workers; i++) {
                threads[i] = new Thread(() -> {
                    while (true) {
                        try {
                            NewtonRunnable p = this.queue.take();
                            if (p == NewtonRunnable.EMPTY) {
                                break;
                            }

                            p.run();
                        } catch (InterruptedException ignored) {
                        }
                    }
                });
                threads[i].start();
            }

            for (int i = 0; i < tracks; i++) {
                int yMin = i * countPerTrack;
                int yMax = i == tracks - 1 ? height - 1 : (i + 1) * countPerTrack - 1;

                this.addToQueue(new NewtonRunnable(this.crp, reMin, reMax, imMin, imMax, width, height, yMin, yMax, image, cancel));
            }
            for (int i = 0; i < this.workers; i++) {
                this.addToQueue(NewtonRunnable.EMPTY);
            }

            for (int i = 0; i < this.workers; i++) {
                int finalI = i;
                this.waitTillQueueExecuted(() -> threads[finalI].join());
            }

            System.out.println("Calculation took " + (System.currentTimeMillis() - start) + " ms.");
            observer.acceptResult(image, (short) (this.crp.toComplexPolynom().order() + 1), requestNo);
        }
    }
}
