//学生別成績一覧

package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TestListSubject implements Serializable {

	/**
	 * 何年生：int
	 */
	private int entYear;

	/**
	 * 学生番号：String
	 */
	private String studentNo;

	/**
	 * 学生名：String
	 */
	private String studentName;

	/**
	 * クラス：String
	 */
	private String classNum;

	/**
	 * クラス：Map<integer,integer>
	 */
	private Map<Integer,Integer> points=new HashMap<>();



	/**
	 * ゲッタ・セッタ
	 */
	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Map<Integer,Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer,Integer> points) {
		this.points = points;
	}






}
