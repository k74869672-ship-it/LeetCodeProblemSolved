class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        boolean hasOdd = false, hasEven = false;

        for (int x : nums1) {
            if (x % 2 == 0) {
                hasEven = true;
                minEven = Math.min(minEven, x);
            } else {
                hasOdd = true;
                minOdd = Math.min(minOdd, x);
            }
        }
        boolean targetEvenPossible = !hasOdd;

        boolean targetOddPossible = !hasEven || (hasOdd && minOdd < minEven);
        return targetEvenPossible || targetOddPossible;
    }
}