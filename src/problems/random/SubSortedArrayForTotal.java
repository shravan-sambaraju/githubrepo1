package problems.random;

/**
 * Given an array of integers,write a method to find indices m and n such that if you sorted
 * elements m through n, the entire array would be sorted. Minimize n - m
 */
class SubSortedArrayForTotal {

  private static int findEndOfLeftSubsequence(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i] < array[i - 1]) {
        return i - 1;
      }
    }
    return array.length - 1;
  }

  private static int findStartOfRightSubsequence(int[] array) {
    for (int i = array.length - 2; i >= 0; i--) {
      if (array[i] > array[i + 1]) {
        return i + 1;
      }
    }
    return 0;
  }

  private static int shrinkLeft(int[] array, int min_index, int start) {
    int comp = array[min_index];
    for (int i = start - 1; i >= 0; i--) {
      if (array[i] <= comp) {
        return i + 1;
      }
    }
    return 0;
  }

  private static int shrinkRight(int[] array, int max_index, int start) {
    int comp = array[max_index];
    for (int i = start; i < array.length; i++) {
      if (array[i] >= comp) {
        return i - 1;
      }
    }
    return array.length - 1;
  }

  private static void findUnsortedSequence(int[] array) {

    int end_left = findEndOfLeftSubsequence(array);
    if (end_left >= array.length - 1) {
      // System.out.println("The array is already sorted.");
      return;
    }

    int start_right = findStartOfRightSubsequence(array);
    int max_index = end_left; // max of left side
    int min_index = start_right; // min of right side
    for (int i = end_left + 1; i < start_right; i++) {
      if (array[i] < array[min_index]) {
        min_index = i;
      }
      if (array[i] > array[max_index]) {
        max_index = i;
      }
    }

    int left_index = shrinkLeft(array, min_index, end_left);
    int right_index = shrinkRight(array, max_index, start_right);

    if (validate(array, left_index, right_index)) {
      System.out.println("TRUE: " + left_index + " " + right_index);
    } else {
      System.out.println("FALSE: " + left_index + " " + right_index);
    }
  }

  private static boolean validate(int[] array, int left_index, int right_index) {
    int[] middle = new int[right_index - left_index + 1];
    for (int i = left_index; i <= right_index; i++) {
      middle[i - left_index] = array[i];
    }
    java.util.Arrays.sort(middle);
    for (int i = left_index; i <= right_index; i++) {
      array[i] = middle[i - left_index];
    }
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int[] array = {1, 9, 4, 3, 5};
    findUnsortedSequence(array);
  }
}
