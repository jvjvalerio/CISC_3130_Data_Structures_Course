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
        String[] placeHolder = new String[allTransactions.size()];
        String[] allMasterPlaceHolder = new String[allMaster.size()];
        for (int i = 0; i < allTransactions.size(); i++) {
            placeHolder = allTransactions.get(i).split("\\s+");
            if (placeHolder[0].equals("O")) {
                int index = findCustomerID(placeHolder[1], allMaster);
                allMasterPlaceHolder = allMaster.get(index).split("\\s+");
                float discountPrice = Float.parseFloat(placeHolder[5]) * Float.parseFloat(placeHolder[6]) / 100;
                float cost = discountPrice * Float.parseFloat(placeHolder[4]);
                float balance = Float.parseFloat(allMasterPlaceHolder[2]);
                balance += cost;
                allMasterPlaceHolder[2] = Float.toString(balance);
                String newRecord = String.join(",", allMasterPlaceHolder);
                allMaster.set(index, newRecord);
            } else if (placeHolder[0].equals("P")) {
                int index = findCustomerID(placeHolder[1], allMaster);
                allMasterPlaceHolder = allMaster.get(index).split("\\s+");
                float discountPayment = Float.parseFloat(placeHolder[3]) * Float.parseFloat(placeHolder[4]) / 100;
                float reduceBalance = Float.parseFloat(placeHolder[3]) - discountPayment;
                float balance = Float.parseFloat(allMasterPlaceHolder[3]);
                balance -= reduceBalance;
                allMasterPlaceHolder[2] = Float.toString(balance);
                String newRecord = String.join(",", allMasterPlaceHolder);
                allMaster.set(index, newRecord);
            }
        }
        return allMaster;
    }

    public static int findCustomerID(String customerID, ArrayList<String> allMasterArrayList) {
        int index = 0;
        for (int i = 0; i < allMasterArrayList.size(); i++) {
            if (customerID.equals(allMasterArrayList.get(0))) {
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
        System.out.println(updatedMaster.toString());
    }
}