import java.io.*;
import java.util.*;

class CCTV {
	public int number;
	public int x_pos;
	public int y_pos;
	public int direction = -1;
	public CCTV(int number, int x_pos, int y_pos) {
		this.number = number;
		this.x_pos = x_pos;
		this.y_pos = y_pos;
	}
}

public class Main {
	public static int n,m; //n: x m: y
	public static int[][] room;
	public static ArrayList<CCTV> cctvs = new ArrayList<>();
	public static int[][] copyRoom;
	
	public static int minArea = Integer.MAX_VALUE;
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	public static void watch(int x, int y, int dir) {
		int[] dx = {0,1,0,-1}; //동 남 서 북 순서
		int[] dy = {1,0,-1,0}; 
		
		dir = dir %4; // 방향이 범위를 벗어나지 않게 해주기 위함.
		
		while(true) {
			x= x+dx[dir];
			y=y+dy[dir];
			
			if(!inRange(x,y) || copyRoom[x][y] == 6)
				return;
			if(copyRoom[x][y] == 0)
				copyRoom[x][y] = -1;
		}
		
	}
	
	public static void calcMin() {
		copyRoom = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copyRoom[i][j] = room[i][j];
			}
		}
		
		for (CCTV c : cctvs) {
			if(c.number == 1) {
				watch(c.x_pos,c.y_pos,c.direction);
			}
			else if(c.number == 2) {
				watch(c.x_pos, c.y_pos, c.direction);
				watch(c.x_pos, c.y_pos, c.direction+2);
			}
			else if(c.number == 3) {
				watch(c.x_pos,c.y_pos, c.direction);
				watch(c.x_pos,c.y_pos,c.direction+1);
			}
			else if(c.number == 4) {
				watch(c.x_pos,c.y_pos,c.direction);
				watch(c.x_pos,c.y_pos,c.direction+1);
				watch(c.x_pos,c.y_pos,c.direction+2);
			}
			else {
				watch(c.x_pos,c.y_pos,c.direction);
				watch(c.x_pos,c.y_pos,c.direction+1);
				watch(c.x_pos,c.y_pos,c.direction+2);
				watch(c.x_pos,c.y_pos,c.direction+3);
			}
		}
		
		int area = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(copyRoom[i][j] ==0)
					area++;
			}
		}
		minArea = Integer.min(area, minArea);
	}
	
	public static void chooseDirections(int idx) {
		if(idx == cctvs.size()) {
			calcMin();
			return;
		}
		CCTV cctv = cctvs.get(idx);
		for(int d=0; d<4; d++) {
			cctv.direction = d;
			chooseDirections(idx+1);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	room = new int[n][m];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<m; j++) {
    			room[i][j] = Integer.parseInt(st.nextToken());
    			if(room[i][j]>=1 && room[i][j] <=5) {
    				cctvs.add(new CCTV(room[i][j], i, j));
    			}
    		}
    	}
    	
    	chooseDirections(0);
    	System.out.println(minArea);
    }
}