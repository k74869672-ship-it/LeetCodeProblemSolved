class Solution {
    private static final int MOD = 1000000007;

    public int zigZagArrays(int n, int l, int r) {
        int k = r - l + 1;
        if (n <= 1) return 0;

        // Size of our transition matrix
        int size = 2 * k;

        // 1. Initialize the base vector for n = 2
        // Indices 0 to k-1 will represent DOWN (dir = 0)
        // Indices k to 2k-1 will represent UP (dir = 1)
        long[] base = new long[size];
        for (int v = 1; v <= k; v++) {
            base[v - 1] = k - v;       // DOWN ways: values strictly greater than v
            base[k + v - 1] = v - 1;   // UP ways: values strictly smaller than v
        }

        // If n == 2, just sum up the base cases (though constraint says n >= 3)
        if (n == 2) {
            long total = 0;
            for (long val : base) total = (total + val) % MOD;
            return (int) total;
        }

        // 2. Build the transition matrix M
        long[][] M = new long[size][size];
        for (int v = 1; v <= k; v++) {
            // Target state: UP at v (index: k + v - 1)
            // It transitions from previous states that were DOWN (index: u - 1) where u < v
            for (int u = 1; u < v; u++) {
                M[k + v - 1][u - 1] = 1;
            }

            // Target state: DOWN at v (index: v - 1)
            // It transitions from previous states that were UP (index: k + u - 1) where u > v
            for (int u = v + 1; u <= k; u++) {
                M[v - 1][k + u - 1] = 1;
            }
        }

        // 3. Compute M^(n - 2) using binary exponentiation
        long[][] TransitionMatrix = matrixPower(M, n - 2, size);

        // 4. Multiply the transition matrix by the base vector to get final states
        long totalSum = 0;
        for (int i = 0; i < size; i++) {
            long finalStateValue = 0;
            for (int j = 0; j < size; j++) {
                finalStateValue = (finalStateValue + TransitionMatrix[i][j] * base[j]) % MOD;
            }
            totalSum = (totalSum + finalStateValue) % MOD;
        }

        return (int) totalSum;
    }

    // Helper method for Matrix Multiplication
    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Helper method for Matrix Exponentiation
    private long[][] matrixPower(long[][] base, int exp, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) res[i][i] = 1; // Identity Matrix

        long[][] m = base;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, m, size);
            }
            m = multiply(m, m, size);
            exp >>= 1;
        }
        return res;
    }
}