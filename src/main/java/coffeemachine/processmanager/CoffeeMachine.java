package coffeemachine.processmanager;

import coffeemachine.entity.container.*;
import coffeemachine.entity.drink.BlackCoffee;
import coffeemachine.entity.drink.Capuchino;
import coffeemachine.entity.drink.Drink;
import coffeemachine.entity.drink.HotMilk;
import coffeemachine.exception.ContainerIsEmptyException;
import coffeemachine.exception.ContainerIsFullException;
import coffeemachine.exception.NotEnoughMaterialException;
import coffeemachine.service.IOService;
import coffeemachine.service.IOServiceImpl;

public class CoffeeMachine {
    private CoffeContainer coffeContainer;
    private GarbageContainer garbageContainer;
    private MilkContainer milkContainer;
    private WaterContainer waterContainer;
    private IOService ioService;

    private int operation;

    public CoffeeMachine() {
        this.coffeContainer = new CoffeContainer(10);
        this.garbageContainer = new GarbageContainer(5);
        this.milkContainer = new MilkContainer(3);
        this.waterContainer = new WaterContainer(6);
        this.ioService = new IOServiceImpl();
    }

    public void run() {
        ioService.write("Hello! Its me , your coffee machine");
        chooseOpertion();
    }

    private void chooseOpertion() {
        ioService.write("Выберите Напиток");
        ioService.write("Нажмите 1 если хотите черный кофе");
        ioService.write("Нажмите 2 если хотите капучино");
        ioService.write("Нажмите 3 если хотите горячее молоко");
        ioService.write("для прекращения работынажмите 0");
        operation = readOperation();
        switch (operation) {
            case 0:
                break;
            case 1:
                createBlackCoffee();
                break;
            case 2:
                createCapuchino();
                break;
            case 3:
                createHotMilk();
                break;
        }
    }

    private void createBlackCoffee() {
        Drink drink = new BlackCoffee();
        processingBlackCoffeeException(drink);
        changeContainer(drink);
        doYouWantToExit();
    }

    private void createCapuchino() {
        Drink drink = new Capuchino();
        processingCapuchinoException(drink);
        changeContainer(drink);
        doYouWantToExit();
    }

    private void createHotMilk() {
        Drink drink = new HotMilk();
        processingHotMilkException(drink);
        changeContainer(drink);
        doYouWantToExit();
    }

    private void doYouWantToExit() {
        ioService.write("Хотите продолжить работу ?");
        ioService.write("Нажмите 1 если хотите продолжить");
        operation = readOperation();
        if (operation == 1) {
            chooseOpertion();
        }
    }

    private void changeContainer(Drink drink) {
        coffeContainer.changeCapacity(drink);
        waterContainer.changeCapacity(drink);
        milkContainer.changeCapacity(drink);
        garbageContainer.changeCapacity(drink);
    }

    private void processingBlackCoffeeException(Drink drink) {
        try {
            chekingCoffeeContainer(drink);
            chekingWaterContainer(drink);
            chekingGarbageContainer(drink);
        } catch (NotEnoughMaterialException e) {
            ioService.write(e.getMessage());
            ioService.write("1");
            chooseOpertion();
        } catch (ContainerIsFullException e) {
            ioService.write(e.getMessage());
            ioService.write("2");
            chooseOpertion();
        } catch (ContainerIsEmptyException e) {
            ioService.write(e.getMessage());
            ioService.write("3");
            chooseOpertion();
        }
    }

    private void processingCapuchinoException(Drink drink) {
        try {
            chekingCoffeeContainer(drink);
            chekingWaterContainer(drink);
            chekingMilkContainer(drink);
            chekingGarbageContainer(drink);
        } catch (NotEnoughMaterialException e) {
            ioService.write(e.getMessage());
            chooseOpertion();
        } catch (ContainerIsFullException e) {
            ioService.write(e.getMessage());
            chooseOpertion();
        } catch (ContainerIsEmptyException e) {
            ioService.write(e.getMessage());
            chooseOpertion();
        }
    }

    private void processingHotMilkException(Drink drink) {
        try {
            chekingMilkContainer(drink);
        } catch (NotEnoughMaterialException e) {
            ioService.write(e.getMessage());
            chooseOpertion();
        } catch (ContainerIsEmptyException e) {
            ioService.write(e.getMessage());
            chooseOpertion();
        }
    }

    private void chekingWaterContainer(Drink drink) throws NotEnoughMaterialException, ContainerIsEmptyException {
        if (waterContainer.getCapacity() - drink.getWater() < 0) {
            throw new NotEnoughMaterialException("НЕдостаточно воды");
        }
        if (waterContainer.isEmpty()) {
            throw new ContainerIsEmptyException(waterContainer.getName());
        }
    }

    private void chekingCoffeeContainer(Drink drink) throws NotEnoughMaterialException, ContainerIsEmptyException {
        if (coffeContainer.getCapacity() - drink.getCoffee() < 0) {
            throw new NotEnoughMaterialException("НЕдостаточно кофе");
        }
        if (coffeContainer.isEmpty()) {
            throw new ContainerIsEmptyException(coffeContainer.getName());
        }
    }

    private void chekingMilkContainer(Drink drink) throws NotEnoughMaterialException, ContainerIsEmptyException {
        if (milkContainer.getCapacity() - drink.getMilk() < 0) {
            throw new NotEnoughMaterialException("НЕдостаточно молока");
        }
        if (milkContainer.isEmpty()) {
            throw new ContainerIsEmptyException(milkContainer.getName());
        }
    }

    private void chekingGarbageContainer(Drink drink) throws ContainerIsFullException {
        if (garbageContainer.getCurrentState() + drink.getCoffee() > garbageContainer.getCapacity()) {
            throw new ContainerIsFullException();
        }
    }

    private int readOperation() {
        operation = ioService.read();
        if (operation != 0) {
            return operation;
        }
        return 0;
    }
}
