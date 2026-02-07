import java.util.Scanner;

public class Main {
    public static final char COMPUTER = 'X';
    public static final char PERSON = 'O';
    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    public static int checkStats(char[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == COMPUTER) return 10;
                if (board[i][0] == PERSON) return -10; //проверяем строки
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                if (board[0][i] == COMPUTER) return 10;
                if (board[0][i] == PERSON) return -10; //проверяем столбцы
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == COMPUTER) return 10;
            if (board[0][0] == PERSON) return -10; //проверяем диагональ слева направо
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == COMPUTER) return 10;
            if (board[0][2] == PERSON) return -10; // проверяем диагональ справа налево
        }
        return 0; //если выигрыша нет возвращаем 0
    }
    public static boolean movesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return true; //если есть хоть одна пустая клетка на поле, возвращаем true
                }
            }
        }
        return false;
    }
    public static int miniMax(char[][] board, boolean isComputerTurn) {
        int score = checkStats(board);
        if (score != 0) return score; //если не ничья, возвращаем счет
        if (!movesLeft(board)) return 0; //проверка на ничью (при отсутствии пустых клеток)

        /* компьютер думает что человек идеален, и каждый его ход безошибочен.
        он хочет найти лучший для себя ход, поэтому начинает с минимума(Integer.MIN_VALUE)
        (так как +10 победа компьютера)
        и компьютер также предполагает, что ход человека - самый лучший для человека и самый
        худший для компьютера, поэтому начинаем с максимума(Integer.MAX_VALUE)
        (так как -10 победа человека)
         */
        int bestScore;
        if (isComputerTurn) {
            bestScore = Integer.MIN_VALUE;
        } else {
            bestScore = Integer.MAX_VALUE;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') { // если клетка пуста
                    if (isComputerTurn) {
                        board[i][j] = COMPUTER; // ход пк
                    } else {
                        board[i][j] = PERSON; // ход человека
                    }

                    int currentScore = miniMax(board, !isComputerTurn); //сравнение с лучшим ходом

                    board[i][j] = ' '; //очищаем для дальнейших проверок

                    if (isComputerTurn) {
                        bestScore = Math.max(bestScore, currentScore);
                    } else {
                        bestScore = Math.min(bestScore, currentScore); //выбираем лучшие варианты
                    }

                }
            }
        }
        return bestScore;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        while (true) {
            printBoard(board); //выводим доску

            System.out.print("Введите номер клетки от 0 до 8: ");// начинает человек
            int kletka =  scanner.nextInt();
            if (kletka < 0 || kletka > 8) {
                System.out.println("Вы ввели неправильную цифру клетки.");
                continue;
            }
            int str = kletka / 3;
            int stolb =  kletka % 3; // вычисление координат

            if (board[str][stolb] != ' ') {
                System.out.println("Клетка занята.");
                continue;
            }

            board[str][stolb] = PERSON; // ход человека

            int result = checkStats(board); // проверка
            if (result == -10) {
                printBoard(board);
                System.out.println("Вы выиграли!");
                break;
            }

            if (!movesLeft(board)) {
                printBoard(board);
                System.out.println("Ничья.");
                break;
            }

            int bestScore = Integer.MIN_VALUE; // ход пк
            int bestI = -1, bestJ = -1;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = COMPUTER;
                        int score = miniMax(board, false);
                        board[i][j] = ' ';

                        if (score > bestScore) {
                            bestScore = score;
                            bestI = i;
                            bestJ = j;
                        }
                    }
                }
            }
            board[bestI][bestJ] = COMPUTER;
            System.out.println("Компьютер сходил в клетку " + (bestI * 3 + bestJ));

            if (checkStats(board) == 10) {
                printBoard(board);
                System.out.println("Вы проиграли.");
                break;
            }
            if (!movesLeft(board)) {
                printBoard(board);
                System.out.println("Ничья.");
                break;
            }
        }
    }
}