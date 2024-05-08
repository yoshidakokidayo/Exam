package scoremanager.main;

//𠮷田
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

public class TestRegisExecuteAction extends Action {

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
		// saveメソッドで情報を登録
		testDao.save(list);
		// レスポンス値をセット 6
		// リクエストに入学年度をセット
		req.setAttribute("ent_year", ent_year);
		// リクエストにクラス番号をセット
		req.setAttribute("class_num", class_num);
		// リクエストに学生番号をセット
		req.setAttribute("no", student_no);
		// リクエストに氏名をセット
		req.setAttribute("name", student_name);
		// リクエストに点数をセット
		req.setAttribute("score", score);

		// JSPへフォワード 7
		if (errors.isEmpty()) { // エラーメッセージがない場合
			// 登録完了画面にフォワード
			req.getRequestDispatcher("test_regis_done.jsp").forward(req, res);
		}
	}
}
