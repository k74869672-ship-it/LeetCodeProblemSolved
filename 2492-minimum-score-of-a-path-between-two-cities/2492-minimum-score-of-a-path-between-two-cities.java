import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build adjacency list: each entry stores [neighbor, weight]
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int a = road[0], b = road[1], dist = road[2];
            graph.get(a).add(new int[]{b, dist});
            graph.get(b).add(new int[]{a, dist});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        int minScore = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int[] edge : graph.get(curr)) {
                int neighbor = edge[0];
                int weight = edge[1];

                // Every edge reachable from city 1 is a candidate,
                // regardless of whether neighbor was visited before
                minScore = Math.min(minScore, weight);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return minScore;
    }
}