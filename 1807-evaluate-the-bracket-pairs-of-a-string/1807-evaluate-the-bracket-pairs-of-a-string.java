class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
            } else {
                i++; // skip '('
                StringBuilder key = new StringBuilder();

                while (s.charAt(i) != ')') {
                    key.append(s.charAt(i));
                    i++;
                }

                ans.append(map.getOrDefault(key.toString(), "?"));
                i++; // skip ')'
            }
        }

        return ans.toString();
    }
}