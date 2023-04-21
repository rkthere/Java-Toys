import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ToyStore {
    private List<Toy> toys; // Список игрушек
    private Random random; // Генератор случайных чисел

    public ToyStore() { // Конструктор класса
        toys = new ArrayList<>();
        random = new Random();
    }

    public void addToy(Toy toy) { // Метод добавления новой игрушки в список игрушек
        toys.add(toy);
    }

    public void updateToyWeight(int id, int weight) { // Метод изменения веса (частоты выпадения) игрушки с заданным id
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                break;
            }
        }
    }

    public Toy selectPrizeToy() { // Метод выбора призовой игрушки
        int totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight();
        }
        int randomNumber = random.nextInt(totalWeight);
        int currentWeight = 0;
        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (currentWeight > randomNumber) {
                toy.decrementQuantity();
                return toy;
            }
        }
        return null;
    }

    public void savePrizeToyToFile(Toy toy, String filename) { // Метод сохранения призовой игрушки в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(toy.getName() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { // Метод для демонстрации работы класса ToyStore
        ToyStore toyStore = new ToyStore(); // Создание экземпляра класса ToyStore
        toyStore.addToy(new Toy(1, "Car", 10, 50)); // Добавление игрушки в магазин
        toyStore.addToy(new Toy(2, "Doll", 5, 30));
        toyStore.addToy(new Toy(3, "Teddy bear", 7, 20));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of prize toys to be selected:");
        int numberOfPrizeToys = scanner.nextInt();

        List<Toy> prizeToys = new ArrayList<>();
        for (int i = 0; i < numberOfPrizeToys; i++) {
            Toy prizeToy = toyStore.selectPrizeToy(); // Выбор призовой игрушки
            if (prizeToy != null) {
                prizeToys.add(prizeToy);
            }
        }

        System.out.println("The following toys were selected as prizes:");
        for (Toy prizeToy : prizeToys) { // Вывод призовых игрушек на экран и сохранение их в файл
            System.out.println(prizeToy.getName());
            toyStore.savePrizeToyToFile(prizeToy, "prize_toys.txt");
        }
    }
}
