<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<script src="/resources/js/sync/scheduler.js"></script>
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">동기화 > 동기화 관리</span>
				<span class="text-title h2">
					동기화 관리<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					일괄 동기화 : 전체 직원정보를 즉시 동기화 합니다.<br> 선택부서 동기화 : 설정한 부서의 직원정보를 즉시 동기화 합니다.<br> 주기적 일괄 동기화 : 설정한 시간에 따라 직원정보를 일괄 동기화 합니다.<br> (매월 체크 시 일을 선택, 매주 체크 시 요일을 선택하실 수 있습니다.)<br> 주기적 일괄 동기화 정지 : 설정한 시간 동기화를 해제합니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col-md-6 border">
				<div class="rounded p-3 m-3">
					<div class="form-group">
						<h5 class="text-lb">현재 설정된 시간</h5>
					</div>
					<label id="schedule-msg" class="text-lb"></label>
				</div>
			</div>
			<div class="col-md-6 border">
				<div id="sync-history" class="rounded p-3 m-3"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<a href="#" id="sync-all-button" class="btn btn-block btn-lg btn-outline-dark pt-3" onclick="openSyncAll()"> <i class="xi-sitemap mr-1 display-4"></i><br>일괄 동기화<i class="xi-sync ml-1 pb-3"></i>
				</a>
			</div>
			<div class="col-md-3">
				<a href="#" id="sync-selection-button" class="btn btn-block btn-lg btn-outline-primary pt-3" onclick="openSyncSelection()"> <i class="xi-sitemap-o mr-1 display-4"></i><br>선택부서 동기화<i class="xi-sync ml-1 pb-3"></i>
				</a>
			</div>
			<div class="col-md-3">
				<a href="#" id="update-sync-button" class="btn btn-block btn-lg btn-outline-warning pt-3" onclick="openUpdateSchedule()"> <i class="xi-time-o mr-1 display-4"></i><br>주기적 일괄 동기화 설정<i class="xi-sync ml-1 pb-3"></i>
				</a>
			</div>
			<div class="col-md-3">
				<a href="#" id="reset-sync-button" class="btn btn-block btn-lg btn-outline-info pt-3" onclick="resetSync()"> <i class="xi-close mr-1 display-4"></i><br>주기적 일괄 동기화 해제<i class="xi-sync ml-1 pb-3"></i>
				</a>
			</div>
		</div>
	</div>
</section>

<!-- 시간 동기화 -->

<style>
.layer {
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -100px 0 0 -100px;
}
</style>
<jsp:include page="scheduler/syncAllModal.jsp" flush="true" />
<jsp:include page="scheduler/syncSelectionModal.jsp" flush="true" />
<jsp:include page="scheduler/updateScheduleModal.jsp" flush="true" />
<%@include file="/WEB-INF/views/include/footer.jsp"%>