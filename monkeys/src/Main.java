import java.time.LocalDate;
import java.util.Scanner;

class Monkey {
    private String name;
    private int age;
    private LocalDate dateOfArrival;

    public Monkey(String name, int age) {
        this.name = name;
        this.age = age;
        this.dateOfArrival = LocalDate.now();
    }
    public Monkey(String name) {
        this.name = name;
        this.dateOfArrival = LocalDate.now();
    }
    public Monkey(int age) {
        this.age = age;
        this.dateOfArrival = LocalDate.now();
    }
    public Monkey(LocalDate dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }
    public Monkey() {
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return String.format("%s, %d, %s", name, age, dateOfArrival);
    }

    public void voice() {
        System.out.println("У-у А-А-А");
    }
}

public class Main {
    static Monkey[] monkeys = new Monkey[10];
    final static int CREATE = 1;
    final static int READ = 2;
    final static int UPDATE = 3;
    final static int DELETE = 4;
    final static int EXIT = 5;

    public int findFreeIndex(Monkey[] monkeys) {
        for (int i = 0; i < monkeys.length; i++) {
            if (monkeys[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void create(String name, int age) {
        Monkey monkey = new Monkey(name, age);

        int result = findFreeIndex(monkeys);
        if (result == -1) {
            Monkey[] copy = new Monkey[monkeys.length * 2];
            System.arraycopy(monkeys, 0, copy, 0, monkeys.length);
            copy[copy.length - 1] = monkey;
            monkeys = copy;
        } else {
            monkeys[result] = monkey;
        }
        monkey.voice();
    }

    public void read(Monkey[] monkeys) {
        StringBuilder monkey = new StringBuilder();
        for (int i = 0; i < monkeys.length; i++) {
            if (monkeys[i] != null) {
                monkey.append(monkeys[i]);
            }
        }
        System.out.println(monkey.toString());
    }

    public void update(int id, Monkey[] monkeys, String newName, int newAge) {
        if (monkeys[id] != null && id >= 0 && id < monkeys.length) {
            if (newName != null && !newName.isEmpty()) {
                monkeys[id].setName(newName);
            }
            if (newAge >= 0) {
                monkeys[id].setAge(newAge);
            }

        }
    }

    public void delete(int id, Monkey[] monkeys) {
        Monkey monkey = monkeys[id];
        monkeys[id] = null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main object = new Main();
        while (true) {
            System.out.println("Введите цифру операции: \n1.Создать обезьянку\n" +
                    "2.Прочитать всех обезьянок\n" +
                    "3.Обновить обезьянку по айди\n" +
                    "4.Удалить обезьянку" +
                    "\n5.Выйти");
            int choice = sc.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Введите корректный выбор от 1 до 5");
            }
            switch (choice) {
                case CREATE:
                    System.out.println("Введите имя и возраст обезьянки");
                    sc.nextLine();
                    String name = sc.nextLine();
                    int age = sc.nextInt();
                    object.create(name, age);
                    break;
                case READ:
                    object.read(Main.monkeys);
                    break;
                case UPDATE:
                    System.out.println("Введите айди обезьянки и новое имя с новым возрастом");
                    sc.nextLine();
                    int id = sc.nextInt();
                    sc.nextLine();
                    String newName = sc.nextLine();
                    int newAge = sc.nextInt();
                    object.update(id, Main.monkeys, newName, newAge);
                    break;
                case DELETE:
                    System.out.println("Введите айди обезьянки, которую хотите удалить");
                    sc.nextLine();
                    id = sc.nextInt();
                    object.delete(id, Main.monkeys);
                    break;
                case EXIT:
                    return;
            }
        }
    }
}