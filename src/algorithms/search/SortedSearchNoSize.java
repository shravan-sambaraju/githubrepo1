package algorithms.search;

/** Given a sorted array with no size create a method which returns index */
class SortedSearchNoSize {

  private static int binarySearch(Listy list, int value, int low, int high) {
    int mid;
    while (low <= high) {
      mid = (low + high) / 2;
      int middle = list.elementAt(mid);

      if (middle > value || middle == -1) {
        high = mid - 1;
      } else if (middle < value) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  private static int search(Listy list, int value) {
    int index = 1;
    while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
      index *= 2;
    }
    return binarySearch(list, value, index / 2, index);
  }

  public static void main(String[] args) {
    int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18};
    SortedSearchNoSize sortedSearchNoSize = new SortedSearchNoSize();
    Listy list = sortedSearchNoSize.new Listy(array);
    for (int a : array) {
      System.out.println(search(list, a));
    }
    System.out.println(search(list, 15));
  }

  private class Listy {
    int[] array;

    public Listy(int[] arr) {
      array = arr.clone();
    }

    private int elementAt(int index) {
      if (index >= array.length) {
        return -1;
      }
      return array[index];
    }
  }
}
