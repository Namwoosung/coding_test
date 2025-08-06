import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[] arr;
	static int[] opArr;
	static int winCnt; static int loseCnt;
	static Set<Integer> cards;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			
			st = new StringTokenizer(br.readLine());
			arr = new int[9];
			Set<Integer> mine = new HashSet<>();
			for(int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				mine.add(arr[i]);
			}
			
			//상대방 카드 저장
			opArr = new int[9]; int index = 0;
			for(int i = 1; i <= 18; i++) {
				if(!mine.contains(i)) {
					opArr[index] = i;
					index++;
				}
			}
			
			//점수 최대값 계산
			int remain = 0;
			for(int i = 1; i <= 18; i++) {
				remain += i;
			}
			
			winCnt = 0; loseCnt = 0;
			cards = new HashSet<>();
			// 모든 값을 넘기면서 호출
			for(int i = 0; i < 9; i++) {
				dfs(0, opArr[i], 0, 0, remain);
			}
			
			sb.append("#").append(testCase).append(" ").append(winCnt).append(" ").append(loseCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static void dfs(int index, int num, int myScore, int opScore, int remainScore) {
		// 카드 추가 및 점수 계산
		cards.add(num);
		int nowScore = arr[index] + num;
		if(arr[index] > num) {
			myScore += nowScore;
		}else {
			opScore += nowScore;
		}
		remainScore -= nowScore;
		
		// 종료 조건 검사: 현재 상황 기준으로 이미 승패가 결정된 경우
		if(myScore > opScore + remainScore) { // 무조건 내가 이기는 경우
			int cnt = 1;
			for(int i = 9 - cards.size(); i > 1; i--) {
				cnt *= i;
			}
			winCnt += cnt;
			cards.remove(num);
			return;
			
		} else if(myScore + remainScore < opScore) { // 무조건 지는 경우
			int cnt = 1;
			for(int i = 9 - cards.size(); i > 1; i--) {
				cnt *= i;
			}
			loseCnt += cnt;
			cards.remove(num);
			return;
			
		}
		
		// 다음 인덱스 호출
		for(int i = 0; i < 9; i++) {
			if(!cards.contains(opArr[i])) { // 현재 포함되지 않은 카드에 대해서만 전달
				dfs(index+1, opArr[i], myScore, opScore, remainScore);
			}
		}
		cards.remove(num);
	}
}
