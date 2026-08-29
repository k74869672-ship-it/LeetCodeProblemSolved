class Solution {
    public String[] largestString(int[] nums) {
        int n = nums.length;
        String[] ans = new String[n];
        int[] num = nums;
        for (int i = 0; i < n; i++) {
            ans[i] = buildString(num[i]);
        }
        return ans;
    }

    private String buildString(int x) {
        StringBuilder sb = new StringBuilder();

        int zCount = x >> 25; // multiples of 2^25 that can't merge further
        int low = x & ((1 << 25) - 1); // remaining value: bits 0..24 ('a' to 'y')

        for (int i = 0; i < zCount; i++) {
            sb.append('z');
        }

        for (int bit = 24; bit >= 0; bit--) {
            if ((x & (1 << bit)) != 0) {
                sb.append((char) ('a' + bit));
            }
        }
        return sb.toString();
    }
}