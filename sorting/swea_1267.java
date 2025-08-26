import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static List<Integer>[] graph = new ArrayList[1001];
	static int[] inOrder = new int[1001];
	static Deque<Integer> queue = new ArrayDeque<>();
	
	static {
		for(int i = 1; i <= 1000; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	for(int testCase = 1; testCase <= 10; testCase++) {
    		st = new StringTokenizer(br.readLine());
    		int V = Integer.parseInt(st.nextToken()); int E = Integer.parseInt(st.nextToken());
    		
    		// 이번 testCase에서 사용할 만큼 static 변수 초기화
    		for(int i = 1; i <= V; i++) {
    			graph[i].clear();
    			inOrder[i] = 0;
    		}
    		sb.append("#").append(testCase).append(" ");

    		st = new StringTokenizer(br.readLine());
    		for(int i = 0; i < E; i++) {
    			int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
    			graph[a].add(b);
    			inOrder[b]++;
    		}
    		
    		// 진입 차수가 0이면 queue에 추가
    		for(int i = 1; i <= V; i++) {
    			if(inOrder[i] == 0) queue.add(i);
    		}
    		
    		while(!queue.isEmpty()) {
    			int now = queue.poll();
    			sb.append(now).append(" "); // 결과에 바로바로 추가
    			
    			for(int next: graph[now]) {
    				inOrder[next]--;
    				if(inOrder[next] == 0) queue.add(next);
    			}
    		}
    		
    		sb.append("\n");
    	}
    	System.out.print(sb);
    }
}