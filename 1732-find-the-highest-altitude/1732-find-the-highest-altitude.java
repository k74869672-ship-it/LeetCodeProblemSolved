class Solution {
    public int largestAltitude(int[] gain) {
      int currentAltitude = 0;
        int maxAltitude = 0;
        
        // Loop through each net change in altitude
        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i];
            
            // Check if we have reached a new peak altitude
            if (currentAltitude > maxAltitude) {
                maxAltitude = currentAltitude;
            }
        }
        
        return maxAltitude;  
    }
}