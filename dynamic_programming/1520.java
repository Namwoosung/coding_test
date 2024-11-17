//아래에 2가지 버전의 풀이 있음 => 풀고 참고하면 굉장히 좋을 듯
public class Main {
    public static void main(String[] args) {
        
    }
}








/*
DP를 bottom up 방식으로 구현
모든 좌표를 높이순으로 정렬하고, 가장 높은 곳부터 dp를 더해나감
(0,0)이 아닌데 (0,0)보다 큰 곳은 어짜피 dp[x][y]가 0이니 의미없음
=> (0,0)인 곳의 높이부터 사방 탐색을 통해 자신보다 낮은 곳의 dp를 더함
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] board = new int[m][n];
        int[][] dp = new int[m][n]; // DP 배열: 각 위치까지의 경로 수

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 초기화
        dp[0][0] = 1; // 시작 지점은 경로 수가 1

        // 이동 방향
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 내림차순으로 모든 좌표를 정렬 (높이가 높은 순서대로)
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                points.add(new Point(i, j, board[i][j]));
            }
        }

        points.sort((a, b) -> b.height - a.height); // 높이를 기준으로 내림차순 정렬

        // Bottom-Up 방식으로 DP 계산
        for (Point point : points) {
            int x = point.x;
            int y = point.y;

            for (int i = 0; i < 4; i++) {
                int xx = x + dx[i];
                int yy = y + dy[i];

                // 유효한 범위이고, board[xx][yy] < board[x][y]인 경우
                if (xx >= 0 && xx < m && yy >= 0 && yy < n && board[xx][yy] < board[x][y]) {
                    dp[xx][yy] += dp[x][y];
                }
            }
        }

        // 도착 지점의 경로 수 출력
        System.out.println(dp[m - 1][n - 1]);

        br.close();
    }

    private static class Point {
        int x, y, height;

        Point(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }
}
*/

/*
DB를 top down방식으로 구현
0,0을 기준으로 함수를 호출하고, 가능한 경로마다 dfs로 호출
=> m-1,n-1에 도달한 경우에 1을 return
=> 모든 가능한 경로는 1이 더해지고 최종적으로 0,0에 가능한 경로가 저장됨
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] board = new int[m][n];
        int[][] dp = new int[m][n]; // 경로 수를 저장하는 DP 배열
        boolean[][] visited = new boolean[m][n]; // 방문 여부 확인

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // DP 배열 초기화 (아직 경로 계산 안 함)
            }
        }

        System.out.println(dfs(board, dp, visited, 0, 0));

        br.close();
    }

    private static int dfs(int[][] board, int[][] dp, boolean[][] visited, int x, int y) {
        int m = board.length;
        int n = board[0].length;

        // 목표 지점에 도달하면 경로 수는 1
        if (x == m - 1 && y == n - 1) {
            return 1;
        }

        // 이미 계산된 경로 수가 있다면 바로 반환
        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        // 방문 처리
        visited[x][y] = true;

        dp[x][y] = 0; // 현재 위치에서 가능한 경로 수 초기화
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int xx = x + dx[i];
            int yy = y + dy[i];

            // 유효한 좌표이고, 이동 조건(board[x][y] > board[xx][yy])을 만족하면
            if (xx >= 0 && xx < m && yy >= 0 && yy < n && board[x][y] > board[xx][yy]) {
                dp[x][y] += dfs(board, dp, visited, xx, yy);
            }
        }

        visited[x][y] = false; // 방문 해제 (백트래킹)

        return dp[x][y];
    }
}

 */
