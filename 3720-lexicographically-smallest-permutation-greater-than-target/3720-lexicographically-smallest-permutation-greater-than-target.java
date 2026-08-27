class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = target.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int[] cur = cnt.clone();

        int bestI = -1;
        int bestChar = -1;
        int[] bestCnt = null;

        for (int i = 0; i < n; i++) {
            int t = target.charAt(i) - 'a';

            // Find smallest available letter strictly greater than t
            for (int c = t + 1; c < 26; c++) {
                if (cur[c] > 0) {
                    bestI = i;
                    bestChar = c;
                    bestCnt = cur.clone();
                    bestCnt[c]--;
                    break;
                }
            }

            // Try to extend exact match with target
            if (cur[t] > 0) {
                cur[t]--;
            } else {
                break; // can't match target further, no point continuing
            }
        }

        if (bestI == -1) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, bestI); // matched prefix
        sb.append((char) ('a' + bestChar)); // the greater character

        // Fill the rest with remaining letters in ascending order
        for (int c = 0; c < 26; c++) {
            for (int k = 0; k < bestCnt[c]; k++) {
                sb.append((char) ('a' + c));
            }
        }

        return sb.toString();
    }
}