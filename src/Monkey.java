class Monkey extends Animal implements Castratable, Feedable {
    public Monkey(String name, int age) {
        super(name, age, FoodType.OMNIVORE);
    }

    public void voice() {
        System.out.println("У-у А-А-А");
    }

    public void acceptCastration() {
    }

    public void receiveFeed() {
    }

    public String toString() {
        return "Обезьянка{имя = " + this.name + ", возраст = " + this.age + "}";
    }
}
