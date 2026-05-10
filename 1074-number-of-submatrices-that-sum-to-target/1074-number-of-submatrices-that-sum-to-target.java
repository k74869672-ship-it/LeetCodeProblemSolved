class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                matrix[i][j] += matrix[i][j-1];
            }
        }
        int count = 0;
        for (int col1 = 0; col1 < cols; col1++) {
            for (int col2 = col1; col2 < cols; col2++) {
                Map<Integer,Integer> map = new HashMap<>();
                map.put(0, 1);
                int sum = 0;

                for (int row = 0; row < rows; row++) {
                    sum += matrix[row][col2] - (col1 > 0 ? matrix[row][col1 - 1] : 0);
                    count += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return count;
    }
}