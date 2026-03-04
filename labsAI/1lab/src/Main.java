import java.util.Scanner;

public class Main {
    public static final char COMPUTER = 'X';
    public static final char PERSON = 'O';
    public static int size; // размер доски

    // Счетчики для статистики
    public static int totalMiniMaxCalls = 0;
    public static int currentMoveCalls = 0;

    public static void printBoard(char[][] board) {
        System.out.println("-".repeat(size * 4 + 1));
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-".repeat(size * 4 + 1));
        }
    }

    public static int checkStats(char[][] board) {
        int n = board.length;

        // проверка строк
        for (int i = 0; i < n; i++) {
            boolean win = true;
            for (int j = 1; j < n; j++) {
                if (board[i][j] != board[i][0] || board[i][0] == ' ') {
                    win = false;
                    break;
                }
            }
            if (win) {
                if (board[i][0] == COMPUTER) return 10;
                if (board[i][0] == PERSON) return -10; // проверяем строки
            }
        }

        // проверка столбцов
        for (int j = 0; j < n; j++) {
            boolean win = true;
            for (int i = 1; i < n; i++) {
                if (board[i][j] != board[0][j] || board[0][j] == ' ') {
                    win = false;
                    break;
                }
            }
            if (win) {
                if (board[0][j] == COMPUTER) return 10;
                if (board[0][j] == PERSON) return -10; // проверяем столбцы
            }
        }

        // проверка диагонали слева направо
        boolean win = true;
        for (int i = 1; i < n; i++) {
            if (board[i][i] != board[0][0] || board[0][0] == ' ') {
                win = false;
                break;
            }
        }
        if (win) {
            if (board[0][0] == COMPUTER) return 10;
            if (board[0][0] == PERSON) return -10; // проверяем диагональ слева направо
        }

        // проверка диагонали справа налево
        win = true;
        for (int i = 1; i < n; i++) {
            if (board[i][n - 1 - i] != board[0][n - 1] || board[0][n - 1] == ' ') {
                win = false;
                break;
            }
        }
        if (win) {
            if (board[0][n - 1] == COMPUTER) return 10;
            if (board[0][n - 1] == PERSON) return -10; // проверяем диагональ справа налево
        }

        return 0; // если выигрыша нет возвращаем 0
    }

    public static boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return true; // если есть хоть одна пустая клетка на поле, возвращаем true
                }
            }
        }
        return false;
    }

    public static int miniMax(char[][] board, boolean isComputerTurn, int alpha, int beta) {
        // Увеличиваем счетчики вызовов
        totalMiniMaxCalls++;
        currentMoveCalls++;

        int score = checkStats(board);
        if (score != 0) return score; // если не ничья, возвращаем счет
        if (!isMovesLeft(board)) return 0; // проверка на ничью (при отсутствии пустых клеток)

        /* компьютер думает что человек идеален, и каждый его ход безошибочен.
        он хочет найти лучший для себя ход, поэтому начинает с минимума(Integer.MIN_VALUE)
        (так как +10 победа компьютера)
        и компьютер также предполагает, что ход человека - самый лучший для человека и самый
        худший для компьютера, поэтому начинаем с максимума(Integer.MAX_VALUE)
        (так как -10 победа человека)
         */

        if (isComputerTurn) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == ' ') { // если клетка пуста
                        board[i][j] = COMPUTER; // ход пк
                        int currentScore = miniMax(board, false, alpha, beta);
                        board[i][j] = ' '; // очищаем для дальнейших проверок
                        bestScore = Math.max(bestScore, currentScore);
                        alpha = Math.max(alpha, currentScore);
                        if (beta <= alpha) {
                            return bestScore; // отсечение
                        }
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == ' ') { // если клетка пуста
                        board[i][j] = PERSON; // ход человека
                        int currentScore = miniMax(board, true, alpha, beta);
                        board[i][j] = ' '; // очищаем для дальнейших проверок
                        bestScore = Math.min(bestScore, currentScore);
                        beta = Math.min(beta, currentScore);
                        if (beta <= alpha) {
                            return bestScore; // отсечение
                        }
                    }
                }
            }
            return bestScore;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер доски (например, 3 или 4): ");
        size = scanner.nextInt();
        char[][] board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }

        while (true) {
            // Сбрасываем счетчик для текущего хода
            currentMoveCalls = 0;

            printBoard(board); // выводим доску

            System.out.print("Введите номер клетки от 0 до " + (size * size - 1) + ": "); // начинает человек
            int kletka = scanner.nextInt();
            if (kletka < 0 || kletka >= size * size) {
                System.out.println("Вы ввели неправильную цифру клетки.");
                continue;
            }
            int str = kletka / size;
            int stolb = kletka % size; // вычисление координат

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

            if (!isMovesLeft(board)) {
                printBoard(board);
                System.out.println("Ничья.");
                break;
            }

            int bestScore = Integer.MIN_VALUE; // ход пк
            int bestI = -1, bestJ = -1;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (board[i][j] == ' ') { // если клетка пуста
                        board[i][j] = COMPUTER;
                        int score = miniMax(board, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
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
            System.out.println("Компьютер сходил в клетку " + (bestI * size + bestJ));
            System.out.println("Вызовов минимакс на этом ходу: " + currentMoveCalls);

            if (checkStats(board) == 10) {
                printBoard(board);
                System.out.println("Вы проиграли.");
                break;
            }
            if (!isMovesLeft(board)) {
                printBoard(board);
                System.out.println("Ничья.");
                break;
            }
        }

        System.out.println("Всего вызовов минимакс за игру: " + totalMiniMaxCalls);

        scanner.close();
    }
}