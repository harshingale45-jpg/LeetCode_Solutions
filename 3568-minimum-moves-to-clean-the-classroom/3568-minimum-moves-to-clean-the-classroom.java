import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int[][] idx = new int[m][n];
        int sr = 0, sc = 0, cnt = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    idx[i][j] = cnt++;
                }
            }
        }

        if (cnt == 0) return 0;

        int fullMask = (1 << cnt) - 1;

        // best[r][c][mask] = maximum remaining energy seen
        int[][][] best = new int[m][n][1 << cnt];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(best[i][j], -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0, energy}); // r,c,mask,energy
        best[sr][sc][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                if (mask == fullMask) return steps;

                if (best[r][c][mask] != e) continue;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;
                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    int ne = e - 1;
                    if (ne < 0) continue;

                    int nmask = mask;

                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'R') ne = energy;
                    else if (ch == 'L') nmask |= (1 << idx[nr][nc]);

                    if (ne <= best[nr][nc][nmask]) continue;

                    best[nr][nc][nmask] = ne;
                    q.offer(new int[]{nr, nc, nmask, ne});
                }
            }
            steps++;
        }

        return -1;
    }
}