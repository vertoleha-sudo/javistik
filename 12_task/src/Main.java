public class Main {
    public static void main(String[] args) {
        int maxPalindrom = 0;
        for (int i = 999; i >= 100; i--) {
            for (int j = 999; j >= 100; j--) {
                int umnozh = i * j;
                String str = Integer.toString(umnozh);
                String reversed = new StringBuilder(str).reverse().toString();
                if (str.equals(reversed)) {
                    if (maxPalindrom < umnozh) {
                        maxPalindrom = umnozh;
                    }
                }
            }
        }
        System.out.println(maxPalindrom);
    }
}