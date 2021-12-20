package hr.fer.zemris.math;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Complex {
    public static final Complex ZERO = new Complex(0, 0);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex ONE_NEG = new Complex(-1, 0);
    public static final Complex IM = new Complex(0, 1);
    public static final Complex IM_NEG = new Complex(0, -1);

    private final double real;
    private final double imaginary;

    public Complex(double re, double im) {
        this.real = re;
        this.imaginary = im;
    }

    public Complex(Complex other) {
        this(other.real, other.imaginary);
    }

    public Complex() {
        this(0, 0);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    private static double parseImaginary(String str) {
        if (str.equals("i")) {
            return 1;
        }
        return Double.parseDouble(str.substring(1));
    }

    public static Complex fromString(String raw) {
        if (Objects.requireNonNull(raw).isEmpty()) {
            throw new IllegalArgumentException("Empty string is not a valid complex number.");
        }

        String[] parts = raw.split("\\s+");
        if (parts.length == 1) {
            if (parts[0].charAt(0) == 'i') {
                return new Complex(0, parseImaginary(parts[0]));
            }

            return new Complex(Double.parseDouble(parts[0]), 0);
        } else if (parts.length == 3) {
            double real = Double.parseDouble(parts[0]);

            if (!parts[2].startsWith("i")) {
                throw new IllegalArgumentException("Invalid complex number, second part must start with 'i'");
            }

            double imaginary = parseImaginary(parts[2]);
            if (parts[1].equals("-")) {
                imaginary *= -1;
            }

            return new Complex(real, imaginary);
        } else {
            throw new IllegalArgumentException("Invalid complex number format.");
        }
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
        Complex tmp = new Complex(this);
        for (int i = 0; i < n; i++) {
            tmp = tmp.multiply(this);
        }
        return tmp;
    }

    public List<Complex> root(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Power must be non-negative.");
        }

        double mag = Math.pow(Math.sqrt(real * real + imaginary * imaginary), 1.0 / 2);
        double initialAngle = Math.atan2(imaginary, real);

        return IntStream.range(0, n)
                .boxed()
                .map(i -> (initialAngle + 2 * i * Math.PI) / n)
                .map(ang -> new Complex(mag * Math.cos(ang), mag * Math.sin(ang)))
                .toList();
    }

    public double distance(Complex other) {
        return this.sub(other).module();
    }

    @Override
    public String toString() {
        String prefix = this.imaginary < 0 ? "-" : "+";
        return String.format("%s%si%s", this.real, prefix, Math.abs(this.imaginary));
    }
}
