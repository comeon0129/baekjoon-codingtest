import java.io.*;
import java.util.*;

public class Main {
	static int[][] board = new int[10][10];
	
	static boolean isValid(int x, int y, int num) {
		//같은 가로줄에 해당 숫자가 이미 존재하는 지 확인
		for(int i=1; i<=9; i++) {
			if(board[x][i] == num)
				return false;
		}
		//같은 세로줄에 해당 숫자가 이미 존재하는 지 확인
		for(int i=1; i<=9; i++) {
			if(board[i][y] == num)
				return false;
		}
		//3*3 격자 안에 해당 숫자가 이미 존재하는 지 확인
		int startRow = ((x-1)/3) * 3 + 1;
		int startCol = ((y-1)/3) * 3 + 1;
		for(int i = startRow; i< startRow+3; i++) {
			for(int j= startCol; j< startCol+3; j++) {
				if(board[i][j] == num)
					return false;
			}
		}
		
		return true;
	}
	
	static void printBoard() {
		for(int i=1; i<=9; i++) {
			for(int j=1; j<=9; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void inputNum(int idx) {
		if(idx == empty.size()) {
			printBoard();
			System.exit(0);
		}
		
		int[] pos = empty.get(idx);
		int x = pos[0];
		int y = pos[1];
		
		for(int i=1; i<=9; i++) {
			if(isValid(x,y,i)) {
				board[x][y] = i;
				inputNum(idx+1);
				board[x][y] = 0;
			}
		}
	}
	
	static ArrayList<int[]> empty = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=1; i<=9; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 0)
					empty.add(new int[] {i,j});
			}
		}
		inputNum(0);
		
	}
}