class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        
        // suf[i] = length of longest suffix of word2 that can be matched
        // as an exact subsequence within word1[i:]
        int[] suf = new int[n + 1];
        suf[n] = 0;
        int j = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }
            suf[i] = (m - 1) - j;
        }
        
        int[] result = new int[m];
        int idx = 0;
        j = 0;
        boolean mismatchUsed = false;
        
        for (int i = 0; i < n && j < m; i++) {
            if (word1.charAt(i) == word2.charAt(j)) {
                result[idx++] = i;
                j++;
            } else if (!mismatchUsed && (m - j - 1) <= suf[i + 1]) {
                result[idx++] = i;
                j++;
                mismatchUsed = true;
            }
        }
        
        return (j == m) ? result : new int[0];
    }
}