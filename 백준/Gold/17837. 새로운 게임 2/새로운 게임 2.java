import java.io.*;
import java.util.*;

class Piece {
	int x,y,dir;
	
	public Piece(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		// TODO Auto-generated constructor stub
	}
}

public class Main {
	static int n, k; // n: 체스판 크기 k: 말의 개수
	
	static int[] dx = {0,0,-1,1}; //우,좌,상,하 순서
	static int[] dy = {1,-1,0,0}; 
	
	static int[] reverse = {1,0,3,2};
	
	static int[][] board_color;
	
	static Piece[] pieces;
	
	static ArrayList<Integer>[][] board;
	
	static boolean inRange(int x, int y) {
		return 1<=x && x<=n && 1<=y && y<=n;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	board_color = new int[n+1][n+1];
    	pieces = new Piece[k+1];
    	board = new ArrayList[n+1][n+1];
    	
    	for(int i=1; i<=n; i++) {
    		for(int j=1; j<=n; j++) {
    			board[i][j] = new ArrayList<>(); 
    		}
    	}
    	
    	for(int i=1; i<=n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=1; j<=n; j++) {
    			board_color[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i=1; i<=k; i++) {
    		st = new StringTokenizer(br.readLine());
    		int x = Integer.parseInt(st.nextToken());
    		int y = Integer.parseInt(st.nextToken());
    		int dir = Integer.parseInt(st.nextToken());
    		pieces[i] = new Piece(x,y,dir-1);
    	}
    	
    	//1. 보드판 위에 시작 말 깔기
    	for(int i=1; i<=k; i++) {
    		board[pieces[i].x][pieces[i].y].add(i);
    	}
    	
    	
    	int turn = 0;
    	boolean exit = false;
    	while(turn <= 1000 && !exit) {
    		
    		for(int i=1; i<=k; i++) {
    			int x = pieces[i].x;
    			int y = pieces[i].y;
    			int dir = pieces[i].dir;
    			
    			int p_index = -1; //해당 칸에서 현재 말이 위치한 index
    			
    			for(int j=0; j<board[x][y].size(); j++) {
    				if(board[x][y].get(j) == i) {
    					p_index = j;
    					break;
    				}
    			}
    			
    			int nx = x+dx[dir];
    			int ny = y+dy[dir];
    			
    			if(!inRange(nx,ny) || board_color[nx][ny] == 2) {//범위를 벗어나거나 파란색 칸 인경우
    				dir = reverse[dir];
    				pieces[i].dir = dir; //갱신 해줘야함.
    				
    				nx = x+dx[dir];
    				ny = y+dy[dir];
    				
    				//그래도 여전히 범위를 벗어나거나 파란색 칸이면 그냥 멈춰섬
    				if(!inRange(nx,ny) || board_color[nx][ny] == 2)
    					continue;
    			}
    			
    			List<Integer> moving = new ArrayList<>(board[x][y].subList(p_index, board[x][y].size()));
    			
    			//이동할 애들의 말 좌표 전부 수정
    			for(Integer j: moving) {
    				pieces[j].x = nx;
    				pieces[j].y = ny;
    			}
    			
    			//원래 칸에서 제거
    			board[x][y].subList(p_index, board[x][y].size()).clear();
    			
    			//흰색인 경우 처리
    			if(board_color[nx][ny] == 0) {
    				board[nx][ny].addAll(moving);
    			}
    			//빨간색인경우 처리
    			else {
    				Collections.reverse(moving);
    				board[nx][ny].addAll(moving);
    			}
    			
        		//종료조건 확인
        		for(int j=1; j<=n; j++) {
        			for(int r=1; r<=n; r++) {
        				if(board[j][r].size() >=4)
        					exit = true;
        			}
        		}
        		if(exit)
        			break;
    			
    		}
    		
    		turn++;
    	}
    	
    	if(!exit)
    		System.out.println(-1);
    	else
    		System.out.println(turn);
    }
}

