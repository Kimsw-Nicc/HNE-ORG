<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/tour/list.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">관광안내 > 관광안내 조회회</span>
				<span class="text-title h2">
					관광안내<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					관광안내 정보 수정 및 삭제는 아래 목록 화면에서 관리 할 수 있습니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col">
				<a href="/tour/insert" class="float-right btn btn-dark"> <i class="xi-plus mr-1"></i>관광안내 등록
				</a>
			</div>
		</div>
		<div id="tour-container" class="row"></div>
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
