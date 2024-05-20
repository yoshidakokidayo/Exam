package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の指定1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String student_no = ""; // 学生番号
		Student student = new Student(); // 学生
		TestListStudentDao tlsDao = new TestListStudentDao();
		StudentDao studentDao = new StudentDao();
		SubjectDao subjectDao = new SubjectDao();
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao
		LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
		int year = todaysDate.getYear(); // 現在の年を取得
		List<TestListStudent> tlslist = new ArrayList<>();

		// リクエストパラーメーターの取得2
		student_no = req.getParameter("f4");

		// DBからの取得3
		student = studentDao.get(student_no);
		if (student != null) {
			tlslist = tlsDao.filter(student);
		}

		List<String>cNumlist = cNumDao.filter(teacher.getSchool()); //クラス情報
		List<Subject>list = subjectDao.filter(teacher.getSchool()); //科目情報

		//ビジネスロジック4
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		// レスポンス値をセット6
		// リクエストに学生情報をセット
		req.setAttribute("student", student);
		// リクエストに学生別一覧をセット
		req.setAttribute("tlslist", tlslist);
		// リクエストに学生番号をセット
		req.setAttribute("f4", student_no);
		//リクエストにクラス情報リストをセット
		req.setAttribute("cNumlist", cNumlist);
		//リクエストに科目情報リストをセット
		req.setAttribute("list", list);
		//リクエストに入学年度リストをセット
		req.setAttribute("entYearSet", entYearSet);

		// フォワード7
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
