class Solution {
    public int[] sortedSquares(int[] nums) {
        int  n=nums.length;
        int start=0;
        int end=n-1;
        int[] arr=new int[n];
        int p=n-1;
        while (start<=end) {
            int startSqr=nums[start]*nums[start];
                int endSqr=nums[end]*nums[end];
                if (startSqr>endSqr) {
                    arr[p]=startSqr;
                    start++;
                    p--;
                }else{
                    arr[p]=endSqr;
                    end--;
                    p--;
                }
            
        }
        return arr;
        
    }
}