import java.util.*;

class Solution {

    static class Interval {
        int left, right, weight, idx;

        Interval(int l, int r, int w, int i) {
            left = l;
            right = r;
            weight = w;
            idx = i;
        }
    }

    static class State {
        long weight;
        List<Integer> selected;

        State(long w, List<Integer> s) {
            weight = w;
            selected = s;
        }
    }

    private State[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        List<Interval> arr = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            List<Integer> x = intervals.get(i);
            arr.add(new Interval(x.get(0), x.get(1), x.get(2), i));
        }

        arr.sort(Comparator.comparingInt(a -> a.left));

        memo = new State[arr.size()][5];

        List<Integer> ans = dp(arr, 0, 4).selected;
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private State dp(List<Interval> arr, int i, int quota) {
        if (i == arr.size() || quota == 0)
            return new State(0, new ArrayList<>());

        if (memo[i][quota] != null)
            return memo[i][quota];

        State skip = dp(arr, i + 1, quota);

        Interval cur = arr.get(i);
        int next = firstGreater(arr, cur.right);

        State nxt = dp(arr, next, quota - 1);

        List<Integer> picked = new ArrayList<>(nxt.selected);
        picked.add(cur.idx);
        Collections.sort(picked);

        State take = new State(cur.weight + nxt.weight, picked);

        if (take.weight > skip.weight)
            return memo[i][quota] = take;

        if (take.weight < skip.weight)
            return memo[i][quota] = skip;

        return memo[i][quota] =
                compare(take.selected, skip.selected) < 0 ? take : skip;
    }

    private int firstGreater(List<Interval> arr, int right) {
        int l = 0, r = arr.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (arr.get(m).left > right)
                r = m;
            else
                l = m + 1;
        }
        return l;
    }

    private int compare(List<Integer> a, List<Integer> b) {
        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }
        return Integer.compare(a.size(), b.size());
    }
}