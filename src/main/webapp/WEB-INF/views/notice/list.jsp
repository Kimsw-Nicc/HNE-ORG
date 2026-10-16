<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/notice/list.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">공지사항 > 공지사항조회</span>
				<span class="text-title h2">
					공지사항<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					공지사항을 등록, 수정, 삭제가 가능한 페이지 입니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<div class="row">
			<div class="col-6">
				<c:if test="${'ADMIN_ROLE' == login.userRole}">
					<select id="org-cd"></select>
				</c:if>
			</div>
			<div class="col-6">
				<div class="input-group">
					<input type="text" id="search-input" class="form-control" placeholder="검색 제목 입력" oninput="checkText(event)" autocomplete="off" />
					<div class="input-group-append">
						<button id="search-btn" class="btn btn-outline-secondary mr-2">
							<i class="xi-search mr-1">검색</i>
						</button>
						<a href="/notice/insert" class="float-right btn btn-dark"> <i class="xi-plus mr-1"></i>공지사항 등록
						</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table table-hover mails m-0 table table-actions-bar">
			<thead>
				<tr>
					<th class="text-center" width="9%">수정</th>
					<th class="text-center" width="9%">구분</th>
					<th class="text-center" width="35%">제목</th>
					<th class="text-center" width="6%">순서</th>
					<th class="text-center" width="22%">표시기간</th>
					<th class="text-center" width="11%">작성일</th>
					<th class="text-center" width="6%">삭제</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="">
				<ul class="pagination justify-content-center paging"></ul>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>