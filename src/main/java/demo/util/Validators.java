package demo.util;

public final class Validators {

    private Validators() {
    }

    public static boolean isLetters(String letters, int numbers) {
        return letters != null && letters.matches("\\w{" + numbers + "}");
    }

    public static boolean isDigits(String digits, int numbers) {
        return digits != null && digits.matches("\\d{" + numbers + "}");
    }

    public static boolean isDigits(String digits) {
        return digits != null && digits.matches("[0-9]+");
    }

}
