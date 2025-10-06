import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution{
	static class Edge implements Comparable<Edge>{
		int node;
		int weight;
		
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.weight, other.weight);
		}
	}
	
	
	static boolean[] isVisited = new boolean[100001];
	static List<Edge>[] graph = new ArrayList[100001];
	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static {
		for(int i = 1; i <= 100000; i++) {
			graph[i] = new ArrayList<>();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); int E = Integer.parseInt(st.nextToken());

			// static 변수 초기화
			for(int i = 1; i <= V; i++) {
				isVisited[i] = false;
				graph[i].clear();
			}
			pq.clear();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				graph[a].add(new Edge(b, c));
				graph[b].add(new Edge(a, c));
			}
			
			//1 번 노드를 시작지점으로 설정
			isVisited[1] = true;
			for(int i = 0; i < graph[1].size(); i++) {
				pq.add(graph[1].get(i));
			}
			
			long result = 0;
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
			
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}
