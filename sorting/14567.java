import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] course = new int[n];
        int[] into = new int[n];
        List<List<Integer>> preCourse = new ArrayList<>();
        for(int i = 0; i < n; i++){
            preCourse.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            preCourse.get(a).add(b);
            into[b]++;
        }

        Deque<Integer> queue = new ArrayDeque();

        for(int i = 0; i < n; i++){
            if(into[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            Integer now = queue.remove();

            List<Integer> nowList = preCourse.get(now);
            for (Integer i : nowList) {
                course[i] = Math.max(course[i], course[now] + 1);
                into[i]--;
                if(into[i] == 0) queue.add(i);
            }
        }

        for (int i : course) {
            bw.write((i + 1) + " ");
        }
        bw.flush();

        br.close();
        bw.close();

    }
}

/*
똑같은데 변수 명 같은 게 더 위상정렬에 적합하게 설정됨
import java.io.*;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] order = new int[n];       // 각 노드의 위상 정렬 순서
            int[] inDegree = new int[n];    // 진입 차수 배열
            List<List<Integer>> graph = new ArrayList<>(); // 인접 리스트 그래프
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                graph.get(a).add(b);   // 간선 추가
                inDegree[b]++;         // 진입 차수 증가
            }

            Queue<Integer> queue = new ArrayDeque<>(); // 위상 정렬을 위한 큐
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i); // 진입 차수가 0인 노드를 큐에 추가
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.remove();
                for (int next : graph.get(current)) {
                    order[next] = Math.max(order[next], order[current] + 1);
                    if (--inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            for (int i : order) {
                bw.write((i + 1) + " ");
            }
            bw.flush();
        } finally {
            // BufferedReader와 BufferedWriter를 명시적으로 닫아줌
            br.close();
            bw.close();
        }
    }
}
*/
