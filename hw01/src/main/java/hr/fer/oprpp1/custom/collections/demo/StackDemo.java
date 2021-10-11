package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ObjectStack;

/**
 * examples: "8 2 /" or "-1   8    2 / +"
 */
public class StackDemo {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("You need to pass exactly one argument");
            System.exit(1);
        }

        try {
            int val = calculate(args[0]);
            System.out.printf("Expression evaluates to %d.", val);
        } catch (InvalidExpression ex) {
            System.err.println("Error evaluating expression");
        } catch (ArithmeticException ex) {
            System.err.printf("You have a math problem with your expression: %s", ex.getMessage());
        }
    }

    private static int calculate(String expression) {
        ObjectStack stack = new ObjectStack();

        for (String v : expression.split(" ")) {
            if (v.trim().length() > 0) {
                try {
                    stack.push(Integer.parseInt(v));
                } catch (NumberFormatException ex) {
                    stack.push(eval((int) stack.pop(), (int) stack.pop(), v));
                }
            }
        }

        if (stack.size() != 1) {
            throw new InvalidExpression();
        }

        return (int) stack.pop();
    }

    private static int eval(int a, int b, String operation) {
        return switch (operation) {
            case "+" -> b + a;
            case "-" -> b - a;
            case "/" -> b / a;
            case "*" -> b * a;
            case "%" -> b % a;
            default -> throw new RuntimeException("Wrong operation");
        };
    }

}
