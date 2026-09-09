class Solution {
    public long countCommas(long n) {
        long total = 0;
        long start = 0;
        long p = 10;

        while (start <= n) {
            long end = p - 1;
            long rangeEnd = Math.min(end, n);
            long count = rangeEnd - start + 1;

            int d = Long.toString(start).length();
            long commas = (d - 1) / 3;

            total += count * commas;
            start = p;
            p *= 10;
        }

        return total;
    }
}