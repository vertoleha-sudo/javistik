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
    }
    public Monkey(int age) {
        this.age = age;
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
        return name + ", " + age + ", " + dateOfArrival;
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

    public int findFreeIndex(Monkey[] monkeys) {
        for (int i = 0; i < monkeys.length; i++) {
            if (monkeys[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void Create(String name, int age) {
        Monkey monkey = new Monkey(name, age);

        int result = findFreeIndex(monkeys);
        if (result == -1) {
            Monkey[] copy = new Monkey[monkeys.length + 1];
            System.arraycopy(monkeys, 0, copy, 0, monkeys.length);
            copy[copy.length - 1] = monkey;
            monkeys = copy;
        } else {
            monkeys[result] = monkey;
        }
        monkey.voice();
    }

    public void Read(Monkey[] monkeys) {
        StringBuilder monkey = new StringBuilder();
        for (int i = 0; i < monkeys.length; i++) {
            if (monkeys[i] != null) {
                monkey.append(monkeys[i]);
            }
        }
        System.out.println(monkey.toString());
    }

    public void Update(int id, Monkey[] monkeys, String newName, int newAge) {
        if (monkeys[id] != null && id >= 0 && id < monkeys.length) {
            if (newName != null && !newName.isEmpty()) {
                monkeys[id].setName(newName);
            }
            if (newAge >= 0) {
                monkeys[id].setAge(newAge);
            }

        }
    }

    public void Delete(int id, Monkey[] monkeys) {
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
                case 1:
                    System.out.println("Введите имя и возраст обезьянки");
                    sc.nextLine();
                    String name = sc.nextLine();
                    int age = sc.nextInt();
                    object.Create(name, age);
                    break;
                case 2:
                    object.Read(Main.monkeys);
                    break;
                case 3:
                    System.out.println("Введите айди обезьянки и новое имя с новым возрастом");
                    sc.nextLine();
                    int id = sc.nextInt();
                    sc.nextLine();
                    String newName = sc.nextLine();
                    int newAge = sc.nextInt();
                    object.Update(id, Main.monkeys, newName, newAge);
                    break;
                case 4:
                    System.out.println("Введите айди обезьянки, которую хотите удалить");
                    sc.nextLine();
                    id = sc.nextInt();
                    object.Delete(id, Main.monkeys);
                    break;
                case 5:
                    return;
            }
        }
    }
}