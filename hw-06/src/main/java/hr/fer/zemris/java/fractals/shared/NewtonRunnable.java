package hr.fer.zemris.java.fractals.shared;

import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.concurrent.atomic.AtomicBoolean;

public class NewtonRunnable implements Runnable {
    private final ComplexRootedPolynomial crp;
    private final ComplexPolynomial cp;

    public static final double ROOT_THRESHOLD = 0.002;
    public static final double CONVERGENCE_THRESHOLD = 0.0001;
    public static final int MAX_ITER = 16 * 16 * 16 * 16;

    private final double reMin, reMax;
    private final double imMin, imMax;
    private final int width, height;
    private final int yMin, yMax;

    private final short[] image;
    private final AtomicBoolean cancel;

    public static NewtonRunnable EMPTY = new NewtonRunnable();

    public NewtonRunnable(ComplexRootedPolynomial crp,
                          double reMin, double reMax, double imMin, double imMax,
                          int width, int height,
                          int yMin, int yMax, short[] image,
                          AtomicBoolean cancel) {

        this.crp = crp;
        this.cp = crp == null ? null : crp.toComplexPolynom();

        this.reMin = reMin;
        this.reMax = reMax;
        this.imMin = imMin;
        this.imMax = imMax;
        this.width = width;
        this.height = height;
        this.yMin = yMin;
        this.yMax = yMax;
        this.image = image;
        this.cancel = cancel;
    }

    public NewtonRunnable() {
        this(null, 0, 0, 0, 0, 0, 0, 0, 0, null, null);
    }


    @Override
    public void run() {
        int offset = yMin * width;

        for (int y = yMin; y <= yMax; y++) {
            if (cancel.get()) break;

            for (int x = 0; x < width; x++) {
                Complex c = new Complex(
                        x / (width - 1.0) * (reMax - reMin) + reMin,
                        (height - 1.0 - y) / (height - 1) * (imMax - imMin) + imMin
                );

                Complex znold;
                Complex zn = c;
                int iter = 0;

                do {
                    znold = zn;
                    zn = zn.sub(cp.apply(zn).divide(cp.derive().apply(zn)));
                    iter++;
                } while (zn.sub(znold).module() > CONVERGENCE_THRESHOLD && iter < MAX_ITER);

                short index = (short) this.crp.indexOfClosestRootFor(zn, ROOT_THRESHOLD);
                this.image[offset++] = (short) (index + 1);
            }
        }
    }
}
