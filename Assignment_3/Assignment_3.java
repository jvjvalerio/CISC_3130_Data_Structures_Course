import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Sales_Record {
    // Variables for constructors
    String recordType;
    String woodType;
    String widgetsOrDiscounts;
    double price;
    int discount;
    int payment;

    // Sales_Record object
    public Sales_Record(String recordType, String woodType, String widgetsOrDiscounts, double price) {
        this.recordType = recordType;
        this.woodType = woodType;
        this.widgetsOrDiscounts = widgetsOrDiscounts;
        this.price = price;
    }
    public Sales_Record(String recordType, String woodType, int payment) {
        this.recordType = recordType;
        this.woodType = woodType;
        this.payment = payment;
    }

    public Sales_Record(String recordType, int discount) {
        this.recordType = recordType;
        this.discount = discount;
    }    
}

public class Assignment_3 {

    // Method to read in data file and add to arraylist
    public static ArrayList<String> readDataFile() throws Exception {
        ArrayList<String> salesRecord = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("Sales_Record.txt"));
        String line = null;

        while ((line = reader.readLine()) != null) {
            salesRecord.add(line);
        }
        reader.close();
        return salesRecord;
    }

    // Method for creating objects of records
    public static ArrayList<Sales_Record> populateObjectArray(ArrayList<String> salesRecord) {
        ArrayList<Sales_Record> updatedSalesRecords = new ArrayList<Sales_Record>();
        String[] records;

        for (int i = 0; i < salesRecord.size(); i++) {
            records = salesRecord.get(i).split("\\s+");
            if (records[0].equals("R")) {
                updatedSalesRecords.add(new Sales_Record(records[0], records[1], records[2], Double.parseDouble(records[3])));
            } else if (records[0].equals("S")) {
                updatedSalesRecords.add(new Sales_Record(records[0], records[1], Integer.parseInt(records[2])));
            } else if (records[0].equals("P")) {
                updatedSalesRecords.add(new Sales_Record(records[0], Integer.parseInt(records[1])));
            }
        }

        return updatedSalesRecords;
    }

    // Driver Code
    public static void main(String[] args) throws Exception {
        // ArrayLists for our records and stock of wood
        ArrayList<String> salesRecord = new ArrayList<>();
        ArrayList<Sales_Record> updatedSalesRecords = new ArrayList<>();
        LinkedList<String> woodStock = new LinkedList<>();

        // Method Calls
        salesRecord = readDataFile();
        updatedSalesRecords = populateObjectArray(salesRecord);
    }
}