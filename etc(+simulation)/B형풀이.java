import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserSolution {
	 static final int MAX_BOARD_SIZE = 10000;
	 static final int MAX_IMAGE_SIZE = 700;
	 // ceil로 최대 크기를 설정
	 static final int MAX_BUCKET_SIZE = (MAX_BOARD_SIZE + MAX_IMAGE_SIZE) / MAX_IMAGE_SIZE;
	 
	 static class Image{
		 int id;
		 int size;
		 int[][] preSum1; // 원본
		 int[][] preSum2; // 90도
		 int[][] preSum3; // 180도
		 int[][] preSum4; // 270도
		 
		 public Image(int id, int size) {
			this.id = id;
			this.size = size;
		 }
	 }
	 
	 static class PressedImage{
		 int id;
		 int dir;
		 // 왼쪽 상단 좌표를 저장
		 int x;
		 int y;
		 public PressedImage(int id, int dir, int x, int y) {
			this.id = id;
			this.dir = dir;
			this.x = x;
			this.y = y;
		 }
	 }
	 
	 static Map<Integer, Image> images = new HashMap<>();
	 static List<PressedImage>[][] pressedImages = new ArrayList[MAX_BUCKET_SIZE][MAX_BUCKET_SIZE];
	 static {
		 for(int i = 0; i < MAX_BUCKET_SIZE; i++) {
			 for(int j = 0; j < MAX_BUCKET_SIZE; j++) {
				 pressedImages[i][j] = new ArrayList<>();
			 }
		 }
	 }
	 
	 static int boardN;
	 static int bucketCnt;
	 
	void init(int N)
	{
		// static 변수들 초기화
		boardN = N;
		bucketCnt = (N + MAX_IMAGE_SIZE - 1) / MAX_IMAGE_SIZE;
		images.clear();
		for(int i = 0; i < bucketCnt; i++) {
			for(int j = 0; j < bucketCnt; j++) {
				pressedImages[i][j].clear();
			}
		}
	
	}

	void addPrint(int mID, int mSize, int mCnt, int mPixel[][])
	{
		// base가 되는소형 이미지를 입력받음
		int n = mSize;
		int[][] base = new int[n][n];
		
		for(int i = 0; i < mCnt; i++) {
			base[mPixel[i][0]][mPixel[i][1]] = mPixel[i][2];
		}
		
		Image img = new Image(mID, n);
		// 가중합 저장 row = 0과 col = 0부분을 패딩
		img.preSum1 = new int[n+1][n+1];
		img.preSum2 = new int[n+1][n+1];
		img.preSum3 = new int[n+1][n+1];
		img.preSum4 = new int[n+1][n+1];

		// 방향 별로 가중합을 저장
		int endIndex = n - 1;
		for (int i = 0; i < n; i++) {
		    int ii = endIndex - i;
		    for (int j = 0; j < n; j++) {
		        int jj = endIndex - j;
		        int r = i + 1, c = j + 1;
		        
		        // (r, c)에 해당되는 base의 값
		        int v0 = base[i][j];     // dir=0: base[i][j]
		        int v1 = base[j][ii];    // dir=1: base[j][n-1-i]
		        int v2 = base[ii][jj];   // dir=2: base[n-1-i][n-1-j]
		        int v3 = base[jj][i];    // dir=3: base[n-1-j][i]

		        // 가중합 갱
		        img.preSum1[r][c] = img.preSum1[r-1][c] + img.preSum1[r][c-1] - img.preSum1[r-1][c-1] + v0;
		        img.preSum2[r][c] = img.preSum2[r-1][c] + img.preSum2[r][c-1] - img.preSum2[r-1][c-1] + v1;
		        img.preSum3[r][c] = img.preSum3[r-1][c] + img.preSum3[r][c-1] - img.preSum3[r-1][c-1] + v2;
		        img.preSum4[r][c] = img.preSum4[r-1][c] + img.preSum4[r][c-1] - img.preSum4[r-1][c-1] + v3;
		    }
		}
		
		images.put(mID, img);
	}

	void pressPrint(int mID, int mRow, int mCol, int mDir)
	{
		// 해당되는 버킷 구역에 저장(mID의 이미작 mDir방향으로 (mRow, mCol)에 저장되었 다는 정보만 저장)
		int br = mRow / MAX_IMAGE_SIZE;
		int bc = mCol / MAX_IMAGE_SIZE;
		pressedImages[br][bc].add(new PressedImage(mID, mDir, mRow, mCol));
	}

	int getDepth(int mRow, int mCol)
	{
		int result = 0;
		
		// 합을 구할 구역
	    int qR1 = mRow;
		int qC1 = mCol;
	    int qR2 = mRow + 24;
		int qC2 = mCol + 24;
		
		// 검사할 버킷 구역
	    int bR = mRow / MAX_IMAGE_SIZE;
	    int bC = mCol / MAX_IMAGE_SIZE;
		
	    // 버킷 근처만 검사, 범위가 아니라면 pass
	    for (int br = bR - 1; br <= bR + 1; br++) {
	        if (br < 0 || br >= bucketCnt) continue;
	        for (int bc = bC - 1; bc <= bC + 1; bc++) {
	            if (bc < 0 || bc >= bucketCnt) continue;
	            
	            // 해당 버킷에 저장된 pressedImage들을 검사
	            for(PressedImage press : pressedImages[br][bc]) {
	            	Image img = images.get(press.id);
	            	
	            	// 실제 board에 반영되어 있는 image 정보
	                int imgSize   = img.size;
	                int imgR1    = press.x;
	                int imgC1   = press.y;
	                int imgR2 = imgR1  + imgSize - 1;
	                int imgC2  = imgC1 + imgSize - 1;
	            	
	                // 겹치는 구역의 좌표
	                int oR1 = (qR1 > imgR1) ? qR1 : imgR1;
	                int oC1 = (qC1 > imgC1) ? qC1 : imgC1;
	                int oR2 = (qR2 < imgR2) ? qR2 : imgR2;
	                int oC2 = (qC2 < imgC2) ? qC2 : imgC2;
	                
	                // 교차 없으면 패스
	                if (oR1 > oR2 || oC1 > oC2) continue;
	                
	                // 좌표를 가중합 기준 좌표로 다시 치
	                // (pressed top-left를 원점으로 한 로컬 좌표)
	                int iR1 = oR1 - imgR1;
	                int iC1 = oC1 - imgC1;
	                int iR2 = oR2 - imgR1;
	                int iC2 = oC2 - imgC1;
	                
	                // 가중합을 계산, 가중합 죄표는 +1만큼 패딩했니 반영해서 계산
	                int r1 = iR1, c1 = iC1, r2 = iR2 + 1, c2 = iC2 + 1;
	                if (press.dir == 0) {
	                	result += img.preSum1[r2][c2]
	                           - img.preSum1[r1][c2]
	                           - img.preSum1[r2][c1]
	                           + img.preSum1[r1][c1];
	                } else if (press.dir == 1) {
	                	result += img.preSum2[r2][c2]
	                           - img.preSum2[r1][c2]
	                           - img.preSum2[r2][c1]
	                           + img.preSum2[r1][c1];
	                } else if (press.dir == 2) {
	                	result += img.preSum3[r2][c2]
	                           - img.preSum3[r1][c2]
	                           - img.preSum3[r2][c1]
	                           + img.preSum3[r1][c1];
	                } else { // press.dir == 3
	                	result += img.preSum4[r2][c2]
	                           - img.preSum4[r1][c2]
	                           - img.preSum4[r2][c1]
	                           + img.preSum4[r1][c1];
	                }
	            }
	        }
        }
	    
		
		return result;
	}
}