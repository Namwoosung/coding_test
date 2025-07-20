// 대각선 위치 설정 관련 참고하기 좋음

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int testCase = 0; testCase < T; testCase++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            // board 입력
            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //brute force로 탐색

            // + 방향
            int[] dx1 = {-1, 1, 0, 0};
            int[] dy1 = {0, 0, -1, 1};

            // x 방향
            int[] dx2 = {-1, -1, 1, 1};
            int[] dy2 = {-1, 1, -1, 1};

            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int sumPlus = board[i][j];
                    int sumX = board[i][j];

                    // + 방향
                    for (int d = 1; d < M; d++) {
                        for (int dir = 0; dir < 4; dir++) {
                            int ni = i + dx1[dir] * d;
                            int nj = j + dy1[dir] * d;
                            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                                sumPlus += board[ni][nj];
                            }
                        }
                    }

                    // x 방향
                    for (int d = 1; d < M; d++) {
                        for (int dir = 0; dir < 4; dir++) {
                            int ni = i + dx2[dir] * d;
                            int nj = j + dy2[dir] * d;
                            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                                sumX += board[ni][nj];
                            }
                        }
                    }

                    result = Math.max(result, Math.max(sumPlus, sumX));
                }
            }
            bw.write("#" + (testCase+1) + " " + result + "\n");
        }
        bw.flush();
    }
}