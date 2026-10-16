<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/user/hidden.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<div class="row">
			<div class="col-md-12">
				<button class="btn btn-primary" onclick="createUesrsAll()">일괄생성</button>
			</div>
			<div class="col">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>부서이름</th>
								<th>계정이름</th>
								<th>생성버튼</th>
								<th>삭제버튼</th>
							</tr>
						</thead>
						<tbody id="user-tbody">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="./list/checkPasswordModal.jsp"%>
<%@include file="./list/resetAllPasswordModal.jsp"%>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
