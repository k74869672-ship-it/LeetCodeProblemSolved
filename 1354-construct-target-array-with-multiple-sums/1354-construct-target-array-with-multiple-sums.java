import java.util.*;

class Solution {
    public boolean isPossible(int[] target) {
        int n = target.length;
        if (n == 1) {
            return target[0] == 1;
        }

        PriorityQueue<Long> heap = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        for (int t : target) {
            heap.offer((long) t);
            sum += t;
        }

        while (heap.peek() != 1) {
            long max = heap.poll();
            long rest = sum - max;

            // rest must be positive and max must be the true maximum (max > rest),
            // otherwise this state is unreachable
            if (rest == 0 || max <= rest) {
                return false;
            }

            long prevMax;
            if (rest == 1) {
                // Can't take modulo by 1 meaningfully (would give 0); 
                // directly reduce max to 1 since only one element can exceed 1 at a time here
                prevMax = 1;
            } else {
                prevMax = max % rest;
                if (prevMax == 0) {
                    return false; // would produce a 0, which is invalid
                }
            }

            sum = rest + prevMax;
            heap.offer(prevMax);
        }

        return true;
    }
}