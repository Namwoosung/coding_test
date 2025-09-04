import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution{
	static class Edge implements Comparable<Edge>{
		int node;
		double weight;
		
		public Edge(int node, double weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return Double.compare(this.weight, other.weight);
		}
	}
	
	
	static boolean[] isVisited = new boolean[1001];
	static List<Edge>[] graph = new ArrayList[1001];
	static int[][] islands = new int[1001][2];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static {
		for(int i = 1; i <= 1000; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			// 전역 변수 초기화
			for(int i = 1; i <= N; i++) {
				isVisited[i] = false;
				graph[i].clear();
			}
			pq.clear();
			
			// 입력값 처리
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				islands[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				islands[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			
			for(int i = 1; i <= N; i++) {
				for(int j = i + 1; j <= N; j++) {
					double dist = Math.pow(Math.hypot(Math.abs(islands[i][0] - islands[j][0]), Math.abs(islands[i][1] - islands[j][1])), 2);
					
					graph[i].add(new Edge(j, dist));
					graph[j].add(new Edge(i, dist));
				}
			}
			
			//1 번 노드를 시작지점으로 설정
			isVisited[1] = true;
			for(int i = 0; i < graph[1].size(); i++) {
				pq.add(graph[1].get(i));
			}
			
			double result = 0;
			while(!pq.isEmpty()) {
				Edge now = pq.poll();
				if(isVisited[now.node]) continue;
				
				result += now.weight;
				isVisited[now.node] = true;
				for(int i = 0; i < graph[now.node].size(); i++) {
					Edge next = graph[now.node].get(i);
					if(!isVisited[next.node])pq.add(next);
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(Math.round(result * E)).append("\n");
		}
		System.out.print(sb);
	}
}
