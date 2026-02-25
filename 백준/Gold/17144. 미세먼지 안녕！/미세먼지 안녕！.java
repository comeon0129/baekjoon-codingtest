
import java.io.*;
import java.util.*;

public class Main {
	static int r,c,t; //r : x, c: y, t: 시간
	
	static int[][] room;
		
	static ArrayList<Integer> cleaner_x = new ArrayList<>();
	
	static void spread() {
		
//		1. 미세먼지 확장과정
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		
		int[][] diff = new int[r][c];
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(room[i][j] != -1 && room[i][j] != 0) {
					int spreadDir = 0;
					for(int k =0; k<4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(inRange(nx,ny) && room[nx][ny] != -1) {
							spreadDir++;
							diff[nx][ny] += room[i][j] /5;
						}
					}
					diff[i][j] -= (room[i][j]/5) * spreadDir;
				}
			}
		}
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				room[i][j] += diff[i][j];
			}
		}
		
//		2. 공기청정기 작동
		int upCleaner = cleaner_x.get(0); //윗부분 청소
		//2-1 윗부분 맨 왼쪽부터 처리
		for(int i= upCleaner-1; i>=1; i--)
			room[i][0] = room[i-1][0];
		//2-1 윗부분 맨 위쪽 처리
		for(int j=0; j<c-1; j++)
			room[0][j] = room[0][j+1];
		//2-2 윗부분 맨 오른쪽 처리
		for(int i=0; i<upCleaner; i++)
			room[i][c-1] = room[i+1][c-1];
		//2-3 윗부분 맨 아래쪽 처리
		for(int j=c-1; j>=2; j--) {
			room[upCleaner][j] = room[upCleaner][j-1];
		}
		room[upCleaner][1] = 0;
		
		int downCleaner = cleaner_x.get(1);
		//2-4 아랫부분 맨 왼쪽부터 처리
		for(int i= downCleaner+1; i<r-1; i++)
			room[i][0] = room[i+1][0];
		//2-5 아랫부분 맨 아래쪽 처리
		for(int j=0; j<c-1; j++)
			room[r-1][j] = room[r-1][j+1];
		//2-6 아랫부분 맨 오른쪽 처리
		for(int i=r-1; i>downCleaner; i--)
			room[i][c-1] = room[i-1][c-1];
		//2-7 아랫부분 맨 위쪽 처리
		for(int j = c-1; j>1; j--) {
			room[downCleaner][j] = room[downCleaner][j-1];
		}
		room[downCleaner][1] = 0;
		
	}
	static boolean inRange(int x, int y) {
		return 0<=x && x<r && 0<=y && y<c; 
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	t = Integer.parseInt(st.nextToken());
    	room = new int[r][c];
    	
    	for(int i=0; i<r; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<c; j++) {
    			room[i][j] = Integer.parseInt(st.nextToken());
    			if(room[i][j] == -1) {
    				cleaner_x.add(i);
    			}
    		}
    	}
    	
    	while(t-->0) {
    		spread();
    	}
    	
    	int answer =0;
    	
    	for(int i=0; i<r; i++) {
    		for(int j=0; j<c; j++) {
    			if(room[i][j] != -1 && room[i][j] != 0)
    				answer+=room[i][j];
    		}
    	}
    	System.out.println(answer);
    }
    	
}