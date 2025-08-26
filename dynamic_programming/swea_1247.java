import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
class Solution{
    static int[][] allNode = new int[12][2];
    static int[][] allDist = new int[12][12];
    static int[][] dp = new int[1024][10]; // 지나온 과정을 dp로 저장
    static int N;
    static int end;
    static int result;
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase = 1; testCase <= T; testCase++) {
            // 입력값 처리 먼저 각 노드들의 좌표를 저장
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            allNode[0][0] = Integer.parseInt(st.nextToken());
            allNode[0][1] = Integer.parseInt(st.nextToken());
            allNode[1][0] = Integer.parseInt(st.nextToken());
            allNode[1][1] = Integer.parseInt(st.nextToken());
            for(int i = 0; i < N; i++) {
                allNode[i+2][0] = Integer.parseInt(st.nextToken());
                allNode[i+2][1] = Integer.parseInt(st.nextToken());
            }
             
            // 각 노드별 거리를 저장(양방향으로 전부 저장)
            for(int i = 0; i < N + 2; i ++) {
                for(int j = i + 1; j < N+2; j++) {
                    int dist = Math.abs(allNode[i][0] - allNode[j][0]) + Math.abs(allNode[i][1] - allNode[j][1]);
                    allDist[i][j] = dist;
                    allDist[j][i] = dist;
                }
            }
            int allVisited = (1<<N)-1;
 
            // 이번에 사용할 만큼 DP 배열 초기화
            for(int i = 0; i <= allVisited; i++) {
                for(int j = 0; j < N; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
             
            // 먼저 회사에서 각 고객에게 가는 경우를 dp에 저장
            for(int i = 0; i < N; i++) {
                dp[(1<<i)][i] = allDist[0][i+2];
            }
 
            // 모든 노드를 경유하는 case를 dp로 update해 나감
            for(int nowVisited = 3; nowVisited <= allVisited; nowVisited++) {
                 
                // 현재 방문처리를 기준으로, i번째 인덱스가 마지막 방문일 경우를 저장
                for(int lastNode = 0; lastNode < N; lastNode++) {
                    // 아직 방문한 게 아니면 pass
                    if((nowVisited & (1<<lastNode)) == 0) continue;
                     
                    // 이전 방문
                    int prevVisited = nowVisited - (1<<lastNode);
                    if(prevVisited == 0) break; // 이전 방문이 0이라면(지금이 처음 고객이라면) pass
                     
                    // 이전 방문에서 현재 방문으로 넘어올 수 있는 경우를 dp로 업데이트
                    for(int i = 0; i < N; i++) {
                        if((prevVisited & (1<<i)) == 0) continue; // 이전 방문에서 방문 안 한 노드면 pass
                             
                        dp[nowVisited][lastNode] = Math.min(dp[nowVisited][lastNode], dp[prevVisited][i] + allDist[i+2][lastNode+2]);
                    }
                }
            }
             
             
            // 마지막 고객에서 집으로 가는 경우를 순회하며 result를 update
            result = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++) {
                result = Math.min(dp[allVisited][i] + allDist[i+2][1], result);
            }
             
            sb.append("#").append(testCase).append(" ").append(result).append("\n");
             
        }
        System.out.print(sb);
    }
}