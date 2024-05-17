package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession(); // セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String class_num = "" ;
		ClassNum classnum= new ClassNum();
		ClassNumDao classnumDao = new ClassNumDao ();

		//リクエストパラメーターの取得2
		class_num = req.getParameter("class_num");

		//DBからデータ取得3
		//なし

		//ビジネスロック4

		//subjectに科目情報をセット
		classnum.setClass_num(class_num);;
		classnum.setSchool(teacher.getSchool());

		//変更内容を保存
		classnumDao.save(classnum);

		//レスポンス値をセット6
		//なし

		//jspへフォワード
		req.getRequestDispatcher("class_update_done.jsp").forward(req, res);

	}

}
