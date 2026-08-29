import java.util.*;

class Solution {

    int n;
    long[] arr;
    long[][] table;
    int[] log2;
    long[] P, S;

    public int maxValidSplits(int[] nums) {
        n = nums.length;
        arr = new long[n];
        // store the input midway in the function
        long[] inputCopy = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            inputCopy[i] = nums[i];
        }

        buildSparseTable();

        P = new long[n];
        S = new long[n];
        P[0] = arr[0];
        for (int i = 1; i < n; i++)
            P[i] = gcd(P[i - 1], arr[i]);
        S[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--)
            S[i] = gcd(S[i + 1], arr[i]);

        List<long[]> Pseg = buildSegments(P);
        List<long[]> Sseg = buildSegments(S);

        long best = countNoRemoval(Pseg, Sseg);

        for (int k = 0; k < n; k++) {
            long score = computeForRemoval(k, Pseg, Sseg);
            best = Math.max(best, score);
        }
        return (int) best;
    }

    private long countNoRemoval(List<long[]> Pseg, List<long[]> Sseg) {
        if (n < 2) {
            return 0;
        }
        List<long[]> PsegClipped = clip(Pseg, 0, n - 2);
        List<long[]> SsegShifted = shiftClip(Sseg, -1, 0, n - 2);
        return countEqualOverlap(PsegClipped, SsegShifted);
    }

    private long computeForRemoval(int k, List<long[]> Pseg, List<long[]> Sseg) {
        long score = 0;

        if (k >= 1 && k <= n - 2) {
            long firstPart = P[k - 1];
            long secondPart = S[k + 1];
            if (firstPart == secondPart)
                score++;
        }
        if (k + 1 <= n - 2) {
            long initVal = (k >= 1) ? P[k - 1] : 0;
            List<long[]> segsFirst = prefixBreakpoints(k + 1, n - 2, initVal);
            List<long[]> segsSecond = shiftClip(Sseg, -1, k + 1, n - 2);
            score += countEqualOverlap(segsFirst, segsSecond);
        }
        if (k - 2 >= 0) {
            long Tk = query(k + 1, n - 1);
            List<long[]> segsG = suffixBreakpoints(1, k - 1, Tk);
            List<long[]> segsSecond2 = shiftClip(segsG, -1, 0, k - 2);
            List<long[]> segsFirst2 = clip(Pseg, 0, k - 2);
            score += countEqualOverlap(segsFirst2, segsSecond2);
        }

        return score;
    }

    private List<long[]> prefixBreakpoints(int lo, int hi, long initVal) {
        List<long[]> res = new ArrayList<>();
        if (lo > hi) {
            return res;
        }
        int cur = lo;
        while (cur <= hi) {
            long curVal = gcd(initVal, query(lo, cur));
            int l2 = cur, h2 = hi, r = cur;
            while (l2 <= h2) {
                int mid = (l2 + h2) / 2;
                long v = gcd(initVal, query(lo, mid));
                if (v == curVal) {
                    r = mid;
                    l2 = mid + 1;
                } else {
                    h2 = mid - 1;
                }
            }
            res.add(new long[] { cur, r, curVal });
            cur = r + 1;
        }
        return res;
    }

    private List<long[]> suffixBreakpoints(int lo, int hi, long initVal) {
        List<long[]> res = new ArrayList<>();
        if (lo > hi)
            return res;
        int cur = hi;
        while (cur >= lo) {
            long curVal = gcd(initVal, query(cur, hi));
            int l2 = lo, h2 = cur, l0 = cur;
            while (l2 <= h2) {
                int mid = (l2 + h2) / 2;
                long v = gcd(initVal, query(mid, hi));
                if (v == curVal) {
                    l0 = mid;
                    h2 = mid - 1;
                } else {
                    l2 = mid + 1;
                }
            }
            res.add(new long[] { l0, cur, curVal });
            cur = l0 - 1;
        }
        return res;
    }

    private List<long[]> clip(List<long[]> segs, int lo, int hi) {
        List<long[]> res = new ArrayList<>();
        for (long[] s : segs) {
            long l = Math.max(s[0], lo), r = Math.min(s[1], hi);
            if (l <= r) {
                res.add(new long[] { l, r, s[2] });
            }
        }
        return res;
    }

    private List<long[]> shiftClip(List<long[]> segs, int shift, int lo, int hi) {
        List<long[]> res = new ArrayList<>();
        for (long[] s : segs) {
            long l = s[0] + shift, r = s[1] + shift;
            l = Math.max(l, lo);
            r = Math.min(r, hi);
            if (l <= r)
                res.add(new long[] { l, r, s[2] });
        }
        return res;
    }

    private List<long[]> buildSegments(long[] a) {
        List<long[]> res = new ArrayList<>();
        int i = 0;
        while (i < a.length) {
            int j = i;
            while (j + 1 < a.length && a[j + 1] == a[i]) {
                j++;
            }
            res.add(new long[] { i, j, a[i] });
            i = j + 1;
        }
        return res;
    }

    private long countEqualOverlap(List<long[]> A, List<long[]> B) {
        A.sort((x, y) -> Long.compare(x[0], y[0]));
        B.sort((x, y) -> Long.compare(x[0], y[0]));
        long total = 0;
        int i = 0;
        int j = 0;
        while (i < A.size() && j < B.size()) {
            long[] a = A.get(i), b = B.get(j);
            long lo = Math.max(a[0], b[0]), hi = Math.min(a[1], b[1]);
            if (lo <= hi && a[2] == b[2])
                total += hi - lo + 1;
            if (a[1] < b[1])
                i++;
            else
                j++;
        }
        return total;
    }

    private void buildSparseTable() {
        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }
        int K = log2[n] + 1;
        table = new long[K][n];
        table[0] = arr.clone();
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                table[j][i] = gcd(table[j - 1][i], table[j - 1][i + (1 << (j - 1))]);
            }
        }
    }

    private long query(int l, int r) {
        if (l > r) {
            return 0;
        }
        int len = r - l + 1;
        int k = log2[len];
        return gcd(table[k][l], table[k][r - (1 << k) + 1]);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}