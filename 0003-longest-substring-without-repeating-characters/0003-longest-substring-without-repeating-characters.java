class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int maxLeft = 0;
        int right = s.length();
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < right; i++) {
            while (charSet.contains(s.charAt(i))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(i));
           maxLeft= Math.max(maxLeft, i - left + 1);
        }
        return maxLeft;
    }
}