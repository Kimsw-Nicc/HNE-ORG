<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/basicsetting/manage.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">기본설정 > 기본설정조회</span>
				<span class="text-title h2">
					기본설정 이미지<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					기본설정이미지 등록은 "기본설정이미지 등록" 버튼을 통해 하실 수 있으며, 기본설정이미지의 수정 및 삭제는 아래 목록 화면에서 관리 할 수 있습니다. <br> 로고/슬로건은 1개 이상 등록하실 수 없습니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col">
				<a href="#" class="float-right btn btn-dark" onclick="openInsertSettingModal()"> <i class="xi-plus mr-1"></i>기본설정이미지 등록
				</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="tab-content" id="my-tab-content">
					<div class="tab-pane show active" id="main"></div>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="manage/insertBasicsettingModal.jsp" flush="true" />
<jsp:include page="manage/updateBasicsettingModal.jsp" flush="true" />
<%@include file="/WEB-INF/views/include/footer.jsp"%>
