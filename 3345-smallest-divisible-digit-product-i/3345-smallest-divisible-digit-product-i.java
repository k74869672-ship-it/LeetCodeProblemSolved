class Solution {
    public int smallestNumber(int n, int t) {
        int x = n;
        while (true) {
            if (getDigitProduct(x) % t == 0) {
                return x;
            }
            x++;
        }
    }

    private long getDigitProduct(int num) {
        long prod = 1;
        while (num > 0) {
            prod *= (num % 10);
            num /= 10;
        }
        return prod;
    }
}