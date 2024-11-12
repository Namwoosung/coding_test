//추후 보강 풀이 필요, 실패 => 접근 방식은 맞았지만, 구현에서 미비점이 많음
//(아래에 참고 코드 존재, 다 풀고 참고 코드보고 재검토 할 것)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];

        boolean isFirst = true;
        Posision posCoin1 = new Posision();
        Posision posCoin2 = new Posision();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

            for(int j = 0; j < m; j++){
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'o'){
                    if(isFirst){
                        posCoin1 = new Posision(i,j);
                        isFirst = false;
                    }else{
                        posCoin2 = new Posision(i,j);
                    }
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        AllPos start = new AllPos(posCoin1, posCoin2);
        Deque<AllPos> queue = new ArrayDeque<>();
        List<AllPos> check = new ArrayList<>();
        queue.add(start);
        check.add(start);

        boolean coin1Out = false; boolean coin2Out = false;
        Posision newCoin1 = new Posision();
        Posision newCoin2 = new Posision();
        AllPos newPos = new AllPos();

        boolean isPromise = true;

        for(int i = 0; i < 10; i++){
            int iter_num = queue.size();
            for(int iter = 0; iter < iter_num; iter++){
                AllPos stand = queue.remove();
                for(int j = 0; j < 4; j++){
                    coin1Out = false; coin2Out = false;
                    isPromise = true;

                    int xx = stand.posCoin1.x + dx[j];
                    int yy = stand.posCoin1.y + dy[j];

                    if(xx < 0 || xx >= n || yy < 0 || yy >= m){
                        coin1Out = true;
                    } else if(board[xx][yy] == '#') {
                        newCoin1.x = stand.posCoin1.x;
                        newCoin1.y = stand.posCoin1.y;
                    }else{
                        newCoin1.x = xx;
                        newCoin1.y = yy;
                    }


                    xx = stand.posCoin2.x + dx[j];
                    yy = stand.posCoin2.y + dy[j];

                    if(xx < 0 || xx >= n || yy < 0 || yy >= m){
                        coin2Out = true;
                    } else if(board[xx][yy] == '#') {
                        newCoin2.x = stand.posCoin2.x;
                        newCoin2.y = stand.posCoin2.y;
                    }else{
                        newCoin2.x = xx;
                        newCoin2.y = yy;
                    }


                    if(coin1Out != coin2Out){
                        bw.write(String.valueOf(i+1));
                        bw.flush();
                        return ;
                    } else if(!coin1Out){
                        newPos = new AllPos(newCoin1, newCoin2);

                        for(int k = 0; k < check.size(); k++){
                            if(check.get(k).equals(newPos) || posCoin1.equals(posCoin2)){
                                isPromise = false;
                                break;
                            }
                        }

                        if(isPromise){
                            queue.add(newPos);
                            check.add(newPos);
                        }
                    }

                }
            }
        }

        bw.write(String.valueOf(-1)); bw.flush();

        bw.close();
        br.close();
    }

    private static class Posision {
        int x;
        int y;

        public Posision() {
        }

        public Posision(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class AllPos {
        Posision posCoin1;
        Posision posCoin2;

        public AllPos() {
        }
        public AllPos(Posision posCoin1, Posision posCoin2) {
            this.posCoin1 = posCoin1;
            this.posCoin2 = posCoin2;
        }
    }
}


/*
//동일한 접근 방식이지만, 훨씬 깔끔하게 접근한 코드
//bfs 심화과정의 경우 아래와 같이 접근하는 것이 좋을 듯
import java.util.*;

public class Main {

    static int n, m;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static char[][] board;
    static Coin[] coin; // 동전의 위치 저장.
    static boolean[][][][] visited;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();

        board = new char[n][m];
        coin = new Coin[2];
        int coinIdx = 0;
        for(int i = 0; i < n; i++) {
            String str = scan.next();
            for(int j = 0; j < m; j++) {
                char c = str.charAt(j);
                if(c == 'o') {
                    coin[coinIdx++] = new Coin(i, j); //코인의 위치 저장
                }
                board[i][j] = c;
            }
        }

        visited = new boolean[n][m][n][m]; //[첫번째동전의x위치][첫번째동전의y위치][두번째동전의x위치][두번째동전의y위치]
        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<towCoins> q = new LinkedList<>();
        q.offer(new towCoins(coin[0].x, coin[0].y, coin[1].x, coin[1].y, 0));
        visited[coin[0].x][coin[0].y][coin[1].x][coin[1].y] = true;

        while(!q.isEmpty()) {
            towCoins current = q.poll();

            if(current.count >= 10) break;

            for(int i = 0; i < 4; i++) {
                int nx1 = current.x1 + dx[i];
                int ny1 = current.y1 + dy[i];
                int nx2 = current.x2 + dx[i];
                int ny2 = current.y2 + dy[i];

                //이동할 좌표가 벽이면 이동할 수 없으므로 이동하지 않는다.
                if(!canMoveCoin(nx1, ny1)) {
                    nx1 = current.x1;
                    ny1 = current.y1;
                }
                if(!canMoveCoin(nx2, ny2)) {
                    nx2 = current.x2;
                    ny2 = current.y2;
                }

                int flag = 0; //떨어지지 않는 동전의 개수
                if(nx1 >= 0 && ny1 >= 0 && nx1 < n && ny1 < m) flag++;
                if(nx2 >= 0 && ny2 >= 0 && nx2 < n && ny2 < m) flag++;

                if(flag == 1) return current.count + 1;
                else if(flag == 2 && !visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.offer(new towCoins(nx1, ny1, nx2, ny2, current.count + 1));
                }
            }
        }
        return -1;
    }

    public static boolean canMoveCoin(int nx, int ny) {
        if(nx >= 0 && ny >= 0 && nx < n && ny < m && board[nx][ny] == '#') {
            return false;
        }
        return true;
    }

    public static class towCoins { //두 동전의 위치와 현재 버튼을 누른 횟수를 기록하는 객체
        int x1;
        int y1;
        int x2;
        int y2;
        int count;

        public towCoins(int x1, int y1, int x2, int y2, int count) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.count = count;
        }
    }

    public static class Coin { //동전의 좌표를 기억하는 객체
        int x;
        int y;

        public Coin(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
 */
