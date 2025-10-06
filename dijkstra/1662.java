import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int node;
        int weight;
        Edge(int node, int weight) {
        	this.node = node; 
        	this.weight = weight; 
        	}
    }
    
    static class Candiate implements Comparable<Candiate> {
        int node;
        int change;     // 남은 포장 횟수
        long weight;    // 누적 거리
        Candiate(int node, int change, long weight) {
            this.node = node; 
            this.change = change; 
            this.weight = weight;
        }
        @Override public int compareTo(Candiate o) {
            return Long.compare(this.weight, o.weight); // 가중치만 비교
        }
    }

    static List<Edge>[] graph;
    static long[][] dist;
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        // dist[node][change] = 그 상태로의 최소 거리
        dist = new long[N + 1][K + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        dist[1][K] = 0L;

        PriorityQueue<Candiate> pq = new PriorityQueue<>();
        pq.add(new Candiate(1, K, 0L));

        while (!pq.isEmpty()) {
            Candiate now = pq.poll();

            // 이미 더 좋은 경로가 있으면 스킵
            if (now.weight != dist[now.node][now.change]) continue;

            // 첫 pop된 N이 전역 최소
            if (now.node == N) {
                System.out.println(now.weight);
                return;
            }

            for (Edge next : graph[now.node]) {
                // 1) 포장 안 함
                long nd = now.weight + next.weight;
                if (nd < dist[next.node][now.change]) {
                    dist[next.node][now.change] = nd;
                    pq.add(new Candiate(next.node, now.change, nd));
                }
                // 2) 포장 해서 0 비용으로 지나감
                if (now.change > 0) {
                    if (now.weight < dist[next.node][now.change - 1]) {
                        dist[next.node][now.change - 1] = now.weight;
                        pq.add(new Candiate(next.node, now.change - 1, now.weight));
                    }
                }
            }
        }

        // 이 문제에선 항상 연결 가정이지만, 안전하게 남겨둠
        long result = Long.MAX_VALUE;
        for (int k = 0; k <= K; k++) result = Math.min(result, dist[N][k]);
        System.out.println(result == Long.MAX_VALUE ? -1 : result);
    }
}
