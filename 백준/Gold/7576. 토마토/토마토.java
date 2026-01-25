import java.io.*;
import java.util.*;

public class Main {
	
	public static int m,n; // m: 가로칸, n: 세로칸
	public static int[][] box;
	
	public static Queue<int[]> q = new LinkedList<>();
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	public static void bfs() {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int x= pos[0];
			int y = pos[1];
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(inRange(nx, ny) && box[nx][ny] == 0) {
					box[nx][ny] = box[x][y]+1;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
	}
	
	public static boolean isNotAll() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(box[i][j] == 0)
					return true;
			}
		}
		return false;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	box = new int[n][m];
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<m; j++) {
    			box[i][j] = Integer.parseInt(st.nextToken()); 
    		}
    	}
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			if(box[i][j] == 1) {
    				q.add(new int[] {i,j});
    			}
    		}
    	}
    	bfs();	
    	if(isNotAll()) {
    		System.out.println(-1);
    	}
    	else {
    		int max = 1;
    		for(int i=0; i<n; i++) {
    			for(int j=0; j<m; j++) {
    				if(box[i][j] > max)
    					max = box[i][j];
    			}
    		}
    		System.out.println(max-1);
    	}
    }
}
