package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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
		String old_class_num = "";
		ClassNum classNum= new ClassNum();
		ClassNumDao cnDao = new ClassNumDao ();
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		//リクエストパラメーターの取得2
		class_num = req.getParameter("class_num");
		old_class_num = req.getParameter("old_class_num");

		//DBからデータ取得3
		//なし

		//ビジネスロック4
		if (cnDao.get(class_num, teacher.getSchool()) != null) { // クラス番号が重複している場合
			errors.put("1", "クラス番号が重複しています");
			// リクエストにエラーメッセージをセット
			req.setAttribute("errors", errors);
		} else {
			// classNumにセット
			classNum.setClass_num(old_class_num);
			classNum.setSchool(teacher.getSchool());
			// saveメソッドで情報を登録
			cnDao.save(classNum, class_num);
		}

		//レスポンス値をセット6
		req.setAttribute("class_num", class_num);
		req.setAttribute("old_class_num", old_class_num);

		//jspへフォワード7
		if (errors.isEmpty()) { // エラーメッセージがない場合
			// 登録完了画面にフォワード
			req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
		} else { // エラーメッセージがある場合
			// 登録画面にフォワード
			req.getRequestDispatcher("class_update.jsp").forward(req, res);
		}
	}

}
