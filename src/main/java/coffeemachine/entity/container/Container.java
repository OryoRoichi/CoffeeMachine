package coffeemachine.entity.container;

import coffeemachine.entity.drink.Drink;

public abstract class Container {
    int capacity;



    String name;

    public Container(int capacity) {
        this.capacity = capacity;
    }

    public boolean isEmpty(){
        return capacity==0;
    }

    public abstract void changeCapacity(Drink drink);
    public void charge(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getName() {
        return name;
    }
}
