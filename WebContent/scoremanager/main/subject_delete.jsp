<!-- 科目削除JSP -->
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<form action="SubjectDelete.action" method="get">

				<div>
					<p>「${subject_name }（${subject_cd }）」を削除してもよろしいですか</p>
				</div>

				<div class="mx-auto py-2">
					<button class="btn btn-danger" value="削除">削除</button>
				</div>
			</form>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>