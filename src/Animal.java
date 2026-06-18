import java.io.Serializable;
import java.time.LocalDate;

abstract class Animal implements Serializable {
    protected long id;
    protected String name;
    protected int age;
    protected LocalDate dateOfArrival;
    protected static long nextId = 1L;
    protected FoodType foodType;

    public Animal(String name, int age, FoodType foodType) {
        this.id = (long)(nextId++);
        this.name = name;
        this.age = age;
        this.dateOfArrival = LocalDate.now();
        this.foodType = foodType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public FoodType getFoodType() {
        return this.foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public long getId() {
        return this.id;
    }

    public String toString() {
        return String.format("Айди: %d, Имя: %s, Возраст: %d, Дата приезда: %s, Тип: %s", this.id, this.name, this.age, this.dateOfArrival, this.foodType);
    }

    public abstract void voice();
}
