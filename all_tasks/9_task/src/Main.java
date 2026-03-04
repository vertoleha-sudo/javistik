import static java.lang.Math.sqrt;
public class Main {
    public static void main(String[] args) {
        int allNum = 2;
        int summa = 0;
        while (allNum < 2000000) {
            boolean flag = true;
            int koren = (int)Math.sqrt(allNum);
            for (int j = 2; j <= koren; j++) {
                if (allNum % j == 0) {
                    flag = false;
                }
            }
            if (flag) {
                summa += allNum;
            }

            allNum++;
        }
        if (allNum >= 2000000) {
            System.out.println(summa);
        }
    }
}