class Solution {
    public long sumAndMultiply(int n) {
        if (n == 0) {
            return 0;
        }

        String s = Integer.toString(n);
        StringBuilder xBuilder = new StringBuilder();
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int digit = c - '0';

            if (digit != 0) {
                xBuilder.append(c);
                sum += digit;

            }
        }

        long x = xBuilder.length() == 0 ? 0 : Long.parseLong(xBuilder.toString());

        return x * sum;
    }
}