//열쇠 : M*M
//자물쇠: N*N

//회전과 이동을 통해 모든 홈을 채울 수 있으면 TRUE 안되면 FALSE 리턴

//M,N 값이 크지않으므로 완탐하는 문제고

//1.원래상태
//2.시계방향 90도 회전
//3.시계방향 180도 회전
//4.시계방향 270도 회전
//이 모든 경우에서 다 시도해보고 안되면 FALSE 리턴
import java.util.*;
class Solution {
    static void rotate(int[][]key){
        int m = key.length;
        int[][] temp = new int[m][m];
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                temp[j][m-1-i] = key[i][j];
            }
        }
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                key[i][j] = temp[i][j];
            }
        }
    }
    
    public boolean solution(int[][] key, int[][] lock){
        int m = key.length;
        int n = lock.length;
        int[][] grid = new int[3*n][3*n];
        for(int i=n; i<2*n; i++)
            for(int j=n; j<2*n; j++)
                grid[i][j] = lock[i-n][j-n];
        
        //1.원래상태로 테스트
        for(int i=0; i<3*n-m; i++){
            for(int j=0; j<3*n-m; j++){
                //키 끼워맞추기
                for(int k=0; k<m; k++){
                    for(int l=0; l<m; l++){
                        grid[i+k][j+l] += key[k][l];
                    }
                }
                //키가 홈에 딱 맞는지 테스트
                boolean isPossible = true;
                for(int k=n; k<2*n; k++){
                    for(int l=n; l<2*n; l++){
                        if(grid[k][l] ==2 || grid[k][l] == 0)
                            isPossible = false;
                    }
                }
                if(isPossible)
                    return true;
                
                //다시 키 빼주기
                for(int k=0; k<m; k++){
                    for(int l=0; l<m; l++){
                        grid[i+k][j+l] -= key[k][l];
                    }
                }
            }
        }
        //2.시계방향으로 90/180/270도 회전
        int cnt = 0;
        while(cnt<3){
            rotate(key);
            for(int i=0; i<3*n-m; i++){
                for(int j=0; j<3*n-m; j++){
                    //키 끼워맞추기
                    for(int k=0; k<m; k++){
                        for(int l=0; l<m; l++){
                            grid[i+k][j+l] += key[k][l];
                        }
                    }
                    //키가 홈에 딱 맞는지 테스트
                    boolean isPossible = true;
                    for(int k=n; k<2*n; k++){
                        for(int l=n; l<2*n; l++){
                            if(grid[k][l] ==2 || grid[k][l] == 0)
                                isPossible = false;
                        }
                    }
                    if(isPossible)
                        return true;
                
                    //다시 키 빼주기
                    for(int k=0; k<m; k++){
                        for(int l=0; l<m; l++){
                            grid[i+k][j+l] -= key[k][l];
                        }
                    }
                }
            }
            cnt++;
        }
        return false;
    }
}