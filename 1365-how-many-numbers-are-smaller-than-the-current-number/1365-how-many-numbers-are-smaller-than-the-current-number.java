import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();

        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        }
        int count = 0;

        HashMap<Integer, Integer> smallerCount = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry :  freqMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            smallerCount.put(number, count);
            count += frequency;

        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = smallerCount.get(nums[i]);

        }
        return result;

    }
}