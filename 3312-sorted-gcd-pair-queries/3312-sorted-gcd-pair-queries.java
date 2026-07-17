class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] cnt = new int[maxVal + 1];
        for (int num : nums) {
            cnt[num]++;
        }
        int[] multipleCount = new int[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            long sum = 0;
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                sum += cnt[multiple];

            }
            multipleCount[g] = (int) sum;
        }
        long[] pairsDivisibleBy = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            long m = multipleCount[g];
            pairsDivisibleBy[g] = m * (m - 1) / 2;
        }

        // Step 4: exact gcd count, process from high g to low g
        long[] exactGcdCount = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long total = pairsDivisibleBy[g];
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                total -= exactGcdCount[multiple];
            }
            exactGcdCount[g] = total;
        }
        long[] prefix = new long[maxVal + 1];
        prefix[0] = 0;
        long[] fullPrefix = new long[maxVal + 2];
        for (int g = 1; g <= maxVal; g++) {
            fullPrefix[g] = fullPrefix[g - 1] + exactGcdCount[g];
        }
        int q = queries.length;
        int[] answer = new int[q];
        for (int i = 0; i < q; i++) {
            long target = queries[i] + 1; // 0-indexed -> need prefix >= target
            int lo = 1, hi = maxVal;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (fullPrefix[mid] >= target)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            answer[i] = lo;
        }
        return answer;

    }

}