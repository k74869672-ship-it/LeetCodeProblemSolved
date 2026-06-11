class Solution {
    public int assignEdgeWeights(int[][] edges) {
        int middle = 1000000007;
        int n=0;
        for (int[] edge : edges) {
            n = Math.max(n, Math.max(edge[0], edge[1]));
        }
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();

        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);

        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;
        int l = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int neighbour : adj[curr]) {
                    if (!visited[neighbour]) {
                        visited[neighbour] = true;
                        queue.offer(neighbour);
                    }
                }
            }
            l++;
        }
        long[][] dp = new long[l + 1][2];

        dp[0][0] = 1;
        dp[0][1] = 0;

        for (int i = 1; i <= l; i++) {

            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % middle;

            dp[i][1] = (dp[i - 1][1] + dp[i - 1][0]) % middle;
        }
        return (int) dp[l][1];
    }
}