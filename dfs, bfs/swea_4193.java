// queue에 데이터 입력 시 int 배열 대신, 클래스를 사용하면 가독성 향상 가능 ex) class Node { int x, y, time; }
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int testCase = 1; testCase <= T; testCase++){
            //입력값
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] board = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] startPos = new int[2];
            int[] endPos = new int[2];
            st = new StringTokenizer(br.readLine());
            startPos[0] = Integer.parseInt(st.nextToken()); startPos[1] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            endPos[0] = Integer.parseInt(st.nextToken()); endPos[1] = Integer.parseInt(st.nextToken());

            // dfs로 탐색
            Deque<int[]> deque = new ArrayDeque<>();

            // 각 position 도달 시 최소값들을 저장, 더 짧은 경로로 도달 가능할 때만 queue에 삽입
            int[][] posTimes = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    posTimes[i][j] = Integer.MAX_VALUE;
                }
            }

            // 좌표처리를 위함
            int[] dx = {1, -1, 0, 0};
            int[] dy = {0, 0, 1, -1};

            deque.add(new int[]{startPos[0], startPos[1], 0}); //x, y, time
            posTimes[startPos[0]][startPos[1]] = 0;
            int[] nowPos;
            while(!deque.isEmpty()){
                nowPos = deque.remove();

                //현재 위치 기준 사방 탐색
                for(int dir = 0; dir < 4; dir++){
                    int nx = nowPos[0] + dx[dir];
                    int ny = nowPos[1] + dy[dir];

                    if(0 <= nx && nx < N && 0 <= ny && ny < N){
                        if(board[nx][ny] == 0){ //일반 구역이라면, 현재 경로가 이하인 경우만 deque에 삽입
                            int nowTime = nowPos[2] + 1;
                            if(posTimes[nx][ny] > nowTime){
                                deque.add(new int[]{nx, ny, nowTime});
                                posTimes[nx][ny] = nowTime;
                            }

                        }
                        else if(board[nx][ny] == 1) continue; //섬이면 pass
                        else{ //소용돌이라면 3의 배수일 때만 접근 가능, 현재 시간 이상인 가까운 3의 배수를 입력
                            int nowTime = nowPos[2] + 1;
                            if(nowTime % 3 != 0){
                                nowTime = ((nowTime / 3) + 1) * 3;
                            }

                            if(posTimes[nx][ny] > nowTime){
                                deque.add(new int[]{nx, ny, nowTime});
                                posTimes[nx][ny] = nowTime;
                            }
                        }
                    }
                }
            }

            int result = posTimes[endPos[0]][endPos[1]];
            if (result == Integer.MAX_VALUE) result = -1;
            bw.write("#" + testCase + " " + result + "\n");
        }
        bw.flush();
    }
}