package hr.fer.zemris.math;

import java.util.Arrays;

/**
 * Complex polynomial class
 *
 * @author franzekan
 */
public class ComplexPolynomial {
    private final Complex[] factors;

    /**
     * Instantiates a new Complex polynomial.
     *
     * @param factors the factors
     */
    public ComplexPolynomial(Complex... factors) {
        this.factors = factors;
    }

    /**
     * Order of the polynomial.
     *
     * @return the short
     */
    public short order() {
        return (short) (this.factors.length - 1);
    }

    /**
     * Multiply complex polynomial.
     *
     * @param p the p
     * @return the complex polynomial
     */
    public ComplexPolynomial multiply(ComplexPolynomial p) {
        Complex[] result = new Complex[this.factors.length + p.factors.length - 1];
        Arrays.fill(result, Complex.ZERO);

        for (int i = 0; i < this.factors.length; i++) {
            for (int j = 0; j < p.factors.length; j++) {
                result[i + j] = result[i + j].add(this.factors[i].multiply(p.factors[j]));
            }
        }

        return new ComplexPolynomial(result);
    }

    /**
     * Derive complex polynomial.
     *
     * @return the complex polynomial
     */
    public ComplexPolynomial derive() {
        Complex[] newFactors = new Complex[this.factors.length - 1];
        for (int i = 1; i < this.factors.length; i++) {
            newFactors[i - 1] = new Complex(this.factors[i].getReal() * i, this.factors[i].getImaginary() * i);
        }

        return new ComplexPolynomial(newFactors);
    }

    /**
     * Apply complex.
     *
     * @param z the z
     * @return the complex
     */
    public Complex apply(Complex z) {
        Complex result = this.factors[0];
        for (int i = 1; i < this.factors.length; i++) {
            result = result.add(z.power(i-1).multiply(this.factors[i]));
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = this.factors.length - 1; i >= 0; i--) {
            if (i == 0) {
                sb.append(String.format("(%s)", this.factors[i]));
                break;
            }

            sb.append(String.format("(%s)z^%d+", this.factors[i], i));
        }

        return sb.toString();
    }
}
