<!-- 成績参照検索JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me=4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

					<div class="col-2">
					<p>科目情報</p>
					</div>

					<div class="col-2">
						<label class="form-label" for="subject-f1-select">入学年度</label>
						<select class="form-select" id="subject-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set }">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${year }" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select" id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject.cd" items="${class_subject.cd_set }">
								<%-- 現在のsubject.cdと選択されていたf3が一致していた場合selectedを追記 --%>
								<option value="${subject.cd }" <c:if test="${subject.cd==f3 }">selected</c:if>>${subject.cd }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-2 text-center">
					<input type="hidden" name="f" value="sj">
						<button class="btn btn-secondary" id="subject-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>

					<hr>

					<div class="col-2">
					<p>学生情報</p>
					</div>

					<div class="col-4">
						<label class="form-label" for="student-f4-select">学生番号</label>
						<input class="form-control" type="text" id="student-f4-select"
						name="f4" value="${f4 }" required maxlength="10"
						placeholder="学生番号を入力してください"/>
					</div>

					<div class="col-2 text-center">
						<input type="hidden" name="f" value="st">
						<button class="btn btn-secondary" id="student-button">検索</button>
					</div>

				</div>
			</form>

			<p>
				<font color="5accf2">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
				</font>
			</p>

			<!-- 画面設計書の画面項目15、16の識別コードの書き方が分からないです！！！！！！！ -->
			<!-- それに伴って、検索の分け方もわからないです！！！！ -->

			</section>
	</c:param>
</c:import>