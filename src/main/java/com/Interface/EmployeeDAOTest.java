package com.Interface;

/**
 * <p>
 * TODO 描述
 * </p>
 *
 * @author hyong
 * @since 2021/10/21
 */
public class EmployeeDAOTest {
	@MyBefore
	public void init(){
		System.out.println("初始化......");
	}

	@MyAfter
	public void destroy(){
		System.out.println("销毁中......");
	}

	@MyTest
	public void testSave(){
		System.out.println("save......");
	}

	@MyTest
	public void testDelete(){
		System.out.println("delete......");
	}
}
