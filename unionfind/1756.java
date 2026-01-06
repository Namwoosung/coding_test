import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int[] root;
	static List<Integer>[] enemies;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		root = new int[N + 1];
		enemies = new ArrayList[N+1];
		
		
		for(int i = 0; i <= N; i++) {
			root[i] = i;
			enemies[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			String val = st.nextToken();
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			if(val.equals("F")) {
				union(n1, n2);
			}else {
				enemies[n1].add(n2);
				enemies[n2].add(n1);
				for(int v : enemies[n1]) union(v, n2);
				for(int v : enemies[n2]) union(v, n1);
			}
		}
		
		
		Set<Integer> result = new HashSet<Integer>();
		for(int i = 1; i <= N; i++) {
			result.add(find(i));
		}
		System.out.println(result.size());
	}
	
	static int find(int n) {
		if(root[n] == n) return n;
		
		return root[n] = find(root[n]);
	}
	
	static void union(int n1, int n2) {
		if( find(n1) == find(n2)) return;
		else root[root[n1]] = root[n2];
		
	}
}

/*
// 가독성 및 안정성 증가 코드
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int[] parent;
    static int[] rank;
    static Set<Integer>[] enemy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        rank = new int[N + 1];
        enemy = new HashSet[N + 1];

        // 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
            enemy[i] = new HashSet<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd.equals("F")) {
                // 친구 관계
                union(a, b);
            } else {
                // 적 관계
                enemy[a].add(b);
                enemy[b].add(a);

                // a의 적들은 b와 친구
                for (int e : enemy[a]) {
                    union(e, b);
                }

                // b의 적들은 a와 친구
                for (int e : enemy[b]) {
                    union(e, a);
                }
            }
        }

        // 서로 다른 집합 개수 계산
        Set<Integer> groups = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            groups.add(find(i));
        }

        System.out.println(groups.size());
    }

    // Find with Path Compression
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by Rank
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return;

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else {
            parent[rootB] = rootA;
            if (rank[rootA] == rank[rootB]) {
                rank[rootA]++;
            }
        }
    }
}

*/
