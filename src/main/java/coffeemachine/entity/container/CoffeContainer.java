package coffeemachine.entity.container;

import coffeemachine.entity.drink.Drink;

public class CoffeContainer extends Container {
    public CoffeContainer(int capacity) {
        super(capacity);
        this.name="контейнер для кофе";
    }

    public void changeCapacity(Drink drink) {
        this.capacity= this.capacity - drink.getCoffee();
    }


}
