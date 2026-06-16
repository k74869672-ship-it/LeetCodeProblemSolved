class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i < n; i++) {
            int nextMultipleOf2 = ugly[i2] * 2;
            int nextMultipleOf3 = ugly[i3] * 3;
            int nextMultipleOf5 = ugly[i5] * 5;

            int nextUgly = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));

            ugly[i] = nextUgly;

            if (nextUgly == nextMultipleOf2) {
                i2++;
            }
            if (nextUgly == nextMultipleOf3) {
                i3++;
            }
            if (nextUgly == nextMultipleOf5) {
                i5++;
            }

        }
        return ugly[n - 1];
    }
}