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
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine()); int M = Integer.parseInt(br.readLine());
		
		// 친구 관계 설정
		List<Integer>[] graph = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(b);
			graph[b].add(a);
		}
		boolean[] isVisited = new boolean[N];
		
		Deque<Integer> queue = new ArrayDeque<>();
		int result = 0;
		isVisited[0] = true;
		//먼저 0번과 연결된 사람처리
		for(int i = 0; i < graph[0].size(); i++) {
			int next = graph[0].get(i);
			if(!isVisited[next]) {
				queue.add(next);
				isVisited[next] = true;
				result++;
			}
		}
		
		// 친구의 친구 개수 카운팅
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i);
				if(!isVisited[next]) {
					isVisited[next] = true;
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}