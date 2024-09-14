public abstract class Menu {
    protected String message = "";

    abstract public void displayMenu();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
