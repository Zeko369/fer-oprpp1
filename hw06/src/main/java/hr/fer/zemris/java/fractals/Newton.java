package hr.fer.zemris.java.fractals;

import hr.fer.zemris.java.fractals.shared.NewtonRunnable;
import hr.fer.zemris.java.fractals.util.RootPolynomialInput;
import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class implementing the Newton-Raphson fractal.
 *
 * @author franzekan
 */
public class Newton {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
        System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");

        ComplexRootedPolynomial crp = RootPolynomialInput.loadFromInput();
        FractalViewer.show(new SingleThreadedFractalProducer(crp));
    }

    private static class SingleThreadedFractalProducer implements IFractalProducer {
        private final ComplexPolynomial cp;
        private final ComplexRootedPolynomial crp;

        /**
         * Instantiates a new Single threaded fractal producer.
         *
         * @param crp the crp
         */
        public SingleThreadedFractalProducer(ComplexRootedPolynomial crp) {
            this.crp = crp;
            this.cp = crp.toComplexPolynom();
        }

        @Override
        public void produce(double reMin, double reMax, double imMin, double imMax, int width, int height, long requestNo,
                            IFractalResultObserver observer, AtomicBoolean cancel) {
            long start = System.currentTimeMillis();
            short[] image = new short[width * height];
            new NewtonRunnable(
                    this.crp,
                    reMin,
                    reMax,
                    imMin,
                    imMax,
                    width,
                    height,
                    0,
                    height - 1,
                    image,
                    cancel
            ).run();

            System.out.println("Calculation took " + (System.currentTimeMillis() - start) + " ms.");
            observer.acceptResult(image, (short) (this.cp.order() + 1), requestNo);
        }
    }
}
