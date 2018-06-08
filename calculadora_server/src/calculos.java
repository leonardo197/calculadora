public class calculos {
    public float som(float num1, float num2) {
        return num1 + num2;
    }

    public float sub(float num1, float num2) {
        return num1 - num2;
    }

    public float div(float num1, float num2) {
        return num1 / num2;
    }

    public float mult(float num1, float num2) {
        return num1 * num2;
    }

    public double pow(float num1, float num2) {
        return Math.pow(Double.valueOf(num1), Double.valueOf(num2));
    }

    public float fatorial(float num1) {
        if (num1 <= 1) return 1;
        else return num1 * fatorial(num1 - 1);
    }

    public double sin(float num2) {
        return Math.sin(Math.toRadians(num2));
    }

    public double cos(float num2) {
        return Math.cos(Math.toRadians(num2));
    }

    public double tan(float num2) {
        return Math.tan(Math.toRadians(num2));
    }
}
