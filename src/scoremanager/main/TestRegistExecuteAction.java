package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String subject = ""; // 科目コード
		int count = 0; // 回数
		int point = 0; // 点数
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		TestDao testDao = new TestDao();
		List<Test> list = new ArrayList<>();
		int c = 0; // エラーの番号
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		//リクエストパラメーターの取得2
		subject = req.getParameter("subject");
		count = Integer.parseInt(req.getParameter("count"));
		String[] student_no = req.getParameterValues("regist");

		// DBからデータ取得 3
		// なし

		// ビジネスロジック 4
		for (String item : student_no) {
			Test test = new Test();

			point = Integer.parseInt(req.getParameter("point_" + item));

			if (point < 0 || point > 100) { // 得点が0～100の間の数値でない場合
				errors.put("" + c, "0～100の範囲で入力してください");
				// リクエストに入学年度をセット
				req.setAttribute("f1", studentDao.get(item).getEntYear());
				// リクエストにクラス番号をセット
				req.setAttribute("f2", studentDao.get(item).getClassNum());
				// リクエストに科目をセット
				req.setAttribute("f3", subjectDao.get(subject, teacher.getSchool()));
				// リクエストに回数をセット
				req.setAttribute("f4", count);
				break;
			} else {
				// testに学生情報をセット
				test.setStudent(studentDao.get(item));
				test.setClassNum(studentDao.get(item).getClassNum());
				test.setSubject(subjectDao.get(subject, teacher.getSchool()));
				test.setSchool(teacher.getSchool());
				test.setNo(count);
				test.setPoint(point);
				// リストに格納
				list.add(test);
			}
			// エラーの番号
			c++;
		}

		if (errors.isEmpty()) { // エラーがなかった場合
			// saveメソッドで情報を登録
			testDao.save(list);
		} else {
			// リクエストにエラーメッセージをセット
			req.setAttribute("errors", errors);
		}

		// レスポンス値をセット 6
		// なし

		// JSPへフォワード 7
		if (errors.isEmpty()) { // エラーメッセージがない場合
			// 登録完了画面にフォワード
			req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		} else {
			// 成績一覧画面にフォワード
			req.getRequestDispatcher("TestRegist.action").forward(req, res);
		}
	}
}
