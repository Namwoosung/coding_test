import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	private static List<Integer>[] graph;
	private static BitSet[] stores;

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N+1];
		stores = new BitSet[N+1];

		// graph에는 다음을 저장
		// graph[1]: 1번 인덱스를 선택하면 연쇄적으로 해킹되는 index들을 저장
        
        // store에는 다음을 저장
		// stores[1]: 1번 인덱스부터 해킹했을 때, 결과적으로 해킹되는 전체 인덱스을 Set으로 저장
		// 최적화를 위해 사용 => 현재 검사하기 이전 index는 이미 확정된 애들 => 바로 set을 병합
        // 이때 이 상황에서는 일반적인 Set을 사용하는 것보다 BitSet이 훨씬 효율적이라고 해서 BitSet을 사용
		for(int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
			stores[i] = new BitSet(N+1);
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph[B].add(A); // 역방향 저장
		}

		int maxCount = 0;
		Deque<Integer> bfs = new ArrayDeque<>();

		for(int i = 1; i <= N; i++) {
			boolean[] isVisited = new boolean[N+1];

			bfs.offer(i);
			isVisited[i] = true;
			stores[i].set(i);

			while(!bfs.isEmpty()) {
				int now = bfs.poll();

				for(int next : graph[now]) {
					if (!isVisited[next]) {
						isVisited[next] = true;
						if (next < i) {
							stores[i].or(stores[next]);
						} else {
							bfs.offer(next);
							stores[i].set(next);
						}
					}
				}
			}
			maxCount = Math.max(maxCount, stores[i].cardinality());
		}

		for(int i = 1; i <= N; i++) {
			if (stores[i].cardinality() == maxCount) {
				sb.append(i).append(" ");
			}
		}

		System.out.println(sb);
	}
}
