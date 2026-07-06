import java.util.*;

class Solution {
    // LeetCode passes 3 arguments, NOT 4!
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        // Derive n from the online array length
        int n = online.length;

        // 1. Count out-degrees to allocate exact sizes for primitive arrays
        int[] outDegree = new int[n];
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        // 2. Build adjacency list using pure primitive arrays
        int[][] adjTo = new int[n][];
        int[][] adjCost = new int[n][];
        for (int i = 0; i < n; i++) {
            adjTo[i] = new int[outDegree[i]];
            adjCost[i] = new int[outDegree[i]];
        }

        int[] nextIdx = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            int index = nextIdx[u]++;
            adjTo[u][index] = v;
            adjCost[u][index] = cost;
        }

        // 3. Perform Topological Sort
        int[] topoOrder = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        
        int idx = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder[idx++] = u;
            for (int i = 0; i < adjTo[u].length; i++) {
                int v = adjTo[u][i];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // 4. Binary Search for the maximum path score
        int low = 0, high = 1000000000;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(n, adjTo, adjCost, topoOrder, online, k, mid)) {
                ans = mid; 
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean isValid(int n, int[][] adjTo, int[][] adjCost, int[] topoOrder, boolean[] online, long k, int minEdgeCost) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        // Process nodes in topological order
        for (int u : topoOrder) {
            if (dist[u] == Long.MAX_VALUE) continue;
            
            // If an intermediate node is offline, skip it
            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (int i = 0; i < adjTo[u].length; i++) {
                int v = adjTo[u][i];
                int cost = adjCost[u][i];

                if (cost >= minEdgeCost) {
                    if (dist[u] + cost < dist[v]) {
                        dist[v] = dist[u] + cost;
                    }
                }
            }
        }

        return dist[n - 1] <= k;
    }
}