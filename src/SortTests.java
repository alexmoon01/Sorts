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
    mergeSortTest();
  }
  
  /**
   * Runs the merge sort algorithm and prints out the process
   */
  public static void mergeSortTest() {
    Integer[] sortable = new Integer[50];
    for (int i = 0; i < sortable.length; i++) {
      sortable[i] = (int)(Math.random() * 100);
    }
    System.out.println("Testing Merge:");
    System.out.println("Starting array: " + asString(sortable));
    Sort.mergeSort(sortable, 0);
    System.out.println("Sorted array: " + asString(sortable));
  }
  
  public static <T> String asString(T[] array) {
    String asString = "[";
    for (T member: array) {
      asString += "" + member + ", ";
    }
    asString = asString.substring(0, asString.length() - 2);
    asString += "]";
    return asString;
  }
  
}
