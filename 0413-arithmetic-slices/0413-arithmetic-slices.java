class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int currentSlices = 0;
        int totalSlices = 0;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                currentSlices++;
                totalSlices += currentSlices;
            } else {
                currentSlices = 0;
            }
        }
        return totalSlices;

    }
}