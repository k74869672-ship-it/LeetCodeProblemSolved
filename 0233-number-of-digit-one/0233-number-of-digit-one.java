class Solution {
    public int countDigitOne(int n) {
        long count = 0;
        for (long pos = 1; pos <= n; pos *= 10) {
            long high = n / (pos * 10);
            long cur = (n / pos) % 10;
            long low = n % pos;
            
            if (cur == 0) {
                count += high * pos;
            } else if (cur == 1) {
                count += high * pos + low + 1;
            } else {
                count += (high + 1) * pos;
            }
        }
        return (int) count;
    }
}