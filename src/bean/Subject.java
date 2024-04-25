package bean;

import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 学校コード:String
	 */
	private String SCHOOL_CD;

	/**
	 * 科目コード:String
	 */
	private String CD;

	/**
	 * 科目名:String
	 */
	private String NAME;

	/**
	 * ゲッター・セッター
	 */
	public String getSCHOOL_CD() {
		return SCHOOL_CD;
	}

	public void setSCHOOL_CD(String SCHOOL_CD) {
		this.SCHOOL_CD = SCHOOL_CD;
	}

	public String getCD() {
		return CD;
	}

	public void setCD(String CD) {
		this.CD = CD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}
}
