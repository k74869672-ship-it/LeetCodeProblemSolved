class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        while (true) {

            slow = squareSumNumber(slow);
            fast = squareSumNumber(squareSumNumber(fast));
            if (fast == 1) {
                return true;
            }
            if (slow == fast) {
                return false;
            }
        }

    }

    private int squareSumNumber(int n) {
        int output = 0;

        while (n > 0) {
            int digit = n % 10;
            output += digit * digit;
            n = n / 10;
        }

        return output;
    }
}