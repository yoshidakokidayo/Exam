package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String subject_cd = "" ;
		String subject_name = "";
		Subject subject = new Subject();
		SubjectDao subjectDao = new SubjectDao();

		//リクエストパラメーターの取得2
		subject_cd = req.getParameter("subject_cd");
		subject_name = req.getParameter("subject_name");

		//DBからデータ取得3
		//なし

		//ビジネスロック4

		//subjectに科目情報をセット
		subject.setCd(subject_cd);
		subject.setName(subject_name);
		subject.setSchool(teacher.getSchool());

		//削除
		subjectDao.delete(subject);

		//レスポンス値をセット6
		//なし

		//jspへフォワード
		req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

	}

}
