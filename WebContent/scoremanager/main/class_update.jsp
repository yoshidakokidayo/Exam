<!-- クラス変更JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section>
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">クラス情報変更</h2>
			<form action="SubjectUpdateExecute.action" method="get">
				<div>
					<label class="mx-auto py-2" for="class_num">クラス名</label><br>
					<input class="border border-0 ps-3" type="text" id="class_num" name="class_num" value="${class_num }" readonly />
				</div>
				<div class="mt-2 text-warning">${errors.get("1") }</div>

				<div class="mx-auto py-2">
					<input class="btn btn-primary" type="submit" value="変更"/>
				</div>
			</form>
			<a href="ClassList.action">戻る</a>
		</section>
	</c:param>
</c:import>