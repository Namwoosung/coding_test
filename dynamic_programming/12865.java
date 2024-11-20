import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] things = new int[n + 1][2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            things[i][0] = Integer.parseInt(st.nextToken());
            things[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][k + 1];

        for(int i = 1; i <= n; i++) {
            int weight = things[i][0];
            int value = things[i][1];

            for (int j = 0; j <= k; j++) {
                if (j < weight) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + value);
                }

            }
        }

        System.out.println(dp[n][k]);

        br. close();
    }
}
