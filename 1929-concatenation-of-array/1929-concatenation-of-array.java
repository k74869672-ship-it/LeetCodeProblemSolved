class Solution {
    public int[] getConcatenation(int[] nums) {
        int num = nums.length;
        int[] result = new int[2 * num];
        for (int i = 0; i < num; i++) {
            result[i] = nums[i];
            result[i + num] = nums[i];
        }
        return result;

    }
}