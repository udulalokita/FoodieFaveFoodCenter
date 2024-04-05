import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int burgersCount = 50;
    private static int burgersWarningCount = 10;
    private static FoodQueue queue1;
    private static FoodQueue queue2;
    private static FoodQueue queue3;
    private static ArrayList<Customer> waitingList;

    public static void main(String[] args) {

        queue1 = new FoodQueue(2);
        queue2 = new FoodQueue(3);
        queue3 = new FoodQueue(5);

        waitingList = new ArrayList<>();

        while (true){
            Scanner input = new Scanner(System.in);
            String option;

            System.out.println();
            System.out.println("Welcome to Foodies Fave Food center!");
            System.out.println();
            System.out.println("--------- Menu ---------");
            System.out.println();
            System.out.println("100 or VFQ: View all Queues.");
            System.out.println("101 or VEQ: View all Empty Queues.");
            System.out.println("102 or ACQ: Add customer to a Queue.");
            System.out.println("103 or RCQ: Remove a customer from a Queue.");
            System.out.println("104 or PCQ: Remove a served customer.");
            System.out.println("105 or VCS: View Customers Sorted in alphabetical order.");
            System.out.println("106 or SPD: Store Program Data into file.");
            System.out.println("107 or LPD: Load Program Data from file.");
            System.out.println("108 or STK: View Remaining burgers Stock.");
            System.out.println("109 or AFS: Add burgers to Stock");
            System.out.println("110 or IFQ: Print the Income of Each Queue");
            System.out.println("999 or EXT: Exit the Program.");
            System.out.println();
            System.out.print("Select an Option :");

            option = input.nextLine();
            String optionUpperCase = option.toUpperCase();


            switch (optionUpperCase) {
                case "100", "VFQ":

                    viewQueues();
                    break;
                case "101", "VEQ":

                    viewEmptyQueues();
                    break;
                case "102","ACQ":

                    addCustomer();
                    break;
                case "103","RCQ":

                    removeCustomer();
                    break;
                case "104","PCQ":

                    removeServedCustomer();
                    break;
                case "105","VCS":

                    viewCustomersAlphabetical();
                    break;
                case "106","SPD":

                    storeData();
                    break;
                case "107","LPD":

                    loadData();
                    break;
                case "108","STK":

                    viewRemainingBurgers();
                    break;
                case "109","AFS":

                    addStock();
                    break;
                case "110","IFQ":

                    getIncome();
                    break;
                case "999","EXT":

                    exit();
                    break;
                default:
                    System.out.println("Invalid Input, Try Again.");
                    break;

            }

            if(burgersCount<=burgersWarningCount){
                System.out.println("Warning!...only "+burgersCount+ " available in the stock. Please add burgers to the stock.");
            }
        }
    }


    /**
     Views all 3 cashier queues.
     * */
    private static void viewQueues() {
            System.out.println("*****************");
            System.out.println("*   Cashiers    *");
            System.out.println("*****************");

            int maxQueueCapacity = Math.max(queue1.getMaxQueueCapacity(), Math.max(queue2.getMaxQueueCapacity(), queue3.getMaxQueueCapacity()));

            for (int i = 0; i< maxQueueCapacity; i++){
                ArrayList<Customer> customersQueue1 = queue1.getCustomers();
                int occupied1 = customersQueue1.size();
                ArrayList<Customer> customersQueue2 = queue2.getCustomers();
                int occupied2 = customersQueue2.size();
                ArrayList<Customer> customersQueue3 = queue3.getCustomers();
                int occupied3 = customersQueue3.size();

                String line1;
                String line2;
                String line3;

                 if (i< queue1.getMaxQueueCapacity() && i< occupied1){
                        line1 = "o";
                    }else{
                        line1 = "x";
                    }

                if (i< queue2.getMaxQueueCapacity() && i< occupied2){
                        line2 = "o";
                    }else{
                        line2 = "x";
                    }

                    if (i< occupied3){
                        line3 = "o";
                    }else{
                        line3 = "x";
                    }

                    if (i< queue1.getMaxQueueCapacity()){
                        System.out.print(line1 +"     ");
                    }else{
                        System.out.print("      ");
                    }
                    if (i< queue2.getMaxQueueCapacity()){
                        System.out.print(line2 +"     ");
                    }else {
                        System.out.print("      ");
                    }
                    System.out.print(line3);

                    System.out.println();
                }
    }


    /**
     Views all empty slots of all 3 cashier queues.
     * */
    public static void viewEmptyQueues(){
        ArrayList<Customer> customersQueue1 = queue1.getCustomers();
        int emptySlots1 = queue1.getMaxQueueCapacity() - customersQueue1.size();
        ArrayList<Customer> customersQueue2 = queue2.getCustomers();
        int emptySlots2 = queue2.getMaxQueueCapacity() - customersQueue2.size();
        ArrayList<Customer> customersQueue3 = queue3.getCustomers();
        int emptySlots3 = queue3.getMaxQueueCapacity() - customersQueue3.size();

        System.out.println("Queue 1 Empty Slots: "+ emptySlots1);
        System.out.println("Queue 2 Empty Slots: "+ emptySlots2);
        System.out.println("Queue 3 Empty Slots: "+ emptySlots3);

    }

    /**
     Add customers to empty slots.
     * */
    public static void addCustomer() {
        Scanner input = new Scanner(System.in);

        System.out.println("Insert First Name: ");
        String customerFirstName = input.next();

        System.out.println("Insert Second Name: ");
        String customerSecondName = input.next();

        try {
            System.out.println("Insert Number of Burgers Required: ");
            int noOfBurgers = input.nextInt();

            FoodQueue selectedQueue= getMinimumLengthQueue();
            Customer customerToAdd = new Customer(customerFirstName,customerSecondName,noOfBurgers);

            if(selectedQueue != null){

                selectedQueue.getCustomers().add(customerToAdd);
                burgersCount -= noOfBurgers;
            }else{

                waitingList.add(customerToAdd);
                System.out.println("All Queues are Full. Customer Will be Added to the Waiting List.");
            }

        }catch(InputMismatchException exception){
            System.out.println("Invalid Input.");
        }
    }

    private static FoodQueue getMinimumLengthQueue() {
        int queue1Size = queue1.getCustomers().size();
        int queue2Size = queue2.getCustomers().size();
        int queue3Size = queue3.getCustomers().size();

        int minimumSize = Math.min(queue1Size, Math.min(queue2Size,queue3Size));

        if (queue1Size== minimumSize && queue1Size < queue1.getMaxQueueCapacity()){
            return queue1;
        } else if (queue2Size== minimumSize && queue2Size < queue2.getMaxQueueCapacity()) {
            return queue2;
        } else if (queue3Size== minimumSize && queue3Size < queue3.getMaxQueueCapacity()) {
            return queue3;
        }else{
            return null;
        }

    }

    /**
     Remove specific customer.
     * */
    public static void removeCustomer(){
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose Queue Number (1,2,3): ");
            int queueNumber = input.nextInt();

            if (queueNumber != 1 && queueNumber != 2 && queueNumber != 3 ) {
                System.out.println("Please Enter a Correct Queue Number (1,2,3)");
                return;
            }
            System.out.println("Mention Specific Location (1,2,3,4,5): ");
            int location = input.nextInt();

            if (queueNumber != 1 && queueNumber != 2 && queueNumber != 3 && queueNumber != 4 && queueNumber != 5 ) {
                System.out.println("Please Enter a Correct Slot Number (1,2,3,4,5)");
                return;
            }

            FoodQueue selectedQueue = null;

            switch (queueNumber) {
                case 1:
                    selectedQueue = queue1;
                    break;
                case 2:
                    selectedQueue = queue2;
                    break;
                case 3:
                    selectedQueue = queue3;
                    break;
            }

            if (selectedQueue != null) {
                ArrayList<Customer> queueCustomers = selectedQueue.getCustomers();
                if (location >= 1 && location <= queueCustomers.size()) {
                    Customer removedCustomer = queueCustomers.remove(location - 1);

                    System.out.println(removedCustomer.getFirstName() + " successfully removed from Queue " + queueNumber);
                } else {
                    System.out.println("Invalid Location.");
                }
            } else {
                System.out.println("Invalid Queue Number.");
            }
        }catch (InputMismatchException exception){
            System.out.println("Invalid Input. Try Again.");
        }
    }
    /**
     Remove a Served Customer
     * */
    public static void removeServedCustomer() {

        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Choose Queue Number (1,2,3): ");
            int queueNumber = input.nextInt();

            if (queueNumber != 1 && queueNumber != 2 && queueNumber != 3 ) {
                System.out.println("Please Enter a Correct Queue Number (1,2,3)");
                return;
            }

            FoodQueue selectedQueue = null;

            switch (queueNumber){
                case 1:
                    selectedQueue = queue1;
                    break;
                case 2:
                    selectedQueue = queue2;
                    break;
                case 3:
                    selectedQueue = queue3;
                    break;
            }


            if (selectedQueue != null){
                ArrayList<Customer> queueCustomers = selectedQueue.getCustomers();
                if(!queueCustomers.isEmpty()){
                    Customer removeServedCustomer = queueCustomers.remove(0);
                    System.out.println(removeServedCustomer.getFirstName() + " removed successfully.");
                    // Adding customers from waiting list.
                    if(!waitingList.isEmpty()){
                        queueCustomers.add(waitingList.get(0));
                        System.out.println("Added Next Customer from the Waiting List to the Queue.");
                    }
                }else{
                    System.out.println("No Customers Available.");
                }
            }else{
                System.out.println("Invalid Queue Number.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Try again.");
        }
    }

    /**
     Display All Customer Names in Alphabetical Order
     * */
    public static void viewCustomersAlphabetical() {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        allCustomers.addAll(queue1.getCustomers());
        allCustomers.addAll(queue2.getCustomers());
        allCustomers.addAll(queue3.getCustomers());

        for(int i = 0; i < allCustomers.size()-1; i++){
            for(int j = 0; j < allCustomers.size()-i-1;j++){
                Customer customer1 = allCustomers.get(j);
                Customer customer2 = allCustomers.get(j+1);

                String fullName1 = customer1.getFirstName()+ " "+ customer1.getSecondName();
                String fullName2 = customer2.getFirstName()+ " "+ customer2.getSecondName();

                if(fullName1.compareToIgnoreCase(fullName2)>0){
                    allCustomers.set(j,customer2);
                    allCustomers.set(j+1,customer1);

                }
            }
        }
        System.out.println("Customers Sorted in Alphabetical Order: ");
        for(Customer customer: allCustomers){
            System.out.println(customer.getFirstName() + " " +customer.getSecondName());
        }
    }

    /**
     Store data in to a text file
     * */
    public static void storeData() {
        try{
            File file = new File("FoodQueue.txt");
            boolean newFile = file.createNewFile();
            if (newFile) {
                System.out.println("Text File has been Successfully Created: " + file.getName());

            }
            ArrayList<Customer> queue1Customers = queue1.getCustomers();
            ArrayList<Customer> queue2Customers = queue2.getCustomers();
            ArrayList<Customer> queue3Customers = queue3.getCustomers();

            FileWriter foodQueueFileWriter  = new FileWriter("FoodQueue.txt");
            foodQueueFileWriter.write("----Queue 1 Customers----");
            foodQueueFileWriter.write(System.getProperty("line.separator"));
            foodQueueFileWriter.write(System.getProperty("line.separator"));

            for(Customer customer1: queue1Customers){
                foodQueueFileWriter.write("Customer Full Name:" + customer1.getFirstName() + " " + customer1.getSecondName());
                foodQueueFileWriter.write(System.getProperty("line.separator"));
                foodQueueFileWriter.write("Number of Burgers Required: " + customer1.getNumberOfBurgersRequired());
                foodQueueFileWriter.write(System.getProperty("line.separator"));


            }
            foodQueueFileWriter.write(System.getProperty("line.separator"));
            foodQueueFileWriter.write("----Queue 2 Customers----");
            foodQueueFileWriter.write(System.getProperty("line.separator"));
            foodQueueFileWriter.write(System.getProperty("line.separator"));

            for(Customer customer2: queue2Customers){
                foodQueueFileWriter.write("Customer Full Name:" + customer2.getFirstName() + " " + customer2.getSecondName());
                foodQueueFileWriter.write(System.getProperty("line.separator"));
                foodQueueFileWriter.write("Number of Burgers Required: " + customer2.getNumberOfBurgersRequired());
                foodQueueFileWriter.write(System.getProperty("line.separator"));


            }
            foodQueueFileWriter.write(System.getProperty("line.separator"));
            foodQueueFileWriter.write("----Queue 3 Customers----");
            foodQueueFileWriter.write(System.getProperty("line.separator"));
            foodQueueFileWriter.write(System.getProperty("line.separator"));

            for(Customer customer3: queue3Customers){
                foodQueueFileWriter.write("Customer Full Name:" + customer3.getFirstName() + " " + customer3.getSecondName());
                foodQueueFileWriter.write(System.getProperty("line.separator"));
                foodQueueFileWriter.write("Number of Burgers Required: " + customer3.getNumberOfBurgersRequired());
                foodQueueFileWriter.write(System.getProperty("line.separator"));


            }
            foodQueueFileWriter.close();

            if (file.exists()){
                System.out.println("New Data has been Added Successfully.");
            }
        }catch(IOException exception){
            System.out.println("File could not be created. Try Again.");
        }
    }

    /**
     Read the data from the saved text file.
     * */
    public static void loadData(){
        try{
            File file = new File("FoodQueue.txt");
            Scanner readFile = new Scanner(file);

            String lines;
                while(readFile.hasNext()){
                    lines = readFile.nextLine();
                    System.out.println(lines);
                }
            readFile.close();

        }catch(IOException exception){
            System.out.println("File could not be loaded. Try Again.");

        }
    }

    /**
     Views the Remaining Burger Count
     * */
    public static void viewRemainingBurgers(){
        System.out.println("Remaining burgers: "+burgersCount);

    }

    /**
     Adds preferred burger stock
     * */
    public static void addStock(){
        try{
            System.out.println("How many burgers do you want to add?");
            Scanner input = new Scanner(System.in);
            int newStock= input.nextInt();
            burgersCount+=newStock;
            System.out.println("Total Burgers: "+ burgersCount);
        }catch(InputMismatchException exception){
            System.out.println("Invalid Input. Try Again.");
        }

    }
    /**
     Gathers income of each Queue
     * */
    private static void getIncome() {
        int queue1Income = calculateIncome(queue1);
        int queue2Income = calculateIncome(queue2);
        int queue3Income = calculateIncome(queue3);

        System.out.println("Queue 1 income: "+ queue1Income + " LKR");
        System.out.println("Queue 2 income: "+ queue2Income + " LKR");
        System.out.println("Queue 3 income: "+ queue3Income + " LKR");

    }
    /**
     Calculating the Income
     @param queue
     @return Total Income
      * */
    private static int calculateIncome(FoodQueue queue) {
        int priceOfABurger = 650;
        int totalIncome = 0;
        ArrayList<Customer> customers = queue.getCustomers();

        for(int i = 0; i< customers.size(); i++){
            int totalBurgers = customers.get(i).getNumberOfBurgersRequired();
            int income = totalBurgers*priceOfABurger;
            totalIncome += income;
        }
        return totalIncome;
    }


    /**
     Exits the program
     * */
    public static void exit(){
        System.out.println("Thank you!");
        System.exit(0);

    }
}

/**
 REFERENCES:

 1. GeeksforGeeks. System.exit() in Java. Available from https://www.geeksforgeeks.org/system-exit-in-java/

 2. edureka!. File Handling in Java | Reading and Writing File in Java | Java Training | Edureka. YouTube. Java For Each Loop  https://www.youtube.com/watch?v=SslMi6ptwH8

 3. W3Schools. Java For-Each Loop. Available from https://www.w3schools.com/java/java_foreach_loop.asp

 4.GeeksforGeeks. Math.max method. Available from https://www.geeksforgeeks.org/java-math-max-method-examples/

 5. Beginnersbook. Bubble sort method. Available from https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/

 6. GeeksforGeeks. Sorting Strings using Bubble Sort. Avilable from https://www.geeksforgeeks.org/sorting-strings-using-bubble-sort-2/

 **/
