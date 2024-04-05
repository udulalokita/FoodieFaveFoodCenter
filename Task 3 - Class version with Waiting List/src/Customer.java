public class Customer {
    private String firstName;
    private String secondName;
    private int numberOfBurgersRequired;

    public Customer(String firstName, String secondName, int numberOfBurgersRequired) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.numberOfBurgersRequired = numberOfBurgersRequired;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getNumberOfBurgersRequired() {
        return numberOfBurgersRequired;
    }

    public void setNumberOfBurgersRequired(int numberOfBurgersRequired) {
        this.numberOfBurgersRequired = numberOfBurgersRequired;
    }

}
