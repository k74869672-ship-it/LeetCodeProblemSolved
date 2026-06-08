class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int j = 0; j < n; j++) {

            while (!stack.isEmpty() && temperatures[j] > temperatures[stack.peek()]) {
                int i = stack.pop();
                answer[i] = j - i;

            }
            stack.push(j);
        }
        return answer;
    }
}