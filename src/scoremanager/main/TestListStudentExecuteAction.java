package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の指定1
		String student_no = ""; // 学生番号
		Student student = new Student(); // 学生
		TestListStudentDao tlsDao = new TestListStudentDao();
		StudentDao studentDao = new StudentDao();

		// リクエストパラーメーターの取得2
		student_no = req.getParameter("f4");

		// DBからの取得3
		student = studentDao.get(student_no);
		List<TestListStudent> list = tlsDao.filter(student);

		// ビジネスロジック4
		// なし

		// レスポンス値をセット6
		// リクエストに学生情報をセット
		req.setAttribute("student", student);
		// リクエストに学生別一覧をセット
		req.setAttribute("list", list);

		// フォワード7
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
