package com.victoree2.system;

import java.util.ArrayList;
import java.util.List;

public class CeatSystem {
	
	String test[][] = { {"[1]" , "[2]"},
			{"[3]", "[4]"}};
	String test2[][] = { {"[1]" , "[2]"},
			{"[3]", "[4]"}};
	List<String[][]> testList = new ArrayList<String[][]>();
//	
//	Scanner scan = new Scanner(System.in);
//	String a = scan.nextLine();
//	for(int i =0 ; i < test.length ; i++) {
//		for(int j = 0 ; j < test[i].length ; j++) {
//			
//			test[i][j] = test[i][j].replace("[", "");
//			test[i][j] = test[i][j].replace("]", "");
//			if(test[i][j].equals(a))
//				System.out.print(test[i][j]+","+i+","+j);
//		}
//		System.out.println();
//	}
	
	public List<String[][]> getTestList() {
		return testList;
	}

	public void setTestList(List<String[][]> testList) {
		this.testList = testList;
	}

	public void init() {//초기화 함수.
		//반복문으로 배열을 초기값 설정 값은 행열 순서대로 1좌석부터 시작, 각 좌석 [] 관호 사용 (ex) [1][2][3]......
		
		testList.add(test);
		testList.add(test2);
	}
	
	public void print() { //출력함수
		for(int i = 0 ; i < test.length ; i++) {
			for(int j = 0 ; j < test[i].length ; j++) {
				System.out.print(test[i][j]);
			}
			System.out.println();
		}
	}
	
}
