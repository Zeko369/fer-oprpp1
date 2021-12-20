package hr.fer.zemris.java.fractals.util;

import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexRootedPolynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RootPolynomialInput {
    public static ComplexRootedPolynomial loadFromInput() {
        List<Complex> nums = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Root 1> ");
        while (sc.hasNext()) {
            System.out.printf("Root %d> ", nums.size() + 1);
            String line = sc.nextLine();
            if (line.equals("done")) {
                break;
            }

            nums.add(Complex.fromString(line));
        }

        return new ComplexRootedPolynomial(Complex.ONE, nums.toArray(new Complex[0]));
    }
}
