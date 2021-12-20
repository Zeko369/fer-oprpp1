package hr.fer.zemris.math;

import java.util.Arrays;

public class ComplexRootedPolynomial {
    private final Complex constant;
    private final Complex[] roots;

    public ComplexRootedPolynomial(Complex constant, Complex... roots) {
        this.constant = constant;
        this.roots = roots;
    }

    // computes polynomial value at given point z
    public Complex apply(Complex z) {
        return Arrays.stream(this.roots)
                .reduce(
                        new Complex(this.constant),
                        (acc, root) -> acc.multiply((z.sub(root)))
                );
    }

    // converts this representation to ComplexPolynomial type
    // f(z) oblika z0*(z-z1)*(z-z2)*...*(z-zn)
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

    // finds index of closest root for given complex number z that is within
    // treshold; if there is no such root, returns -1
    // first root has index 0, second index 1, etc
    public int indexOfClosestRootFor(Complex z, double threshold) {
        int indexMin = 0;
        double min = this.roots[0].sub(z).module();

        for (int i = 1; i < this.roots.length; i++) {
            double tmp = this.roots[i].distance(z);
            if (tmp < min) {
                min = tmp;
                indexMin = i;
            }
        }

        return min < threshold ? indexMin : -1;
    }
}
