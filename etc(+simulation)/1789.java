import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long s = Long.parseLong(br.readLine());

        long stand = 0;
        if(s == 1){
            bw.write(String.valueOf(1));
            bw.flush();
            return;
        }
        for(int i = 0; i <= s; i ++){
            stand += i;
            if(stand > s){
                bw.write(String.valueOf(i - 1));
                bw.flush();
                break;
            }
        }
    }
}
