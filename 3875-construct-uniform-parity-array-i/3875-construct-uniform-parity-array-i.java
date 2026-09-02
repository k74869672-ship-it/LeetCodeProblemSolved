class Solution {
    public boolean uniformArray(int[] nums1) {
        int n = nums1.length;
        int pivotIndex = -1;

        for (int i = 0; i < n; i++) {
            if (nums1[i] % 2 != 0) {
                pivotIndex = i;
                break;
            }
        }
        int[] nums2 = new int[n];

        if (pivotIndex == -1) {
            nums2 = nums1.clone();

        } else {
            for (int i = 0; i < n; i++) {
                if (nums1[i] % 2 != 0) {
                    nums2[i] = nums1[i];
                } else {
                    nums2[i] = nums1[i] - nums1[pivotIndex];
                }
            }
        }
        return true;
    }
}