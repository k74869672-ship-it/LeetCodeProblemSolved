class Solution {
    public char processStr(String s, long k) {
        long[] lengths = new long[s.length()];
        long currentLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                currentLength++;
            } else if (ch == '*') {
                currentLength = Math.max(0, currentLength - 1);
            } else if (ch == '#') {
                currentLength *= 2;
            } else if (ch == '%') {
                // Reversal doesn't change the length
            }
            lengths[i] = currentLength;
        }
        if (k < 0 || k >= currentLength) {
            return '.';
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            long prevLength = (i > 0) ? lengths[i - 1] : 0;
            if (lengths[i] == 0) {
                continue;
            }
            if (Character.isLetter(ch)) {
                if (k == lengths[i] - 1) {
                    return ch;
                }
            } else if (ch == '#') {
                if (k >= prevLength) {
                    k %= prevLength;
                }

            }else if (ch == '%') {
                // Reverse maps index k to its mirrored index
                k = lengths[i] - 1 - k;
            }
             else if (ch == '*') {

            }
        }
        return '.';
    }
}
