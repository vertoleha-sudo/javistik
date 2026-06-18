import java.time.LocalDate;

class Penguin extends Animal implements Feedable, Castratable {
    private LocalDate dateOfBirth;

    public Penguin(String name, int age, LocalDate dateOfBirth) {
        super(name, age, FoodType.PREDATOR);
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + ", день рождения: " + String.valueOf(this.dateOfBirth);
    }

    public void voice() {
        System.out.println("ГАГгаГАГгагГ");
    }

    public void acceptCastration() {
    }

    public void receiveFeed() {
    }
}
