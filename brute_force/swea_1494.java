import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] nodes = new int[20][2];
    static long sumX, sumY;
    static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            N = Integer.parseInt(br.readLine());
            sumX = sumY = 0;
            for (int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                nodes[i][0] = x;
                nodes[i][1] = y;
                sumX += x;
                sumY += y;
            }

            result = Long.MAX_VALUE;

            // 0번 점은 항상 선택(+)으로 고정하여 탐색 절반으로 단축
            dfs(1, 1, nodes[0][0], nodes[0][1]);

            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.print(sb);

    }

    // idx: 현재  인덱스, picked: 지금까지 선택(+)한 개수
    // sx, sy: 선택(+)한 점들의 부분합
    static void dfs(int idx, int picked, long sx, long sy) {
        // 필요한 개수 다 뽑았으면 거리 계산
        if (picked == N / 2) {
            long vx = sumX - 2 * sx;
            long vy = sumY - 2 * sy;
            long dist2 = vx * vx + vy * vy;
            if (dist2 < result) result = dist2;
            return;
        }
        // 끝까지 왔는데 덜 뽑은 경우
        if (idx == N) return;

        // 남은 요소로 목표 개수(N/2)를 채울 수 없는 경우 중단
        int remain = N - idx;
        if (picked + remain < N / 2) return;

        // 1) idx를 선택(+)하는 가지
        dfs(idx + 1, picked + 1, sx + nodes[idx][0], sy + nodes[idx][1]);

        // 2) idx를 선택하지 않는 가지
        dfs(idx + 1, picked, sx, sy);
    }
}
