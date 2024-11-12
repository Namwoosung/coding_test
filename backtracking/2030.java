import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Deque<String> queue = new ArrayDeque<>();
        queue.add("2"); queue.add("3"); queue.add("5"); queue.add("7");

        while(!queue.isEmpty()){
            String now = queue.remove();
            if(now.length() == n) bw.write(now + "\n");
            else{
                for(int i = 0; i < 10; i++){
                    int target = Integer.parseInt(now + String.valueOf(i));
                    if(checkPrime(target)) queue.add(String.valueOf(target));
                }
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }

    private static boolean checkPrime(int target) {
        for(int i = 2; i <= Math.sqrt(target); i++){
            if(target % i == 0) return false;
        }
        return true;
    }
}

/*
더 효율적인 코드, 접근 방식은 동일하지만 굳이 string으로 처리할 필요 없이 int로 처리 한 코드
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(2); queue.add(3); queue.add(5); queue.add(7);

        while(!queue.isEmpty()){
            int now = queue.remove();
            if(Integer.toString(now).length() == n) {
                bw.write(now + "\n");
            } else {
                for(int i = 1; i < 10; i += 2) { // 홀수만 추가
                    int target = now * 10 + i;
                    if(checkPrime(target)) queue.add(target);
                }
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }

    private static boolean checkPrime(int target) {
        if(target < 2) return false;
        if(target == 2) return true;
        if(target % 2 == 0) return false; // 짝수 제거

        for(int i = 3; i <= Math.sqrt(target); i += 2) { // 홀수만 검사
            if(target % i == 0) return false;
        }
        return true;
    }
}
 */
