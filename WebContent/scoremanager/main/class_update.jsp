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
			<form action="ClassUpdateExecute.action" method="get">
				<div>
					<label class="mx-auto py-2" for="class_num">クラス名</label><br>
					<input class="form-control" type="text" name="class_num" id="class_num" value="${class_num }"
					required maxlength="5" />
				</div>
				<div class="mt-2 text-warning">${errors.get("1") }</div>

				<div class="mx-auto py-2">
					<input class="btn btn-primary" type="submit" value="変更"/>
				</div>
				<input type="hidden" name="old_class_num" <c:if test="${not empty errors }">value="${old_class_num }"</c:if>value="${class_num }">
			</form>
			<a href="ClassList.action">戻る</a>
		</section>
	</c:param>
</c:import>