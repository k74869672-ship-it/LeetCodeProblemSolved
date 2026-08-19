import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // bit i (0-indexed) represents seat (i+1)
        final int LEFT_MASK  = 0b0011110;   // seats 2,3,4,5  -> bits 1,2,3,4
        final int MID_MASK   = 0b1111000;   // seats 4,5,6,7  -> bits 3,4,5,6
        final int RIGHT_MASK = 0b111100000; // seats 6,7,8,9  -> bits 5,6,7,8

        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            int bit = 1 << (col - 1); // set bit for this seat
            rowMasks.merge(row, bit, (oldVal, newVal) -> oldVal | newVal);
        }

        // Rows with no reservations at all get 2 groups each
        long result = 2L * (n - rowMasks.size());

        for (int mask : rowMasks.values()) {
            boolean leftFree  = (mask & LEFT_MASK)  == 0;
            boolean midFree   = (mask & MID_MASK)   == 0;
            boolean rightFree = (mask & RIGHT_MASK) == 0;

            if (leftFree && rightFree) {
                result += 2;
            } else if (leftFree || midFree || rightFree) {
                result += 1;
            }
            // else: 0 groups for this row
        }

        return (int) result;
    }
}