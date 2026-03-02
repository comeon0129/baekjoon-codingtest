import java.io.*;
import java.util.*;

public class Main {
	static int r,c,k; // r: x좌표 c: y좌표, k: 목표하는 값
	
	static int[][] matrix = new int[101][101];

	static int row = 3;
	static int column = 3;
	
//	모든 행에 대해서 정렬을 수행하는 함수
	static void rCalc() {
		int maxCol = -1;
		
		for(int i=1; i<=row; i++) {
			int[] cnt = new int[101];
			for(int j=1; j<=column; j++) {
				cnt[matrix[i][j]] ++;
			}
			ArrayList<int[]> list = new ArrayList<>();
			for(int num = 1; num<=100; num++) {
				if(cnt[num] > 0)
					list.add(new int[] {num, cnt[num]});
			}
			list.sort((a,b) -> a[1] != b[1] ? a[1]-b[1] : a[0]-b[0]);
			
			for(int j=1; j<=100; j++) //정렬값 새로 채우기 전에 배열 초기화
 				matrix[i][j] = 0;
			
			if(list.size() * 2 >100) {
				for(int j=0; j<50; j++) {
					matrix[i][2*j+1] = list.get(j)[0];
					matrix[i][2*j+2] = list.get(j)[1];
					maxCol = 100;
				}
			}
			else {
				for(int j=0; j<list.size(); j++) {
					matrix[i][2*j+1] = list.get(j)[0];
					matrix[i][2*j+2] = list.get(j)[1];
					maxCol = Math.max(maxCol, list.size()*2);
				}
			}
		}
		column = maxCol;
	}

// 모든 열에 대해서 정렬을 수행하는 함수
	static void cCalc() {
		int maxRow = -1;
		
		for(int j=1; j<=column; j++) {
			int[] cnt = new int[101];
			for(int i=1; i<=row; i++) {
				cnt[matrix[i][j]] ++;
			}
			ArrayList<int[]> list = new ArrayList<>();
			for(int num = 1; num<=100; num++) {
				if(cnt[num] > 0)
					list.add(new int[] {num, cnt[num]});
			}
			list.sort((a,b) -> a[1] != b[1] ? a[1]-b[1] : a[0]-b[0]);
			
			for(int i=1; i<=100; i++) //정렬값 새로 채우기 전에 배열 초기화
 				matrix[i][j] = 0;
			
			
			if(list.size() * 2 >100) {
				for(int i=0; i<50; i++) {
					matrix[2*i+1][j] = list.get(i)[0];
					matrix[2*i+2][j] = list.get(i)[1];
					maxRow = 100;
				}
			}
			else {
				for(int i=0; i<list.size(); i++) {
					matrix[2*i+1][j] = list.get(i)[0];
					matrix[2*i+2][j] = list.get(i)[1];
					maxRow = Math.max(maxRow, list.size()*2);
				}
			}
		}
		row = maxRow;
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	for(int i=1; i<=row; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=1; j<=column; j++) {
    			matrix[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int time = 0;
    	
    	while(matrix[r][c] !=k && time <= 100) {
    		if(row >= column)
    			rCalc();
    		else
    			cCalc();
    		
    		time++;
    	}
    	
    	if(time > 100)
    		System.out.println(-1);
    	else
    		System.out.println(time);
    }
}