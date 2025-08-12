//내 코드, 매번 반복문을 돌면서 좌우 쵀대 벽의 크기를 확인 -> O(n^2)
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] blocks = new int[w];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < w; i++){
            blocks[i] = Integer.parseInt(st.nextToken());

        }

        int left_wall = 0; int right_wall = 0;
        int left = 0; int right = 0;
        int water = 0;
        for(int i = 0; i < w; i++){

            left_wall = blocks[i];
            left = i - 1;
            while (left > -1){
                if(blocks[left] > left_wall){
                    left_wall = blocks[left];
                }
                left--;
            }


            right_wall = blocks[i];
            right = i + 1;
            while(right < w){
                if(blocks[right] > right_wall){
                    right_wall = blocks[right];
                }
                right++;
            }

            if(left_wall > blocks[i] && right_wall > blocks[i]){
                water += Math.min(left_wall, right_wall) - blocks[i];
            }

        }

        bw.write(String.valueOf(water));
        bw.flush();
    }
}

/*
//더 효율적인 코드, 미리 각각의 위치에서 왼쪽과 오른쪽의 최대값을 배열로 정의 -> O(N)
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] blocks = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        // 좌우 최대값 배열을 미리 계산
        int[] leftMax = new int[w];
        int[] rightMax = new int[w];

        leftMax[0] = blocks[0];
        for (int i = 1; i < w; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blocks[i]);
        }

        rightMax[w - 1] = blocks[w - 1];
        for (int i = w - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blocks[i]);
        }

        // 물의 양 계산
        int water = 0;
        for (int i = 0; i < w; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - blocks[i];
        }

        bw.write(String.valueOf(water));
        bw.flush();
    }
}

 */
