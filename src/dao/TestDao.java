package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select test.student_no, test.subject_cd, test.school_cd, test.no, test.point, test.class_num from test";

	public Test get(Student student, Subject subject, School school, int no) throws Exception {

		// テストインスタンスを初期化
		Test test = new Test();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + "where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
			// プリペアードステートメントに値をバインド
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// 学生Daoを初期化
			StudentDao studentDao = new StudentDao();
			// 科目Daoを初期化
			SubjectDao subjectDao = new SubjectDao();
			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// テストインスタンスに検索結果をセット
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(rSet.getString("subject_cd"), schoolDao.get(rSet.getString("school_cd"))));
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
			} else {
				// リザルトセットが存在しない場合
				// テストインスタンスにnullをセット
				test = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return test;
	}

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		try {
			// リザルトセット全権走査
			while (rSet.next()) {
				// 得点インスタンスを初期化
				Test test = new Test();
				// 得点インスタンスに検索結果をセット
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setClassNum(rSet.getString("class_num"));
				test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				// リストを初期化
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

		// リストを初期化
		List<Test> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の結合
		String join = " join student on student.no = test.student_no";
		// SQL文の条件
		String condition = " where student.ent_year = ? and test.class_num = ? and test.subject_cd = ? and test.no = ? and student.school_cd = ?";
		// SQL文のソート
		String order = " order by student_no asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + join + condition + order);
			// プリペアードステートメントに入学年度をバインド
			statement.setInt(1, entYear);
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(2, classNum);
			// プリペアードステートメントに科目番号をバインド
			statement.setString(3, subject.getCd());
			// プリペアードステートメントにテスト回数をバインド
			statement.setInt(4, num);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(5, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	public boolean save(List<Test> list) throws Exception {

		try {
			for (Test test : list) {
				// コネクションを確立
				Connection connection = getConnection();
				// saveメソッドで情報を保存
				save(test, connection);
			}
		} catch (Exception e) {
			throw e;
		}

		return true;
	}

	private boolean save(Test test, Connection connection) throws Exception {

		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースからテスト情報を取得
			Test old = get(test.getStudent(), test.getSubject(), test.getSchool(), test.getNo());
			if (old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("insert into test(student_no, subject_cd, school_cd, no, point, class_num) values(?, ?, ?, ?, ?, ?)");
				// プリペアードステートメントに値をバインド
				statement.setString(1, test.getStudent().getNo());
				statement.setString(2, test.getSubject().getCd());
				statement.setString(3, test.getSchool().getCd());
				statement.setInt(4, test.getNo());
				statement.setInt(5, test.getPoint());
				statement.setString(6, test.getClassNum());
			} else {
				// テストが存在した場合
				// プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update test set point = ? where student_no = ? and subject_cd = ? and school_cd = ? and no = ?");
				// プリペアードステートメントに値をバインド
				statement.setInt(1, test.getPoint());
				statement.setString(2, test.getStudent().getNo());
				statement.setString(3, test.getSubject().getCd());
				statement.setString(4, test.getSchool().getCd());
				statement.setInt(5, test.getNo());
			}

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}

	public boolean delete(List<Test> list) throws Exception {

		// 機能なし
		return true;
	}

	private boolean delete(Test test, Connection connection) throws Exception {

		// 機能なし
		return true;
	}
}