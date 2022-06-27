package coffeemachine.exception;

public class NotEnoughMaterialException extends Exception{
    public NotEnoughMaterialException(String message) {
        super(message);
    }
}
