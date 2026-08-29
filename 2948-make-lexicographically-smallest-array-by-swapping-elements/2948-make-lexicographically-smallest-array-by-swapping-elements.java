class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        //pair (value,originalindex)
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; //valur
            arr[i][1] = i; //original index

        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            List<Integer> groupIndices = new ArrayList<>();
            List<Integer> groupValues = new ArrayList<>();

            groupIndices.add(arr[i][1]);
            groupValues.add(arr[i][0]);

            int j = i + 1;
            // Step 3: extend group while adjacent difference <= limit
            while (j < n && arr[j][0] - arr[j - 1][0] <= limit) {
                groupIndices.add(arr[j][1]);
                groupValues.add(arr[j][0]);
                j++;
            }
            Collections.sort(groupIndices);
            for (int k = 0; k < groupIndices.size(); k++) {
                result[groupIndices.get(k)] = groupValues.get(k);
            }
            i = j;
        }
        return result;
    }
}