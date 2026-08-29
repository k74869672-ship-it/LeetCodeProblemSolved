class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int[] num = nums;

        int best = computeScore(num, -1);
        for (int k = 0; k < n; k++) {
            best = Math.max(best, computeScore(num, k));
        }
        return best;
    }

    private int computeScore(int[] nums, int skip) {
        int n = nums.length;
        int m = (skip == -1) ? n : n - 1;
        if (m <= 1) {
            return 0;
        }

        long[] prefix = new long[m];
        long[] suffix = new long[m];

        int idx = 0;
        long g = 0;
        for (int i = 0; i < n; i++) {
            if (i == skip) {
                continue;
            }
            g = gcd(g, nums[i]);
            prefix[idx] = g;
            idx++;
        }
        idx = m - 1;
        g = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i == skip) {
                continue;
            }
            g = gcd(g, nums[i]);
            suffix[idx] = g;
            idx--;
        }
        int count = 0;
        for (int i = 0; i < m - 1; i++) {
            if (prefix[i] == suffix[i + 1]) {
                count++;
            }

        }
        return count++;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}