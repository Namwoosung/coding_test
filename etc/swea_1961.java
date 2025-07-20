// 다음과 같이 bw.write를 반복적으로 사용해야 할 때는 StringBuilder를 사용하는 것이 가독성 측면에서 더 효과적

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
//import java.io.FileInputStream;


class Solution {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("/Users/namws/Desktop/coding_test/ForCodingTest_java/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int testCase = 1; testCase <=T; testCase++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 보드 입력
            int[][] board = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] board90 = new int[N][N];
            int[][] board180 = new int[N][N];
            int[][] board270 = new int[N][N];

            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j++){
                    board90[i][j] = board[N - 1 - j][i];
                    board180[i][j] = board[N - 1 - i][N - 1 - j];
                    board270[i][j] = board[j][N - 1 - i];
                }
            }

            bw.write("#" + testCase + "\n");
            for(int i = 0; i < N; i ++){
                for(int j = 0; j < N; j++){
                    bw.write(String.valueOf(board90[i][j]));
                }
                bw.write(" ");
                for(int j = 0; j < N; j++){
                    bw.write(String.valueOf(board180[i][j]));
                }
                bw.write(" ");
                for(int j = 0; j < N; j++){
                    bw.write(String.valueOf(board270[i][j]));
                }
                bw.write("\n");
            }
        }
        bw.flush();

    }
}