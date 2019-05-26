package tobeorganized.codingproblems;

public class ShortestCommonSuperSequence {

  public static void main(String[] args) {
    ShortestCommonSuperSequence scs = new ShortestCommonSuperSequence();
    System.out.println(scs.findSCSLength("abcf", "bdcf"));
    System.out.println(scs.findSCSLengthBottomUp("abcf", "bdcf"));
    System.out.println(scs.findSCSLength("dynamic", "programming"));
    System.out.println(scs.findSCSLengthBottomUp("dynamic", "programming"));
  }

  public int findSCSLength(String s1, String s2) {
    Integer[][] dp = new Integer[s1.length()][s2.length()];
    return findSCSLengthRecursive(dp, s1, s2, 0, 0);
  }

  private int findSCSLengthRecursive(Integer[][] dp, String s1, String s2, int i1, int i2) {
    // if we have reached the end of a string, return the remaining length of the other string, as
    // in
    // this case we have to take all of the remaing other string
    if (i1 == s1.length()) return s2.length() - i2;
    if (i2 == s2.length()) return s1.length() - i1;

    if (dp[i1][i2] == null) {
      if (s1.charAt(i1) == s2.charAt(i2))
        dp[i1][i2] = 1 + findSCSLengthRecursive(dp, s1, s2, i1 + 1, i2 + 1);
      else {
        int length1 = 1 + findSCSLengthRecursive(dp, s1, s2, i1, i2 + 1);
        int length2 = 1 + findSCSLengthRecursive(dp, s1, s2, i1 + 1, i2);
        dp[i1][i2] = Math.min(length1, length2);
      }
    }

    return dp[i1][i2];
  }

  public int findSCSLengthBottomUp(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    // if one of the strings is of zero length, SCS would be equal to the length of the other string
    for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
    for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
        else dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
      }
    }
    return dp[s1.length()][s2.length()];
  }
}
