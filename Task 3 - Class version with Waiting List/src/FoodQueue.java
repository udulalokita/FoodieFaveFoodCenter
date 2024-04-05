import java.util.ArrayList;

public class FoodQueue {
    private int maxQueueCapacity;
    private ArrayList<Customer> customers;
    public FoodQueue(int maxQueueCapacity) {
        this.maxQueueCapacity = maxQueueCapacity;
        customers = new ArrayList<>();
    }

    public int getMaxQueueCapacity() {
        return maxQueueCapacity;
    }

    public void setMaxQueueCapacity(int maxQueueCapacity) {
        this.maxQueueCapacity = maxQueueCapacity;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
}
