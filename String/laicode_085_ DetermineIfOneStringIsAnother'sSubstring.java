/*
Determine if a small string is a substring of another large string.

Return the index of the first occurrence of the small string in the large string.

Return -1 if the small string is not a substring of the large string.

Assumptions

Both large and small are not null
If small is empty string, return 0
Examples

“ab” is a substring of “bcabc”, return 2
“bcd” is not a substring of “bcabc”, return -1
"" is substring of "abc", return 0
*/
public class Solution {
  public int strstr(String large, String small) {
    // Robin Karp
    if (large.length() < small.length()) {
      return -1;
    }
    // return 0 if small is empty
    if (small.length() == 0) {
      return 0;
    }
    int largePrime = 101;
    int prime = 31;
    int seed = 1;
    int targetHash = small.charAt(0) % largePrime;
    for (int i = 1; i < small.length(); i++) {
      // 公共部分的冗余提前算出来 31^(small.length() - 1)
      seed = moduleHash(seed, 0, prime, largePrime);
      targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
    }
    int hash = 0;
    for (int i = 0; i < small.length(); i++) {
      hash = moduleHash(hash, large.charAt(i), prime, largePrime);
    }
    if (hash == targetHash && equals(large, 0, small)) {
      return 0;
    }
    for (int i = 1; i <= large.length() -small.length(); i++) {
      hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime); //减去头部的数字
      hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
      if (hash == targetHash && equals(large, i, small)) {
        return i;
      }
    }
    return -1;
  }

  public boolean equals(String large, int start, String small) {
    for (int i = 0; i < small.length(); i++) {
      if (large.charAt(i +start) != small.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  public int moduleHash(int hash, int addition, int prime, int largePrime) {
    return (hash * prime % largePrime + addition) % largePrime;
  }

  public int nonNegative(int hash, int largePrime) {
    if (hash < 0) {
      hash += largePrime;
    }
    return hash;
  }
}
