import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {
    private List<Toy> toys;
    private List<Toy> prizeToys;

    public ToyStore() {
        toys = new ArrayList<>();
        prizeToys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void updateToyWeight(int toyId, double weight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public void startRaffle() {
        prizeToys.clear();
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        Random random = new Random();

        while (!prizeToys.containsAll(toys)) {
            double randomNumber = random.nextDouble() * totalWeight;
            double weightCount = 0;

            for (Toy toy : toys) {
                weightCount += toy.getWeight();
                if (weightCount >= randomNumber && !prizeToys.contains(toy)) {

                    prizeToys.add(toy);
                    break;
                }
            }
        }
    }

    public Toy getPrizeToy() {
        Toy prizeToy = prizeToys.get(0);
        prizeToy.decreaseQuantity();
        prizeToys.remove(0);
        saveToyToFile(prizeToy);
        return prizeToy;
    }

    // private void saveToyToFile(Toy toy) {
    // try (BufferedWriter writer = new BufferedWriter(new
    // FileWriter("prize_toys.txt", true))) {
    // writer.write(toy.getName());
    // writer.newLine();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    public void removeToy(int toyId) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toys.remove(toy);
                break;
            }
        }
    }

    public void updateToyQuantity(int toyId, int quantity) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.quantity = quantity;
                break;
            }
        }
    }

    public void readToysFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int quantity = Integer.parseInt(fields[2]);
                double weight = Double.parseDouble(fields[3]);
                Toy toy = new Toy(id, name, quantity, weight);
                toys.add(toy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToyToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Toy toy : toys) {
                writer.write(toy.getId() + "," + toy.getName() + "," + toy.getQuantity() + "," + toy.getWeight());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editToy(int toyId, String name, int quantity) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.name = name;
                toy.quantity = quantity;
                break;
            }
        }
    }

    public void startRaffle(int numPrizeToys) {
        prizeToys.clear();
        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }

        Random random = new Random();
        while (prizeToys.size() < numPrizeToys) {
            double randomNumber = random.nextDouble() * totalWeight;
            double weightCount = 0;
            for (Toy toy : toys) {
                weightCount += toy.getWeight();
                if (weightCount >= randomNumber && !prizeToys.contains(toy)) {
                    prizeToys.add(toy);
                    break;
                }
            }
        }
    }

    public List<Toy> getPrizeToys() {
        List<Toy> prizeToysCopy = new ArrayList<>(prizeToys);
        for (Toy prizeToy : prizeToysCopy) {
            prizeToy.decreaseQuantity();
            saveToyToFile(prizeToy);
        }
        prizeToys.clear();
        return prizeToysCopy;
    }

    private void saveToyToFile(Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("prize_toys.txt", true))) {
            writer.write(toy.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
