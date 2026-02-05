import static java.lang.Math.sqrt;
class Main {
    public static void main (String[] args) {
        long num = 600851475143L;
        long longest = 0;
        boolean flag = true;
        int koren = (int)Math.sqrt(num);
        for (int i = 2; i<=koren; i++) {
            while (num % i == 0) {
                longest = i;
                num /= i;
            }
        }
        if (num > 1) {
            longest = num;
        }
        System.out.print(longest);
    }
}