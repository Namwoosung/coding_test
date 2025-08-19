import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0 ,0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Pos{
		int x; 
		int y;
		
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
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
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); int M = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Deque<Pos> queue = new ArrayDeque<>();
		int islandNum = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(board[i][j] == 1) {
					queue.add(new Pos(i, j));
					board[i][j] = -1 * islandNum;
					
					while(!queue.isEmpty()) {
						Pos now = queue.poll();
						
						for(int dir = 0; dir < 4; dir++) {
							int nx = now.x + dx[dir];
							int ny = now.y + dy[dir];
							
							if(!(nx >= 0 && nx < N && ny >= 0 && ny < M)) continue;
							
							if(board[nx][ny] == 1) {
								queue.add(new Pos(nx, ny));
								board[nx][ny] = -1 * islandNum;
							}
						}
					}
					islandNum++;
				}
			}
		}
		
		int islandCnt = islandNum - 1; 
		
		// 각 행 기준 다리 건설 가능 여부를 조사
		List<List<Edge>> graph = new ArrayList<>();
		for(int i = 0; i <= islandCnt; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < N; i++) {
			int prev = 0; int cnt = 0;
			
			for(int j = 0; j < M; j++) {
				if(prev == 0 && board[i][j] == 0) continue;
				else if(prev == 0 && board[i][j] != 0) {
					prev = board[i][j];
				}else if(prev != 0 && board[i][j] == prev) {
					cnt = 0;
				}
				else if(prev != 0 && board[i][j] == 0) {
					cnt++;
				}else if(prev != 0 && board[i][j] != prev) {
					// 다리 길이가 2이상이면 연결관계 설정
					// 동일한 노드기리 여러개의 가중치를 가진 그래프가 들어가도 큰 상관없음. 이후 PQ를 통해 관리하고, 방문 처리를 할 예정
					if(cnt >= 2) {
						graph.get(-1 * prev).add(new Edge(-1 * board[i][j], cnt));
						graph.get(-1 * board[i][j]).add(new Edge(-1 * prev, cnt));
					}
					
					prev = board[i][j];
					cnt = 0;
				}
			}
		}
		// 열 기준 검사
		for(int j = 0; j < M; j++) {
			int prev = 0; int cnt = 0;

			for(int i = 0; i < N; i++) {
				if(prev == 0 && board[i][j] == 0) continue;
				else if(prev == 0 && board[i][j] != 0) {
					prev = board[i][j];
				}else if(prev != 0 && board[i][j] == prev) {
					cnt = 0;
				}
				else if(prev != 0 && board[i][j] == 0) {
					cnt++;
				}else if(prev != 0 && board[i][j] != prev) {
					// 다리 길이가 2이상이면 연결관계 설정
					// 동일한 노드기리 여러개의 가중치를 가진 그래프가 들어가도 큰 상관없음. 이후 PQ를 통해 관리하고, 방문 처리를 할 예정
					if(cnt >= 2) {
						graph.get(-1 * prev).add(new Edge(-1 * board[i][j], cnt));
						graph.get(-1 * board[i][j]).add(new Edge(-1 * prev, cnt));
					}
					
					prev = board[i][j];
					cnt = 0;
				}
			}
		}
		
		
		int result = 0;
		Set<Integer> allIsland = new HashSet<>();
		PriorityQueue<Edge> candiate = new PriorityQueue<>();
		allIsland.add(1);
		for(int i = 0; i < graph.get(1).size(); i++) {
			candiate.add(graph.get(1).get(i));
		}
		
		while(!candiate.isEmpty()) {
			Edge nowEdge = candiate.poll();
			if(allIsland.contains(nowEdge.node)) continue;
			
			result += nowEdge.weight;
			allIsland.add(nowEdge.node);
			for(int i = 0; i < graph.get(nowEdge.node).size(); i++) {
				candiate.add(graph.get(nowEdge.node).get(i));
			}
		}
		
		
		if(allIsland.size() != islandCnt) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
	}
}


/*
// 더 효율적인 풀이
// kruskal 사용 + 중복 간선 최소화(cnt 배열)
import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000;
    static int N, M;
    static int[][] a;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Pos {
        int x, y;
        Pos(int x, int y) { this.x = x; this.y = y; }
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge o) { return Integer.compare(this.w, o.w); }
    }

    static class DSU {
        int[] p, r;
        DSU(int n) { p = new int[n+1]; r = new int[n+1]; for (int i=1;i<=n;i++) p[i]=i; }
        int find(int x){ return p[x]==x? x : (p[x]=find(p[x])); }
        boolean union(int a, int b){
            a = find(a); b = find(b);
            if (a==b) return false;
            if (r[a]<r[b]) { int t=a; a=b; b=t; }
            p[b]=a;
            if (r[a]==r[b]) r[a]++;
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        a = new int[N][M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) a[i][j] = Integer.parseInt(st.nextToken());
        }

        // 1) 섬 라벨링 (1..K)
        int K = labelIslands();

        if (K <= 1) { // 섬이 0개/1개면 다리 길이 0
            System.out.println(0);
            return;
        }

        // 2) 행/열 스캔으로 다리 최소 길이 추출 (쌍 최소만 보존)
        int[][] minEdge = new int[K+1][K+1];
        for (int i=1;i<=K;i++) Arrays.fill(minEdge[i], INF);

        // 행 스캔
        for (int i=0;i<N;i++) {
            int prev = 0, cnt = 0;
            for (int j=0;j<M;j++) {
                int v = a[i][j];
                if (v == 0) { if (prev != 0) cnt++; continue; }
                // v != 0 (땅)
                if (prev == 0) { prev = v; cnt = 0; continue; }
                if (v == prev) { // 같은 섬을 다시 만남: 다리 후보 막힘 → 리셋
                    cnt = 0;
                } else { // 다른 섬
                    if (cnt >= 2) {
                        int u = prev, w = v;
                        if (u != w) {
                            if (minEdge[u][w] > cnt) {
                                minEdge[u][w] = cnt;
                                minEdge[w][u] = cnt;
                            }
                        }
                    }
                    prev = v;
                    cnt = 0;
                }
            }
        }

        // 열 스캔
        for (int j=0;j<M;j++) {
            int prev = 0, cnt = 0;
            for (int i=0;i<N;i++) {
                int v = a[i][j];
                if (v == 0) { if (prev != 0) cnt++; continue; }
                if (prev == 0) { prev = v; cnt = 0; continue; }
                if (v == prev) {
                    cnt = 0;
                } else {
                    if (cnt >= 2) {
                        int u = prev, w = v;
                        if (u != w) {
                            if (minEdge[u][w] > cnt) {
                                minEdge[u][w] = cnt;
                                minEdge[w][u] = cnt;
                            }
                        }
                    }
                    prev = v;
                    cnt = 0;
                }
            }
        }

        // 3) 간선 리스트 생성(중복/자기루프 없음, 최솟값만)
        List<Edge> edges = new ArrayList<>();
        for (int i=1;i<=K;i++) {
            for (int j=i+1;j<=K;j++) {
                if (minEdge[i][j] < INF) edges.add(new Edge(i, j, minEdge[i][j]));
            }
        }

        // 간선이 전혀 없으면 연결 불가
        if (edges.isEmpty()) {
            System.out.println(-1);
            return;
        }

        // 4) Kruskal MST
        Collections.sort(edges);
        DSU dsu = new DSU(K);
        int used = 0, cost = 0;
        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                cost += e.w;
                used++;
                if (used == K - 1) break;
            }
        }

        // 모든 섬이 연결되었는지 확인
        if (used != K - 1) System.out.println(-1);
        else System.out.println(cost);
    }

    // BFS로 섬 라벨링(1..K)
    static int labelIslands() {
        boolean[][] vis = new boolean[N][M];
        int label = 0;
        Deque<Pos> q = new ArrayDeque<>();
        for (int i=0;i<N;i++) for (int j=0;j<M;j++) {
            if (a[i][j] != 1 || vis[i][j]) continue;
            label++;
            vis[i][j] = true;
            a[i][j] = label;
            q.clear();
            q.add(new Pos(i, j));
            while (!q.isEmpty()) {
                Pos cur = q.poll();
                for (int d=0; d<4; d++) {
                    int nx = cur.x + dx[d], ny = cur.y + dy[d];
                    if (nx<0||nx>=N||ny<0||ny>=M) continue;
                    if (vis[nx][ny] || a[nx][ny] != 1) continue;
                    vis[nx][ny] = true;
                    a[nx][ny] = label;
                    q.add(new Pos(nx, ny));
                }
            }
        }
        return label;
    }
}


*/
