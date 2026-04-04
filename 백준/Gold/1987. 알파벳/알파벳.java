import java.io.*;
import java.util.*;

public class Main {
	static int r,c;
	static char[][] map;
	static boolean visited[] = new boolean[26];
	static int maxCnt = 0;
	static int [] dx  = {-1,1,0,0}; //상하좌우
	static int[] dy = {0,0,-1,1};
	
	static boolean inRange(int x, int y) {
		return 1<= x && x<=r && 1<=y && y<=c;
	}
	static void dfs(int x, int y, int count) {
		maxCnt = Math.max(maxCnt, count);
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(inRange(nx,ny) && !visited[map[nx][ny] - 'A']) {
				visited[map[nx][ny] - 'A'] = true;
				dfs(nx,ny, count +1);
				visited[map[nx][ny] - 'A'] = false;
			}
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r+1][c+1];
		for(int i=1; i<=r; i++) {
			String input = br.readLine();
			for(int j=1; j<=c; j++) {
				map[i][j] = input.charAt(j-1);
			}
		}
		visited[map[1][1] - 'A'] = true; // 1,1 알파벳은 이미 방문처리
		dfs(1,1,1);
		System.out.println(maxCnt);
	}
	
}
