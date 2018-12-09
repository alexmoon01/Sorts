/**
 * Implementation of various sorting algorithms for practice
 * @author Alex
 */
public class Sort {

  /**
   * Sorts a comparable array using the merge sort algorithm
   * @param <T> The comparable type of the members of the array
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
        System.out.println(SortTests.asString(firstHalf) + SortTests.asString(secondHalf));
      }
        
      //Sorts the halves of the array independently
      mergeSort(firstHalf, spaces + 2);
      mergeSort(secondHalf, spaces + 2);
      
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
   * @param <T> The comparable type of the members of the array
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
      System.out.println("  " + SortTests.asString(sortable) + ":          " + SortTests.asString(sorted, 0, i));
    }
    
    //Sets the sortable array to be the new sorted version
    for (int i = 0; i < sortable.length; i++) {
      sortable[i] = sorted[i];
    }
  }
  
  /**
   * Sorts a comparable array using the insertion sort algorithm.
   * @param <T> The comparable type of the members of the array
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
      System.out.println(SortTests.asString(sortable, 0, i) + ":    " + 
          SortTests.asString(sortable, i + 1, sortable.length - 1));
    }
  }
  
  /**
   * Sorts a range in a comparable array using the quick sort algorithm.
   * <br> Pivots are the median of the first, middle, and last entries.
   * @param <T> The comparable type of the members of the array
   * @param sortable The array to be sorted
   * @param min the index of the first value in the range to be sorted
   * @param max the index of the last value in the range to be sorted
   * @param spaces the amount of spaces to be printed before the array.
   * Only used for organization in the console.
   */
  public static <T extends Comparable<T>> void quickSort(T[] sortable, int min, int max, int spaces) {
    if (max > min) {
      for (int i = 0; i < spaces; i++) {
        System.out.print(" ");
      }
      System.out.println(SortTests.asString(sortable, min, max) + " P = " + 
          sortable[medianIndex(sortable, min, (min + max) / 2, max)]);
      
      //Partitions the array
      int newMedianIndex = qPartition(sortable, min, max, medianIndex(sortable, min, (min + max) / 2, max));
      
      //Sorts the partitioned halves
      if (max - min > 1) {
        quickSort(sortable, min, newMedianIndex - 1, spaces + 2);
        quickSort(sortable, newMedianIndex + 1, max, spaces + 2);
      }
      
    }
  }
  
  /**
   * Partitions a range in an array for quickSort based off of the partition value
   * @param <T> The comparable type of the members of the array
   * @param sortable The array to be partitioned
   * @param min The min index of the range to be partitioned
   * @param max The max index of hte range to be partitioned
   * @param partitionVal The value which the array will be partitioned based off of.
   * @return The new index of the median value
   * @see Sort#quickSort(Comparable[], int, int)
   */
  private static <T extends Comparable<T>> int qPartition(T[] sortable, int min, int max, int partitionIndex) {
    swapInArray(sortable, partitionIndex, max);
    int i = min - 1;
    //Searches through the selected portion of the array and partitions it based on the partition val
    for (int j = min; j < max; j++) {
      if (minOf(sortable[j], sortable[max]) == sortable[j]) {
        i++;
        swapInArray(sortable, i, j);
      }
    }
    //Inserts the partition value into the proper position
    swapInArray(sortable, max, i + 1);
    return i + 1;
  }
  
  /**
   * Finds the index of the median value of three values within an array.
   * @param <T> The comparable type of the array
   * @param sortable The array to be searched through
   * @param first The index of the first value to be checked
   * @param second The index of the second value to be checked
   * @param third The index of the third value to be checked
   * @return The index of the median value of the three
   */
  private static <T extends Comparable<T>> int medianIndex(T[] sortable, int first, int second, int third) {
    if (minOf(sortable[first], sortable[second]) == sortable[first] 
        && minOf(sortable[first], sortable[third]) == sortable[first]) {
      //First is min value
      if (minOf(sortable[second], sortable[third]) == sortable[third]) {
        return third;
      } else {
        return second;
      }
    } else if (minOf(sortable[first], sortable[second]) == sortable[second] 
        && minOf(sortable[first], sortable[third]) == sortable[third]) {
      //First is max value
      if (minOf(sortable[second], sortable[third]) == sortable[third]) {
        return second;
      } else {
        return third;
      }
    } else {
      //First is median value
      return first;
    }
  }
  
  /**
   * Helper method which finds the minimum of two comparable objects
   * @param <T> The comparable type of the two objects to be analyzed
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
   * @param <T> The comparable type of the members of the array
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
   * @param <T> The comparable type of the members of the array
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
   * @param <T> The generic type of the array.
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