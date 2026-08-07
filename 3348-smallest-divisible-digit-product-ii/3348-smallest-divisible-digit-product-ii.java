class Solution {
    // exponent of prime {2,3,5,7} contributed by digit d (index 0..9)
    static final int[] EXP2 = {0,0,1,0,2,0,1,0,3,0};
    static final int[] EXP3 = {0,0,0,1,0,0,1,0,0,2};
    static final int[] EXP5 = {0,0,0,0,0,1,0,0,0,0};
    static final int[] EXP7 = {0,0,0,0,0,0,0,1,0,0};

    // pairs (dx,dy) for digits contributing to exp2/exp3: 2,3,4,6,8,9
    static final int[][] PAIRS = {{1,0},{0,1},{2,0},{1,1},{3,0},{0,2}};

    private int a, b, c, d;
    private int[][] dp; // dp[i][j] = min digits to reach exp2>=i and exp3>=j

    public String smallestNumber(String num, long t) {
        long rem = t;
        a = 0; while (rem % 2 == 0) { rem /= 2; a++; }
        b = 0; while (rem % 3 == 0) { rem /= 3; b++; }
        c = 0; while (rem % 5 == 0) { rem /= 5; c++; }
        d = 0; while (rem % 7 == 0) { rem /= 7; d++; }
        if (rem != 1) return "-1";

        // build dp table
        dp = new int[a + 1][b + 1];
        dp[0][0] = 0;
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= b; j++) {
                if (i == 0 && j == 0) continue;
                int best = Integer.MAX_VALUE;
                for (int[] p : PAIRS) {
                    int ni = Math.max(i - p[0], 0);
                    int nj = Math.max(j - p[1], 0);
                    if (ni == i && nj == j) continue; // digit doesn't reduce the need here
                    int val = 1 + dp[ni][nj];
                    if (val < best) best = val;
                }
                dp[i][j] = best;
            }
        }

        int n = num.length();
        int[] pe2 = new int[n + 1], pe3 = new int[n + 1], pe5 = new int[n + 1], pe7 = new int[n + 1];
        int k = n; // first zero index, or n if none
        for (int idx = 0; idx < n; idx++) {
            int digit = num.charAt(idx) - '0';
            if (digit == 0 && k == n) k = idx;
            pe2[idx + 1] = Math.min(a, pe2[idx] + EXP2[digit]);
            pe3[idx + 1] = Math.min(b, pe3[idx] + EXP3[digit]);
            pe5[idx + 1] = Math.min(c, pe5[idx] + EXP5[digit]);
            pe7[idx + 1] = Math.min(d, pe7[idx] + EXP7[digit]);
        }

        if (k == n && pe2[n] >= a && pe3[n] >= b && pe5[n] >= c && pe7[n] >= d) return num;

        int ihigh = (k < n) ? k : n - 1;

        for (int i = ihigh; i >= 0; i--) {
            int remaining = n - 1 - i;
            int startV = (num.charAt(i) - '0') + 1;
            for (int v = startV; v <= 9; v++) {
                int t2 = Math.min(a, pe2[i] + EXP2[v]);
                int t3 = Math.min(b, pe3[i] + EXP3[v]);
                int t5 = Math.min(c, pe5[i] + EXP5[v]);
                int t7 = Math.min(d, pe7[i] + EXP7[v]);
                int ra = a - t2, rb = b - t3, rc = c - t5, rd = d - t7;
                if (dp[ra][rb] + rc + rd <= remaining) {
                    char[] res = new char[n];
                    for (int x = 0; x < i; x++) res[x] = num.charAt(x);
                    res[i] = (char) ('0' + v);
                    fillGreedy(res, i + 1, n, ra, rb, rc, rd);
                    return new String(res);
                }
            }
        }

        int M = dp[a][b] + c + d;
        int L = Math.max(n + 1, M);
        char[] res = new char[L];
        fillGreedy(res, 0, L, a, b, c, d);
        return new String(res);
    }

    private void fillGreedy(char[] res, int start, int end, int curA, int curB, int curC, int curD) {
        for (int pos = start; pos < end; pos++) {
            int slotsLeftAfter = end - 1 - pos;
            for (int dg = 1; dg <= 9; dg++) {
                int na = Math.max(curA - EXP2[dg], 0);
                int nb = Math.max(curB - EXP3[dg], 0);
                int nc = Math.max(curC - EXP5[dg], 0);
                int nd = Math.max(curD - EXP7[dg], 0);
                if (dp[na][nb] + nc + nd <= slotsLeftAfter) {
                    res[pos] = (char) ('0' + dg);
                    curA = na; curB = nb; curC = nc; curD = nd;
                    break;
                }
            }
        }
    }
}