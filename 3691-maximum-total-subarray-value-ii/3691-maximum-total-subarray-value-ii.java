import java.util.*;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            st.insert(i, nums[i]);
        }
        
        // Max-heap stores arrays of format: [value, left_index, right_index]
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(b[0], a[0]));
        
        int[] initialQuery = st.query(0, n - 1);
        long initialValue = (long) initialQuery[1] - initialQuery[0];
        pq.offer(new long[]{initialValue, 0, n - 1});
        
        // Use a hash set to prevent processing the same sub-range multiple times
        Set<Long> visited = new HashSet<>();
        // Pack l and r into a single long to save memory and speed up hashing
        visited.add(((long) 0 << 32) | (n - 1));
        
        long totalMaxSum = 0;
        
        while (k > 0 && !pq.isEmpty()) {
            long[] curr = pq.poll();
            long val = curr[0];
            int l = (int) curr[1];
            int r = (int) curr[2];
            
            totalMaxSum += val;
            k--;
            
            // Candidate 1: Shrink from the left [l + 1, r]
            if (l + 1 <= r) {
                long nextKey = ((long) (l + 1) << 32) | r;
                if (!visited.contains(nextKey)) {
                    visited.add(nextKey);
                    int[] nextQuery = st.query(l + 1, r);
                    pq.offer(new long[]{(long) nextQuery[1] - nextQuery[0], l + 1, r});
                }
            }
            
            // Candidate 2: Shrink from the right [l, r - 1]
            if (l <= r - 1) {
                long nextKey = ((long) l << 32) | (r - 1);
                if (!visited.contains(nextKey)) {
                    visited.add(nextKey);
                    int[] nextQuery = st.query(l, r - 1);
                    pq.offer(new long[]{(long) nextQuery[1] - nextQuery[0], l, r - 1});
                }
            }
        }
        
        return totalMaxSum;
    }
    
    // Segment Tree implementation for Range Minimum & Maximum Queries
    class SegmentTree {
        int n;
        int[] maxTree;
        int[] minTree;
        
        SegmentTree(int n) {
            this.n = n;
            maxTree = new int[4 * n];
            minTree = new int[4 * n];
        }
        
        void insert(int idx, int val) {
            insert(1, 0, n - 1, idx, val);
        }
        
        private void insert(int node, int start, int end, int idx, int val) {
            if (start == end) {
                maxTree[node] = val;
                minTree[node] = val;
                return;
            }
            int mid = start + (end - start) / 2;
            if (idx <= mid) {
                insert(2 * node, start, mid, idx, val);
            } else {
                insert(2 * node + 1, mid + 1, end, idx, val);
            }
            maxTree[node] = Math.max(maxTree[2 * node], maxTree[2 * node + 1]);
            minTree[node] = Math.min(minTree[2 * node], minTree[2 * node + 1]);
        }
        
        int[] query(int l, int r) {
            return query(1, 0, n - 1, l, r);
        }
        
        private int[] query(int node, int start, int end, int l, int r) {
            if (r < start || l > end) {
                return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
            }
            if (l <= start && end <= r) {
                return new int[]{minTree[node], maxTree[node]};
            }
            int mid = start + (end - start) / 2;
            int[] leftResult = query(2 * node, start, mid, l, r);
            int[] rightResult = query(2 * node + 1, mid + 1, end, l, r);
            
            return new int[]{
                Math.min(leftResult[0], rightResult[0]),
                Math.max(leftResult[1], rightResult[1])
            };
        }
    }
}