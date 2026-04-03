import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] board;
	static int answer = 0;
	
	static boolean isPossible(int x, int y) {
		//위쪽 세로 줄 부터 검사
		for(int i=0; i<x; i++) {
			if(board[i][y] == 1)
				return false;
		}
		//대각선 왼쪽 위 검사
		int leftX = x-1;
		int leftY = y-1;
		while(leftX >=0 && leftY >= 0) {
			if(board[leftX][leftY] == 1)
				return false;
			leftX--;
			leftY--;
		}
		
		//대각선 오른쪽 위 검사
		int rightX = x-1;
		int rightY = y+1;
		while(rightX >=0 && rightY <n) {
			if(board[rightX][rightY] == 1)
				return false;
			rightX--;
			rightY++;
		}	
		return true;
	}
	
	static void nQueen(int depth) {
		if(depth == n) {
			answer++;
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(isPossible(depth,i)) {
				board[depth][i] = 1;
				nQueen(depth+1);
				board[depth][i] = 0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board = new int[n][n];
		nQueen(0);
		System.out.println(answer);
		
	}
}
