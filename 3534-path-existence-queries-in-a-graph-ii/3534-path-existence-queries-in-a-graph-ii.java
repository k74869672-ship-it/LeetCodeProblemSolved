import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // ---- Step 1: sort node indices by their nums value ----
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> nums[a] - nums[b]);

        int[] sortedVal = new int[n];
        int[] pos = new int[n];           // pos[originalIndex] = position in sorted order
        for (int i = 0; i < n; i++) {
            sortedVal[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        // ---- Step 2: two-pointer -> R[i] = farthest sorted position reachable directly from i ----
        int[] R = new int[n];
        int right = 0;
        for (int i = 0; i < n; i++) {
            if (right < i) right = i;
            while (right + 1 < n && sortedVal[right + 1] - sortedVal[i] <= maxDiff) right++;
            R[i] = right;
        }

        // ---- Step 3: component ids (only need to check consecutive sorted neighbors) ----
        int[] comp = new int[n];
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            comp[i] = (sortedVal[i] - sortedVal[i - 1] <= maxDiff) ? comp[i - 1] : comp[i - 1] + 1;
        }

        // ---- Step 4: binary lifting on R for O(log n) hop-counting ----
        int LOG = 1;
        while ((1 << LOG) < n) LOG++;
        LOG++; // small safety margin

        int[][] up = new int[LOG][n];
        up[0] = R;
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        // ---- Step 5: answer each query ----
        int q = queries.length;
        int[] ans = new int[q];
        for (int idx = 0; idx < q; idx++) {
            int u = queries[idx][0], v = queries[idx][1];
            int pu = pos[u], pv = pos[v];

            if (pu == pv) { ans[idx] = 0; continue; }

            int p = Math.min(pu, pv), target = Math.max(pu, pv);

            if (comp[p] != comp[target]) { ans[idx] = -1; continue; }

            int cur = p, dist = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < target) {
                    dist += (1 << k);
                    cur = up[k][cur];
                }
            }
            ans[idx] = dist + 1; // one more hop reaches target, since target lies in cur's window
        }

        return ans;
    }
}