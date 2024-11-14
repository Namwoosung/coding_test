import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] workTime = new int[n];
        int[] totalWorkTime = new int[n];
        int[] inDegree = new int[n];
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            workTime[i] = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++){
                int a = Integer.parseInt(st.nextToken()) - 1;

                graph.get(a).add(i);
                inDegree[i]++;
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            if(inDegree[i] == 0){
                queue.add(i);
                totalWorkTime[i] = workTime[i];
            }
        }

        while(!queue.isEmpty()){
            Integer current = queue.remove();
            List<Integer> currentList = graph.get(current);

            for (Integer target : currentList) {
                totalWorkTime[target] = Math.max(totalWorkTime[target], totalWorkTime[current] + workTime[target]);
                inDegree[target]--;
                if(inDegree[target] == 0) queue.add(target);
            }
        }

        bw.write(String.valueOf(Arrays.stream(totalWorkTime).max().orElseThrow()));


        br.close();
        bw.close();
    }
}
