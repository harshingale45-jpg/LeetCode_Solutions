class Solution {

    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        if (dfs(0, false, target, count, ans)) {
            return ans.toString();
        }

        return "";
    }

    private boolean dfs(int idx, boolean greater, String target,
                        int[] count, StringBuilder ans) {

        if (idx == target.length()) {
            return greater;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) continue;

            char c = (char) ('a' + i);

            if (!greater && c < target.charAt(idx))
                continue;

            count[i]--;
            ans.append(c);

            if (greater || c > target.charAt(idx)) {
                for (int j = 0; j < 26; j++) {
                    while (count[j] > 0) {
                        ans.append((char) ('a' + j));
                        count[j]--;
                    }
                }
                return true;
            }

            if (dfs(idx + 1, false, target, count, ans))
                return true;

            ans.deleteCharAt(ans.length() - 1);
            count[i]++;
        }

        return false;
    }
}