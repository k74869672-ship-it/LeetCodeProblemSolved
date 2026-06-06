import java.util.*;

class Solution {
    // Phone digit to letters mapping
    String[] phoneMap = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return result;
        }
        backTrack(digits, 0, new StringBuilder());
        return result;
    }

    private void backTrack(String digits, int index, StringBuilder current) {

        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = phoneMap[digits.charAt(index) - '0'];

        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backTrack(digits, index + 1, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}