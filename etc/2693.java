import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int testcase = Integer.parseInt(st.nextToken());
        for(int i = 0; i < testcase; i++){
            st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
            for(int j = 0; j < 10; j++){
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list, Collections.reverseOrder());
            bw.write(String.valueOf(list.get(2)) + '\n');
        }

        bw.flush();
    }
}

/*
더 효율적인 코드, list 대신 heap을 사용해 크기가 3번째로 큰 값 출력
package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testcase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(3);

            for (int j = 0; j < 10; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (minHeap.size() < 3) {
                    minHeap.offer(num);
                } else if (num > minHeap.peek()) {
                    minHeap.poll();
                    minHeap.offer(num);
                }
            }
            // 3번째로 큰 값 출력
            bw.write(minHeap.peek() + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
 */
