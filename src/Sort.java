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
    //Iterates through sortable, putting the elements into sorted in order
    for (int i = 0; i < sortable.length; i++) {
      int minIndex = minOf(sortable, i, sortable.length - 1);
      swapInArray(sortable, i, minIndex);
      //Prints out current progress
      System.out.println("" + SortTests.asString(sortable, i + 1, sortable.length - 1) +
          ":    " + SortTests.asString(sortable, 0, i));
    }
  }
  
  /**
   * Sorts a comparable array using the insertion sort algorithm.
   * @param <T> The comparable type of the members of the array
   * @param sortable The array to be sorted
   */
  public static <T extends Comparable<T>> void insertionSort(T[] sortable) {
    for (int i = 0; i < sortable.length; i++) {
      //Finding the correct index for the first value in the unsorted portion
      if (i > 0) {
        if (minOf(sortable[i], sortable[i - 1]) == sortable[i]) {
          swapInArray(sortable, i, i - 1);
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
   * Sorts a comparable array using the heap sort algorithm.
   * @param <T> The comparable type of the members of the array
   * @param sortable The array to be sorted.
   */
  public static <T extends Comparable<T>> void heapSort(T[] sortable) {
    System.out.println("Heapifying:");
    T[] heap = heapify(sortable);
    System.out.println("Sorting:");
    for (int i = 0; heap[0] != null; i++) {
      sortable[i] = removeTop(heap);
      System.out.println("Sorted array: " + SortTests.asString(sortable, 0, i) + 
          "    Heap: " + SortTests.asString(heap, 0, heap.length - 2 - i));
    }
  }
  
  /**
   * Returns a heapified version of the input array.
   * @param <T> The comparable type of the members of the array
   * @param heapifiable The array to be heapified.
   */
  @SuppressWarnings("unchecked")
  private static <T extends Comparable<T>> T[] heapify(T[] heapifiable) {
    //Instantiates the heapified version
    T[] heapified = (T[])(new Comparable[heapifiable.length]);
    //Heapifies as it copies the old array to the new one
    for (int i = 0; i < heapified.length; i++) {
      heapified[i] = heapifiable[i];
      percolateUp(heapified, i);
      System.out.println(SortTests.asString(heapified, 0, i));
    }
    return heapified;
  }
  
  /**
   * Removes the first index of the input. Assumes min heap property satisfied.
   * @param <T> The comparable type of the members of the array
   * @param heap The heap to be affected
   * @param heapSize the size of the heap within the array
   * @return The top item in the heap
   */
  private static <T extends Comparable<T>> T removeTop(T[] heap) {
    //The value to be returned. The rest of the method is
    //removing this value while maintaining the minheap property.
    T top = heap[0];
    //Finds the size of the heap
    int heapSize;
    for (heapSize = heap.length - 1; heapSize > 0 && heap[heapSize] == null; heapSize--);
    
    //Puts the top of the heap at the bottom
    swapInArray(heap, 0, heapSize);
    //Removes the former top post
    heap[heapSize] = null;
    
    //Rearranges heap to maintain minheap property
    percolateDown(heap);
    return top;
  }
  
  /**
   * Percolates the nth of the heap up into the appropriate position (min heap property).
   * @param <T> The comparable type of the members of the array
   * @param heap The heap to be organized.
   * @param index The index of the element to be percolated up.
   */
  private static <T extends Comparable<T>> void percolateUp(T[] heap, int index) {
    int percIndex = index;
    //While the parent is greater than the child
    while (minOf(heap[percIndex], heap[(percIndex - 1) / 2]) == heap[percIndex]) {
      if (percIndex == 0) {
        break;
      }
      //Swaps the parent and child, satisfying the minHeap requirement
      swapInArray(heap, percIndex, (percIndex - 1) / 2);
      percIndex = (percIndex - 1) / 2;
    }
  }
  
  /**
   * Percolates the top term down to the appropriate term position (min heap property)
   * @param <T> The comparable type of the members of the array
   * @param heap The heap to be organized.
   */
  private static <T extends Comparable<T>> void percolateDown(T[] heap) {
    int percIndex = 0;
    int leftChildIndex = (percIndex * 2) + 1;
    int rightChildIndex = (percIndex * 2) + 2;
    //While the children exist and the smallest child is lesser than the parent
    while (minOf(minOf(heap[leftChildIndex], heap[rightChildIndex]), 
        heap[percIndex]) != heap[percIndex]) {
      int minChildIndex;
      //Finds the index of the minimum child of the current index
      if (minOf(heap[leftChildIndex], heap[rightChildIndex]) == heap[leftChildIndex]) {
        minChildIndex = leftChildIndex;
      } else {
        minChildIndex = rightChildIndex;
      }
      //Swaps the current focus with its minimum child
      swapInArray(heap, percIndex, minChildIndex);
      //Sets new locations for parent and two children
      percIndex = minChildIndex;
      leftChildIndex = (percIndex * 2) + 1;
      rightChildIndex = (percIndex * 2) + 2;
      //If current index has no children, 
      if (leftChildIndex >= heap.length || heap[leftChildIndex] == null) {
        break;
      }
      if (rightChildIndex >= heap.length || heap[rightChildIndex] == null) {
        rightChildIndex = leftChildIndex;
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
    if (second == null && first == null) {
      return null;
    } else if (second == null) {
      return first;
    } else if (first == null) {
      return second;
    } else if (first.compareTo(second) < 0) {
      return first;
    } else {
      return second;
    }
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