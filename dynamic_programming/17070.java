import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	// col, row = 0은 패딩
    	int[][][] dp = new int[N+1][N+1][3]; // 좌표별로 가로, 세로, 대각선의 가능한 경로를 dp로 저장
    	boolean[][] wall = new boolean[N+1][N+1];
    	
    	for(int i = 1; i <= N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= N; j++) {
    			if(st.nextToken().equals("1")) wall[i][j] = true;
    		}
    	}
    	
    	// 처음 상태가 끝이 1, 2이고 가로 모양이므로 해당 모양을 반영
    	dp[1][2][0] = 1;
    	
    	// (1,3) 기준으로 dp를 update
    	for(int i = 1; i <= N; i++) {
    		for(int j = 3; j <= N; j++) {
    			if(wall[i][j]) continue; // 벽이면 그냥 0
    			
    			// 가로는 왼쪽에서 가로로 오거나 대각선으로 오는 경우
    			dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
    			// 세로는 위에서 세로로 오거나 대각선으로 오는 경우
    			dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
    			// 대각선은 대각선방향에서 가로, 세로, 대각선인 경우. 벽에 걸리지 않는 다면 추가
    			if(!wall[i-1][j] && !wall[i][j-1]) dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
    		}
    	}
    	
    	System.out.print(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    	
    }
}