import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] buildTime = new int[n];
        int[] result = new int[n];

        int[] preNum = new int[n];
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            buildTime[i] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken()) - 1;
            while(num != -2){
                graph.get(num).add(i);
                preNum[i]++;
                num = Integer.parseInt(st.nextToken()) - 1;
            }

            if(preNum[i] == 0){
                result[i] = buildTime[i];
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            Integer current = queue.remove();

            for(int i = 0; i < graph.get(current).size(); i++){
                Integer target = graph.get(current).get(i);
                result[target] = Math.max(result[target], result[current] + buildTime[target]);
                preNum[target]--;
                if(preNum[target] == 0){
                    queue.add(target);
                }

            }

        }

        for (int i : result) {
            bw.write(i+"\n");
        }
        bw.flush();




        br.close();
        bw.close();
    }
}


/*
//동일한 풀이, 가독성 향상
// 위상정렬의 경우 inDegree, graph의 용어가 어울림
 import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = Integer.parseInt(br.readLine());
            int[] buildTime = new int[n];
            int[] totalTime = new int[n];
            int[] inDegree = new int[n];
            List<List<Integer>> graph = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                buildTime[i] = Integer.parseInt(st.nextToken());

                while (true) {
                    int prerequisite = Integer.parseInt(st.nextToken()) - 1;
                    if (prerequisite == -2) break;
                    graph.get(prerequisite).add(i);
                    inDegree[i]++;
                }
            }

            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    totalTime[i] = buildTime[i];
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int next : graph.get(current)) {
                    totalTime[next] = Math.max(totalTime[next], totalTime[current] + buildTime[next]);
                    if (--inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            for (int time : totalTime) {
                bw.write(time + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} 
 */
