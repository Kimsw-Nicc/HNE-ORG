<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/movie/list.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">홍보콘텐츠 > 홍보콘텐츠 조회</span>
				<span class="text-title h2">
					홍보콘텐츠<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					등록된 홍보콘텐츠 목록을 표시합니다. 홍보콘텐츠는 파일 업로드 후 각 장비별 스트리밍 방식으로 송출 됩니다.<br /> 용량이 클 경우 정상적으로 재생이 안 될 수 있으니 참고하시기 바랍니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col-6">
				<c:if test="${'ADMIN_ROLE' == login.userRole}">
					<select id="org-cd"></select>
				</c:if>
			</div>
			<div class="col-6">
				<a href="/movie/insert" class="float-right btn btn-dark"> <i class="xi-plus mr-1"></i>홍보콘텐츠 등록
				</a>
			</div>
		</div>
		<div class="row" id="movie-container"></div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="">
				<ul class="pagination justify-content-center paging"></ul>
			</div>
		</div>
	</div>
	<!-- end row -->
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
