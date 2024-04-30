package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//ローカル変数1
		SubjectDao subjectDao = new SubjectDao(); // 科目Dao

		//DBからデータ取得3
		List<String>list = subjectDao.filter(teacher.getSchool());

		//レスポンス値をセット6
		req.setAttribute("list",list);

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);

	}

}
