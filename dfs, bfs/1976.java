import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static Set<Integer> dest = new HashSet<>();
	static Deque<Integer> queue = new ArrayDeque<>();
	static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N];
		isVisited = new boolean[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i] = new ArrayList<>();
			
			for(int j = 0; j < N; j++) {
				if(st.nextToken().equals("1"))
					graph[i].add(j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		// 일단 하나를 시작 인덱스로 잡음
		int sIndex = Integer.parseInt(st.nextToken()) - 1;
		dest.add(sIndex);
		queue.add(sIndex);
		isVisited[sIndex] = true;
		
		for(int i = 1; i < M; i++) {
			dest.add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		// 연결된 모든 노드들을 검사.
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			dest.remove(now);
			if(dest.isEmpty()) break; // 만약 이미 전체 목적지를 도달했으면 종료
			
			for(int next : graph[now]) {
				if(!isVisited[next]) {
					queue.add(next);
					isVisited[next] = true;
				}
			}
		}
		
		if(dest.isEmpty()) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}
}