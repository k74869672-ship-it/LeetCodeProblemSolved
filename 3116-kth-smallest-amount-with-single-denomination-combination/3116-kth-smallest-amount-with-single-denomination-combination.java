class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int size = 1 << n;
        long[] lcmSubset = new long[size];
        
        // Precompute LCM for every non-empty subset of coins
        for (int mask = 1; mask < size; mask++) {
            long l = 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    l = lcm(l, coins[i]);
                    // Cap to avoid overflow; if it exceeds our search range it's useless anyway
                    if (l > (long) 2e9 * 25) {
                        l = -1; // mark as "too large / invalid"
                        break;
                    }
                }
            }
            lcmSubset[mask] = l;
        }
        
        int minCoin = Integer.MAX_VALUE;
        for (int c : coins) minCoin = Math.min(minCoin, c);
        
        long lo = 1, hi = (long) k * minCoin; // upper bound: kth multiple of smallest coin
        
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countLE(mid, lcmSubset, n) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    // Count how many numbers <= x are achievable (inclusion-exclusion over subsets)
    private long countLE(long x, long[] lcmSubset, int n) {
        long count = 0;
        int size = 1 << n;
        for (int mask = 1; mask < size; mask++) {
            long l = lcmSubset[mask];
            if (l == -1 || l > x) continue;
            int bits = Integer.bitCount(mask);
            long term = x / l;
            if (bits % 2 == 1) {
                count += term;
            } else {
                count -= term;
            }
        }
        return count;
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}