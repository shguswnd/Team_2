package com.victoree2.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.victoree2.common.AccountData;
import com.victoree2.common.ActionInterface;
import com.victoree2.common.ReturnMessage;
import com.victoree2.main.ReadingRoom;

public class UserSystem extends ReturnMessage implements ActionInterface{
	// 변수
	private int paymentpoint = -1;  //아래 pay 배열값 포인터
	private boolean payStatus; // false = 정기권   true = 시간권
	private String paymentSeason[] = {"65,000", "99,000","190,000"};		//시즌권
	private String paymentSeasonCoupon[] = {"45,500","69,300","133,000"};	//쿠폰 시즌권
	private String paymentTime[] = {"3,000","5,000","8,000","10,000","12,000"};		//시간권
	private String paymentTimeCoupon[] = {"2,300","3,500","5,600","7,000","8,400"}; //쿠폰 시간권
	private String checkIn;
	private int key;

	
	
	CeatSystem test = new CeatSystem();
	
	ReadingRoomFactory factory = new ReadingRoomFactory();// 데이터 수정/삭제시 사용
	AccountSystem user = factory.getUser();
	
	ReadingRoom room = new ReadingRoom();
	Scanner scan = new Scanner(System.in);
	AccountData userStatus;
	
	public UserSystem(AccountData userStatus) {
		this.userStatus = userStatus;
		checkIn = (userStatus.getCheckIn()) ? message(room.language,"0028") : message(room.language,"0027"); //입퇴실값 초기화.
		test.init();
	}
	@Override
	public void run() {
		System.out.println(userStatus.getName()+message(room.language, "0019"));
		while ((key = selectMenu("0")) != 0) {
			switch (key) {
			case 1: //마이페이지
				myPage();
				if(room.firstMain == true) {
					room.firstMain = false;
					return;
				}	
				break;
			case 2: //정기권 예약
				payStatus = false;
				seasonReservation();
				break;
			case 3: //시간권 예약
				payStatus = true;
				timeReservation();
				break;
			case 4: //스터디룸 예약 보류....
				break;
			case 5: // 입/퇴실
//				checkInOut();

				break;
			case 6: //좌석보기
				checkSeat();
				break;
			case -1: //시스템 종료.
				System.out.println(message(room.language, "0018"));
				System.exit(0);
//				return;
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}
	
	public void myPage() {	
		System.out.print(message(room.language, "0200"));
		System.out.print(userStatus.getName());
		System.out.print(message(room.language, "0201"));
		System.out.print(message(room.language, "0202"));
		System.out.println();
		while ((key = selectMenu("01")) != 0) {
			switch (key) {
			case 1:// 패스워드 초기화
				pwdReset();
				if(room.firstMain == true)
					return;
				break;
			case 2:// 결제취소
				paymentCancel();
			case 0:
				System.out.println(message(room.language, "0018"));
				System.exit(0);
			}
		}
	}
	public void checkInOut() { //입실/퇴실관리.
//		(userStatus.getCheckIn()) 
		//checkSeat();
	}
	@Override
	public void selectAccount() {
		//내 남은 시간조회
	}

	public void seasonReservation(){//정기권 예약
		while ((key = selectMenu("02")) != 0) {
			switch (key) {
			case 1: //2주권
				paymentpoint = 0;
				payment(); //payment() 이동
				break;
			case 2:// 4주권
				paymentpoint = 1;
				payment(); //payment() 이동
				break;
			case 3:// 8주권
				paymentpoint = 2;
				payment(); //payment() 이동
				break;
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
		
	}
	public void timeReservation() {//시간권예약
		while ((key = selectMenu("03")) != 0) {
			switch (key) {
			
			case 1: //2주권
				paymentpoint = 0;
				payment(); //payment() 이동
				break;
				
			case 2:// 4주권
				paymentpoint = 1;
				payment(); //payment() 이동
				break;
				
			case 3:// 8주권
				paymentpoint = 2;
				payment(); //payment() 이동
				break;
				
			case 4:// 8주권
				paymentpoint = 3;
				payment(); //payment() 이동
				break;
			
			case 5:// 8주권
				paymentpoint = 4;
				payment(); //payment() 이동
				break;

			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}
	public void payment() { //결제하다
		while ((key = selectMenu("021")) != 0) {
			switch (key) {
			
			case 1:
				paymentFinal();
				break;
			case 2:
				// 쿠폰 창
				break;
				
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
	}
	
	//최종결제 후 좌석결제
		public void paymentFinal() {
			while ((key = selectMenu("0211")) != 0) {
				switch (key) {
				
				case 1:
					//좌석선택 메뉴 추가해주기
					break;
				default:
					System.out.println(message(room.language, "0020"));
					break;
				}
			}
		}
		
	@Override
	public void paymentCancel() {
		//결제 취소 
		while (true) {
			String menu;
			System.out.println(message(room.language, "0208"));
			System.out.println(message(room.language, "0209"));
			System.out.println(message(room.language, "0210"));
			menu = scan.nextLine();
			if (menu.equals("1")) {
				System.out.println(message(room.language, "0211"));
				System.exit(key=0);
				//**********************************
				// 회원 정보 삭제 보류
			} else {
				return;
			}
		}
	}

	@Override
	public void checkSeat() {
		//내 좌석 조회
//		System.out.println(message(room.language, "0104"));
		
		while ((key = selectMenu("06")) != 0) {
			switch (key) {
			
			case 1:
				
				test.print();
				break;
			default:
				System.out.println(message(room.language, "0020"));
				break;
			}
		}
		
		
	}
	@Override
	public void pwdReset() {
		//패스워드 초기
		while (true) {
			String pwd;
			String newPwd;
			System.out.println(message(room.language, "0204"));
			pwd = scan.nextLine();// 현재 비밀번호 입력

			if ((!userStatus.getPassword().equals(pwd))) {
				System.out.println(message(room.language, "0205"));

			} else {
				System.out.println(message(room.language, "0206"));
				newPwd = scan.nextLine();
				userStatus.setPassword(newPwd);
				user.update(userStatus);
				System.out.println(message(room.language, "0207"));
				// 하단에서 최상위인 첫 메인화면으로 이동할경우 firstMain 값을 true 변경할것이다.
				room.firstMain = true; 
				return;
			}	
		}
	}
	
	@Override
	public int selectMenu(String index) {
		if(index == "0")
			System.out.printf(message(room.language, "0002"),checkIn);
		else if(index == "01")
			System.out.println(message(room.language, "0026"));
		// selectMenu 메소드
		else if(index == "02")
			System.out.printf(message(room.language, "0100"), paymentSeason[0],paymentSeason[1],paymentSeason[2]); //정기권 가격 출력
		else if(index == "03")
			System.out.printf(message(room.language, "0108"), paymentTime[0],paymentTime[1],paymentTime[2],paymentTime[3],paymentTime[4]); //시간권 가격 출력
		else if(index == "06")
		{//특수케이스
			System.out.println(message(room.language, "0111"));//열람실 개수는 3개 고정으로 둘것이지만 추후 증가될것을 생각하여 for문으로 직접 작성.

			for(int i = 0 ; i < test.testList.size(); i++) {
				System.out.printf((i+1) + " : " + message(room.language, "0029"),(i+1));
			}
			System.out.println();
		}

		else if(index == "021") 
			System.out.printf(message(room.language, "0102"), payStatus == false ? paymentSeason[paymentpoint] : paymentTime[paymentpoint]);  // 무슨 권이냐에 따라 가격 출력
		else if(index == "0211")
			System.out.printf(message(room.language, "0103"), userStatus.getId(), payStatus == false ? "" : "\n 0: 뒤로가기 ");  // 아이디 출력 후 시즌권에 따라 뒤로가기 다르게 출력
		return Integer.parseInt(scan.nextLine());
	}
}
