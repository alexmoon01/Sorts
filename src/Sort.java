/**
 * Implementation of various sorting algorithms for practice
 * @author Alex
 */
public class Sort {

  /**
   * Sorts a comparable array using the merge sort algorithm
   * @param sortable The array to be sorted
   * @param spaces The amount of spaces to be printed before the array
   * (more if the array is deeper in the call stack)
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void mergeSort(T[] sortable, int spaces) {
    if (sortable.length > 1) {
      //Splits the array into two parts
      int middle = sortable.length / 2;
      T[] firstHalf = (T[])new Comparable[middle];
      for (int i = 0; i < middle; i++) {
        firstHalf[i] = sortable[i];
      }
      T[] secondHalf = (T[])new Comparable[sortable.length - middle];
      for (int i = middle; i < sortable.length; i++) {
        secondHalf[i - middle] = sortable[i];
      }
      
      //Prints out the split array before sorting
      if (sortable.length > 2) {
        for (int i = 0; i <= spaces; i++) {
          System.out.print(" ");
        }
        System.out.println(SortTests.asString(firstHalf) + SortTests.asString(secondHalf)  + ":");
      }
        
      //Sorts the halves of the array independently
      mergeSort(firstHalf, spaces + 2);
      mergeSort(secondHalf, spaces + 2);
      
      //Prints out the split array after sorting
      if (sortable.length > 2) {
        for (int i = 0; i <= spaces; i++) {
          System.out.print(" ");
        }
        System.out.println(SortTests.asString(firstHalf) + SortTests.asString(secondHalf));
      }

      
      //Merges the sorted arrays
      int i = 0;
      int j = 0;
      while (i + j < sortable.length) {
        //If firstHalf has been exhausted, takes from secondHalf
        if (i >= firstHalf.length) {
          sortable[i + j] = secondHalf[j];
          j++;
          //Likewise for secondHalf
        } else if (j >= secondHalf.length) {
          sortable[i + j] = firstHalf[i];
          i++;
        } else {
          //Inserts the minimum of the two current parts
          T insert = minOf(firstHalf[i], secondHalf[j]);
          sortable[i + j] = insert;
          //Moves past the sed object
          if (insert == firstHalf[i]) {
            i++;
          } else {
            j++;
          }
        }
      }
    }
  }
  
  /**
   * Sorts a comparable array using the selection sort algorithm
   * @param sortable The array to be sorted
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>> void selectionSort(T[] sortable) {
    //Creates a new array of the same length as sortable
    T[] sorted = (T[])(new Comparable[sortable.length]);
    
    //Iterates through sortable, putting the elements into sorted in order
    for (int i = 0; i < sorted.length; i++) {
      int minIndex = minOf(sortable);
      sorted[i] = sortable[minIndex];
      sortable[minIndex] = null;
      //Prints out current progress
      System.out.println("  " + SortTests.asString(sortable) + ":          " + SortTests.asString(sorted));
    }
    
    //Sets the sortable array to be sorted
    for (int i = 0; i < sortable.length; i++) {
      sortable[i] = sorted[i];
    }
  }
  
  /**
   * Helper method which finds the minimum of two comparable objects
   * @param first The first object to be analyzed
   * @param second The second object to be analyzed
   * @return The minimum of the two objects; second if they're equal
   */
  private static <T extends Comparable<T>> T minOf(T first, T second) {
    if (first.compareTo(second) < 0) {
      return first;
    } else {
      return second;
    }
  }
  
  /**
   * Helper method which finds the minimum of an array.<br>
   * Returns 0 if no non-null elements.
   * @param array The array to be searched through.
   * @return The index of the minimum object in the array.
   */
  private static <T extends Comparable<T>> int minOf(T[] array) {
    int minIndex = 0;
    for (int i = 1; i < array.length; i++) {
      //Finds the first non-null element in the array
      if (array[minIndex] == null) {
        minIndex = i;
      } else if (array[i] != null){
        //If the currently searched location is less than min, 
        //the new min is this location
        if (minOf(array[minIndex], array[i]) == array[i]) {
          minIndex = i;
        }
      }
    }
    return minIndex;
  }
}