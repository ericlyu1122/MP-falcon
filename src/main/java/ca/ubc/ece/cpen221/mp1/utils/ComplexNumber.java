package ca.ubc.ece.cpen221.mp1.utils;

public class ComplexNumber {
    private double R, I;

    // constructor.
    public ComplexNumber(double real, double imaginary) {
        this.R = real;
        this.I = imaginary;
    }

    /**
     * @return the real part of the complex number.
     *
     */
    public double real() {
        return R;
    }

    /**
     * @return the imaginary part of the complex number
     *
     */
    public double imaginary() {
        return I;
    }

    /** Compute the magnitude of a complex number
     * @return the magnitude
     */
    public double magnitude() {
        return Math.sqrt( Math.pow(R,2)* Math.pow(I,2));
    }


    /**
     *  @return the result of addition between two complex number
     */
    public static ComplexNumber add(ComplexNumber C1, ComplexNumber C2) {
        return new ComplexNumber(C1.R+ C2.R, C1.I + C2.I);
    }

    /**
     * @return the result of addition
     */
    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.R + c.R, this.I + c.I);
    }

    /** @return the result of multiply */
    public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2) {
        return new ComplexNumber(c1.R * c2.R - c1.I * c2.I, c1.R * c2.I + c1.I * c2.R);
    }

    /**
     * @return the result of multiply
     */
    public ComplexNumber multiply(ComplexNumber c) {
        return new ComplexNumber(R * c.R - I* c.I, R * c.I + I * c.R);
    }
}
