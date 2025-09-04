import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
	static boolean[][] magnet = new boolean[4][8];
	static int[][] sideIndex = new int[4][2]; // 왼쪽과 오른쪽의 끝을 담당하는 인덱스
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int K = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++) {
					if(Integer.parseInt(st.nextToken()) == 0) magnet[i][j] = false;
					else magnet[i][j] = true;
				}
				sideIndex[i][1] = 2;
				sideIndex[i][0] = 6;
			}
			
			int[] rotateDir = new int[4];
			for(int turn = 0; turn < K; turn++) {
				st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				
				// 각 자석들의 회전 방향을 검사
				for(int i = 0; i < 4; i++) rotateDir[i] = 0;
				rotateDir[index] = dir;
				
				// 왼쪽 검사
				int leftIndex = index-1;
				while(leftIndex >= 0) {
					if(magnet[leftIndex][sideIndex[leftIndex][1]] == magnet[leftIndex+1][sideIndex[leftIndex+1][0]]) break;
					rotateDir[leftIndex] = rotateDir[leftIndex+1] * -1;
					leftIndex--;
				}
				// 오른쪽 검사
				int rightIndex = index + 1;
				while(rightIndex <= 3) {
					if(magnet[rightIndex-1][sideIndex[rightIndex-1][1]] == magnet[rightIndex][sideIndex[rightIndex][0]]) break;
					rotateDir[rightIndex] = rotateDir[rightIndex-1] * -1;
					rightIndex++;
				}
				
				// 자석을 회전, 진짜 회전하는 게 아니라 왼쪽과 오른쪽 끝 담당 인덱스만 변환
				for(int i = 0; i < 4; i++) {
					if(rotateDir[i] == 1) {
						sideIndex[i][0] -= 1;
						sideIndex[i][1] -= 1;
						
						if(sideIndex[i][0] < 0) sideIndex[i][0] += 8;
						if(sideIndex[i][1] < 0) sideIndex[i][1] += 8;
					}else if(rotateDir[i] == -1) {
						sideIndex[i][0] = (sideIndex[i][0] + 1) % 8;
						sideIndex[i][1] = (sideIndex[i][1] + 1) % 8;
					}
				}
			}
			
			
			int result = 0;
			for(int i = 0; i < 4; i++) {
				if(sideIndex[i][0] < sideIndex[i][1]) {
					if(magnet[i][(sideIndex[i][0] + sideIndex[i][1]) / 2]) result += (Math.pow(2, i));
				}else{
					if(magnet[i][(((sideIndex[i][0] + sideIndex[i][1]) / 2) + 4) % 8]) result += (Math.pow(2, i));
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}