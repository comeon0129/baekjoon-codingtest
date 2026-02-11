import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] numbers;
	static int[] operatorCounts = new int[4]; // 0: +, 1: -, 2: * , 3: /
	static int maxVal = Integer.MIN_VALUE;
	static int minVal = Integer.MAX_VALUE;
	
	public static void dfs(int curResult, int depth) {
		if(depth == n) {
			maxVal = Math.max(curResult, maxVal);
			minVal = Math.min(curResult, minVal);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operatorCounts[i] >0) {
				operatorCounts[i] --;
				int nextResult = 0;
				switch(i) {
					case 0: nextResult = curResult + numbers[depth]; break;
					case 1: nextResult = curResult - numbers[depth]; break;
					case 2: nextResult = curResult * numbers[depth]; break;
					case 3: nextResult = curResult / numbers[depth]; break;
				}
			
				dfs(nextResult, depth+1);
				operatorCounts[i] ++;
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n= Integer.parseInt(st.nextToken());
    	numbers = new int[n];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<n; i++) {
    		numbers[i] = Integer.parseInt(st.nextToken());
    	}
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<4; i++) {
    		operatorCounts[i] = Integer.parseInt(st.nextToken());
    	}
    	dfs(numbers[0],1); //첫번째 숫자를 초기값으로 주고 다음 인덱스부터 탐색
    	System.out.println(maxVal);
    	System.out.println(minVal);
    }
}