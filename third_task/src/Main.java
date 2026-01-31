public class Main {
    public static void main(String[] args) {
        for (int a = 1; a <= 1000; a++) {
            for (int b = 1; b <= 1000; b++) {
                for (int c = 1; c <= 1000; c++) {
                    if (a < b && b < c) {
                        if (a * a + b * b == c * c && a + b + c == 1000) {
                            int result = a * b * c;
                            System.out.println(result);
                        }
                    }
                }
            }
        }
    }
}