class Solution {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        int[] count = new int[26];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (count[ch - 'a'] > 0 && count[ch - 'a'] < k) {
                String leftString = s.substring(0, i);
                String rightString = s.substring(1+i);

                int leftResult = longestSubstring(rightString, k);
                int rightResult = longestSubstring(leftString, k);

                return Math.max(leftResult, rightResult);
            }
        }
        return s.length();

    }
}