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
   * Sorts a comparable array using the insertion sort algorithm.
   * @param sortable The array to be sorted
   */
  public static <T extends Comparable<T>> void insertionSort(T[] sortable) {
    for (int i = 0; i < sortable.length; i++) {
      //Finding the index and value of the minimum unsorted value of the array
      int minInRange = minOf(sortable, i, sortable.length - 1);
      T minVal = sortable[minInRange];
      
      //Pushing the remainder of the unsorted portion of array up
      for (int j = minInRange; j > i; j--) {
        sortable[j] = sortable[j - 1]; 
      }
      
      //Inserting the minValue into the appropriate location in the sorted portion of array
      sortable[i] = minVal;
      for (int k = i; k > 0; k--) {
        if (minOf(sortable[k], sortable[k - 1]) == sortable[k]) {
          swapInArray(sortable, k, k - 1);
        } else {
          break;
        }
      }
      System.out.println(SortTests.asString(sortable));
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

  /**
   * Helper method which finds the min of an array within certain bounds.
   * @param array The array to be searched through.
   * @param start The start index of the searched section.
   * @param end The end index of the searched section
   * @return The index of the minimum value in the selected range.
   */
  private static <T extends Comparable<T>> int minOf(T[] array, int start, int end) {
    int minIndex = start;
    for (int i = start; i <= end; i++) {
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

  /**
   * Helper method that swaps two data at specified indices.
   * @param array The array that we're operating it.
   * @param firstIndex The index of the first swapee.
   * @param secondIndex The index of the second swapee.
   */
  private static <T> void swapInArray(T[] array, int firstIndex, int secondIndex) {
    T temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
  }
}