/**
 * Implementation of various sorting algorithms for practice
 * @author Alex
 */
public class Sort <T extends Comparable<T>> {

  @SuppressWarnings("unchecked")
  public static <T extends Comparable<T>>void mergeSort(T[] sortable, int spaces) throws InterruptedException {
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
      
      if (sortable.length > 2) {
        for (int i = 0; i <= spaces; i++) {
          System.out.print(" ");
        }
        System.out.println(SortTests.asString(firstHalf) + SortTests.asString(secondHalf)  + ":");
      }
        
      //Sorts the halves of the array independently
      Thread.sleep(5);
      mergeSort(firstHalf, spaces + 2);
      Thread.sleep(5);
      mergeSort(secondHalf, spaces + 2);
      
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
  
  private static <T extends Comparable<T>> T minOf(T first, T second) {
    if (first.compareTo(second) < 0) {
      return first;
    } else {
      return second;
    }
  }
  
}
