import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] ans = new int[n];
        int i = 0;

        while (i < n) {
            int j = i;

            while (j + 1 < n && arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            for (int k = i; k <= j; k++) {
                indices.add(arr[k][1]);
                values.add(arr[k][0]);
            }

            Collections.sort(indices);

            for (int k = 0; k < indices.size(); k++) {
                ans[indices.get(k)] = values.get(k);
            }

            i = j + 1;
        }

        return ans;
    }
}