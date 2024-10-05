import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine());
        int[] stairs = new int[number];
        for(int i = 0; i < number; i++){
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if(number == 1){
            System.out.println(stairs[0]);
            return;
        }

        int[] dp = new int[number]; // 각 계단에서 얻을 수 있는 최대 점수
        dp[0] = stairs[0];
        dp[1] = stairs[0] + stairs[1];

        for(int i = 2; i < number; i++){
            dp[i] = Math.max(dp[i - 2], dp[i - 1] - stairs[i - 1]) + stairs[i];
        }

        System.out.println(dp[number - 1]);
    }
}

