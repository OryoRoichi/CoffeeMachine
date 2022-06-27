package coffeemachine.entity.container;

import coffeemachine.entity.drink.Drink;

public class MilkContainer extends Container {
    public MilkContainer(int capacity) {
        super(capacity);
        this.name="контейнер для молока";
    }

    public void changeCapacity(Drink drink) {
        this.capacity= this.capacity - drink.getMilk();
    }
}
