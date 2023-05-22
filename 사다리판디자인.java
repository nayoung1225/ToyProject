package ladder;


public interface 사다리판디자인 {


	// 사다리판을 만드는 메소드 : random()과 for문을 통해서 사다리판을 만든다. 높이는 사다리 갯수의 2이다.
    // int a = random() * 사다리 갯수를 a가 사다리 갯수 - 1면 이 층은 선이 없는 층이고
    // 아니라면 이 층은 1과 -1 을 가지는 층이다.
    // 결과적으로 사다리판을 완성시켜야한다.
    public abstract void 판만들기();

    // random * 10 해서 짝수면 정답 (true)으로 랜덤한 위치의 정답판에 넣어둔다 언제까지? 입력받은 숫자개수 만큼
    // while랑 random를 조합
    public abstract void 당첨판만들기(int 당첨갯수);

    // 가려진 상태의 판을 출력하는 메소드
    public abstract void 가려진상태출력();

    // return this.말.사다리게임(this, 시작지점);
    public abstract void 말사다리게임(int 시작지점);

    // this.말.당첨인가요(this)를 호출해서 당첨(true)이면 "당첨입니다." 출력, 꽝이면 "꽝입니다." 출력;
    public abstract void 말당첨인가요();

    // return this.말.경로출력();
    public abstract void 말경로출력();

    // 필드 판 Getter
    public abstract int[][] get판();

    // 필드 당첨판 Getter
    public abstract boolean[] get당첨판();

    // 자원객체 클리어메소드
    public abstract void clear();

	public abstract void 당첨인가요();
	
	public abstract void 사다리게임();

	public abstract void 당첨여부();


} // 사다리판디자인