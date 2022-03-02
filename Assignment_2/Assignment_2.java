import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

// PayRoll class
class PayRoll {
    String employeeNo = "";
    String numOfDays = "";
    String startTime = "";
    String endTime = "";

    // PayRoll Constructor
    public PayRoll(String employeeNo, String numOfDays, String startTime, String endTime) {
        this.employeeNo = employeeNo;
        this.numOfDays = numOfDays;
        this.startTime = startTime;
        this.endTime = endTime;
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
        ArrayList<Personnel> arrayOfPersonnel = new ArrayList<Personnel>();
        payDataFile = readPayDataFile();
        persDataFile = readPersDataFile();

        int j = 0;
        for (int i = 0; i < 7; i++) {
            arrayOfPersonnel.add(new Personnel(persDataFile.get(j), persDataFile.get(j+1), persDataFile.get(j+2), persDataFile.get(j+3), persDataFile.get(j+4), persDataFile.get(j+5)));
            j+=6;
        }

        // for (Personnel obj : arrayOfPersonnel) {
        //     System.out.println(obj.employeeNo + " " + obj.lastNameFirstName + " " + obj.streetAddress + " " + obj.cityStateZip + " " + obj.hourlyRate + " " + obj.blankSpace);
        // }
    }
}