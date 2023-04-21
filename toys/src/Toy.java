public class Toy {
    private int id; // Идентификатор игрушки
    private String name; // Название игрушки
    private int quantity; // Количество игрушек в магазине
    private int weight; // Вес игрушки (частота выпадения)

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() { // Геттер для идентификатора
        return id;
    }

    public String getName() { // Геттер для названия
        return name;
    }

    public int getQuantity() { // Геттер для количества
        return quantity;
    }

    public void decrementQuantity() { // Метод для уменьшения количества на 1
        quantity--;
    }

    public int getWeight() { // Геттер для веса
        return weight;
    }

    public void setWeight(int weight) { // Сеттер для веса
        this.weight = weight;
    }
}
