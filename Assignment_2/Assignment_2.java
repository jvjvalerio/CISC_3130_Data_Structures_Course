import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// PayRoll class
class PayRoll {
    String employeeNo = "";
    String numOfDays = "";
    String firstStartTime = "";
    String firstEndTime = "";
    String secondStartTime = "";
    String secondEndTime = "";
    String thirdStartTime = "";
    String thirdEndTime = "";

    // PayRoll Constructor for only one day
    public PayRoll(String employeeNo, String numOfDays, String firstStartTime, String firstEndTime) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstStartTime = firstStartTime;
        this.firstEndTime = firstEndTime;
    }

    // PayRoll Constructor overloaded for two days
    public PayRoll(String employeeNo, String numOfDays, String firstStartTime, String firstEndTime, String secondStartTime, String secondEndTime) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstStartTime = firstStartTime;
        this.firstEndTime = firstEndTime;
        this.secondStartTime = secondStartTime;
        this.secondEndTime = secondEndTime;
    }

    // PayRoll Constructor overloaded for three days
    public PayRoll(String employeeNo, String numOfDays, String firstStartTime, String firstEndTime, String secondStartTime, String secondEndTime, String thirdStartTime, String thirdEndTime) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.firstStartTime = firstStartTime;
        this.firstEndTime = firstEndTime;
        this.secondStartTime = secondStartTime;
        this.secondEndTime = secondEndTime;
        this.thirdStartTime = thirdStartTime;
        this.thirdEndTime = thirdEndTime;
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
        BufferedReader reader = new BufferedReader(new FileReader("/Users/untitled/Desktop/CISC_3130_Data_Structures_Course/Assignment_2/Payroll_Data_File.txt"));
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
        BufferedReader reader = new BufferedReader(new FileReader("/Users/untitled/Desktop/CISC_3130_Data_Structures_Course/Assignment_2/Personnel_Data_File.txt"));
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
        ArrayList<Personnel> arrayOfPersonnel = new ArrayList<Personnel>();
        ArrayList<PayRoll> arrayOfPayroll = new ArrayList<PayRoll>();
        
        payDataFile = readPayDataFile();
        persDataFile = readPersDataFile();

        int h = 0;
        for (int i = 0; i < 6; i++) {
            if (payDataFile.get(i) == "1") {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(h-1), payDataFile.get(h), payDataFile.get(h+1), payDataFile.get(h+2)));
            } else if (payDataFile.get(i) == "2") {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(h-1), payDataFile.get(h), payDataFile.get(h+1), payDataFile.get(h+2), payDataFile.get(h+3), payDataFile.get(h+4)));
            } else if (payDataFile.get(i) == "3") {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(h-1), payDataFile.get(h), firstStartTime, firstEndTime, secondStartTime, secondEndTime, thirdStartTime, thirdEndTime))
            }
            h++;
        }
        
        int j = 0;
        for (int k = 0; k < 6; k++) {
            arrayOfPersonnel.add(new Personnel(persDataFile.get(j), persDataFile.get(j+1), persDataFile.get(j+2), persDataFile.get(j+3), persDataFile.get(j+4), persDataFile.get(j+5)));
            j+=6;
        }

        for (Personnel obj : arrayOfPersonnel) {
            System.out.print(obj.employeeNo + " " + obj.lastNameFirstName + " " + obj.streetAddress + " " + obj.cityStateZip + " " + obj.hourlyRate + " " + obj.blankSpace+ "\n");
        }
    }
}