import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] trash = new int[n][m];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            trash[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        Deque<Pos> queue = new ArrayDeque<Pos>();
        int result = 0;
        int count = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m ; j++){
                count = 0;
                if(trash[i][j] == 1){
                    trash[i][j] = 0;
                    queue.add(new Pos(i ,j));
                    count += 1;
                }

                while(!queue.isEmpty()){
                    Pos now = queue.remove();

                    if(now.getX() + 1 < n &&  trash[now.getX() + 1][now.getY()] == 1){
                        trash[now.getX() + 1][now.getY()] = 0;
                        queue.add(new Pos(now.getX() + 1, now.getY()));
                        count += 1;
                    }
                    if(now.getX() - 1 >= 0 &&  trash[now.getX() - 1][now.getY()] == 1){
                        trash[now.getX() - 1][now.getY()] = 0;
                        queue.add(new Pos(now.getX() - 1, now.getY()));
                        count += 1;
                    }
                    if(now.getY() + 1 < m &&  trash[now.getX()][now.getY() + 1] == 1){
                        trash[now.getX()][now.getY() + 1] = 0;
                        queue.add(new Pos(now.getX(), now.getY() + 1));
                        count += 1;
                    }
                    if(now.getY() - 1 >= 0 &&  trash[now.getX()][now.getY() - 1] == 1){
                        trash[now.getX()][now.getY() - 1] = 0;
                        queue.add(new Pos(now.getX(), now.getY() - 1));
                        count += 1;
                    }

                }

                result = Math.max(result, count);
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();

    }

    static class Pos{
        private int x;
        private int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}


/*
더 효율적인 코드
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DIRECTIONS = {
        {1, 0},  // down
        {-1, 0}, // up
        {0, 1},  // right
        {0, -1}  // left
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] trash = new int[n][m];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            trash[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }

        Deque<Position> queue = new ArrayDeque<>();
        int maxTrashCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (trash[i][j] == 1) {
                    int trashCount = 0;
                    queue.add(new Position(i, j));
                    trash[i][j] = 0; // Mark as visited

                    while (!queue.isEmpty()) {
                        Position current = queue.poll();
                        trashCount++;

                        for (int[] direction : DIRECTIONS) {
                            int newX = current.x + direction[0];
                            int newY = current.y + direction[1];

                            if (isValid(newX, newY, n, m) && trash[newX][newY] == 1) {
                                trash[newX][newY] = 0; // Mark as visited
                                queue.add(new Position(newX, newY));
                            }
                        }
                    }

                    maxTrashCount = Math.max(maxTrashCount, trashCount);
                }
            }
        }

        bw.write(String.valueOf(maxTrashCount));
        bw.flush();
    }

    private static boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
 */
