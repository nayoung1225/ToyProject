package ladder;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;
public class 사다리판 implements 사다리판디자인{

	@Getter
private int[][] 판;
	@Getter
private boolean[] 당첨판;
private int 사다리갯수;
private 말디자인 기수;

public 사다리판(int 사다리갯수) {

    if (사다리갯수 < 2 || 사다리갯수 > 8) {
        throw new RuntimeException("사다리 갯수의 범위를 벗어난 입력값입니다.");
    }
    this.사다리갯수 = 사다리갯수;
    this.판 = new int[사다리갯수 * 2][사다리갯수];
    this.당첨판 = new boolean[사다리갯수];
    this.기수 = new 말(사다리갯수);
}


@Override
	public void 판만들기() { //사다리의 가로줄 만들기
		// 2. 무작위 하나를 정하고 그 위치에 1을 넣고 그 옆에 -1 을 넣기로 했다
		// 1 옆에 -1이 있어야 하므로 Math.random() 메소드에 곱할 숫자는 열의 개수 미만이어야 한다. 
		// 즉, 열의 개수 = 사다리 갯수
		//    Math.random() 3 X --> 맨마지막 숫자가 아니여야한다. --> 맨마지막 숫자=사다리갯수 - 1
		//  범위 Math.random() * this.사다리갯수 --> 0 1 2 3
		for(int 행 = 0; 행 < this.판.length; 행++) { //사다리의 가로줄 만들기
			int randomNum = (int)(Math.random() * this.사다리갯수);

			if(randomNum == this.사다리갯수 - 1) { // randomNum의 숫자가 사다리의 맨 마지막 위치가 되는 경우
				 continue;                          // -1이 오른쪽에 올 수 없으므로 건너뛰고 다음층
			} else {                             // 만약 사다리갯수가 3이라면 열의 인덱스는 0,1,2만 올 수 있다.
				 this.판[행][randomNum] = 1;       // randomNum  에 해당하는 인덱스에 1을 넣는다.   
				 this.판[행][randomNum + 1] = -1;  //randomNum +1 에 해당하는 인덱스에 -1을 넣는다.
			} // if-else 
		} // for 
	} // 판만들기

@Override
	public void 당첨판만들기(int 당첨갯수) {
		
			//     0  1  2  3
			// 1. {f, f, f, f} --> 2개 당첨 ---> { f, t, f, t} 이런식으로 만드는게 목표
			//   2개니깐 count  -> 1개를 true로 바꾸면 1개가 남고 --> 메소드는 계속 동작해야한다.
      		//   0개가 될 때까지--> while문  (~~가 될때까지 몇번 계산해야지 모르므로)
			// 2. 어떻게 랜덤한 위치를 결정할까? --> Math.random() * this.사다리갯수 활용
			//    0 * 4 < Math.random() * 4 < 1 * 4  
			//    0 < (int)m.r * 4 < 4  --> 0 1 2 3   (int)0.1231241 --> 0 
			//    사다리판 필드 --> 사다리갯수 --> 4개
      		// 혹시 문제 : {f, f, f, f} --> 랜덤수 1 --> {f, t, f, f} --> 랜덤수 1 --> "" 
			// 예외처리 --> 당첨갯수가 0 -1 :XX  사다리갯수가 4 --> 당첨갯수가 5,6 : XX
			if(당첨갯수 < 1 || 당첨갯수 > this.사다리갯수) {
					throw new RuntimeException("당첨갯수의 범위를 벗어난 입력값입니다!!!");
			}

			int count = 당첨갯수;
			while(count > 0) { //당첨갯수가 아직 남아있는 동안 while문을 계속 돌린다.
				 int randomNum =(int)(Math.random() * this.사다리갯수); // 사다리갯수가 4일 경우 0-3이 유효범위이다.
				 if(!this.당첨판[randomNum])  { // boolean 타입이므로 true,false로 판별 
																				// ->무엇을? randomNum에 해당하는 당첨판의 인덱스 자리가 만약 false,즉 빈 자리라면
				   this.당첨판[randomNum] = true; // 당첨이라고 입력한다.
					   count--;	                   // 그리고 나서 당첨갯수에서 -1 해주고 이를 반복한다.            
				 } else {                        //만약 당첨판의 인덱스가 true라면 이미 당첨 자리이므로
					 continue;                     //스킵해준다.
				 } // if-else 
			} // while
      
	} // 당첨판만들기

//사다리를 실행하기전 가려진 상태를 시각적으로 콘솔에 출력하는 메소드
// StringBuffer을 사용햇고 총 7 개의 층으로 구현되어 있다.
// 1층은 사다리 갯수만큼 for문을 순회시키면서 사다리의 인덱스를 (1부터 시작) 추가해 주었고
// 2층은 사다리 갯수만큼 for문을 순회시키면서 사다리 갯수 만큼 "│" 을 추가해 주었고
// 3층은 사다리 갯수만큼 for문을 순회시키면서 "===="을 추가해 주었고
// 4층은 사다리 갯수만큼 for문을 순회시키면서 해당 사다리가 짝홀인지 판단 후 중간 위치에 ???을 추가해 주었고
// 5층은 2층의 반복
// 6층은 1층의 반복
// 7층은 사다리 갯수만큼 for문을 순회시키면서 당첨판[i]의 값에 따라 "당첨" 과 "꽝"을 추가해주었다
@Override
public void 가려진상태출력() {

	StringBuilder sb = new StringBuilder();

	for (int j = 0; j < this.사다리갯수; j++) {
		sb.append(" ").append(j + 1).append("\t");
	}
	sb.append("\n");

	sb.append(" │\t".repeat(Math.max(0, this.사다리갯수)));
	sb.append("\n");

	for (int j = 0; j < this.사다리갯수; j++) {
		sb.append((j == 0) || (j == this.사다리갯수 - 1) ? "======" : "========");
	}
	sb.append("\n");

	for (int j = 0; j < this.사다리갯수; j++) {
		boolean isEven = (this.사다리갯수 % 2 == 0);
		int middle = isEven ? this.사다리갯수 / 2 - 1 : this.사다리갯수 / 2;

		if (j == middle) {
			sb.append(isEven ? "    ???" : "???");
		} else {
			sb.append(" \t");
		}
	}
	sb.append("\n");

	for (int j = 0; j < this.사다리갯수; j++) {
		sb.append((j == 0) || (j == this.사다리갯수 - 1) ? "======" : "========");
	}
	sb.append("\n");

	sb.append(" │\t".repeat(Math.max(0, this.사다리갯수)));
	sb.append("\n");

	for (boolean b : this.당첨판) {
		sb.append(b ? "당첨\t" : " 꽝\t");
	}
	sb.append("\n");

	System.out.println(sb.toString());
} // 가려진상태출력

// 부품 객체인 기수의 사다리게임 메소드를 호출
@Override
public void 말사다리게임(int 시작지점) {
	this.기수.사다리게임(this, 시작지점);
} // 말사다리게임

// 부품 객체인 기수의 당첨인가요 메소드를 호출 해 당첨여부 확인 후 출력
@Override
public void 말당첨인가요() {
	if (this.기수.당첨인가요(this)) {
		System.out.println("***** 당첨입니다!!! *****");
	} else {
		System.out.println("***** 꽝입니다!!! *****");
	} // if-else 
} // 말당첨인가요

// 부품 객체인 기수의 경로출력 메소드를 호출
@Override
public void 말경로출력() {
	this.기수.경로출력(this);
} // 말경로출력

// 자원객체 사다리를 clear해주는 메소드
@Override
public void clear() {
	for (int[] arr : this.판) {
		Arrays.fill(arr, 0);
	} // for
	Arrays.fill(this.당첨판, false);
	this.판 = null;
	this.당첨판 = null;
	this.사다리갯수 = 0;
	this.기수.clear();
}


@Override
public void 당첨인가요() {
	// TODO Auto-generated method stub
	
}


@Override
public void 사다리게임() {
	// TODO Auto-generated method stub
	
}


@Override
public void 당첨여부() {
	// TODO Auto-generated method stub
	
}
}
  
