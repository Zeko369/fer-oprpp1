package hr.fer.zemris.math;

public class ComplexPolynomial {
    public static void main(String[] args) {
        ComplexPolynomial p = new ComplexPolynomial(
                new Complex(7, 2),
                new Complex(2, 0),
                new Complex(5, 0),
                new Complex(1, 0)
        );

        // (7+2i)z^3+2z^2+5z+1 returns (21+6i)z^2+4z+5
        System.out.println(p);
        System.out.println(p.derive());
    }

    private final Complex[] factors;

    public ComplexPolynomial(Complex... factors) {
        this.factors = factors;
    }

    public short order() {
        return (short) (this.factors.length - 1);
    }

    // computes a new polynomial this*p
    public ComplexPolynomial multiply(ComplexPolynomial p) {
        return null;
    }

    // computes first derivative of this polynomial; for example, for
    public ComplexPolynomial derive() {
        Complex[] newFactors = new Complex[this.factors.length - 1];
        int size = this.order();

        for (int i = 0; i < size; i++) {
            newFactors[i] = this.factors[i].multiply(new Complex(size - i, 0));
        }

        return new ComplexPolynomial(newFactors);
    }

    // computes polynomial value at given point z
    public Complex apply(Complex z) {
        Complex result = new Complex(0, 0);
        for (int i = 0; i < this.factors.length; i++) {
            result = result.add(z.power(i).add(this.factors[i]));
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.factors.length; i++) {
            sb.append(String.format("%s*z^%d", this.factors[i], this.factors.length - i - 1));
            if(i < this.factors.length - 1) {
                sb.append("+");
            }
        }

        return sb.toString();
    }
}
