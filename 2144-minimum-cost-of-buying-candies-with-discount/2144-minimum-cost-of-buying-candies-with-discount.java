class Solution {
    public int minimumCost(int[] cost) {
        Arrays.sort(cost);
        int total = 0;

        for (int i = 0; i < cost.length; i++) {
            if ((i + 1) % 3 == 0) {
                continue;
            }
            total += cost[cost.length - 1 - i];
        }
        return total;
    }
}