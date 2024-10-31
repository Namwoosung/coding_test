import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        int count = 0;
        int covered = -1;
        int pos = 0;
        for(int i = 0; i < n; i++){
            pos = list.get(i);
            if(pos <= covered) continue;
            count++;
            covered = pos + l - 1;
        }

        bw.write(String.valueOf(count));
        bw.flush();

    }
}

/*
개선된 코드(by GPT)
방법은 동일하지만 list가 아닌 TreeSet을 활용해 더 효율적으로 풀이
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        TreeSet<Integer> positions = new TreeSet<>();
        while (st.hasMoreTokens()) {
            positions.add(Integer.parseInt(st.nextToken()));
        }

        int count = 0;
        int covered = -1;

        for (int pos : positions) {
            if (pos <= covered) continue;
            count++;
            covered = pos + l - 1;
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
 */
