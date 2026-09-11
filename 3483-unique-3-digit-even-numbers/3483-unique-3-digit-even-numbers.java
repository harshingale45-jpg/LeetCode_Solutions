import java.util.HashSet;
import java.util.Set;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            // Last digit must be even
            if (digits[i] % 2 != 0) continue;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                for (int k = 0; k < n; k++) {
                    // First digit cannot be 0 and indices must be different
                    if (k == i || k == j || digits[k] == 0) continue;

                    int num = digits[k] * 100 + digits[j] * 10 + digits[i];
                    set.add(num);
                }
            }
        }

        return set.size();
    }
}