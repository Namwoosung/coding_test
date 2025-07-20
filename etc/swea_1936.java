import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken());

        if ((a == 1 && b == 3) || (a == 2 && b == 1) || (a == 3 && b == 2)) {
            bw.write("A");
        } else {
            bw.write("B");
        }
        bw.flush();
    }
}