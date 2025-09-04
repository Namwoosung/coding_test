import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static class Atom {
        int id;
        int x;
        int y;
        int dir;
        int energy;

        public Atom(int id, int x, int y, int dir, int energy) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
    }

    static Map<Integer, Atom> atoms = new HashMap<>();
    static Map<Integer, Integer> ids = new HashMap<>();
    static Set<Integer> removeCandiate = new HashSet<>();
    static Set<Integer> deleteCandiate = new HashSet<>();

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            // static 변수 초기화
            atoms.clear();

            // 입력값 처리
            int left = Integer.MAX_VALUE;
            int right = Integer.MIN_VALUE;
            int top = Integer.MIN_VALUE;
            int bottom = Integer.MAX_VALUE;

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());
                atoms.put(i, new Atom(i, x, y, dir, energy));

                left = Math.min(x, left);
                right = Math.max(x, right);
                top = Math.max(y, top);
                bottom = Math.min(y, bottom);
            }

            int result = 0;
            // 모든 원자가 모두 소멸할 때까지 반복
            while (!atoms.isEmpty()) {
                // 현재 위치들은 초기화
                ids.clear();
                removeCandiate.clear();
                deleteCandiate.clear();

                // Map에서 하나씩 꺼내가며 이동을 시작
                Set<Integer> nowKeys = new HashSet<>(atoms.keySet());
                for (int id : nowKeys) {
                    Atom now = atoms.get(id);

                    int nx = now.x + dx[now.dir];
                    int ny = now.y + dy[now.dir];

                    // 좌표를 키로 변환 (오버플로우 방지를 위해 long 타입 사용)
                    int newPosKey = nx * 4100 + ny;
                    
                    // 경로가 겹친 경우: 2개의 원자 모두 삭제 처리
                    if (ids.containsKey(newPosKey)) {
                        removeCandiate.add(id);
                        removeCandiate.add(ids.get(newPosKey));
                        continue;
                    }

                    // 범위 끝에 도달한 경우 삭제 처리
                    if (nx < left || nx > right || ny > top || ny < bottom) {
                        deleteCandiate.add(id);
                    } else { // 정상 이동한 경우
                        ids.put(newPosKey, id);
                        now.x = nx;
                        now.y = ny;
                    }
                }

                //충돌한 경우
                for (int id : removeCandiate) {
                    Atom now = atoms.remove(id);
                    if (now != null) {
                        result += now.energy;
                    }
                }
                //범위 밖으로 나가서 삭제된 경우
                for (int id : deleteCandiate) {
                    atoms.remove(id);
                }
            }
            sb.append("#").append(testCase).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }
}