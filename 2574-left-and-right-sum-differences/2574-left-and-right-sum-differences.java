class Solution {
    public int[] leftRightDifference(int[] nums) {
        int num = nums.length;

        int[] leftSum = new int[num];
        leftSum[0] = 0;
        for (int i = 1; i < num; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];

        }

        int[] rightSum = new int[num];
        rightSum[num - 1] = 0;
        for (int i = num - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];

        }

        int[] answer = new int[num];
        for (int i = 0; i < num; i++) {
            answer[i] = Math.abs(leftSum[i] - rightSum[i]);

        }
        return answer;
    }
}