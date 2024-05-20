package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.ClassDao;
import tool.Action;

public class ClassListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数1
		ClassDao classDao = new ClassDao();//クラスDAO

		//DBからデータ取得3
		List<Subject>list = ClassDao.filter(teacher.getSchool());

		//レスポンス値をセット6
		req.setAttribute("Class",list);

		// JSPへフォワード 7
		req.getRequestDispatcher("class_list.jsp").forward(req, res);

	}

}
