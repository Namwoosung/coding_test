import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int min = Integer.MAX_VALUE;

    static int[][] city;

    static List<Pos> homes = new ArrayList<>();
    static List<Pos> chickens = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1) homes.add(new Pos(i,j));
                else if(city[i][j] == 2) chickens.add(new Pos(i,j));
            }
        }

        checkCombination(new ArrayList<>(), m, 0);
        System.out.println(min);

        br.close();
    }

    private static void checkCombination(List<Pos> current, int m, int start) {
        if(current.size() == m){
            min = Math.min(checkDistance(current), min);
        }

        for(int i = start; i < chickens.size(); i++){
            current.add(chickens.get(i));
            checkCombination(current, m, i + 1);
            current.remove(current.size() - 1);
        }
    }

    private static int checkDistance(List<Pos> current) {
        int distance = 0;

        for (Pos nowHome : homes) {
            int nowDistance = Integer.MAX_VALUE;
            for (Pos check : current) {
                nowDistance = Math.min(nowDistance, Math.abs(nowHome.x - check.x) + Math.abs(nowHome.y - check.y));
            }
            distance += nowDistance;
        }

        return distance;
    }

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
