class Solution {
    private int[] parent, size;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        // For each component root, track number of vertices and number of edges
        Map<Integer, Integer> vertexCount = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = find(i);
            vertexCount.put(root, vertexCount.getOrDefault(root, 0) + 1);
        }

        for (int[] e : edges) {
            int root = find(e[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        int count = 0;
        for (int root : vertexCount.keySet()) {
            int v = vertexCount.get(root);
            int e = edgeCount.getOrDefault(root, 0);
            if (e == (long) v * (v - 1) / 2) {
                count++;
            }
        }

        return count;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        if (size[rootX] < size[rootY]) {
            int temp = rootX;
            rootX = rootY;
            rootY = temp;
        }
        parent[rootY] = rootX;
        size[rootX] += size[rootY];
    }
}