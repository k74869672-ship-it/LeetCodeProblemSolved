import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int j = 0; j <= n; j++) {
            int currentHeight = (j == n) ? 0 : heights[j];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int leftBoundry = stack.isEmpty() ? -1 : stack.peek();
                int width = j - leftBoundry - 1;

                maxArea = Math.max(maxArea, height * width);

            }
            stack.push(j);
        }
        return maxArea;

    }
}