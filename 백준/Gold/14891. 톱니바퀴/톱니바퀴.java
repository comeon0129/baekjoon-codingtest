import java.io.*;
import java.util.*;

public class Main {
	static int[] first = new int[8];
	static int[] second = new int[8];
	static int[] third = new int[8];
	static int[] fourth = new int[8];
	static int k;
	
	public static void clock(int num) {
		switch(num) {
		case 1:
			int temp = first[7];
			for(int i=7; i>0; i--)
				first[i] = first[i-1];
			first[0] = temp;
			break;
		case 2:
			temp = second[7];
			for(int i=7; i>0; i--)
				second[i] = second[i-1];
			second[0] = temp;
			break;
		case 3:
			temp = third[7];
			for(int i=7; i>0; i--)
				third[i] = third[i-1];
			third[0] = temp;
			break;
		case 4:
			temp = fourth[7];
			for(int i=7; i>0; i--)
				fourth[i] = fourth[i-1];
			fourth[0] = temp;
			break;
		}
	}
	
	public static void counterClock(int num) {
		switch(num) {
		case 1:
			int temp = first[0];
			for(int i=0; i<7; i++)
				first[i] = first[i+1];
			first[7] = temp;
			break;
		case 2:
			temp = second[0];
			for(int i=0; i<7; i++)
				second[i] = second[i+1];
			second[7] = temp;
			break;
		case 3:
			temp = third[0];
			for(int i=0; i<7; i++)
				third[i] = third[i+1];
			third[7] = temp;
			break;
		case 4:
			temp = fourth[0];
			for(int i=0; i<7; i++)
				fourth[i] = fourth[i+1];
			fourth[7] = temp;
			break;
		}
	}
	
	public static void rotate(int num, int direct) {
		int[] state = new int[5];
		state[num] = direct;
		
		switch(num) {
		case 1:
			if(first[2] != second[6]) {
				state[2] = -state[1];
				if(second[2] != third[6]) {
					state[3] = -state[2];
					if(third[2] != fourth[6])
						state[4] = -state[3];
				}
			}
			break;
		case 2:
			if(second[6] != first[2])
				state[1] = -state[2];
			if(second[2] != third[6]) {
				state[3] = -state[2];
				if(third[2] != fourth[6])
					state[4] = -state[3];
			}
			break;
		case 3:
			if(third[2] != fourth[6])
				state[4] = -state[3];
			if(third[6] != second[2]) {
				state[2] = -state[3];
				if(second[6] != first[2])
					state[1] = -state[2];
			}
			break;
		case 4:
			if(fourth[6] != third[2]) {
				state[3] = -state[4];
				if(third[6] != second[2]) {
					state[2] = -state[3];
					if(second[6] != first[2])
						state[1] = -state[2];
				}
			}
			break;
			
		}
		
		for(int i=1; i<5; i++) {
			if(state[i] == 0)
				continue;
			if(state[i] == 1)
				clock(i);
			if(state[i] == -1)
				counterClock(i);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String input = br.readLine();
    	for(int i=0; i<input.length(); i++)
    		first[i] = input.charAt(i) - '0';
    	input = br.readLine();
    	for(int i=0; i<input.length(); i++)
    		second[i] = input.charAt(i) - '0';
    	input = br.readLine();
    	for(int i=0; i<input.length(); i++)
    		third[i] = input.charAt(i) - '0';
    	input = br.readLine();
    	for(int i=0; i<input.length(); i++)
    		fourth[i] = input.charAt(i) - '0';
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	k = Integer.parseInt(st.nextToken());
    	int sum = 0;
    	while(k-->0) {
    		st = new StringTokenizer(br.readLine());
    		int num = Integer.parseInt(st.nextToken());
    		int direct = Integer.parseInt(st.nextToken());
    		rotate(num,direct);
    	}
    	if(first[0] == 1)
			sum+=1;
		if(second[0] ==1)
			sum+=2;
		if(third[0] ==1)
			sum+=4;
		if(fourth[0] ==1)
			sum+=8;
    	System.out.println(sum);
    }
}
