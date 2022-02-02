import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment_1 {

    // Method to read our transaction file
    public static ArrayList<String> readData() throws Exception {
        ArrayList<String> transactions = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("/Users/untitled/Desktop/Homework_1/Transactions.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            transactions.add(line);
        }
        reader.close();
        return transactions;
    }

    // Method for creating separate Array for the "O's"
    public static ArrayList<String> processOrders (ArrayList<String>  arr) {
        ArrayList<String> orderTransactions = new ArrayList<String>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).charAt(0) == 'O') {
                orderTransactions.add(arr.get(i)); 
            }
        }
        return orderTransactions;
    } 

    // Method for creating separate Array for the "P's"
    public static ArrayList<String> processPayments (ArrayList<String>  arr) {
        ArrayList<String> paymentTransactions = new ArrayList<String>();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).charAt(0) == 'P') {
                paymentTransactions.add(arr.get(i)); 
            }
        }
        return paymentTransactions;
    } 

    // Method for processing accounts receivable
    public static ArrayList<String> accountsReceivable(ArrayList<String> orders, ArrayList<String> payments) {
        ArrayList<String> masterRecord = new ArrayList<String>();
        return null;
    }
    
    // Driver Code
    public static void main(String[] args) throws Exception {
        ArrayList<String> allTransactions = new ArrayList<String>();
        ArrayList<String> orderTransactions = new ArrayList<String>();
        ArrayList<String> paymentTransactions = new ArrayList<String>();
        
        allTransactions = readData();
        orderTransactions = processOrders(allTransactions);
        paymentTransactions = processPayments(allTransactions);

        String text = orderTransactions.get(0);
        String[] newText = text.split("     "); // Make note of this we have to split it about 5 SPACES 

        // Example formula for our calculation of transactions below
        System.out.print(Float.parseFloat(newText[4]) * Float.parseFloat(newText[5]));
    }
}