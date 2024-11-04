import java.io.*;
import java.math.BigInteger;
import java.util.*;

//BigInteger를 사용해 조합을 바로 계산
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        int n, m;
        BigInteger top, bot;
        for(int i = 0; i < t; i ++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if (n > m - n) {
                n = m - n; // 조합의 대칭성을 이용해 n을 줄임
            }

            top = BigInteger.ONE;
            bot = BigInteger.ONE;
            for(int j = 0; j < n; j++){
                top = top.multiply(BigInteger.valueOf(m-j));
                bot = bot.multiply(BigInteger.valueOf(j+1));
            }
            bw.write(String.valueOf(top.divide(bot)));
            bw.newLine();
        }

        bw.flush();
    }
}

/*
더 적합할 수 있는 코드, 조합을 BigInteger로 어거지로 계산하는 것이 아니라 조합의 성질을 활용해 dp로 계산
import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX = 30; // 조합을 계산할 최대 n, m 값
    private static int[][] dp = new int[MAX + 1][MAX + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // DP 테이블 초기화
        for (int i = 0; i <= MAX; i++) {
            dp[i][0] = 1; // nC0 = 1
            dp[i][i] = 1; // nCn = 1
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // nCk = (n-1)C(k-1) + (n-1)Ck
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            bw.write(dp[m][n] + "\n");
        }

        bw.flush();
    }
}
 */
