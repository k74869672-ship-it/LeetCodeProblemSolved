class Solution {
    public String maskPII(String s) {
        // Check if it's an email (contains '@')
        if (s.indexOf('@') != -1) {
            return maskEmail(s);
        } else {
            return maskPhone(s);
        }
    }
    
    private String maskEmail(String s) {
        // Split into name and domain
        int atIndex = s.indexOf('@');
        String name = s.substring(0, atIndex).toLowerCase();
        String domain = s.substring(atIndex + 1).toLowerCase();
        
        // First letter + 5 asterisks + last letter
        String maskedName = name.charAt(0) + "*****" + name.charAt(name.length() - 1);
        
        return maskedName + "@" + domain;
    }
    
    private String maskPhone(String s) {
        // Extract only digits
        StringBuilder digitsOnly = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitsOnly.append(c);
            }
        }
        
        int n = digitsOnly.length();
        int countryCodeLen = n - 10;
        
        // Last 4 digits
        String lastFour = digitsOnly.substring(n - 4);
        String local = "***-***-" + lastFour;
        
        if (countryCodeLen == 0) {
            return local;
        } else {
            StringBuilder countryCode = new StringBuilder("+");
            for (int i = 0; i < countryCodeLen; i++) {
                countryCode.append('*');
            }
            return countryCode + "-" + local;
        }
    }
}