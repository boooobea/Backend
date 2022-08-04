package org.zerock.myapp.domain;

import java.util.Arrays;

import lombok.Value;


//@Data for DTO
//@Value for VO

@Value //읽기전용 VO, 기본타입은 null을 줄수 없기 때문에 참조타입(Wrapper)으로 타입지정
public class EmpVO {

	private Integer empno;			//PK
	private String ename;			//null
	private Double sal;				//null
	private Integer deptno;			//null


}//end class
