package bean;

import java.io.Serializable;

public class Test implements Serializable {

	//保存できるフィールドを設定する
	/**
	 * 学生番号:String
	 */
	private String STUDENT_NO;

	/**
	 * 科目コード:String
	 */
	private String SUBJECT_CD;

	/**
	 * 学校コード:String
	 */
	private String SCHOOL_CD;

	/**
	 * 回数:int
	 */
	private int NO;

	/**
	 * 得点:int
	 */
	private int POINT;

	/**
	 * クラス番号:String
	 */
	private String CLASS_NUM;


	//ゲッタとセッタ
	public String getSTUDENT_NO() {
		return STUDENT_NO;
	}

	public void setSTUDENT_NO(String STUDENT_NO) {
		this.STUDENT_NO = STUDENT_NO;
	}

	public String getSUBJECT_CD() {
		return SUBJECT_CD;
	}

	public void setSUBJECT_CD(String SUBJECT_CD) {
		this.SUBJECT_CD = SUBJECT_CD;
	}

	public String getSCHOOL_CD() {
		return SCHOOL_CD;
	}

	public void setSCHOOL_CD(String SCHOOL_CD) {
		this.SCHOOL_CD = SCHOOL_CD;
	}

	public int getNO() {
		return NO;
	}

	public void setSUBJECT_CD(int NO) {
		this.NO = NO;
	}

	public int getPOINT() {
		return POINT;
	}

	public void setPOINT(int POINT) {
		this.POINT = POINT;
	}

	public String getCLASS_NUM() {
		return CLASS_NUM;
	}

	public void setCLASS_NUM(String CLASS_NUM) {
		this.CLASS_NUM = CLASS_NUM;
	}
}