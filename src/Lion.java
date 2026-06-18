class Lion extends Animal implements Accessable {
    private boolean isSick;

    public Lion(String name, int age, boolean isSick) {
        super(name, age, FoodType.PREDATOR);
        this.isSick = isSick;
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + ", болен: " + (this.isSick ? "да" : "нет");
    }

    public void voice() {
        System.out.println("РРррРрр");
    }

    public void openAccess() {
    }

    public void closeAccess() {
    }
}
