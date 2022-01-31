import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment_1 {

    // Method to read our transaction files
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
    
    //Driver Code
    public static void main(String[] args) throws Exception {
        ArrayList<String> transactions = new ArrayList<String>();
        transactions = readData();

        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }
}