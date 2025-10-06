import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution{
	static int N;
	static int[] result = new int[1000];
	static boolean[][] graph = new boolean[1000][1000];
	
	static Set<Integer> isVisited = new HashSet<>();
	static Deque<Integer> nowNodes = new ArrayDeque<>();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase = 1; testCase <= T; testCase++){
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		
    		// static 변수 초기화
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N; j++) {
    				graph[i][j] = false;
    			}
    			result[i] = 0;
    		}
    		
    		// 연결 관계 입력
    		for(int i = 0; i < N; i++) {
    			for(int j = 0; j < N; j++) {
    				if(st.nextToken().equals("1")) graph[i][j] = true;
    			}
    		}
    		
    		// 노드별 탐색을 시행. 자신 제외 모든 노드를 다 탐색할 때까지 탐색 진행
    		int minResult = Integer.MAX_VALUE;
    		for(int i = 0; i < N; i++) {
    			isVisited.clear();
    			nowNodes.clear();
    			
    			isVisited.add(i);
    			nowNodes.add(i);
    			int cnt = 0; //시행 횟수

    			outer:
    			while(!nowNodes.isEmpty()) {
    				int nowSize = nowNodes.size();
    				cnt++;
    				
    				for(int j = 0; j < nowSize; j++) {
    					int now = nowNodes.poll();
    					
    					for(int next = N-1; next >= 0; next--){ // 조금이라도 더 최적화 하기 위해 큰 값부터 처리
    						if(graph[now][next]) {
    							if(isVisited.contains(next)) continue;
    	    					isVisited.add(next);
    	    					nowNodes.add(next);
    	    					result[i] += cnt;
    	    					
    	    					if(isVisited.size() == N) break outer; // 모든 노드를 방문했으면 종료
    						}
    					}
    				}
    			}
    			minResult = Math.min(minResult, result[i]);
    		}
    		sb.append("#").append(testCase).append(" ").append(minResult).append("\n");
    	}
    	System.out.print(sb);
    }
}