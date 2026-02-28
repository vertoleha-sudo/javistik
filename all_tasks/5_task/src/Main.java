public class Main {
    public static void main(String[] args) {
        int num = 20;
        boolean flag = true;
        while (true) {
            flag = true;
            for (int i = 11; i <= 20; i++) {
                if (num % i != 0) {
                    flag = false;
                    num += 20;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        if (flag) {
            System.out.println(num);
        }
    }
}