<!-- 成績管理一覧JSP -->

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

			<form method="get">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

				<!-- しかくの中 -->
				    <!-- 入学年度 -->
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set }">
								<%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
								<option value="${year }" <c:if test="${year==entYear }">selected</c:if>>${year }</option>
							</c:forEach>
						</select>
					</div>
					<!-- クラス -->
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num }" <c:if test="${num==classNum }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 科目 -->
					<div class="col-4">
						<label class="form-label" for="student-f2-select">科目</label>
						<select class="form-select" id="student-f2-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject.cd" items="${subject_cd_set }">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${subject.cd }" <c:if test="${subject.cd==subject }">selected</c:if>>${subject.cd }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 回数 -->
					<div class="col-2">
						<label class="form-label" for="student-f2-select">回数</label>
						<select class="form-select" id="student-f2-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set }">
								<%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
								<option value="${num }" <c:if test="${num==count }">selected</c:if>>${num }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 検索ボタン -->
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${errors.get("f1") }</div>
				</div>
			</form>
			<c:choose>


				<c:when test="${testlist.size()>0 }">
					<div>科目：${students.size() }件</div>
					<!-- 表示するテーブルの作成 -->
					<table class="table table-hover">
						<tr>
							<th>入学年度</th>
							<th class="text-center">クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>点数</th>
							<th></th>
							<th></th>
						</tr>
						<c:forEach var="student" items="${students }">
							<tr>
								<td>${student.entYear }</td> <!-- 入学年度 -->
								<td>${test.no }</td> <!-- クラス -->
								<td>${test.student_no }</td> <!-- 学生番号 -->
								<td>${student.name }</td> <!-- 氏名 -->
								<td>${test.point }</td> <!-- 得点 -->
								<td>
								<div class="">
								<label for="point_${学生番号} }">得点</label>
								<input type="text" id="point_${学生番号} }" name="point_${学生番号} }"  />
								</div>
								</td>
							</tr>
						</c:forEach>
					</table>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">登録して終了</button>
					</div>
				</c:when>

			</c:choose>
		</section>
	</c:param>
</c:import>