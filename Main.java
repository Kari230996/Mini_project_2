import java.util.List;

public class Main {

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        Toy toy1 = new Toy(1, "Doll", 5, 25.0);
        Toy toy2 = new Toy(2, "Car", 10, 35.0);
        Toy toy3 = new Toy(3, "Teddy bear", 8, 40.0);
        Toy toy4 = new Toy(4, "Puzzle", 4, 12.0);
        Toy toy5 = new Toy(5, "Lego", 3, 40.0);

        toyStore.addToy(toy1);
        toyStore.addToy(toy2);
        toyStore.addToy(toy3);
        toyStore.addToy(toy4);
        toyStore.addToy(toy5);

        toyStore.readToysFromFile("toys.txt");
        toyStore.updateToyWeight(1, 30.0);

        toyStore.startRaffle(3);

        List<Toy> prizeToys = toyStore.getPrizeToys();

        System.out.println("Победители: ");
        for (Toy prizeToy : prizeToys) {
            System.out.println(prizeToy.getName());
        }

        toyStore.saveToyToFile("toys.txt");

    }
}