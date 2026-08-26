class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String best = "";
        int bestLen = Integer.MAX_VALUE;
        
        int left = 0;
        int ones = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }
            
            // Shrink window from left while:
            // - it has more than k ones, OR
            // - it has exactly k ones but starts with a '0' (can be trimmed)
            while (ones > k || (ones == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }
            
            if (ones == k) {
                int curLen = right - left + 1;
                String curSub = s.substring(left, right + 1);
                
                if (curLen < bestLen) {
                    bestLen = curLen;
                    best = curSub;
                } else if (curLen == bestLen && curSub.compareTo(best) < 0) {
                    best = curSub;
                }
            }
        }
        
        return best;
    }
}