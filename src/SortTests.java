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
    selectionSortTest();
  }
  
  /**
   * Runs the merge sort algorithm and prints out the process
   */
  public static void mergeSortTest() {
    Integer[] sortable = randArray(100, 0, 100);
    System.out.println("Testing Merge:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.mergeSort(sortable, 0);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  public static void selectionSortTest() {
    Integer[] sortable = randArray(20, 0, 100);
    System.out.println("Testing Selection Sort:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.selectionSort(sortable);
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
