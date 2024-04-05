import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static String[] cashier1 = new String[2];
    private static String[] cashier2 = new String[3];
    private static String[] cashier3 = new String[5];

    private static int burgersCount = 50;
    private static int burgersWarningCount = 10;
    private static String[] customerNames = new String[0];

    public static void main(String[] args) {

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
    public static void viewQueues(){
        System.out.println("*****************");
        System.out.println("*   Cashiers    *");
        System.out.println("*****************");

        //stores cashier 1 line customers.
        String line1;
        //stores cashier 2 line customers.
        String line2;
        //stores cashier 3 line customers.
        String line3;


        for (int i=0; i< cashier3.length; i++ ){
            if (i< cashier1.length && cashier1[i] != null){
                line1 = "o";
            }else{
                line1 = "x";
            }

            if (i< cashier2.length && cashier2[i] != null){
                line2 = "o";
            }else{
                line2 = "x";
            }

            if (cashier3[i] != null){
                line3 = "o";
            }else{
                line3 = "x";
            }

            if (i< cashier1.length){
                System.out.print(line1 +"     ");
            }else{
                System.out.print("      ");
            }
            if (i< cashier2.length){
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

        int cashier1EmptySlots = 0;
        int cashier2EmptySlots = 0;
        int cashier3EmptySlots = 0;

        for (String customer : cashier1) {
            if (customer == null) {
                cashier1EmptySlots++;
            }
        }

        System.out.println("Empty Slots in Cashier 1: "+cashier1EmptySlots);

        for (String customer : cashier2) {
            if (customer == null) {
                cashier2EmptySlots++;
            }

        }
        System.out.println("Empty Slots in Cashier 2: "+cashier2EmptySlots);

        for (String customer : cashier3) {
            if (customer == null) {
                cashier3EmptySlots++;
            }

        }
        System.out.println("Empty Slots in Cashier 3: "+cashier3EmptySlots);
    }

    /**
     Add customers to specific queues.
     * */
    public static void addCustomer() {

        Scanner input = new Scanner(System.in);

        System.out.println("Insert Cashier Number (1,2,3): ");
        String cashierNo = input.nextLine();

        if (!cashierNo.equals("1") && !cashierNo.equals("2") && !cashierNo.equals("3")) {
            System.out.println("Please Enter a Correct Cashier Number (1,2,3)");
            return;
        }
        System.out.println("Insert Name: ");
        String customerName = input.next();
        if (cashierNo.equals("1")){
            for (int i = 0; i< cashier1.length; i++){
                if (cashier1[i] == null) {
                    cashier1[i] = customerName;
                    return;
                }
            }
        }
        if (cashierNo.equals("2")){
            for (int i = 0; i< cashier2.length; i++){
                if (cashier2[i] == null) {
                    cashier2[i] = customerName;
                    return;
                }
            }
        }
        if (cashierNo.equals("3")){
            for (int i = 0; i< cashier3.length; i++){
                if (cashier3[i] == null) {
                    cashier3[i] = customerName;
                    return;
                }
            }
        }
        //checks whether the cashier line is full.
        if(isCashierLineFull(cashierNo)){
            System.out.println("Cashier Line Full.");
        }
    }

    /**
     Remove specific customer.
     * */
    public static void removeCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Cashier Line (1,2,3): ");
        String cashierNumber = input.nextLine();

        if (!cashierNumber.equals("1") && !cashierNumber.equals("2") && !cashierNumber.equals("3")) {
            System.out.println("Please Enter a Correct Cashier Number (1,2,3)");
            return;
        }

        try{
            if(cashierNumber.equals("1")){
                System.out.println("Choose slot (1,2): ");
                int slot = input.nextInt();

                if (slot != 1 && slot != 2) {
                    System.out.println("Please Enter a Correct Slot Number (1,2)");
                    return;
                }
                if (cashier1[slot-1] == null){
                    System.out.println("There is No Customer in That Slot!");
                }

                cashier1 = removeSpecificElement(cashier1,slot-1);
                cashier1=addElement(cashier1,null);
            }

            if(cashierNumber.equals("2")){
                System.out.println("Choose slot (1,2,3): ");
                int slot = input.nextInt();

                if (slot != 1 && slot != 2 && slot != 3) {
                    System.out.println("Please Enter a Correct Slot Number (1,2,3)");
                    return;
                }
                if (cashier2[slot-1] == null){
                    System.out.println("There is No Customer in That Slot!");
                }

                cashier2 = removeSpecificElement(cashier2,slot-1);
                cashier2=addElement(cashier2,null);
            }

            if(cashierNumber.equals("3")){
                System.out.println("Choose slot (1,2,3,4,5): ");
                int slot = input.nextInt();

                if (slot != 1 && slot != 2 && slot != 3 && slot != 4 && slot != 5) {
                    System.out.println("Please Enter a Correct Slot Number (1,2,3,4,5)");
                    return;
                }
                if (cashier3[slot-1] == null){
                    System.out.println("There is No Customer in That Slot!");
                }

                cashier3 = removeSpecificElement(cashier3,slot-1);
                cashier3=addElement(cashier3,null);
            }

        }catch (InputMismatchException exception){
            System.out.println("Invalid input. Try again.");
        }

    }
    /**
     Remove a Served Customer
     * */
    public static void removeServedCustomer(){
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Cashier Line (1,2,3): ");
        String cashierNumber = input.nextLine();

        if (cashierNumber.equals( "1")){
            if(cashier1[0]==null){
                System.out.println("No Customer Has been Served.");
            }
            cashier1 = removeSpecificElement(cashier1,0);
            burgersCount-=5;
            cashier1 = addElement(cashier1, null);

        }
        if (cashierNumber.equals( "2")){
            if(cashier2[0]==null){
                System.out.println("No Customer Has been Served.");
            }
            cashier2 = removeSpecificElement(cashier2,0);
            burgersCount-=5;
            cashier2 = addElement(cashier2, null);

        }
        if (cashierNumber.equals( "3")){
            if(cashier3[0]==null){
                System.out.println("No Customer Has been Served.");
            }
            cashier3 = removeSpecificElement(cashier3,0);
            burgersCount-=5;
            cashier3 = addElement(cashier3, null);

        }
    }

    /**
     Display All Customer Names in Alphabetical Order
     * */
    public static void viewCustomersAlphabetical() {
        for (String customer : cashier1) {
            if (customer != null) {
                customerNames = addElement(customerNames, customer);
            }

        }

        for (String customer : cashier2) {
            if (customer != null) {
                customerNames = addElement(customerNames, customer);
            }

        }

        for (String customer : cashier3) {
            if (customer != null) {
                customerNames = addElement(customerNames, customer);
            }

        }

        if (customerNames.length==0) {
            System.out.println("No Customers Available.");
        }else {
            System.out.println("Original Customer Name List: ");
            for (String customer : customerNames) {
                if (!customer.equals("")) {
                    System.out.println(customer + ", ");
                }
            }

            for (int i = 0; i < customerNames.length - 1; i++) {
                for (int j = 0; j < customerNames.length - i - 1; j++) {
                    int length1 = customerNames[j].length();
                    int length2 = customerNames[j + 1].length();
                    int minLength = Math.min(length1, length2);

                    for (int z = 0; z < minLength; z++) {
                        if (customerNames[j].charAt(z) != customerNames[j + 1].charAt(z)) {
                            if (customerNames[j].charAt(z) - customerNames[j + 1].charAt(z) > 0) {
                                String temp = customerNames[j];
                                customerNames[j] = customerNames[j + 1];
                                customerNames[j + 1] = temp;
                            }
                            break;
                        }
                    }
                }
            }
            System.out.println();
            System.out.println("Customer Name List in Alphabetical Order: ");
            for (String sortedNames : customerNames) {
                if (!sortedNames.equals("")) {
                    System.out.println(sortedNames + ", ");
                }
            }
        }
        System.out.println();
        customerNames=new String[0];
    }

    /**
     Store data in to a text file
     * */
    public static void storeData() {
        try{
            File file = new File("FoodiesFaveFoodCenter.txt");
            boolean newFile = file.createNewFile();
            if (newFile) {
                System.out.println("Text File has been Successfully Created: " + file.getName());

            }

            FileWriter fileWriter = new FileWriter("FoodiesFaveFoodCenter.txt");
            fileWriter.write("Customers in Cashier 1 : " + Arrays.toString(cashier1));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("Customers in Cashier 2: " + Arrays.toString(cashier2));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("Customers in Cashier 3: " + Arrays.toString(cashier3));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write("Remaining burger count: " + burgersCount);

            fileWriter.close();

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
            File file = new File("FoodiesFaveFoodCenter.txt");
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
     Exits the program
     * */
    public static void exit(){
        System.out.println("Thank you!");
        System.exit(0);

    }

    /**
     Adding a new element to an array
     @param array array name
     @param x new array element
     @return new string array
      * */
    public static String[] addElement(String array[], String x) {
        int i;

        //new array with an extra element added
        String newArray[] = new String[array.length + 1];

        //adding elements from the old array to the new one
        for (i = 0; i < array.length; i++)
            newArray[i] = array[i];

        newArray[array.length] = x;
        return newArray;
    }

    /**
     Remove a specific element from an array
     @param array array name
     @param index array index value
     @return a new array
      * */
    public static String[] removeSpecificElement(String[] array, int index) {

        if (array == null || index < 0
                || index >= array.length) {

            return array;
        }

        //new array with an element removed
        String [] newArray = new String[array.length - 1];

        //adding elements from the old array to the new one without the index
        for (int i = 0, k = 0; i < array.length; i++) {

            if (i == index) {
                continue;
            }
            newArray[k++] = array[i];
        }
        return newArray;
    }

    /**
     Checks whether the cashier line is full or not.
     @param number cashier number
     @return true or false
      * */
    public static boolean isCashierLineFull(String number){
        String[] cashierLine;

        switch (number){
            case "1":
                cashierLine = cashier1;
                break;

            case "2":
                cashierLine = cashier2;
                break;

            case "3":
                cashierLine = cashier3;
                break;

            default:
                return true;

        }
        for (String customer: cashierLine){
            if(customer== null){
                return false;
            }
        }
        return true;
    }

}

/**
 REFERENCES:

 1. GeeksforGeeks. System.exit() in Java. Available from https://www.geeksforgeeks.org/system-exit-in-java/

 2. edureka!. File Handling in Java | Reading and Writing File in Java | Java Training | Edureka. YouTube. Java For Each Loop  https://www.youtube.com/watch?v=SslMi6ptwH8

 3. W3Schools. Java For-Each Loop. Available from https://www.w3schools.com/java/java_foreach_loop.asp
 */