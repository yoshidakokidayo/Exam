package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		int entYear = 0; // 入力された入学年度
		String classNum = ""; // 入力されたクラス番号
		String subject = ""; //入力された科目
		TestListSubjectDao tlsDao = new TestListSubjectDao();
		SubjectDao subjectDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメーターの取得2
		entYear = Integer.parseInt(req.getParameter("f1"));
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");

		// DBからの取得3
		// ビジネスロジック4
		if (entYear == 0 || classNum == null || subject == null) { // 入学年度、クラス番号、科目のいずれかが未選択の場合
			errors.put("1", "入学年度とクラス番号と科目を選択してください");
			// リクエストにエラーメッセージをセット
			req.setAttribute("errors", errors);
		} else {
			List<TestListSubject> list = tlsDao.filter(entYear, classNum, subjectDao.get(subject, teacher.getSchool()), teacher.getSchool());
			// リクエストに科目別一覧をセット
			req.setAttribute("list", list);
		}

		// レスポンス値をセット6
		// リクエストに入学年度をセット
		req.setAttribute("entYear", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("classNum", classNum);
		// リクエストに科目をセット
		req.setAttribute("subject", subject);

		// フォワード7
		if (errors.isEmpty()) { // エラーが出なかった場合
			req.getRequestDispatcher("test_list_subject.jsp").forward(req , res);
		}else{ //エラーが出た場合
			req.getRequestDispatcher("test_list.jsp").forward(req , res);
		}

	}

}
