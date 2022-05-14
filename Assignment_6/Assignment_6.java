import java.util.Scanner;

public class Assignment_6 {

    public static Scanner input = new Scanner(System.in);

    public static int quickComparison = 0;
    public static int quickInterChange = 0;
    public static int mergeComparison = 0;
    public static int mergeInterChange = 0;

    // This method takes in the values to be used in the array
    private static int[] inputFromUser(int sizeOfArray) {
        int numbersToBeSorted[] = new int[sizeOfArray];
        System.out.println("Now please enter the members of the array. Press enter after each input:");

        for (int i = 0; i < sizeOfArray; i++) {
            numbersToBeSorted[i] = input.nextInt();
        }
        return numbersToBeSorted;
    }

    // Method to be used for bubble sorting
    private static void bubbleSort(int a[], int n) {
        int temp = 0;
        int comp = 0;
        int interChange = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                comp += 1;
                if (a[j] > a[j + 1]) {
                    interChange += 1;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        System.out.println("Comparisons:" + comp);
        System.out.println("InterChanges:" + interChange);
    }

    // This method is used to ensure that all elements which are left to pivot is
    // less than the pivot and right elements is to greater
    private static int partition(int a[], int low, int high) {
        int temp, pivot = a[high];
        int i = low - 1;
        for (int j = low; j <= high - 1; j++) {
            quickComparison += 1;
            if (a[j] <= pivot) {
                // Swap elements
                quickInterChange += 1;
                i++;
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
    }

    // This method is used to sort the array using quick sort
    private static void quickSort(int a[], int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            // Calling partition for getting pivot element
            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
            // Calling quickSort recursively to sort the array
        }
    }

    // In this method we will merge two sorted sub arrays
    private static void merge(int a[], int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1, n2 = r - m;
        int L[] = new int[n1], R[] = new int[n2];

        for (i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }

        for (j = 0; j < n2; j++) {
            R[j] = a[m + j + 1];
        }

        i = 0;
        j = 0;
        k = l;
        mergeComparison += 1;

        while (i < n1 && j < n2) {
            mergeInterChange += 1;
            // Swapping the elements
            if (L[i] <= R[j])
                a[k++] = L[i++];
            else
                a[k++] = R[j++];
        }

        while (i < n1) {
            a[k++] = L[i++];
        }

        while (j < n2) {
            a[k++] = R[j++];
        }
    }

    // This method is used to sort the array using merge sort
    private static void mergeSort(int a[], int l, int r) {

        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(a, l, m);

            // Calling mergeSort recursively to sort the array
            mergeSort(a, m + 1, r);
            merge(a, l, m, r);
            // Calling merge for merging two sorted sub arrays
        }
    }

    public static void main(String args[]) throws Exception {
        int n;
        System.out.print("Please enter how many numbers will be in the array:");
        n = input.nextInt();
        int input[] = new int[n];

        input = inputFromUser(n);
        System.out.println("Bubble Sort");
        bubbleSort(input, n);

        System.out.println("Quick Sort");
        quickSort(input, 0, n - 1);

        System.out.println("Comparisons:" + quickComparison);
        System.out.println("InterChanges:" + quickInterChange);
        System.out.println("Merge Sort");
        mergeSort(input, 0, n - 1);

        System.out.println("Comparisons:" + mergeComparison);
        System.out.println("InterChanges:" + mergeInterChange);
    }
}