/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; // 0-indexed position for curr

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a local maxima or local minima
            boolean isLocalMaxima = curr.val > prev.val && curr.val > next.val;
            boolean isLocalMinima = curr.val < prev.val && curr.val < next.val;

            if (isLocalMaxima || isLocalMinima) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    // Update minimum distance between adjacent critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = next;
            currentIndex++;
        }

        // If fewer than 2 critical points were found
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}