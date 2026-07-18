class Solution {
    public String licenseKeyFormatting(String s, int k) {
        // Remove all dashes and convert to uppercase
        StringBuilder cleaned = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != '-') {
                cleaned.append(Character.toUpperCase(c));
            }
        }
        
        int n = cleaned.length();
        if (n == 0) return "";
        
        StringBuilder result = new StringBuilder();
        
        // Length of first group
        int firstGroupLen = n % k;
        if (firstGroupLen == 0) {
            firstGroupLen = k;
        }
        
        int index = 0;
        result.append(cleaned, index, index + firstGroupLen);
        index += firstGroupLen;
        
        // Remaining groups, each of length k
        while (index < n) {
            result.append('-');
            result.append(cleaned, index, index + k);
            index += k;
        }
        
        return result.toString();
    }
}