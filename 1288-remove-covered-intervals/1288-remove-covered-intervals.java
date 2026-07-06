class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort by start ascending; if starts are equal, by end descending
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int count = 0;
        int prevEnd = 0;

        for (int[] interval : intervals) {
            int end = interval[1];
            // Since starts are sorted (and for equal starts, ends are descending),
            // current interval is covered only if its end <= the max end seen so far
            if (end > prevEnd) {
                count++;
                prevEnd = end;
            }
            // else it's covered by a previous interval, skip it
        }

        return count;
    }
}