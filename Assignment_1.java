import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Assignment_1 {

    // Method to read our Transaction file
    public static ArrayList<String> readTransactionFile() throws Exception {
        ArrayList<String> transactions = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("Transactions.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            transactions.add(line);
        }
        reader.close();
        return transactions;
    }

    // Method to read our Master file
    public static ArrayList<String> readMasterFile() throws Exception {
        ArrayList<String> master = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("Master_File.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            master.add(line);
        }
        reader.close();
        return master;
    }

    // Method for processing Transactions
    public static ArrayList<String> processTransactions(ArrayList<String> allTransactions, ArrayList<String> allMaster) {
        // Create two arrays to hold our transactions and master file entries
        String[] placeHolder = new String[allTransactions.size()];
        String[] allMasterHolder = new String[allMaster.size()];

        // Loop through all transactions
        for (int i = 0; i < allTransactions.size(); i++) {
            placeHolder = allTransactions.get(i).split("\\s+");

            // If transaction is order, process as order
            if (placeHolder[0].equals("O")) {
                int index = findCustomerID(placeHolder[1], allMaster);
                allMasterHolder = allMaster.get(index).split("\\s+");
                float discount = (Integer.parseInt(placeHolder[4]) * Float.parseFloat(placeHolder[5])) * Float.parseFloat(placeHolder[6]) / 100;
                float cost = (Float.parseFloat(placeHolder[4]) * Float.parseFloat(placeHolder[5]) - discount);
                float balance = Float.parseFloat(allMasterHolder[2]);
                balance += cost;
                allMasterHolder[2] = Float.toString(balance);
                String newRecord = String.join(" ", allMasterHolder);
                allMaster.set(index, newRecord);
                
            // If transaction is payment, process as payment
            } else if (placeHolder[0].equals("P")) {
                int index = findCustomerID(placeHolder[1], allMaster);
                allMasterHolder = allMaster.get(index).split("\\s+");
                float discountPayment = Float.parseFloat(placeHolder[3]) * Float.parseFloat(placeHolder[4]) / 100;
                float reduceBalance = Float.parseFloat(placeHolder[3]);
                float balance = Float.parseFloat(allMasterHolder[2]);
                balance -= reduceBalance;
                balance -= discountPayment;
                allMasterHolder[2] = Float.toString(balance);
                String newRecord = String.join(" ", allMasterHolder);
                allMaster.set(index, newRecord);
            }
        }
        
        // Return updated allMaster
        return allMaster;
    }

    // Method for finding Customer Account in the Master File
    public static int findCustomerID(String customerID, ArrayList<String> allMasterArrayList) {
        int index = 0;
        String[] masterPlaceHolder = new String[allMasterArrayList.size()];
        for (int i = 0; i < allMasterArrayList.size(); i++) {
            masterPlaceHolder = allMasterArrayList.get(i).split(" ");
            if (customerID.equals(masterPlaceHolder[0])) {
                index = i;
                return index;
            }
        }
        return 0;
    }
    
    // Driver Code
    public static void main(String[] args) throws Exception {
        ArrayList<String> allTransactions = new ArrayList<String>();
        ArrayList<String> allMaster = new ArrayList<String>();
        ArrayList<String> updatedMaster = new ArrayList<String>();

        allTransactions = readTransactionFile();
        allMaster = readMasterFile();
        updatedMaster = processTransactions(allTransactions, allMaster);
        for (int i = 0; i < updatedMaster.size(); i++) {
            System.out.println(updatedMaster.get(i));
        }
    }
}