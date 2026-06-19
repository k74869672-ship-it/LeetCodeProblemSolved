class Solution {
    public double angleClock(int hour, int minutes) {
        // 1. Calculate positions relative to 12 o'clock (0 degrees)
        double minuteAngle = minutes * 6;
        double hourAngle = (hour % 12 * 30) + (minutes * 0.5);
        
        // 2. Find the absolute difference between the two angles
        double angle = Math.abs(hourAngle - minuteAngle);
        
        // 3. Return the smaller angle
        if (angle > 180) {
            angle = 360 - angle;
        }
        
        return angle;
    }
}