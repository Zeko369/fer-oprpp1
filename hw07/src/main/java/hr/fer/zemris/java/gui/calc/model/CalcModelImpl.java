package hr.fer.zemris.java.gui.calc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class CalcModelImpl implements CalcModel {
    private boolean editable = true;
    private boolean positive = true;

    private double value = 0;
    private String display = "";

    private Double activeOperand;
    private DoubleBinaryOperator pendingOperation;

    private final List<CalcValueListener> listeners = new ArrayList<>();

    @Override
    public void addCalcValueListener(CalcValueListener l) {
        this.listeners.add(l);
    }

    @Override
    public void removeCalcValueListener(CalcValueListener l) {
        this.listeners.remove(l);
    }

    private void notifyListeners() {
        for (CalcValueListener l : listeners) {
            l.valueChanged(this);
        }
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
        this.display = String.valueOf(value);
        this.editable = false;

        // TODO: check NaN -inf, +inf

        this.notifyListeners();
    }

    @Override
    public boolean isEditable() {
        return this.editable;
    }

    @Override
    public void clear() {
        this.display = "";
        this.value = 0;

        this.notifyListeners();
    }

    @Override
    public void clearAll() {
        this.clear();
        this.pendingOperation = null;
        this.activeOperand = null;
        this.editable = true;

        this.notifyListeners();
    }

    private void checkEditable() {
        if (!this.editable) {
            throw new CalculatorInputException("Calc model is not editable.");
        }
    }

    @Override
    public void swapSign() throws CalculatorInputException {
        this.checkEditable();
        this.positive = !this.positive;
        this.value *= -1;

        if (!this.display.isEmpty()) {
            if (this.display.startsWith("-")) {
                this.display = this.display.substring(1);
            } else {
                this.display = "-" + this.display;
            }
        }

        this.notifyListeners();
    }

    @Override
    public void insertDecimalPoint() throws CalculatorInputException {
        this.checkEditable();

        if (this.display.isEmpty()) {
            throw new CalculatorInputException("Invalid number");
        }

        if (this.display.contains(".")) {
            throw new CalculatorInputException("Decimal point already exists.");
        }

        this.display += ".";
        this.notifyListeners();
    }

    @Override
    public void insertDigit(int digit) throws CalculatorInputException, IllegalArgumentException {
        this.checkEditable();

        if (digit > 9 || digit < 0) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }

        String tmp = this.display + digit;

        if (this.display.equals("0")) {
            tmp = String.valueOf(digit);
        }

        this.value = Double.parseDouble(tmp);
        this.display = tmp;

        if (this.value > Double.MAX_VALUE || this.value < -Double.MAX_VALUE || Double.isNaN(this.value)) {
            throw new CalculatorInputException("Invalid number");
        }

        this.notifyListeners();
    }

    @Override
    public boolean isActiveOperandSet() {
        return this.activeOperand != null;
    }

    @Override
    public double getActiveOperand() throws IllegalStateException {
        if (!this.isActiveOperandSet()) {
            throw new IllegalStateException("Active operand is not set.");
        }

        return this.activeOperand;
    }

    @Override
    public void setActiveOperand(double activeOperand) {
        this.activeOperand = activeOperand;
    }

    @Override
    public void clearActiveOperand() {
        this.activeOperand = null;
    }

    @Override
    public DoubleBinaryOperator getPendingBinaryOperation() {
        return this.pendingOperation;
    }

    @Override
    public void setPendingBinaryOperation(DoubleBinaryOperator op) {
        this.pendingOperation = op;
    }

    @Override
    public String toString() {
        if (this.display.isEmpty()) {
            return this.positive ? "0" : "-0";
        }

        return this.display;
    }
}
