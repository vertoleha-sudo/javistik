import static java.lang.Math.sqrt;
public class Main {
    public static void main(String[] args) {
        int total = 0;
        int allNum = 2;
        while (total < 10001) {
            boolean flag = true;
            int koren = (int)Math.sqrt(allNum);
            for (int j = 2; j <= koren; j++) {
                if (allNum % j == 0) {
                    flag = false;
                }
            }
            if (flag) {
                total += 1;
            }
            if (total == 10001) {
                System.out.println(allNum);
            }
            allNum++;
        }
    }
}