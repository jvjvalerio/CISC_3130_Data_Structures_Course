import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// PayRoll class
class PayRoll {
    String employeeNo = "";
    String numOfDays = "";
    String firstDay = "";
    String secondDay = "";
    String thirdDay = "";

    // PayRoll Constructor for only one day
    public PayRoll(String employeeNo, String numOfDays, String firstDay) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstDay = firstDay;
    }

    // PayRoll Constructor overloaded for two days
    public PayRoll(String employeeNo, String numOfDays, String firstDay, String secondDay) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
    }

    // PayRoll Constructor overloaded for three days
    public PayRoll(String employeeNo, String numOfDays, String firstDay, String secondDay, String thirdDay) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
        this.thirdDay = thirdDay;
    }
}

// Personnel class
class Personnel {
    String employeeNo = "";
    String lastNameFirstName = "";
    String streetAddress = "";
    String cityStateZip = "";
    String hourlyRate = "";
    String blankSpace = "";

    // Personnel Constructor
    public Personnel(String employeeNo, String lastNameFirstName, String streetAddress, String cityStateZip, String hourlyRate, String blankSpace) {
        this.employeeNo = employeeNo;
        this.lastNameFirstName = lastNameFirstName;
        this.streetAddress = streetAddress;
        this.cityStateZip = cityStateZip;
        this.hourlyRate = hourlyRate;
        this.blankSpace = blankSpace;
    }
}

public class Assignment_2 {
    // Method for reading in our Payroll_Data_File
    public static ArrayList<String> readPayDataFile() throws Exception {
        ArrayList<String> paymentFile = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("Payroll_Data_File.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            paymentFile.add(line);
        }
        reader.close();
        return paymentFile;
    }

    // Method for reading in our Personnel_Data_File
    public static ArrayList<String> readPersDataFile() throws Exception {
        ArrayList<String> personnelFile = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader("Personnel_Data_File.txt"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            personnelFile.add(line);
        }
        reader.close();
        return personnelFile;
    }

    // Driver Code
    public static void main(String[] args) throws Exception {
        // Create two ArrayLists to hold our data files
        ArrayList<String> payDataFile = new ArrayList<String>();
        ArrayList<String> persDataFile = new ArrayList<String>();
        ArrayList<PayRoll> arrayOfPayroll = new ArrayList<PayRoll>();
        ArrayList<Personnel> arrayOfPersonnel = new ArrayList<Personnel>();
        
        payDataFile = readPayDataFile();
        persDataFile = readPersDataFile();

        // For loop to populate arrayOfPayRoll ArrayList with objects
        for (int i = 0; i < payDataFile.size(); i++) {
            if (payDataFile.get(i).equals("1")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1)));
            } else if (payDataFile.get(i).equals("2")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1), payDataFile.get(i+2)));
            } else if (payDataFile.get(i).equals("3")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1), payDataFile.get(i+2), payDataFile.get(i+3)));
            }
        }
        
        // For loop to populate arrayOfPersonnel ArrayList with objects
        int j = 0;
        for (int k = 0; k < 6; k++) {
            arrayOfPersonnel.add(new Personnel(persDataFile.get(j), persDataFile.get(j+1), persDataFile.get(j+2), persDataFile.get(j+3), persDataFile.get(j+4), persDataFile.get(j+5)));
            j+=6;
        }

        // Test print loop for checking ArrayList of Personnel Objects
        for (Personnel obj : arrayOfPersonnel) {
            System.out.print(obj.employeeNo + "\n" + obj.lastNameFirstName + "\n" + obj.streetAddress + "\n" + obj.cityStateZip + "\n" + obj.hourlyRate + "\n" + obj.blankSpace+ "\n");
        }

        // Test print loop for checking ArrayList of PayRoll Objects
        for (PayRoll obj : arrayOfPayroll) {
            if (obj.numOfDays.equals("1")) {
                System.out.println(obj.employeeNo + "\n" + obj.numOfDays + "\n" + obj.firstDay + "\n");    
            } else if (obj.numOfDays.equals("2")) {
                System.out.println(obj.employeeNo + "\n" + obj.numOfDays + "\n" + obj.firstDay + "\n" + obj.secondDay + "\n");    
            } else if (obj.numOfDays.equals("3")) {
                System.out.println(obj.employeeNo + "\n" + obj.numOfDays + "\n" + obj.firstDay + "\n" + obj.secondDay + "\n" + obj.thirdDay + "\n");
            }
        }
    }
}