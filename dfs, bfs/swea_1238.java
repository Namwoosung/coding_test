import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	// static으로 객체를 생성해두고, 이후 main에서는 이 객체를 재사용
	// 중복 방지를 위해 set을 사용, 순회를 해야 하니 Linked를 사용
	static LinkedHashSet<Integer>[] graph = new LinkedHashSet[101];
	static {
		for(int i = 1; i < 101; i++) {
			graph[i] = new LinkedHashSet<>();
		}
	}
	static int[] cnt = new int[101];
	static Deque<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			// 객체 재사용
			for(int i = 1; i< 101; i++) {
				graph[i].clear();
				cnt[i] = 0;
			}
			queue.clear();
			
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); int startNode = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < (N / 2); i++) {
				int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
			}
			
			int maxCnt = 0;
			queue.add(startNode);
			cnt[startNode] = 1;
			
			while(!queue.isEmpty()) {
				int now= queue.poll();
				maxCnt = Math.max(cnt[now], maxCnt);
				
				for(int next : graph[now]) {
					if(cnt[next] > 0) continue;
					
					queue.add(next);
					cnt[next] = cnt[now] + 1;
				}
			}

			int result = 0;
			for(int i = 100; i > 0; i--) {
				if(maxCnt == cnt[i]) {
					result = i;
					break;
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
}