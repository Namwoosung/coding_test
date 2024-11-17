import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] robot = new int[3];
        st = new StringTokenizer(br.readLine());
        robot[0] = Integer.parseInt(st.nextToken());
        robot[1] = Integer.parseInt(st.nextToken());
        robot[2] = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, -1, 0, 1};
        int[] dy = {1, 0, -1, 0};

        int result = 0;
        boolean is_pos = true;

        while (is_pos){
            if(room[robot[0]][robot[1]] == 0){
                room[robot[0]][robot[1]] = 2;
                result++;
            }

            boolean is_place = false;
            for(int i = 0; i < 4; i++){
                //robot[2] == 0 => dx[2], dy[2]
                //robot[2] == 1 => dx[1], dy[1]
                //robot[2] == 2 => dx[0], dy[0]
                //robot[2] == 3 => dx[1], dy[1]

                int stand = 0;

                switch (robot[2]) {

                    case 0:
                        stand = 2;
                        break;
                    case 1:
                        stand = 1;
                        break;
                    case 2:
                        stand = 0;
                        break;
                    case 3:
                        stand = 3;
                        break;
                }

                int xx = robot[0] + dx[(stand + i) % 4];
                int yy = robot[1] + dy[(stand + i) % 4];

                if(xx >= 0 && xx < n && yy >= 0 && yy < m && room[xx][yy] == 0){
                    is_place = true;
                    robot[0] = xx;
                    robot[1] = yy;

                    switch ((stand + i) % 4){
                        case 0:
                            robot[2] = 1;
                            break;
                        case 1:
                            robot[2] = 0;
                            break;
                        case 2:
                            robot[2] = 3;
                            break;
                        case 3:
                            robot[2] = 2;
                            break;
                    }

                    break;
                }



            }

            if(!is_place){
                int stand = 0;
                switch (robot[2]) {
                    case 0:
                        stand = 3;
                        break;
                    case 1:
                        stand = 2;
                        break;
                    case 2:
                        stand = 1;
                        break;
                    case 3:
                        stand = 0;
                        break;
                }

                int xx = robot[0] + dx[(stand) % 4];
                int yy = robot[1] + dy[(stand) % 4];

                if(xx >= 0 && xx < n && yy >= 0 && yy < m && room[xx][yy] !=1){
                    robot[0] = xx;
                    robot[1] = yy;
                } else{
                    is_pos = false;
                }

            }

        }
        System.out.println(result);

        br.close();
    }
}



/*
가독성이 더 좋은 코드
기존 코드는 동서남북 방향과 반시계 방향 회전을 case별로 복잡하게 처리했다면 여기서는 회전시 +3을 계산, 후진시 +2를 계산해서 간편하게 적용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        int[][] room = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방향 배열: 북, 동, 남, 서 (시계 방향)
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int cleaned = 0;
        boolean isRunning = true;

        while (isRunning) {
            // 현재 위치 청소
            if (room[x][y] == 0) {
                room[x][y] = 2; // 청소 완료
                cleaned++;
            }

            // 4방향 탐색
            boolean foundNext = false;
            for (int i = 0; i < 4; i++) {
                // 왼쪽 방향으로 회전
                direction = (direction + 3) % 4;
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && room[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    foundNext = true;
                    break;
                }
            }

            // 이동 가능한 곳이 없으면 후진
            if (!foundNext) {
                int backDir = (direction + 2) % 4; // 반대 방향
                int bx = x + dx[backDir];
                int by = y + dy[backDir];

                // 후진 가능 여부 확인
                if (bx >= 0 && bx < n && by >= 0 && by < m && room[bx][by] != 1) {
                    x = bx;
                    y = by;
                } else {
                    isRunning = false; // 후진 불가 시 종료
                }
            }
        }

        System.out.println(cleaned);
        br.close();
    }
}
