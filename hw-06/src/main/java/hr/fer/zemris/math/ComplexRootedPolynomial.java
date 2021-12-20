package hr.fer.zemris.math;

import java.util.Arrays;

/**
 * Complex rooted polynomial with a structure of z0*(z-z1)*...*(z-zn)
 *
 * @author franzekan
 */
public class ComplexRootedPolynomial {
    private final Complex constant;
    private final Complex[] roots;

    /**
     * Instantiates a new Complex rooted polynomial.
     *
     * @param constant the constant
     * @param roots    the roots
     */
    public ComplexRootedPolynomial(Complex constant, Complex... roots) {
        this.constant = constant;
        this.roots = roots;
    }

    /**
     * Apply complex.
     *
     * @param z the z
     * @return the complex
     */
    public Complex apply(Complex z) {
        return Arrays.stream(this.roots)
                .reduce(
                        new Complex(this.constant),
                        (acc, root) -> acc.multiply((z.sub(root)))
                );
    }

    /**
     * To complex polynom complex polynomial.
     *
     * @return the complex polynomial
     */
    public ComplexPolynomial toComplexPolynom() {
        ComplexPolynomial res = new ComplexPolynomial(this.constant);
        for (Complex root : this.roots) {
            res = res.multiply(new ComplexPolynomial(root.negate(), Complex.ONE));
        }

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("(%s)", this.constant.toString()));
        for (Complex root : this.roots) {
            sb.append(String.format("*(z-(%s))", root));
        }

        return sb.toString();
    }

    /**
     * Index of closest root for int.
     *
     * @param z         the z
     * @param threshold the threshold
     * @return the int
     */
    public int indexOfClosestRootFor(Complex z, double threshold) {
        int indexMin = 0;
        double min = -1;

        for (int i = 0; i < this.roots.length; i++) {
            if (i == 0) {
                min = this.roots[i].distance(z);
                continue;
            }

            double tmp = this.roots[i].distance(z);
            if (tmp < min) {
                min = tmp;
                indexMin = i;
            }
        }

        if(min < threshold) {
            return indexMin;
        }

        return -1;
    }
}
