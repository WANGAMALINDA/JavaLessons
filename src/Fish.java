public class Fish implements Prey, Preditor {

    @Override
    public void flee() {
        System.out.println("The Fish is swimming away!");
    }

    @Override
    public void hunt() {
        System.out.println("The fish is hunting other smaller fish!");
    }
}
