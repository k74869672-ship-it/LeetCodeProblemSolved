class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sum1 = 0;
        int sum2 = 0;
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);
            if (c == '?')
                cnt1++;
            else
                sum1 += c - '0';
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?')
                cnt2++;
            else
                sum2 += c - '0';
        }

        int cnt = cnt1 + cnt2;
        int diff = sum1 - sum2;

        if (cnt % 2 == 1)
            return true;

        return diff != 9 * (cnt2 - cnt1) / 2;
    }
}