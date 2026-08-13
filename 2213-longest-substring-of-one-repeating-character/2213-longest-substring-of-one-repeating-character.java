class Solution {
    // Segment tree node fields, stored as parallel arrays for speed
    private char[] firstChar, lastChar;
    private int[] pre, suf, best, segLen;
    private char[] s;
    private int n;

    public int[] longestRepeating(String sInput, String queryCharacters, int[] queryIndices) {
        s = sInput.toCharArray();
        n = s.length;
        int size = 4 * n;

        firstChar = new char[size];
        lastChar  = new char[size];
        pre  = new int[size];
        suf  = new int[size];
        best = new int[size];
        segLen = new int[size];

        build(1, 0, n - 1);

        int k = queryCharacters.length();
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char newChar = queryCharacters.charAt(i);
            s[idx] = newChar;
            update(1, 0, n - 1, idx, newChar);
            result[i] = best[1]; // root holds the answer for the whole string
        }

        return result;
    }

    // Build segment tree for range [l, r] at node position `node`
    private void build(int node, int l, int r) {
        if (l == r) {
            firstChar[node] = s[l];
            lastChar[node]  = s[l];
            pre[node] = 1;
            suf[node] = 1;
            best[node] = 1;
            segLen[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        int left = 2 * node, right = 2 * node + 1;
        build(left, l, mid);
        build(right, mid + 1, r);
        merge(node, left, right);
    }

    // Update index `idx` to character `c`
    private void update(int node, int l, int r, int idx, char c) {
        if (l == r) {
            firstChar[node] = c;
            lastChar[node]  = c;
            // pre, suf, best, segLen remain 1
            return;
        }
        int mid = (l + r) / 2;
        int left = 2 * node, right = 2 * node + 1;
        if (idx <= mid) {
            update(left, l, mid, idx, c);
        } else {
            update(right, mid + 1, r, idx, c);
        }
        merge(node, left, right);
    }

    // Combine children `left` and `right` into `node`
    private void merge(int node, int left, int right) {
        firstChar[node] = firstChar[left];
        lastChar[node]  = lastChar[right];
        segLen[node] = segLen[left] + segLen[right];

        // Prefix: normally left's prefix, unless left is fully uniform
        // and matches right's first character, in which case it extends.
        if (pre[left] == segLen[left] && lastChar[left] == firstChar[right]) {
            pre[node] = pre[left] + pre[right];
        } else {
            pre[node] = pre[left];
        }

        // Suffix: normally right's suffix, unless right is fully uniform
        // and matches left's last character, in which case it extends.
        if (suf[right] == segLen[right] && lastChar[left] == firstChar[right]) {
            suf[node] = suf[right] + suf[left];
        } else {
            suf[node] = suf[right];
        }

        // Best: max of left's best, right's best, and the bridge run
        // that forms if left's suffix character equals right's prefix character.
        best[node] = Math.max(best[left], best[right]);
        if (lastChar[left] == firstChar[right]) {
            best[node] = Math.max(best[node], suf[left] + pre[right]);
        }
    }
}