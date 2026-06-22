class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];
        
        // 1. Poore text ke har character ki frequency count karo
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        
        // 2. Har letter se maximum kitne "balloon" ban sakte hain, wo nikaalo
        int maxB = counts['b' - 'a'];       // 'b' chahiye 1 baar
        int maxA = counts['a' - 'a'];       // 'a' chahiye 1 baar
        int maxL = counts['l' - 'a'] / 2;   // 'l' chahiye 2 baar (isliye / 2 kiya)
        int maxO = counts['o' - 'a'] / 2;   // 'o' chahiye 2 baar (isliye / 2 kiya)
        int maxN = counts['n' - 'a'];       // 'n' chahiye 1 baar
        
        // 3. Jo sabse kam (bottleneck) hoga, wahi hamara answer hoga
        int ans = Math.min(maxB, maxA);
        ans = Math.min(ans, maxL);
        ans = Math.min(ans, maxO);
        ans = Math.min(ans, maxN);
        
        return ans;
    }
}