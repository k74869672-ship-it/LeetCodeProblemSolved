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
            char c = s.charAt(i);
            if (count[c - 'a'] > 0 && count[c - 'a'] < k) {

                String leftString = s.substring(0,i);
                String rightString = s.substring(i+ 1);

                int leftResult = longestSubstring(rightString, k);
                int rightResult = longestSubstring(leftString, k);

                return Math.max(leftResult, rightResult);
            }
        }
        return s.length();
    }
}