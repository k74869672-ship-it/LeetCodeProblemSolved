class Solution {
    public boolean detectCapitalUse(String word) {
        int n = word.length();
        int capitalCount = 0;
        
        for (char c : word.toCharArray()) {
            if (Character.isUpperCase(c)) {
                capitalCount++;
            }
        }
        
        // Case 1: All capitals
        if (capitalCount == n) return true;
        
        // Case 2: No capitals
        if (capitalCount == 0) return true;
        
        // Case 3: Only first letter capital, rest lowercase
        if (capitalCount == 1 && Character.isUpperCase(word.charAt(0))) return true;
        
        return false;
    }
}