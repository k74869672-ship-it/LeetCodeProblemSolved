class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> repeated = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String currentWindows = s.substring(i, i + 10);
            if (seen.contains(currentWindows)) {
                repeated.add(currentWindows);
            } else {
                seen.add(currentWindows);
            }
        }
        return new ArrayList<>(repeated);
    }
}