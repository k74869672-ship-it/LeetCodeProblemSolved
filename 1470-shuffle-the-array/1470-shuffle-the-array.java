class Solution {
    public int[] shuffle(int[] nums, int n) {
        int num = nums.length;
        int[] result = new int[num];

        for (int i = 0; i < num; i++) {

            if (i % 2 == 0) {
                result[i] = nums[i / 2];

            } else {
                result[i] = nums[n + i / 2];
            }
        }
        return result;
    }
}