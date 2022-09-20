package com.victoree2.system;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.victoree2.common.AccountData;
import com.victoree2.common.ActionInterface;
import com.victoree2.common.ReturnMessage;
import com.victoree2.main.ReadingRoom;


public class AdminSystem extends ReturnMessage implements ActionInterface{
	Scanner scan = new Scanner(System.in);
	ReadingRoom room = new ReadingRoom();
	ReadingRoomFactory factory;
	HashMap<String, AccountData> userMap;
	Scanner adminScan = new Scanner(System.in);

	public AdminSystem(HashMap<String, AccountData> userMap) {
		this.userMap = userMap;
		this.factory = new ReadingRoomFactory();
	}
	@Override
	public void run() {
		System.out.println(message(room.language, "0021"));
		
		int key=0;
		//key 값이 -1 이면 뒤로가기
		while ((key = selectMenu("0")) != 0) {
			switch (key) {
			case 1://1. 좌석현황
				 // 1. 좌석현황
				System.out.println(message(room.language, "0051"));
				//좌석 배열 보여주기
				System.out.println();
				selectAccount();
				break;
			case 2://2.전체 회원 목록
				System.out.println(message(room.language, "0052"));
				System.out.println();
				selectAccount();
				break;
			case 3://3.회원 검색
				System.out.println(message(room.language, "0053")); //"회원검색합니다. \n검색할 회원의 이름을 입력하세요."
				//[기능] scan통하여 원하는 조회하고자 하는 회원의 이름 입력
				System.out.println(message(room.language, "0058")); //"검색 결과"
				//[기능] selectAccount(String name) scan통하여 원하는 이름을 가진 회원들의 목록 출력
				//해당 이름을 가진 회원 목록 [인덱스][이름][현재 입퇴실 내역(좌석 번호)] 나옴
				//그 회원들의 [인덱스] 중 하나 입력 -> 이제 회원 한 명 특정 됨
				System.out.println(message(room.language, "0059")); //그 특정 회원에 대한 1:회원입퇴실내역조회 2:비밀번호 초기화 3.경고 4.정지 0:뒤로가기
				System.out.println(message(room.language, "0068")); //1.회원의 입퇴실 내역 "이 회원의 입퇴실 내역입니다"
				System.out.println(message(room.language, "0063")); //2.비밀번호 초기화 
				System.out.println(message(room.language, "0060"));  //3.경고 확인 "이 회원에게 경고를 줍니까?"
				System.out.println(message(room.language, "0061"));  //3.경고 "경고를 주었습니다"
				System.out.println(message(room.language, "0066"));  //4.정지 확인 "이 회원을 정지시킵니까?"
				System.out.println(message(room.language, "0067"));  //4. 정지 "회원의 권한을 정지하였습니다"
				
				System.out.println();
				
				//selectAccount(String id) scan통하여 원하는 id값 출력
				break;
			case 4: //4.매출 현황
				System.out.println(message(room.language, "0065")); //"매출 현황 입니다"
				System.out.println();

				break;
			case 5: //5.쿠폰 관리
				System.out.println(message(room.language, "0054")); //"쿠폰을 관리합니다. \n 1:현재 발행된 쿠폰 확인  2:쿠폰 추가  3:쿠폰 삭제  0:뒤로가기"
				System.out.println(message(room.language, "0055")); //1.현재 발행된 쿠폰 확인 "현재 사용가능한 쿠폰 목록입니다."
				System.out.println(message(room.language, "0056")); //2.쿠폰 추가 "추가할 쿠폰의 번호와 할인율을 입력하세요. 추가를 원치 않으면 0을 입력하세요."
				System.out.println(message(room.language, "0057")); //3.쿠폰 삭제 "삭제할 쿠폰의 번호를 입력하세요. 삭제를 원치 않으면 0을 입력하세요."
//				coupon();
				break;
			case -1://0.로그아웃
				System.out.println(message(room.language, "0053")); //"회원검색합니다. \n검색할 회원의 이름을 입력하세요."
				System.out.println(message(room.language, "0018")); //"종료"
				System.out.println();
				System.exit(0);
				break;
			default:
				System.out.println(message(room.language, "0020")); //"잘못 선택하였습니다."
				System.out.println();
				break;
			}
		}

	}
	public void specificUser() {
//		int key = selectMenu("03");  //이걸 살려두면 selectAccount에서 스캐너에 String이 들어가 selectMenu에서 에러가 뜸...어카징?
		System.out.println(message(room.language, "0053"));
		String id = scan.nextLine();
		List<AccountData> searchResult = selectAccount(id);
		Boolean searchResultCheck = searchResult.size()!=0 ? true : false;
		int key;// = selectMenu("03");
			
		if(searchResultCheck) {
			System.out.println("관리할 회원 선택");
			int index = Integer.parseInt(scan.nextLine());
			AccountData user = searchResult.get(index);
			System.out.println(user.toString());
			
			while((key = selectMenu("03"))!=-1){
				switch(key){
					case 1:	
						userManager(user);
						break;
					case 2:
							//입퇴실내역조회
						System.out.println("입퇴실내역조회");
						break;
					case 3:
							//비밀번호 초기화
						System.out.println("Reset pw");
						break;
					case 0:
						return;
							//돌아가기
					default:
						System.out.println(message(room.language, "0020"));
						break;
				}
			}
				return;
			}else {
				//검색결과가 없으니 함수 종료
				return;
			}
	}

	public List<AccountData> selectAccount(String name) {
		//특정회원 조회
		List<AccountData> searchUsers = new ArrayList<>();
		for(AccountData ac : userMap.values()) {
			if(ac.getName().equals(name)) {
				searchUsers.add(ac);
			}
		}
		if(searchUsers.size() == 0) { //검색결과가 0일 때
			System.out.println("검색결과가 존재하지 않습니다."); //이거 나중에 returnMessage에 넣기
			return null;
		}else {
			int index = 1;
			 for(int i = 0; i < searchUsers.size(); i++) {
		            System.out.println(index + " : " + searchUsers.get(i));
		            index++;
		      }
			 return searchUsers;
		}
	}
	
	public void userManager(AccountData user) {//회원관리(경고)
		if(user.getStatus() == 0) {
			System.out.println("해당 회원은 이미 정지된 회원입니다.");
			return;
		}
		System.out.println(message(room.language, "0060")); //회원이 정지상태면 이미 정지된 회원이라고 써야될듯
		int warnCheck = Integer.parseInt(scan.nextLine());
		if(warnCheck == 1) {
			System.out.println("정지");/////////////
			//AccountData.java에서 getCnt, setCnt 만들어야함
			user.setCnt(); //경고 누적
			System.out.println("해당 회원의 현재 누적 경고 수 : " + user.getCnt());
			if(user.getCnt() > 2) {
				System.out.println("해당 회원은 경구 3회 누적으로 정지됩니다.");
				user.setStatus(0);
			}
		}
	}
	public void inoutSeat() { // 회원 입퇴실 내역조회
		
	}
	@Override
	public void pwdReset() { //패스워드 초기화
		
	}
	public void coupon() {//쿠폰
		
	}
	@Override
	public void selectAccount() {
		//회원 전부 조회
		
		Set<String> userlist = userMap.keySet();
		
		for(String value : userlist) {
			System.out.println(userMap.get(value).toString());
		}
	}
	
	@Override
	public void paymentCancel() {
		//관리자는 회원 환불.
		
	}

	@Override
	public void checkSeat() {
		//전체좌석을 확인할 수 있다.
		
	}
	@Override
	public int selectMenu(String index) {
		if(index == "0")
			System.out.println(message(room.language, "0003"));
		else if(index == "01")
			System.out.println("양식");
		else if(index == "03") //검색결과가 존재할 경우
			System.out.println(message(room.language, "0059"));//"0059","1:회원 관리 2:회원입퇴실내역조회 3:분실계정 비밀번호 초기화 0:뒤로가기"
		else if(index == "031")
			System.out.println(message(room.language, "0060"));//	kor_message.put("0060","이 회원에게 경고를 줍니까? 1:예 2:뒤로가기");//1눌렀을 때
		else if(index == "0311")
			System.out.println(message(room.language, "0061"));//	kor_message.put("0061","경고를 주었습니다.");
		else if(index == "032")
			System.out.println(message(room.language, "0062"));//	kor_message.put("0062","해당 회원의 입퇴실내역입니다.");//회원검색결과 2번옵션
		else if(index == "033")
			System.out.println(message(room.language, "0063"));//	kor_message.put("0063","정말 비밀번호를 초기화하겠습니까? 0000으로 초기화됩니다. 1:예 2:뒤로가기");//회원검색결과3번옵션
		else if(index == "0331")
			System.out.println(message(room.language, "0064"));//	kor_message.put("0064","비밀번호가 초기화되었습니다.");//회원검색결과3번옵션-2
		return Integer.parseInt(scan.nextLine());
	}

	
	
}
