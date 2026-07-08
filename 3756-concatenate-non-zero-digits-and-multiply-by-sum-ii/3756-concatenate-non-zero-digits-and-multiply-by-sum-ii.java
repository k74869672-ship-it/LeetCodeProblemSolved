class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long[] f = new long[n + 1]; // concatenation value of non-zero digits, mod M
        int[] cnt = new int[n + 1]; // count of non-zero digits so far
        long[] sumPre = new long[n + 1]; // prefix sum of all digits
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            sumPre[i] = sumPre[i - 1] + d;
            if (d != 0) {
                f[i] = (f[i - 1] * 10 + d) % MOD;
                cnt[i] = cnt[i - 1] + 1;
            } else {
                f[i] = f[i - 1];
                cnt[i] = cnt[i - 1];
            }
        }

        int q = queries.length;
        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int a = l; // prefix index before range
            int b = r + 1; // prefix index after range

            int diffCnt = cnt[b] - cnt[a];
            long x = (f[b] - (f[a] * pow10[diffCnt]) % MOD % MOD + MOD) % MOD;

            long sum = (sumPre[b] - sumPre[a]) % MOD;

            long ans = (x * sum) % MOD;
            answer[i] = (int) ans;
        }

        return answer;
    }
}
