import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] switches = new ArrayList[N * N];
		Set<Integer> isVisited = new HashSet<>();
		boolean[] isLighted = new boolean[N*N];
		
		for(int i = 0; i < N * N; i++) {
			switches[i] = new ArrayList<Integer>();
			isLighted[i] = false;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); 
			int x = Integer.parseInt(st.nextToken()) - 1; int y = Integer.parseInt(st.nextToken()) - 1;
			int tx = Integer.parseInt(st.nextToken()) - 1; int ty = Integer.parseInt(st.nextToken()) - 1;
			
			switches[x * N + y].add(tx * N + ty);
		}
		
		Queue<Integer> bfs = new ArrayDeque<Integer>();
		
		isVisited.add(0);
		isLighted[0] = true;
		bfs.add(0);
		int result = 1;
		
		while(!bfs.isEmpty()) {
			int now = bfs.poll();
			
			for(int next : switches[now]) {
				if(!isLighted[next]) {
					result++;
					isLighted[next] = true;
				}
			}
			
			Set<Integer> nowVisited = new HashSet<>();
			
			for(int next: isVisited) {
				for(int dir = 0; dir < 4; dir++) {
					int nx = (next / N) + dx[dir];
					int ny = (next % N) + dy[dir];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
						int nextPos = nx * N + ny;
						
						if(!isVisited.contains(nextPos) && isLighted[nextPos]) {
							bfs.add(nextPos);
							nowVisited.add(nextPos);
						}
					}
				}
			}
			
			isVisited.addAll(nowVisited);
		}
		
		System.out.println(result);
		
	}
}


/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 각 방에서 켤 수 있는 스위치 목록
        List<Integer>[] switches = new ArrayList[N * N];
        for (int i = 0; i < N * N; i++) {
            switches[i] = new ArrayList<>();
        }

        // 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int tx = Integer.parseInt(st.nextToken()) - 1;
            int ty = Integer.parseInt(st.nextToken()) - 1;

            switches[x * N + y].add(tx * N + ty);
        }

        boolean[] visited = new boolean[N * N]; // 이동 가능 여부
        boolean[] light = new boolean[N * N];   // 불 켜짐 여부

        Queue<Integer> queue = new ArrayDeque<>();

        // 시작 방 (0,0)
        visited[0] = true;
        light[0] = true;
        queue.add(0);

        int result = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 1️⃣ 스위치 작동
            for (int next : switches[cur]) {
                if (!light[next]) {
                    light[next] = true;
                    result++;

                    // 새로 불 켜진 방이 이미 방문한 방과 인접하면 즉시 이동 가능
                    int x = next / N;
                    int y = next % N;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                        int adj = nx * N + ny;
                        if (visited[adj]) {
                            visited[next] = true;
                            queue.add(next);
                            break;
                        }
                    }
                }
            }

            // 2️⃣ 인접 방 이동
            int x = cur / N;
            int y = cur % N;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                int next = nx * N + ny;
                if (!visited[next] && light[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }

        System.out.println(result);
    }
}


*/