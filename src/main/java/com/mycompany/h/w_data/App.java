//نورا عمر العلوي 
//3168173
package com.mycompany.h.w_data;
import java.util.Random;
import java.util.Arrays;
public class App 
{
  
  static final int N = 100;

// Selection Sort
  public static void selectionSort(int[] A, int n) {
    int  smallest, temp;

    for (int i = 0; i < n - 1; i++) {
        smallest = i;

        for (int j = i + 1; j < n; j++) {
            if (A[j] < A[smallest]) {
                smallest = j;
            }
        }
        temp = A[i];
        A[i] = A[smallest];
        A[smallest] = temp;
    }
}

// Insertion Sort
  public static void insertionSort(int[] A) {
    int i, j, temp;

    for (i = 1; i < A.length; i++) {
        temp = A[i];
        j = i - 1;

        while (j >= 0 && A[j] > temp) {
            A[j + 1] = A[j];
            j--;
        }

        A[j + 1] = temp;
    }
}

// Bubble Sort
  public static void bubbleSort(int[] A) {
    int i, j, temp;
    boolean swapped;

    for (i = 0; i < A.length - 1; i++) 
    {
        swapped = false;

        for (j = 0; j < A.length - 1 - i; j++)
        {
            if (A[j] > A[j + 1]) {
                temp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = temp;
                swapped = true;
            }
        }
    }
}

// Merge Sort 
  public static void mergeSort(int[] A, int left, int right) {
    if (left >= right) {
        return;
    }
    int middle = (left + right) / 2;

    mergeSort(A, left, middle);
    mergeSort(A, middle + 1, right);

    merge(A, left, middle, right);
}
  public static void merge(int[] A, int left, int middle, int right) {
    int n1 = middle - left + 1;
    int n2 = right - middle;

    int[] L = new int[n1];
    int[] R = new int[n2];

    for (int i = 0; i < n1; i++) {
        L[i] = A[left + i];
    }

    for (int j = 0; j < n2; j++) {
        R[j] = A[middle + 1 + j];
    }

    int i = 0;
    int j = 0;
    int k = left;

    while (i < n1 && j < n2) {
        if (L[i] < R[j]) {
            A[k++] = L[i++];
        } else {
            A[k++] = R[j++];
        }
    }

    while (i < n1) {
        A[k++] = L[i++];
    }

    while (j < n2) {
        A[k++] = R[j++];
    }
}

// Quick Sort
public static void quickSort(int[] A, int low, int high) {
    if (low < high) {
        int pivot = partition(A, low, high);

        quickSort(A, low, pivot - 1);
        quickSort(A, pivot + 1, high);
    }
}

public static int partition(int[] A, int low, int high) {
    int pivot = A[(low + high) / 2];

    while (low <= high) {

        while (A[low] < pivot) {
            low++;
        }

        while (A[high] > pivot) {
            high--;
        }

        if (low <= high) {
            int temp = A[low];
            A[low] = A[high];
            A[high] = temp;

            low++;
            high--;
        }
    }

    return low;
}

// Create Test Cases
  public static int[][] createTestCases() {

    int[][] cases = new int[4][N];
    Random random = new Random();

    // Random
    for (int i = 0; i < N; i++) {
        cases[0][i] = random.nextInt(N);
    }

    // Reverse Sorted
    for (int i = 0; i < N; i++) {
        cases[1][i] = N - i;
    }

    // Partially Sorted
    for (int i = 0; i < N; i++) {
        cases[2][i] = i;
    }

    for (int i = 0; i < N / 10; i++) {
        int x = random.nextInt(N);
        int y = random.nextInt(N);

        int temp = cases[2][x];
        cases[2][x] = cases[2][y];
        cases[2][y] = temp;
    }

    // Fully Sorted
    for (int i = 0; i < N; i++) {
        cases[3][i] = i;
    }

    return cases;
}

// Calculate Execution Time
  public static double measureTime(int[] original, int algorithm) {

    int[] data = Arrays.copyOf(original, original.length);

    long startTime = System.nanoTime();

    switch (algorithm) {

        case 0:
            selectionSort(data, data.length);
            break;

        case 1:
            insertionSort(data);
            break;

        case 2:
            bubbleSort(data);
            break;

        case 3:
            mergeSort(data, 0, data.length - 1);
            break;
       case 4:
            quickSort(data, 0, data.length - 1);
            break;
    }

    long endTime = System.nanoTime();

    double elapsedTime = (endTime - startTime) / 1000.0;

    return elapsedTime;
}

   public static void main(String[] args) {

    String[] algorithms = {
        "Selection Sort",
        "Insertion Sort",
        "Bubble Sort",
        "Merge Sort",
        "Quick Sort"
    };

    String[] casesNames = {
        "Random",
        "Reverse",
        "Partial",
        "Sorted"
    };

    int[][] testCases = createTestCases();

    System.out.println("Sorting Algorithms");
    System.out.println("Array Size: " + N);
    System.out.println();

    System.out.print("Algorithm\t");

    for (int i = 0; i < casesNames.length; i++) {
        System.out.print(casesNames[i] + "\t");
    }

    System.out.println();
    System.out.println("----------------------------------------------------------");

    for (int i = 0; i < algorithms.length; i++) {

        System.out.print(algorithms[i] + "\t");

        for (int j = 0; j < testCases.length; j++) {

            double time = measureTime(testCases[j], i);

            System.out.print(time + "\t");
        }
        System.out.println();
    }
}  
   
}
