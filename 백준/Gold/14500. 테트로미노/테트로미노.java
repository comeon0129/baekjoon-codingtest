import java.io.*;
import java.util.*;

public class Main {
	public static int n,m; // n: 세로 (x) m: 가로 (y)
	public static int[][] paper;
	public static int maxSum = 0;
	
	public static int[] dx = {-1,1,0,0}; //상,하,좌,우 순서
	public static int[] dy = {0,0,-1,1}; //상 하 좌 우 순서
	public static boolean[][] visited;
	
	public static boolean inRange(int x, int y) {
		return 0<=x && x<n && 0<=y && y<m;
	}
	
	public static void dfs(int x, int y, int depth, int sum) {
//		1. 들어올때 방문체크
		visited[x][y] = true;
		if(depth ==4) {
			if(sum > maxSum)
				maxSum = sum;
			visited[x][y] = false;
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(inRange(nx,ny) && !visited[nx][ny]) {
				dfs(nx,ny,depth+1,sum+paper[nx][ny]);
			}
		}
		visited[x][y] = false;
		return;
	}
	
	public static void checkException(int x, int y) {
		int sum= 0;
//		1. ㅗ 자 모양 처리
		if(y-1>=0 && y+1<m && x-1>=0) {
			sum = paper[x][y-1]+paper[x][y]+paper[x][y+1]+paper[x-1][y];
			if(sum > maxSum)
				maxSum = sum;
		}
//		2. ㅜ 자 모양 처리
		if(y-1>=0 && y+1<m && x+1<n) {
			sum = paper[x][y-1]+paper[x][y]+paper[x][y+1]+paper[x+1][y];
			if(sum > maxSum)
				maxSum = sum;
		}
//		3. ㅓ 자 모양 처리
		if(y-1>=0 && x-1>=0 && x+1<n) {
			sum = paper[x][y-1]+paper[x][y]+paper[x-1][y]+paper[x+1][y];
			if(sum > maxSum)
				maxSum = sum;
		}
//		4. ㅏ 자 모양 처리
		if(y+1<m && x-1>=0 && x+1<n) {
			sum = paper[x][y+1]+paper[x][y]+paper[x-1][y]+paper[x+1][y];
			if(sum > maxSum)
				maxSum = sum;
		}
		return;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	paper = new int[n][m];
    	visited = new boolean[n][m];
    	
    	for(int i=0; i<n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<m; j++) {
    			paper[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
//    	4개의 모양은 dfs로 처리 왜냐? 한붓그리기가 가능하니까
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			dfs(i,j,1,paper[i][j]);
    		}
    	}
    	
//    	ㅜ자 모양은 한붓그리기가 불가능하기에 따로 예외처리 (ㅓ,ㅏ,ㅗ,ㅜ)
    	for(int i=0; i<n; i++) {
    		for(int j=0; j<m; j++) {
    			checkException(i,j);
    		}
    	}
    	System.out.println(maxSum);
    }
}