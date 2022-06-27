package coffeemachine.entity.container;

import coffeemachine.entity.drink.Drink;

public class GarbageContainer extends Container {
    private int currentState;

    public GarbageContainer(int capacity) {
        super(capacity);
        this.name = "контейнер для мусора";
        this.currentState = 0;
    }

    @Override
    public boolean isEmpty() {
        return currentState < capacity;
    }

    @Override
    public void changeCapacity(Drink drink) {
        changeCurrentState(drink);
    }

    @Override
    public int getCapacity() {
        return super.getCapacity();
    }

    private void changeCurrentState(Drink drink) {
        this.currentState = this.currentState + drink.getCoffee();
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }
}
