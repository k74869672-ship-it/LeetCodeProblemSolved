class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
            queue.offer(i);
            while (!queue.isEmpty() && freq[s.charAt(queue.peek()) - 'a'] > 1) {
                queue.poll();

            }

        }
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.peek();
    }
}