class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) {
            maxHeap.offer(s);
        }
        while (maxHeap.size()>1) {
            int y = maxHeap.poll(); //largest
            int x = maxHeap.poll(); //second Largest
            if (x != y) {
                maxHeap.offer(y - x);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}