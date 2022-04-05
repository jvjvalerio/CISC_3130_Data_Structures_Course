import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Assignment_3 {

  // Method to read in data file and add to arraylist
  public ArrayList<String> readDataFile() throws Exception {
    ArrayList<String> salesRecord = new ArrayList<>();
    String file ="Sales_Record.txt";
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String line = null;

    while ((line = reader.readLine()) != null) {
      salesRecord.add(line);
    }
    reader.close();
    return salesRecord;
  }

  // Method for creating objects of records
  public ArrayList<Sales_Record> populateObjectArray(ArrayList<String> salesRecord) {
    ArrayList<Sales_Record> updatedSalesRecords = new ArrayList<Sales_Record>();
    String[] records;

    for (int i = 0; i < salesRecord.size(); i++) {
      records = salesRecord.get(i).split("\\s+");
      if (records[0].equals("R")) {
        updatedSalesRecords.add(new Sales_Record(RecordType.R, Wood.valueOf(records[1]),
          Integer.parseInt(records[2]), Double.parseDouble(records[3])));
      } else if (records[0].equals("S")) {
        updatedSalesRecords.add(new Sales_Record(RecordType.valueOf(records[0]), Wood.valueOf(records[1]),
          Integer.parseInt(records[2])));
      } else if (records[0].equals("P")) {
        updatedSalesRecords.add(new Sales_Record(RecordType.valueOf(records[0]), Integer.parseInt(records[1])));
      }
    }
    return updatedSalesRecords;
  }

  // Method for calculating sales and promotions
  public void calculateStock(ArrayList<Sales_Record> updatedSalesRecords, LinkedList<Sales_Record> oStock, LinkedList<Sales_Record> cStock, LinkedList<Sales_Record> promotion) {
    // LinkedList<Sales_Record> result = new LinkedList<>();
    for (Sales_Record obj: updatedSalesRecords) {
      if (obj.recordType == RecordType.R && obj.woodType == Wood.O) {
        oStock.add(obj); //  25(15), 40(18)
        obj.printReceipt();
      }
      else if (obj.recordType == RecordType.R && obj.woodType == Wood.C) {
        cStock.add(obj); // 15(10), 40)13), 30(16)
        obj.printReceipt();
      }

      else if (obj.recordType == RecordType.S && obj.woodType == Wood.O) {
        calculateSalesPrice(oStock, promotion, obj);
      }

      else if (obj.recordType == RecordType.S && obj.woodType == Wood.C) {
        calculateSalesPrice(cStock, promotion, obj);
      }

      else if (obj.recordType == RecordType.P) {
        promotion.add(obj);
      }
      else {
        System.out.print("error in reading. Please correct your input!!!");
        return;
      }
    }
  }

  private void calculateSalesPrice(LinkedList<Sales_Record> stock, LinkedList<Sales_Record> promotion, Sales_Record sales_record) {
    double totalPrice = 0;
    int count = sales_record.widgetsRequested; // 60
    int temp = count; // 60

    while(temp > 0 && !stock.isEmpty()) {
      Sales_Record val = stock.removeFirst(); // 40(9),
      int rem = val.amountOfWood - temp; // 40 - 40 = 0

      if (rem >= 0) {
        totalPrice += temp * val.price; // 20 * 7 + 40 * 9
        temp = 0;
        if (rem != 0) {
          stock.addFirst(new Sales_Record(val.recordType, val.woodType, rem, val.price));
        }
      }
      if(rem < 0) {
        temp = temp - val.amountOfWood; // 60 - 20 = 40
        totalPrice += val.amountOfWood * val.price; // 20 * 7 +
      }
    }
    if(temp > 0) {
      System.out.println("\nRemainder of " + temp + " pieces of " + sales_record.woodType + " wood not available");
    }

    if(!promotion.isEmpty()) {
      Sales_Record discount = promotion.removeFirst();
      double priceDiscount = totalPrice * (discount.discount / 100);
      sales_record.printPromotion(priceDiscount);
      totalPrice -= priceDiscount;
    }
    System.out.println("\nTotal Sales Price:" + totalPrice + ", for total widgets sold:" + (count - temp) + " of Wood Type:" + sales_record.woodType.getWood());
  }

  private void printRemainingStock(LinkedList<Sales_Record> oStock, LinkedList<Sales_Record> cStock, LinkedList<Sales_Record> promotion) {
    if(cStock.isEmpty()) {
      System.out.println("No Oak is left");
    }
    else {
      System.out.println("\n----------Remaining Oak With Price---------\n");
      for (Sales_Record record : oStock) {
        record.printReceipt();
      }
    }

    if(cStock.isEmpty()) {
      System.out.println("No Cherry Mapple is left");
    }
    else {
      System.out.println("\n----------Remaining Cherry Mapple With Price---------\n");
      for(Sales_Record record : cStock) {
        record.printReceipt();
      }
    }

    if(!promotion.isEmpty()) {
      System.out.println("\n----------Remaining Promotion left With Discount %age---------\n");
      for (Sales_Record record : promotion) {
        record.printPromotion();
      }
    }
  }

  // Driver Code
  public static void main(String[] args) throws Exception {
    Assignment_3 obj = new Assignment_3();
    // ArrayLists for our records and stock of wood
    ArrayList<String> salesRecord = new ArrayList<>();
    ArrayList<Sales_Record> updatedSalesRecords = new ArrayList<>();
    LinkedList<Sales_Record> oStock = new  LinkedList<>();
    LinkedList<Sales_Record> cStock = new  LinkedList<>();
    LinkedList<Sales_Record> promotion = new  LinkedList<>();
    // Method Calls
    salesRecord = obj.readDataFile();
    updatedSalesRecords = obj.populateObjectArray(salesRecord);
    obj.calculateStock(updatedSalesRecords, oStock, cStock, promotion);
    obj.printRemainingStock(oStock, cStock, promotion);
  }
}