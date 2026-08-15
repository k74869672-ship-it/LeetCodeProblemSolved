class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xorAll = 0;
        boolean hasNonZero = false;
        for (int num : nums) {
            xorAll ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }
        if (xorAll != 0) {
            return n;// whole array works

        } else if (hasNonZero) {
            return n - 1;// remove one non-zero element to break the zero XOR
        } else {
            return 0; // all elements are zero
        }
    }
}