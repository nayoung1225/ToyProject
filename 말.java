package ladder;

import java.util.Arrays;

import lombok.Getter;

public class 말 implements 말디자인{

    private int[][] 경로;
	@Getter
    private boolean 당첨여부;

    public 말(int 사다리갯수) {
        this.경로 = new int[사다리갯수 * 2 + 1][사다리갯수];
        this.당첨여부 = false;
    } // 말 

    @Override
	public void 사다리게임(사다리판디자인 사다리A, int 시작지점) {
		
		if (시작지점 < 1 || 시작지점 > this.경로[0].length) {
			throw new RuntimeException("시작지점의 범위를 벗어난 입력값입니다!!");
		} // if
		
		// 2차원 배열을 저장받을 변수와 열을 저장받을 변수
		int[][] 사다리판 = 사다리A.get판();
		int 열 = 시작지점 - 1;

		// 2차원 배열의 행에 대해 순회
		for (int i = 0; i < 사다리판.length; i++) {

			if (사다리판[i][열] == 1) { // 사다리판[i][열]이 1이면, 경로에 기록 후 오른쪽이동 
				경로[i][열] = 1;
				열 = 열 + 1;
			} else if (사다리판[i][열] == -1) { // 사다리판[i][열]이 -1이면, 경로에 기록 후 왼쪽 이동 
				경로[i][열] = -1;
				열 = 열 - 1;
			} else if (사다리판[i][열] == 0) { // 사다리판[i][열]이 0이면, 경로에 기록 후 그냥 내려감
				경로[i][열] = 2;
			} // if else-if
		} // for
		
		// 경로의[맨마지막행][열] 행의 마지막에 3을 넣어준다 
		this.경로[경로.length - 1][열] = 3;
	} // 사다리게임

	@Override
	public boolean 당첨인가요(사다리판디자인 사다리A) { 

	    boolean[] 당첨판 = 사다리A.get당첨판();
	    
	    for (int j = 0; j < 경로[경로.length - 1].length; j++) { // 2차원 boolean 배열 경로의 마지막 행의 열을 순회하는 for문
	        
	        if (경로[경로.length - 1][j] == 3) { // 경로의 마지막 행의 j번째 열이 3인 경우
	        	if (당첨판[j] == true) {	// 당첨판의 마지막 열이 true 일때 
	        		this.당첨여부 = true; 
	        	} // if  
	        } // if 
	    } // for 
	    
	    return this.당첨여부; // 해당 필드를 반환
	        
	 } // 당첨인가요

	// 경로출력을 하기위해 다음과 같은 보조메소드를 필요로 한다.
	// 1. 경로출력 메소드가 너무 길어 해당 메소드를 보조하는 출력보조메소드를 호출해 메소드를 분리한다..
	// 알고리즘 다음과 같다.
	// 1. StringBuffer sb 인스턴스를 만들어줌
	// 2. 사다리A를 매개변수로 받아 해당 필드들(판, 당첨판)을 변수로 저장.
	// 3. 사다리판의 행을 for로 순회하면서 int i에 대해
	//      a. 출력보조메소드(현재 행의 1차원 배열(사다리판[i]), 행(i))을 호출하고 해당 sb를 append 해준다.
	// 4. 사다리당첨판을 for문으로 순회하면서 int i에 대해
	//      a. 사다리당첨판[i]의 값에 따라 당첨 또는 꽝을 append 해준다
	//      b. 마지막에는 "\n"을 추가하여 개행한다.
	// 5. sb.toString()으로 문자열을 출력한다.
	@Override
	public void 경로출력(사다리판디자인 사다리A) {
		StringBuilder sb = new StringBuilder();
		int[][] 사다리A판 = 사다리A.get판();
		boolean[] 사다리당첨판 = 사다리A.get당첨판();
		
		for (int j = 0; j < 사다리당첨판.length; j++) {
			sb.append(" ").append(j + 1).append("\t");
		} // for 
		sb.append("\n");

		for (int i = 0; i < 사다리A판.length; i++) {
			sb.append(this.출력보조메소드(사다리A판[i], i));
		} // for 

		for (int i = 0; i < 사다리당첨판.length; i++) {
			sb.append((this.경로[this.경로.length - 1][i] == 3) ? " ↓\t" : " │\t");
		} // for_i
		sb.append("\n");

		for (boolean b : 사다리당첨판) {
			sb.append(b ? "당첨\t" : " 꽝\t");
		} // for 
		sb.append("\n");

		System.out.println(sb.toString());
	} // 경로출력

	// 알고리즘은 다음과 같다.
	// 1. StringBulider sb 인스턴스를 만든다.
	// 2. 매개변수로 받은 사다리행배열을 for문으로 순회하면서 int col(열)에 대해
	//      a. 경로[row][col]의 값이 0 이면 말은 이쪽을 가지않은 것이므로
	//          i. 사다리행[col] 배열이 1이면 ㅏ 모양 append
	//          ii. 사다리행[col] 배열의 값이 ㅓ 모양 append
	//          iii. 사다리행[col] 배열의 값이 0 이면 ㅣ 모양 append
	//      b. 경로[row][col]의 값이 1 이면 말은 이쪽을 거쳐간 것이므로
	//          i. 이 말이 왼쪽쪽으로 움직인것이면(경로[row][col] == -1) <- 모양을 넣어주기 위해
	//             delete()를 사용해주고, replace를 사용한다.
	//          ii. 이 말이 오른쪽으로 움직인것이면(경로[row][col] == 1) -> 모양을 append하고 col을 ++ 해준다.
	//          iii. 이 말이 그냥 아래로 내려간 거면(경로[row][col] == 2) V 모양을 append한다.
	//      c. col의 값이 끝에 도달하면 개행문자를 append 해준다.
	// 3. sb를 반환한다.

	private StringBuilder 출력보조메소드(int[] 사다리행배열, int row) {
		StringBuilder sb = new StringBuilder();

		for (int col = 0; col < 사다리행배열.length; col++) {
			if (this.경로[row][col] == 0) {
				if (사다리행배열[col] == 1) {
					sb.append(" ├------");
				} else if (사다리행배열[col] == -1) {
					sb.append("-┤\t");
				} else {
					sb.append(" │\t");
				}
			} else {
				if (this.경로[row][col] == -1) {
					sb.delete(3 * (col - 1), 3 * col + 2);
					sb.replace(3 * (col - 1), 3 * col, " │← ← ← ←│\t");
				} else if (this.경로[row][col] == 1) {
					sb.append(" │→ → → →│\t");
					col++;
				} else {
					sb.append(" ↓\t");
				}
			}
		}
		sb.append("\n");
		return sb;
	} // 출력보조메소드

	// 자원객체 말을 clear 해주는 메소드
	@Override
	public void clear() {
		for (int[] arr : this.경로) {
			Arrays.fill(arr, 0);
		} // for_
		this.당첨여부 = false;
		this.경로 = null;
	}
} // end class 