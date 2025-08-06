import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	static class NextPath{
		int row;
		int nextCol;

		public NextPath(int row, int nextCol) {
			super();
			this.row = row;
			this.nextCol = nextCol;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = 10;
		for(int testCase = 1; testCase <= 10; testCase++) {
			String num = br.readLine();
			
			Set<Integer> lines = new HashSet<>(); // 막대들의 인덱스를 저장
			st = new StringTokenizer(br.readLine());
			for(int col = 0; col < 100; col++) {
				if(st.nextToken().equals("1")) lines.add(col);
			}
			
			List<NextPath>[] graph = new ArrayList[100];
			for(int i = 0; i < 100; i++) {
				graph[i] = new ArrayList<NextPath>();
			}
			
			int nowRow = 99; int nowCol = 0;
			for(int i = 1; i < 100; i ++) {
				st = new StringTokenizer(br.readLine());
				boolean isHorizon = false;
				int start = -1; int end = -1; // 수직 막대를 저장하기 위함
				
				for(int j = 0; j < 100; j++) {
					String now = st.nextToken();
					
					if(now.equals("1")) {
						
						if(lines.contains(j)){
							if(isHorizon) { //수직 막대가 끝났음을 의미
								end = j;
								isHorizon = false;
								// 분기 관계 설정
								graph[start].add(new NextPath(i, end));
								graph[end].add(new NextPath(i, start));
							}
						}else { 
							if(!isHorizon) { // 막대가 아닌데 1인 경우 => 수직 막대가 시작함을 의미
								isHorizon = true;
								start = j-1; //시작 인덱스 설정
							}
						}
					}
					
					if(now.equals("2")) nowCol = j;
				}
			}
			
			boolean isMove = true;
			while(isMove) { //이동했다면 계속 검사
				isMove = false;
				for(int i = graph[nowCol].size() -1; i >= 0; i--) {
					NextPath next = graph[nowCol].get(i);
					if(next.row < nowRow) { // 현재 col에서 이동할 수 있는 분기점이 있는 경우
						nowCol = next.nextCol;
						nowRow = next.row;
						isMove = true;
						break;
					}
				}
			}
			sb.append("#").append(num).append(" ").append(nowCol).append("\n");
		}
		System.out.print(sb);
	}
}
