public class Sales_Record {
  // Variables for constructors
  RecordType recordType;
  Wood woodType;
  int amountOfWood;
  double price;
  int discount;
  int widgetsRequested;

  // Sales_Record object
  public Sales_Record(RecordType recordType, Wood woodType, int amountOfWood, double price) {
    this.recordType = recordType;
    this.woodType = woodType;
    this.amountOfWood = amountOfWood;
    this.price = price;
  }
  public Sales_Record(RecordType recordType, Wood woodType, int widgetsRequested) {
    this.recordType = recordType;
    this.woodType = woodType;
    this.widgetsRequested = widgetsRequested;
  }

  public Sales_Record(RecordType recordType, int discount) {
    this.recordType = recordType;
    this.discount = discount;
  }

  /*
  Print a message after each receipt record is read in with the price of the wood received.
   */
  public void printReceipt() {
    System.out.println("WoodType:" + woodType.getWood() + ", price:" + price + ", count:" + amountOfWood);
  }

  /*
  Print a message after each promotion card is read in with the amount of discount the next customer will be receiving.
   */
  public void printPromotion(double price) {
    System.out.println("promotion with discount:" + discount + ", price:" + price);
  }

  public void printPromotion() {
    System.out.println("promotion with discount:" + discount);
  }
}
