package ua.nure.cs.neichev.usermanagement.domain;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	private static final int ETALONE_MAX_STUDENT_AGE = 25;
	private static final int ETALONE_AGE_1 = 19;
	private static final int ETALONE_ADULT_AGE = 18;
	private static final int ETALONE_MIN_STUDENT_AGE = 6;
	private static final int DAY_OF_BIRTH = 17;
	private static final int MONTH_OF_BIRTH = 8;
	private static final int YEAR_OF_BIRTH = 2000;
	private User user;

	public void testGetFullName() {
		user.setFirstName("John");
		user.setLastName("Doe");
		
		assertEquals("Doe, John", user.getFullName());
	}
	
	public void testGetAge1() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		
		assertEquals(ETALONE_AGE_1, user.getAge());
	}
	
	public void testStudentAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		
		assertTrue("Person is not in student age!", user.getAge() > ETALONE_MIN_STUDENT_AGE && user.getAge() < ETALONE_MAX_STUDENT_AGE);
	}
	
	public void testAdultAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		
		assertTrue("Person is too young!", ETALONE_ADULT_AGE <= user.getAge());
	}
	
	public void testUnrealAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(calendar.getTime());
		int birthYear = calendar.get(Calendar.YEAR);
		calendar.setTime(new Date());
		int currentYear = calendar.get(Calendar.YEAR);
		
		assertTrue("There is unreal age!", birthYear <= currentYear);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
