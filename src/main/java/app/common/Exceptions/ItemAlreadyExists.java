package app.common.Exceptions;

public class ItemAlreadyExists extends Exception {

    public ItemAlreadyExists() {
        super("Item already exists");
    }

    public ItemAlreadyExists(String message) {
        super(message);
    }
}
