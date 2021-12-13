package hr.fer.zemris.math;

import java.util.ArrayList;
import java.util.List;

public class Complex {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex ONE_NEG = new Complex(-1, 0);
    public static final Complex IM = new Complex(0, 1);
    public static final Complex IM_NEG = new Complex(0, -1);

    private final double real;
    private final double imaginary;

    public Complex() {
        this.real = 0;
        this.imaginary = 0;
    }

    public Complex(double re, double im) {
        this.real = re;
        this.imaginary = im;
    }

    public double module() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public Complex multiply(Complex other) {
        return new Complex(real * other.real - imaginary * other.imaginary,
                real * other.imaginary + imaginary * other.real);
    }

    public Complex divide(Complex other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        return new Complex((real * other.real + imaginary * other.imaginary) / denominator,
                (imaginary * other.real - real * other.imaginary) / denominator);
    }

    public Complex add(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex sub(Complex other) {
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex negate() {
        return new Complex(-real, -imaginary);
    }

    public Complex power(int n) {
        return new Complex(Math.pow(module(), n), n * Math.atan2(imaginary, real));
    }

    public List<Complex> root(int n) {
        List<Complex> roots = new ArrayList<>();

        return roots;
    }

    @Override
    public String toString() {
        return String.format("(%s+%si)", this.real, this.imaginary);
    }
}
