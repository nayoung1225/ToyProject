package ladder;

import java.util.*;

import lombok.Cleanup;

public class Main {

	public static void main(String[] args) {
		@Cleanup			// lombok Cleanup을 이용하여 자원 해제
		Scanner sc = null;
		int n = 0;

		while (true) {		// 무한루프
			try {
				sc = new Scanner(System.in); // 입력값을 받을 스캐너 생성

				System.out.println("   ┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒");
				System.out.println("   ╊   G  h  o  s  t     L  e  g   ╊");
				System.out.println("   ┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━☛ S T A R T");

				System.out.print("생성하고 싶은 사다리의 개수를 입력해 주세요. ☛ ");
				n = sc.nextInt();  // 스캐너로 입력된 정수를 변수 n에 저장후 출력
				@Cleanup("clear")
				사다리판디자인 사다리A = new 사다리판(n); // 재정의된 메소드들이 있는 사다리판을 생성
				사다리A.판만들기();  // 재정의된 메소드를 호출

				System.out.print("당첨 개수를 입력해 주세요. ☛ ");
				n = sc.nextInt();  // 스캐너로 입력된 정수를 변수 n에 저장후 출력
				사다리A.당첨판만들기(n);  // 재정의된 메소드를 호출

				사다리A.가려진상태출력();  // 재정의된 메소드를 호출

				System.out.print("희망하는 출발 번호를 입력해 주세요. ☛ ");
				n = sc.nextInt();  // 스캐너로 입력된 정수를 변수 n에 저장후 출력
				사다리A.말사다리게임(n);  // 재정의된 메소드를 호출
				System.out.println();

				사다리A.말당첨인가요();  // 재정의된 메소드를 호출
				System.out.println();

				사다리A.말경로출력();  // 재정의된 메소드를 호출
				
				System.out.println("   ┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒");
				System.out.print("       종료  : (0)     재시작 : (1)     ☛ ");
				System.out.println("");
				System.out.println("   ┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚");
				n = sc.nextInt();  // 스캐너로 입력된 정수를 변수 n에 저장후 출력
				
				if (n == 0) {		// 입력값이 0이라면 종료
					break;
				} else {
					continue;  // 반복문의 처음으로 이동
				}

			} catch (InputMismatchException e) {	// 정수를 입력하지 않았을 때 예외처리 
				e.printStackTrace();  // 예외 객체의 스택 추적 정보를 출력
				System.err.println();
				System.err.println("   ┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒");
				System.err.println("          값을 정수로 입력해주세요!       ");
				System.err.println("   ┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚");
				System.err.println("   ┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒");
				System.err.println("          처음부터 다시 시작 합니다.      ");
				System.err.println("   ┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚");
			} catch (RuntimeException e) {			// 조건의 범위를 벗어났을 때 예외처리
				e.printStackTrace();  // 예외 객체의 스택 추적 정보를 출력
				System.err.println();
				System.err.println("   ┎━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┒");
				System.err.println("          처음부터 다시 시작 합니다.      ");
				System.err.println("   ┖━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┚");			

			} // try-catch

		} // end while

	} // main

} // end class