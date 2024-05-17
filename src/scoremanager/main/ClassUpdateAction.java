package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String class_num = "";
		ClassNum classnum= new ClassNum();
		ClassNumDao classnumDao = new ClassNumDao ();


		//リクエストパラメーターの取得2
		class_num = req.getParameter("class_num");

		//DBからのデータ取得3
		//科目の詳細データを取得
		classnum = classnumDao.get(class_num , teacher.getSchool());

		//ビジネスロック4


		// レスポンス値をセット 6
		// リクエストに科目コードをセット
		req.setAttribute("class_num", class_num);


		// JSPへフォワード 7
		req.getRequestDispatcher("class_update.jsp").forward(req, res);

	}

}
