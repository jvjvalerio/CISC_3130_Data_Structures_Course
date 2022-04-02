import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

class Sales_Record {
    // Variables for constructors
    String recordType;
    String woodType;
    int amountOfWood;
    double price;
    int discount;
    int widgetsRequested;

    // Sales_Record object
    public Sales_Record(String recordType, String woodType, int amountOfWood, double price) {
        this.recordType = recordType;
        this.woodType = woodType;
        this.amountOfWood = amountOfWood;
        this.price = price;
    }
    public Sales_Record(String recordType, String woodType, int widgetsRequested) {
        this.recordType = recordType;
        this.woodType = woodType;
        this.widgetsRequested = widgetsRequested;
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
                updatedSalesRecords.add(new Sales_Record(records[0], records[1], Integer.parseInt(records[2]), Double.parseDouble(records[3])));
            } else if (records[0].equals("S")) {
                updatedSalesRecords.add(new Sales_Record(records[0], records[1], Integer.parseInt(records[2])));
            } else if (records[0].equals("P")) {
                updatedSalesRecords.add(new Sales_Record(records[0], Integer.parseInt(records[1])));
            }
        }
        return updatedSalesRecords;
    }

    // Method for calculating oStock
    public static LinkedList<Sales_Record> calculateStock(ArrayList<Sales_Record> updatedSalesRecords, LinkedList<Sales_Record> oStock, LinkedList<Sales_Record> cStock) {
        String[] widgetsSold = new String[5];

        for (Sales_Record obj: updatedSalesRecords) {
            if (obj.recordType.equals("R") && obj.woodType.equals("O")) {
                oStock.add(obj);
            }
            if (obj.recordType.equals("R") && obj.woodType.equals("C")) {
                cStock.add(obj);
            }
            if (obj.recordType.equals("S") && obj.woodType.equals("O")) {
                if (oStock.getLast().amountOfWood < obj.widgetsRequested) {
                    int widgetsLeftOver = obj.widgetsRequested - oStock.getLast().amountOfWood;
                    obj.widgetsRequested = widgetsLeftOver;
                    if (oStock.getLast().amountOfWood == 0) {
                        oStock.removeLast();
                    }
                    double priceWidgetsSoldAt = obj.widgetsRequested * oStock.getLast().price;
                    Sales_Record wood = oStock.getLast();
                    
                        
                    }
                }
            }
            
        }
        return oStock;
    }

    // Driver Code
    public static void main(String[] args) throws Exception {
        // ArrayLists for our records and stock of wood
        ArrayList<String> salesRecord = new ArrayList<>();
        ArrayList<Sales_Record> updatedSalesRecords = new ArrayList<>();
        LinkedList<Sales_Record> oStock = new  LinkedList<>();
        LinkedList<Sales_Record> cStock = new  LinkedList<>();

        // Method Calls
        salesRecord = readDataFile();
        updatedSalesRecords = populateObjectArray(salesRecord);
        oStock = calculateStock(updatedSalesRecords, oStock, cStock);

        for (Sales_Record obj: oStock) {
            System.out.println(obj.recordType + " " + obj.woodType + " " + obj.widgetsOrDiscounts + " " + obj.price);
        }
    }
}