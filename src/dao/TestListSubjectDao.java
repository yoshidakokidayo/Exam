package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "select student.ent_year, test.student_no, student.name, test.class_num, test.no, test.point from test";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 科目別リストインスタンスを初期化
				TestListSubject tls = new TestListSubject();
				// 科目別リストインスタンスに検索結果をセット
				tls.setEntYear(rSet.getInt("ent_year"));
				tls.setStudentNo(rSet.getString("student_no"));
				tls.setStudentName(rSet.getString("name"));
				tls.setClassNum(rSet.getString("class_num"));
				tls.putPoint(rSet.getInt("no"), rSet.getInt("point"));
				// リストに追加
				list.add(tls);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の結合
		String join = "join student on test.student_no = student.no";
		// SQL文の条件
		String condition = "where student.ent_year = ? and test.subject_cd = ? and test.class_num = ? and test.school_cd = ?";
		// SQL文のソート
		String order = "order by test.student_no asc, test.no adc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + join + condition + order);
			// プリペアードステートメントに入学年度をバインド
			statement.setInt(1, entYear);
			// プリペアードステートメントに科目番号をバインド
			statement.setString(2, subject.getCd());
			// プリペアードステートメントにクラス番号をバインド
			statement.setString(3, classNum);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(4, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			list = postFilter(rSet);
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
}
