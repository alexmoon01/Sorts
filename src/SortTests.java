/**
 * Class to test the various sorting algorithms
 * @author Alex
 */
public class SortTests {

  /**
   * Runs sort tests
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Integer[] sortable = randArray(20, 0, 100);
    mergeSortTest(copyArray(sortable));
    System.out.println("\n\n\n\n");
    selectionSortTest(copyArray(sortable));
    System.out.println("\n\n\n\n");
    insertionSortTest(copyArray(sortable));
    System.out.println("\n\n\n\n");
    quickSortTest(copyArray(sortable));
  }
  
  /**
   * Runs the merge sort algorithm and prints out the process
   */
  public static void mergeSortTest(Integer[] sortable) {
    System.out.println("Testing Merge:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.mergeSort(sortable, 0);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  /**
   * Runs the selection sort algorithm and prints out the process
   */
  public static void selectionSortTest(Integer[] sortable) {
    System.out.println("Testing Selection Sort:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.selectionSort(sortable);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  /**
   * Runs the insertion sort algorithm and prints out the process.
   */
  public static void insertionSortTest(Integer[] sortable) {
    System.out.println("Testing Insertion Sort:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.insertionSort(sortable);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  public static void quickSortTest(Integer[] sortable) {
    System.out.println("Testing Quick Sort:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.quickSort(sortable, 0, sortable.length - 1, 0);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  /**
   * Prints an array as a string.
   * @param array The array to be printed.
   * @return The array as a string.
   */
  public static <T> String asString(T[] array) {
    String asString = "[";
    for (T member: array) {
      asString += "" + member + ", ";
    }
    asString = asString.substring(0, asString.length() - 2);
    asString += "]";
    return asString;
  }
  
  public static <T> String asString(T[] array, int min, int max) {
    String asString = "[";
    for (int i = min; i <= max; i++) {
      asString+= "" + array[i] + ", ";
    }
    asString = asString.substring(0, asString.length() - 2);
    asString += "]";
    return asString;
  }
  
  /**
   * Creates a deep copy of an array.
   * @param array The array to be copied.
   * @return A deep copy of the array.
   */
  private static Integer[] copyArray(Integer[] array) {
    Integer[] returnable = new Integer[array.length];
    for (int i = 0; i < array.length; i++) {
      returnable[i] = array[i];
    }
    return returnable;
  }
  
  /**
   * Generates a random array of ints
   * @param length The length of the array
   * @param min The minimum value of the array
   * @param max The maximum value of the array
   * @return A random array with the specified parameters
   */
  private static Integer[] randArray(int length, int min, int max) {
    Integer[] randArray = new Integer[length];
    for (int i = 0; i < length; i++) {
      //Generates a random int between min and max, inclusive
      randArray[i] = (int)(Math.random() * (max - min + 1) + min);
    }
    return randArray;
  }
}
