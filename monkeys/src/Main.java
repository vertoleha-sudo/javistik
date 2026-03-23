import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

interface Feedable {
    void receiveFeed();
}
interface Accessable {
    void openAccess();
    void closeAccess();
}
interface Castratable {
    void acceptCastration();
}

abstract class Animal {
    protected long id;
    protected String name;
    protected int age;
    protected LocalDate dateOfArrival;
    protected static long nextId = 1;

    public Animal(String name, int age) {
        this.id = nextId++;
        this.name = name;
        this.age = age;
        this.dateOfArrival = LocalDate.now();
    }
    public Animal(String name) {
        this(name, 0);
    }
    public Animal(int age) {
        this("", age);
    }
    public Animal() {
        this("хз", 0);
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public long getId() {
        return id;
    }

    public String toString() {
        return String.format("%s, %s, %d, %s", id, name, age, dateOfArrival);
    }

    public abstract void voice();
}

class Monkey extends Animal implements Castratable, Feedable {

    public Monkey(String name, int age) {
        super(name, age);
    }

    @Override
    public void voice() {
        System.out.println("У-у А-А-А");
    }

    @Override
    public void acceptCastration() {  //TODO: реализовать принятие кастрации :8

    }

    @Override
    public void receiveFeed() {  //TODO: реализовать кормление через приложение

    }
}

class Lion extends Animal implements Accessable {
    private boolean isSick;

    public Lion(String name, int age, boolean isSick) {
        super(name, age);
        this.isSick = isSick;
    }

    @Override
    public String toString() {
        return super.toString() + ", болен: " + (isSick ? "да" : "нет"); //сначала через if сделал, потом решил тернарным
    }

    @Override
    public void voice() {
        System.out.println("РРррРрр");
    }

    @Override
    public void openAccess() {  //TODO: реализовать выдачу разрешения

    }

    @Override
    public void closeAccess() {  //TODO: реализовать забирание разрешения

    }
}

class Penguin extends Animal implements Feedable, Castratable {
    private LocalDate dateOfBirth;

    public Penguin(String name, int age, LocalDate dateOfBirth) {
        super(name, age);
        this.dateOfBirth = dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return super.toString() + ", день рождения: " + dateOfBirth;
    }

    @Override
    public void voice() {
        System.out.println("ГАГгаГАГгагГ");
    }

    @Override
    public void acceptCastration() {  //TODO: реализовать принятие кастрации :8

    }

    @Override
    public void receiveFeed() {  //TODO: реализовать кормление через приложение

    }
}

class Koshak extends Animal {
    public Koshak(String name, int age) {
        super(name, age);
    }

    @Override
    public void voice() {
        System.out.println("Мяу, дай пожрать мне");
    }
}

public class Main {
    ArrayList<Animal>  animals = new ArrayList<>();
    final static int CREATE = 1;
    final static int READ = 2;
    final static int UPDATE = 3;
    final static int DELETE = 4;
    final static int AUTOCARE = 5;
    final static int EXIT = 6;

    public void callAutocare() {
        for (Animal animal : animals) {
            if (animal instanceof Feedable) {
                ((Feedable) animal).receiveFeed();    //TODO: реализовать кормление через приложение
            }
            if (animal instanceof Accessable) {
                ((Accessable) animal).openAccess();   //TODO: реализовать выдачу разрешения
                ((Accessable) animal).closeAccess();  //TODO: реализовать забирание разрешения
            }
            if (animal instanceof Castratable) {
                ((Castratable) animal).acceptCastration();  //TODO: реализовать принятие кастрации :8
            }
        }
        System.out.println("Автоуход за всеми животными был вызван");
    }

    public void create(int animalType) {
        Scanner sc = new Scanner(System.in);
        Animal animal = null;
        String name;
        int age;

        System.out.println("Как зовут ваше животное?");
        name = sc.nextLine();
        System.out.println("Сколько лет вашему животному?");
        age = sc.nextInt();

        switch (animalType) {
            case 1:
                animal = new Monkey(name, age);
                break;
            case 2:
                boolean isSick;
                sc.nextLine();
                System.out.println("Болеет ли лев? (да или нет)");
                String answer = sc.nextLine();
                isSick = answer.equals("да");
                animal = new Lion(name, age, isSick);
                break;
            case 3:
                int year;
                int month;
                int day;
                LocalDate dateOfBirth;
                System.out.println("Введите год рождения");
                year = sc.nextInt();
                System.out.println("Введите месяц рождения");
                month = sc.nextInt();
                System.out.println("Введите день рождения");
                day = sc.nextInt();
                dateOfBirth = LocalDate.of(year, month, day);
                animal = new Penguin(name, age, dateOfBirth);
                sc.nextLine();
                break;
            case 4:
                animal = new Koshak(name, age);
                break;
        }
        animals.add(animal);
        animal.voice();
    }

    public void read(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void update(long id, ArrayList<Animal> animals, String newName, int newAge) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == id) {
                animals.get(i).setName(newName);
                animals.get(i).setAge(newAge);
                break;
            }
        }
    }

    public void delete(long id, ArrayList<Animal> animals) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getId() == id) {
                animals.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        int animalType;
        Scanner sc = new Scanner(System.in);
        Main object = new Main();
        while (true) {
            System.out.println("Введите цифру операции: \n1.Создать животное\n" +
                    "2.Прочитать всех животных\n" +
                    "3.Обновить животное по айди\n" +
                    "4.Удалить животное" +
                    "\n5.Вызвать авто-уход для всех животных" +
                    "\n6.Выйти");
            int choice = sc.nextInt();
            if (choice < 1 || choice > 6) {
                System.out.println("Введите корректный выбор от 1 до 6");
            }
            switch (choice) {
                case CREATE:
                    System.out.println("Введите животное которое вы хотите создать:" +
                            "\n1. Обезьянка" +
                            "\n2. Лэфф" +
                            "\n3. Пингвин" +
                            "\n4.Кошак");
                    animalType = sc.nextInt();
                    sc.nextLine();
                    object.create(animalType);
                    break;
                case READ:
                    object.read(object.animals);
                    break;
                case UPDATE:
                    System.out.println("Введите айди животного и новое имя с новым возрастом");
                    sc.nextLine();
                    long id = sc.nextLong();
                    sc.nextLine();
                    String newName = sc.nextLine();
                    int newAge = sc.nextInt();
                    object.update(id, object.animals, newName, newAge);
                    break;
                case DELETE:
                    System.out.println("Введите айди животного, которого хотите удалить");
                    sc.nextLine();
                    id = sc.nextLong();
                    object.delete(id, object.animals);
                    break;
                case AUTOCARE:
                    object.callAutocare();
                    break;
                case EXIT:
                    return;
            }
        }
    }
}