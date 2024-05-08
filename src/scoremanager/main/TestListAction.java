package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao
		SubjectDao subjectDao = new SubjectDao(); //科目Dao
		School teacherSchool = teacher.getSchool(); // テスト情報

		//DBからデータ取得3
		List<String>cNumlist = cNumDao.filter(teacherSchool); //クラス情報
		List<Subject>list = subjectDao.filter(teacherSchool); //科目情報

		//レスポンス値をセット6
		//リクエストにクラス情報リストをセット
		req.setAttribute("cNumlist", cNumlist);
		//リクエストに科目情報リストをセット
		req.setAttribute("list", list);

		//jspへのフォワード7
		req.getRequestDispatcher("test_list.jsp").forward(req , res);
	}

}
