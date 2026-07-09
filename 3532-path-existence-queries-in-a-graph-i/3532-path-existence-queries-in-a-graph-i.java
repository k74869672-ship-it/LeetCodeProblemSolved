class Solution {
    private int[] parent, rank_;

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];

        }
        return x;
    }

    private void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);
        if (rank_[rx] < rank_[ry]) {
            int t = rx;
            rx = ry;
            ry = t;
        }
        parent[ry] = rx;
        if (rank_[rx] == rank_[ry]) {
            rank_[rx]++;
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] <= maxDiff) {
                union(i - 1, i);
            }
        }

        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            answer[i] = find(u) == find(v);
        }
        return answer;
    }
}