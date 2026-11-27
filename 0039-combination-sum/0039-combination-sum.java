import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // lets us stop early
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int remaining, int start,
                           List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break; // sorted, so the rest are too big

            current.add(candidates[i]);                                   // choose
            backtrack(candidates, remaining - candidates[i], i, current, result); // explore (i, not i+1: reuse allowed)
            current.remove(current.size() - 1);                           // un-choose
        }
    }
}