package hr.fer.zemris.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for complex math stuff
 *
 * @author franzekan
 */
class ComplexRootedPolynomialTest {
    @Test
    void testAll() {
        ComplexRootedPolynomial crp = new ComplexRootedPolynomial(
                new Complex(2, 0), Complex.ONE, Complex.ONE_NEG, Complex.IM, Complex.IM_NEG
        );
        ComplexPolynomial cp = crp.toComplexPolynom();

        assertEquals("(2.0+i0.0)*(z-(1.0+i0.0))*(z-(-1.0+i0.0))*(z-(0.0+i1.0))*(z-(0.0-i1.0))", crp.toString());
        assertEquals("(2.0+i0.0)*z^4+(0.0+i0.0)*z^3+(0.0+i0.0)*z^2+(0.0+i0.0)*z^1+(-2.0+i0.0)", cp.toString());
        assertEquals("(8.0+i0.0)*z^3+(0.0+i0.0)*z^2+(0.0+i0.0)*z^1+(0.0+i0.0)", cp.derive().toString());
    }
}
