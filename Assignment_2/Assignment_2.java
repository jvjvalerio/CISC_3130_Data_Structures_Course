import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// PayRoll class
class PayRoll {
    String employeeNo, numOfDays, firstDay, secondDay, thirdDay;

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
    String employeeNo, lastNameFirstName, streetAddress, cityStateZip, hourlyRate, blankSpace;

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

// Payment class
class Payment {
    String lastNameFirstName;
    Double payment;

    // Payment Constructor
    public Payment(String lastNameFirstName, Double payment) {
        this.lastNameFirstName = lastNameFirstName;
        this.payment = payment;
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

    // Method to populate arrayOfPayRoll ArrayList with PayRoll_Data_File content
    public static ArrayList<PayRoll> arrayOfPayroll (ArrayList<String> payDataFile) {
        ArrayList<PayRoll> arrayOfPayroll = new ArrayList<PayRoll>();

        for (int i = 0; i < payDataFile.size(); i++) {
            if (payDataFile.get(i).equals("1")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1)));
            } else if (payDataFile.get(i).equals("2")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1), payDataFile.get(i+2)));
            } else if (payDataFile.get(i).equals("3")) {
                arrayOfPayroll.add(new PayRoll(payDataFile.get(i-1), payDataFile.get(i), payDataFile.get(i+1), payDataFile.get(i+2), payDataFile.get(i+3)));
            }
        }
        return arrayOfPayroll;
    }

    // Method to populate arrayOfPersonnel ArrayList with Personnel_Data_File content
    public static ArrayList<Personnel> arrayOfPersonnel (ArrayList<String> persDataFile) {
        ArrayList<Personnel> arrayOfPersonnel = new ArrayList<Personnel>();

        int j = 0;
        for (int k = 0; k < 6; k++) {
            arrayOfPersonnel.add(new Personnel(persDataFile.get(j), persDataFile.get(j+1), persDataFile.get(j+2), persDataFile.get(j+3), persDataFile.get(j+4), persDataFile.get(j+5)));
            j+=6;
        }
        return arrayOfPersonnel;
    }

    // Method for Calculating Payroll
    // public static void paymentCalculator(ArrayList<PayRoll> arrayOfPayroll, ArrayList<Personnel> arrayOfPersonnel) {
    //     ArrayList<Payment> arrayOfPayment = new ArrayList<Payment>();
    //     String firstDayFirstTime = "";
    //     String firstDaySecondTime = ""; 
    //     String secondDayFirstTime = ""; 
    //     String secondDaySecondTime = ""; 
    //     String thirdDayfirstTime = ""; 
    //     String thirdDaySecondTime = "";

    //     int i = 1;
    //     for (PayRoll obj : arrayOfPayroll) {
    //         if (obj.employeeNo.equals("000" + Integer.toString(i))) {
    //             if (obj.numOfDays.equals("1")) {
    //                 String[] days = new String[2];
    //                 days = obj.firstDay.split("\\s+");
    //                 for (int j = 0; j < days.length; j++) {
    //                     if (days[j].length() == 5) {
    //                         firstDayFirstTime = days[j].substring(0, 1);
    //                         if (Integer.parseInt(firstDayFirstTime) == 1) {
    //                             firstDayFirstTime = "13";
    //                         } else if (Integer.parseInt(firstDayFirstTime) == 2) {
    //                             firstDayFirstTime = "14";
    //                         } else if (Integer.parseInt(firstDayFirstTime) == 3) {
    //                             firstDayFirstTime = "15";
    //                         }
    //                     } else if (days[j].length() == 4) {
    //                         firstDayFirstTime = '0' + days[j];
    //                         firstDayFirstTime = firstDayFirstTime.substring(0, 2);
    //                         if (firstDayFirstTime.equals("01")) {
    //                             firstDayFirstTime = "13";
    //                         } else if (firstDayFirstTime.equals("02")) {
    //                             firstDayFirstTime = "14";
    //                         } else if (firstDayFirstTime.equals("03")) {
    //                             firstDayFirstTime = "15";
    //                         }
    //                     }
    //                     if (Integer.parseInt(firstDayFirstTime) < 9) {
    //                         Double firstPayment = Double.valueOf(9 - Integer.parseInt(firstDayFirstTime));
    //                     } else if (9 <= Integer.parseInt(firstDayFirstTime) && Integer.parseInt(firstDayFirstTime) <= 12) {
    //                         Double firstPayment = Double.valueOf(12 - Integer.parseInt(firstDayFirstTime));
    //                     } else if (12 < Integer.parseInt(firstDayFirstTime)) {
    //                         Double firstPayment = Double.valueOf(Integer.parseInt(firstDayFirstTime) - 12);
    //                     }
    //                     }
    //                 }
    //             }
    //             i++;
    //         }
    //     }

        public static void computePayroll(ArrayList<PayRoll> arrayOfPayroll, ArrayList<Personnel> arrayOfPersonnel) {
            // List of Days
            List<PayRoll> listOfOneDays = new ArrayList<PayRoll>();
            List<PayRoll> listOfTwoDays = new ArrayList<PayRoll>();
            List<PayRoll> listOfThreeDays = new ArrayList<PayRoll>();
            Double[] rates;

            // Use of streams to separate all PayRoll objects in number of days
            listOfOneDays = arrayOfPayroll.stream()
                .filter(PayRoll -> PayRoll.numOfDays.equals("1"))
                .collect(Collectors.toList());
            listOfTwoDays = arrayOfPayroll.stream()
                .filter(PayRoll -> PayRoll.numOfDays.equals("2"))
                .collect(Collectors.toList());
            listOfThreeDays = arrayOfPayroll.stream()
                .filter(PayRoll -> PayRoll.numOfDays.equals("3"))
                .collect(Collectors.toList());   

        }  

    // Driver Code
    public static void main(String[] args) throws Exception {
        // Create two ArrayLists to hold our data files
        ArrayList<String> payDataFile = new ArrayList<String>();
        ArrayList<String> persDataFile = new ArrayList<String>();
        ArrayList<PayRoll> arrayOfPayroll = new ArrayList<PayRoll>();
        ArrayList<Personnel> arrayOfPersonnel = new ArrayList<Personnel>();
        
        // Method Calls
        payDataFile = readPayDataFile();
        persDataFile = readPersDataFile();
        arrayOfPayroll = arrayOfPayroll(payDataFile);
        arrayOfPersonnel = arrayOfPersonnel(persDataFile);
        // paymentCalculator(arrayOfPayroll, arrayOfPersonnel);
        computePayroll(arrayOfPayroll, arrayOfPersonnel);
    }
}