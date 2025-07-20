import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;



class Solution {
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Pos{
        int x;
        int y;

        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static Boolean isMineFound(char[][] board, int x, int y, int N){
        // 8방향 탐색
        for(int dir = 0; dir < 8; dir++){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(0 <= nx && nx < N && 0 <= ny && ny < N){
                if( board[nx][ny] == '*'){
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for(int testCase = 1; testCase <= T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 편하게 처리하기 위해 입력값을 배열로 변환
            char[][] board = new char[N][];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                board[i] = st.nextToken().toCharArray();
            }


            int result = 0;
            Deque<Pos> deque = new ArrayDeque<>();
            // 먼저 주변에 지뢰가 없는 구역 먼저 탐색하고,해당 구역을 먼저 처리
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isMineFound(board, i, j, N) && board[i][j] == '.') { //주변에 지뢰가 없고, 아직 누르지 않은 칸인 경우
                        // 해당 칸을 누름
                        result++;
                        deque.add(new Pos(i, j));
                        board[i][j] = '0';

                        //연쇄 처리
                        while (!deque.isEmpty()) {
                            Pos now = deque.remove();

                            for (int dir = 0; dir < 8; dir++) {
                                int nx = now.x + dx[dir];
                                int ny = now.y + dy[dir];

                                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                                    if (board[nx][ny] == '.' && !isMineFound(board, nx, ny, N)) { // 주변 칸도 지뢰가 없다면 연쇄 처리
                                        deque.add(new Pos(nx, ny));
                                    }
                                    if (board[nx][ny] == '.') { //자신의 칸을 방문 처리
                                        board[nx][ny] = '0';
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // 지뢰가 없는 구역을 모두 탐색했다면, 남은 . 구역은 모두 일일이 눌러야 탐색 가능
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(board[i][j] == '.') result++;
                }
            }


            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
    }
}

/*
// 더 효과적인 풀이, 주면 지뢰 개수를 매번 함수로 탐색하지 말고, 미리 계산해서 배열로 저장해놓으면 가독성과 효율성에서 더 좋음
boolean[][] visited = new boolean[N][N];
int[][] mineCount = new int[N][N];

// 1. 주변 지뢰 수 계산
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        if (board[i][j] == '*') continue;

        for (int d = 0; d < 8; d++) {
            int nx = i + dx[d], ny = j + dy[d];
            if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '*') {
                mineCount[i][j]++;
            }
        }
    }
}

// 2. BFS로 0 주변 확장
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        if (board[i][j] == '.' && mineCount[i][j] == 0 && !visited[i][j]) {
            result++;
            Deque<Pos> dq = new ArrayDeque<>();
            dq.add(new Pos(i, j));
            visited[i][j] = true;

            while (!dq.isEmpty()) {
                Pos now = dq.poll();

                for (int d = 0; d < 8; d++) {
                    int nx = now.x + dx[d], ny = now.y + dy[d];
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N &&
                        board[nx][ny] == '.' && !visited[nx][ny]) {

                        visited[nx][ny] = true;
                        if (mineCount[nx][ny] == 0) dq.add(new Pos(nx, ny));
                    }
                }
            }
        }
    }
}

// 3. 남은 미방문한 칸들 클릭
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        if (board[i][j] == '.' && !visited[i][j]) result++;
    }
}

*/