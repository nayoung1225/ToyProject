package ladder;

public interface 말디자인 {

    // 2차원 배열의 행에 대해 순회하면서 만나는 값에 따라 경로 필드를 채워준다.
    // for문 하나 또는 재귀
    // 결과적으로 경로를 다 채워주어야한다.
    public abstract void 사다리게임(사다리판디자인 사다리A, int 시작지점);

    // 경로의 맨마지막 행의 어느 열이 1인지 판단 후 해당 열을 사다리판의 정답판과 비교시 당첨인지 확인
    // 그리고 말의 당첨 필드를 변경 후 해당 필드를 반환
    public abstract boolean 당첨인가요(사다리판디자인 사다리A);

    // 경로출력
    public abstract void 경로출력(사다리판디자인 사다리A);
		
	// 당첨 필드를 반환하는 메소드 Getter
	public abstract boolean is당첨여부();

	public abstract void clear();
} // 말디자인