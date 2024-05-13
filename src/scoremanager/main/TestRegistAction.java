package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		HttpSession session = req.getSession(); //セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//ローカル変数の取得1
		String entYearStr = null; // 入力された入学年度
		int entYear = 0; // 入学年度
		String classNum = ""; // 入力されたクラス番号
		String subject = ""; //入力された科目
		String countStr = null; //入力された回数
		int count = 0; // 回数
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao
		SubjectDao subjectDao = new SubjectDao(); //科目Dao
		TestDao testDao = new TestDao(); // テストDao
		School teacherSchool = teacher.getSchool(); // テスト情報
		Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		// リクエストパラメーターの取得 2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject = req.getParameter("f3");
		countStr = req.getParameter("f4");

		//DBからデータ取得3
		List<String>cNumlist = cNumDao.filter(teacherSchool); //クラス情報
		List<Subject>list = subjectDao.filter(teacherSchool); //科目情報

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (countStr != null) {
			count = Integer.parseInt(countStr);
		}

		if (entYear != 0 && classNum != null && subject != null && count != 0) {
			List<Test>testlist = testDao.filter(entYear, classNum, subjectDao.get(subject, teacherSchool), count, teacherSchool);
			req.setAttribute("testlist", testlist);
		} else {
			errors.put("1", "入学年度とクラスと科目と回数を選択してください");
			req.setAttribute("errors", errors);
		}


		//レスポンス値をセット6
		//リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		//リクエストに科目をセット
		req.setAttribute("f3", subject);
		//リクエストに回数をセット
		req.setAttribute("f4", count);
		//リクエストにクラス情報リストをセット
		req.setAttribute("cNumlist", cNumlist);
		//リクエストに科目情報リストをセット
		req.setAttribute("list", list);

		//jspへのフォワード7
		req.getRequestDispatcher("test_regist.jsp").forward(req , res);
	}

}
