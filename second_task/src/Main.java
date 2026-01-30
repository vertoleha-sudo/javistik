public class Main {
    public static void main(String[] args) {
        int total = 0;
        int flag = 1;
        int secFlag = 2;
        while (secFlag <= 4000000) {
            if (secFlag % 2 == 0) {
                total += secFlag;
            }
            int next = flag + secFlag;
            flag = secFlag;
            secFlag = next;
        }
        System.out.println(total);
    }
} //.