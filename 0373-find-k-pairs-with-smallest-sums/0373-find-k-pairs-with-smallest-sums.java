import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // min-heap storing {sum, i, j}
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Step 1: seed the heap with the smallest element of each row (j = 0)
        int rows = Math.min(k, nums1.length);
        for (int i = 0; i < rows; i++) {
            heap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Step 2: repeatedly pop the smallest sum, then push the next candidate in that row
        while (!heap.isEmpty() && result.size() < k) {
            int[] curr = heap.poll();
            int i = curr[1], j = curr[2];

            result.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                heap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }
}