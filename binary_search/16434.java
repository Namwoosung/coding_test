import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Long standAtk = Long.valueOf(st.nextToken());

        int[][] room = new int[n][3];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            room[i][0] = Integer.parseInt(st.nextToken());
            room[i][1] = Integer.parseInt(st.nextToken());
            room[i][2] = Integer.parseInt(st.nextToken());
        }

        Long start = 1L;
        Long end = Long.MAX_VALUE - 1;
        Long mHP;
        Long cHP;
        Long atk;

        boolean is_pos;

        Long result = 0L;
        while(start <= end){
            mHP = (start + end) / 2;
            cHP = mHP;
            atk = standAtk;
            is_pos = true;

            for(int i = 0; i < n; i++){
                if(room[i][0] == 1){
                    //몬스터 방
                    cHP -= (room[i][1] * (room[i][2] / atk));
                    if((room[i][2] % atk) == 0 ){
                        //딱 덜어지는 겨웅에는 마지막 공격을 받지 않음
                        cHP += room[i][1];
                    }


                    if(cHP <= 0){
                        is_pos = false;
                        break;
                    }
                }else{
                    //포션방
                    atk = Math.min(Long.MAX_VALUE, atk + room[i][1]);
                    cHP = Math.min(mHP, cHP + room[i][2]);
                }
            }

            if(is_pos){
                result = mHP;
                end = mHP -1;
            }
            else{
                start = mHP + 1;
            }

        }

        System.out.println(result);

        br.close();
    }
}
