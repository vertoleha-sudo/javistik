public class Main {
    public static void main(String[] args) {
        int totalKvadr = 0;
        int summKvadr = 0;
        for (int i = 1; i <= 100; i++) {
            int kvadr = i * i;
            totalKvadr += kvadr;
        }
        for (int j = 1; j <= 100; j++) {
            summKvadr += j;
        }
        int totalSummKvadr = summKvadr * summKvadr;
        int conclusion = totalSummKvadr - totalKvadr;
        System.out.println(conclusion);
    }
}