//명령어를 하나씩 받아서
//그때그때 해당 명령어를 하고 전체 조건이 맞는지 전수조사 하고
//맞으면 결과에 반영하고
import java.util.*;
class Solution {
    public int[][] solution(int n, int[][] build_frame) {
        boolean[][] pillar = new boolean[n + 1][n + 1];
        boolean[][] beam = new boolean[n + 1][n + 1];
        
        for(int[] c: build_frame){
            int x = c[0];
            int y = c[1];
            int a = c[2];
            int b = c[3];
            //1. 설치인지 삭제인지 받아서 실제로 설치 삭제 박기
            if(b==0){ //삭제하는 경우
                if(a== 0) //기둥
                    pillar[x][y] = false;
                else //보
                    beam[x][y] = false;
            }
            else{
                if(a==0)
                    pillar[x][y] = true;
                else
                    beam[x][y] = true;
            }
            //2. 전체 좌표에 대하여 조건 만족하는지 검증
            boolean isValid = true;
            
            checkLoop:
            for(int i=0; i<=n; i++){
                for(int j=0; j<=n; j++){
                    //기둥이 버티는 조건(pillar[i][j]가 true)
                    if(pillar[i][j]){
                        boolean currentOk = false; //현재 기둥이 정상인지 체크
                        //1. 바닥 위
                        if(j == 0) currentOk = true;
                        //2. 보의 한쪽 끝부분 위
                        if((i-1 >=0 && beam[i-1][j]) || beam[i][j]) currentOk = true;
                        //3. 다른 기둥 위
                        if(j-1>=0 && pillar[i][j-1]) currentOk = true;
                        
                        //이 기둥이 어떤 조건도 만족 못해서 공중에 떠있다면?
                        if(!currentOk){
                            isValid = false;
                            break checkLoop;
                        }
                    
                    }
                    //보가 버티는 조건(beam[x][y]가 true)
                    if(beam[i][j]){
                        boolean currentOk = false; //현재 보가 정상인지 체크
                        //1. 한쪽 끝부분이 기둥 위
                        if((j-1>=0 && pillar[i][j-1]) || (i+1 <=n && j-1>=0 && pillar[i+1][j-1])) currentOk = true;
                        //2. 양쪽 끝부분이 다른 보와 동시에 연결 
                        if((i-1>=0 && beam[i-1][j]) && (i+1<=n && beam[i+1][j])) currentOk = true;
                        
                        if(!currentOk){
                            isValid = false;
                            break checkLoop;
                        }
                        
                    }
                
                }
            }
            
            //3. 안된다면 조건 전으로 되돌리기
            if(!isValid){
                if(a == 0)
                    pillar[x][y] = !pillar[x][y];
                else
                    beam[x][y] = !beam[x][y];
            }
        }
        
        //4. 결과 정산
        ArrayList<int[]> answerList = new ArrayList<>();
        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(pillar[i][j])
                    answerList.add(new int[]{i,j,0});
                if(beam[i][j])
                    answerList.add(new int[]{i,j,1});
            }
        }
        int[][] answer = new int[answerList.size()][3];
        for(int i=0; i<answerList.size(); i++){
            for(int j=0; j<3; j++){
                answer[i][j] = answerList.get(i)[j];
            }
        }
        return answer;
    }
}