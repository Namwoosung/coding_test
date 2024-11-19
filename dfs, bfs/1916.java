import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);

        int[] isVisited = new int[n];

        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        int a,b,c;
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        dis[start] = 0;
        int current = start;

        for(int i = 0; i < n; i++){
            isVisited[current] = 1;

            List<Node> currentNodes = graph.get(current);

            //거리 초기화
            for (Node currentNode : currentNodes) {
                dis[currentNode.pos] = Math.min(dis[currentNode.pos], dis[current] + currentNode.weight);
            }

            int minDis = Integer.MAX_VALUE;
            //최소값인 노드를 다음 current로 설정
            for(int j = 0; j < n; j++){
                if(isVisited[j] == 0){
                    if(minDis > dis[j]){
                        minDis = dis[j];
                        current = j;
                    }
                }
            }
        }

        System.out.println(dis[end]);

        br.close();
    }

    private static class Node {
        int pos;
        int weight;

        public Node(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
        }
    }
}


/*
//최적화된 코드
//둘다 다익스트라 알고리즘을 구현한 것이지만, 위에는 직접 dist배열을 탐색해 최소값을 계산하기에 O(n^2)의 복잡도
//아래 코드는 PriorityQueue로 정렬을 해나가면 진행, O((E+V)logV)의 복잡도

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        int result = dijkstra(graph, n, start, end);
        System.out.println(result);

        br.close();
    }

    private static int dijkstra(List<List<Node>> graph, int n, int start, int end) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.pos;

            // 현재 노드와 연결된 인접 노드 탐색
            for (Node neighbor : graph.get(current)) {
                int newDist = distances[current] + neighbor.weight;

                if (newDist < distances[neighbor.pos]) {
                    distances[neighbor.pos] = newDist;
                    pq.add(new Node(neighbor.pos, newDist));
                }
            }
        }

        return distances[end];
    }

    private static class Node {
        int pos;
        int weight;

        public Node(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
        }
    }
}
 */
