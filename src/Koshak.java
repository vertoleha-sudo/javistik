class Koshak extends Animal {
    public Koshak(String name, int age) {
        super(name, age, FoodType.PREDATOR);
    }

    public void voice() {
        System.out.println("Мяу, дай пожрать мне");
    }

    public String toString() {
        return "Кошак{имя = " + this.name + ", возраст = " + this.age + "}";
    }
}
