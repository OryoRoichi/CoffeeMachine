package coffeemachine.entity.container;

import coffeemachine.entity.drink.Drink;

public class WaterContainer extends Container {
    public WaterContainer(int capacity) {
        super(capacity);
        this.name="контейнер для воды";
    }

    public void changeCapacity(Drink drink) {
        this.capacity= this.capacity - drink.getWater();
    }
}
