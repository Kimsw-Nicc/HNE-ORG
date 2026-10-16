<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/alert/insert.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">알리미 > 알리미 등록</span>
				<span class="text-title h2">
					알리미 등록<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					장비에 표시 될 알리미를 등록하는 페이지 입니다. 등록부서, 타입, 내용, 표시시간, 표시여부를 반드시 입력 하시기 바랍니다.<br /> <i class="xi-check input-need mr-1"></i>표시가 붙어있는 항목은 필수 입력 항목입니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->

		<div class="border rounded p-3">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="form-horizontal" role="form">
						<!-- category -->
						<div class="form-group">
							<label class="text-lb">1. 구분<i class="xi-check-min ml-1 mr-1 input-need"></i>
							<span class="small">알리미는 상단표출, 메시지는 메인화면 중앙 표출</span></label>
							<div>
								<div class="form-check form-check-inline">
									<input id="category-alert" class="form-check-input alert-category" type="radio" value="alert" name="category" checked /> <label class="form-check-label" for="category-alert">알리미</label>
								</div>
								<div class="form-check form-check-inline">
									<input id="category-message" class="form-check-input alert-category" type="radio" value="message" name="category" /> <label class="form-check-label" for="category-message">메시지</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="">※메시지 등록 시 메인화면 전체에 안내정보가 표출 됩니다.</div>
						</div>
						<div class="type-group">
							<!-- type -->
							<div class="form-group">
								<label class="text-lb">타입 (중요도)<i class="xi-check-min ml-1 mr-1 input-need"></i></label>
								<div>
									<div class="form-check form-check-inline">
										<input id="type-info" class="form-check-input alert-type" type="radio" value="info" name="type" checked /> <label class="form-check-label" for="type-info">낮음</label>
									</div>
									<div class="form-check form-check-inline">
										<input id="type-warning" class="form-check-input alert-type" type="radio" value="warning" name="type" /> <label class="form-check-label" for="type-warning">중간</label>
									</div>
									<div class="form-check form-check-inline">
										<input id="type-critical" class="form-check-input alert-type" type="radio" value="critical" name="type" /> <label class="form-check-label" for="type-critical">높음</label>
									</div>
								</div>
								<span class="small">중요도에 따라 배경색이 다르게 표시됩니다.</span>
							</div>
						</div>
						<!-- type:alert => st_date, ed_date-->
						<div class="form-group">
							<label class="text-lb">2. 알림기간<i class="xi-check-min ml-1 mr-1 input-need"></i></label>
							<div class="input-group">
								<input id="alert-start-date" type="text" class="form-control datepicker start-date" placeholder="0000-00-00" />
								<span class="input-group-addon m-2"> ~ </span>
								<input id="alert-end-date" type="text" class="form-control datepicker end-date" placeholder="0000-00-00" />
							</div>
						</div>
						<!-- type:lunch => st_time, ed_time-->
						<div class="form-group">
							<label class="text-lb">3. 알림시간<i class="xi-check-min ml-1 mr-1 input-need"></i></label>
							<div class="input-group">
								<select class="form-control sttime" id="alert-start-time">
								</select>
								<span class="input-group-addon m-2"> ~ </span>
								<select class="form-control edtime" id="alert-end-time">
								</select>
							</div>
						</div>
						<!-- content -->
						<div class="form-group">
							<label class="text-lb">* 표출문구<i class="xi-check-min ml-1 mr-1 input-need"></i></label> <input type="text" class="form-control" maxlength="50" placeholder="표출 문구를 입력하세요." id="alert-content" autocomplete="off" />
							<span class="small">최대 50자 이내</span>
						</div>
						<!-- visible -->
						<div class="form-group">
							<label class="text-lb">* 표시여부<i class="xi-check-min ml-1 mr-1 input-need"></i></label>
							<div class="radio radio-inline radio-danger">
								<input class="alert-visible" type="radio" id="visible-false" value="false" name="visible" /> <label for="visible-false">표시안함</label>
							</div>
							<div class="radio radio-inline radio-info">
								<input class="alert-visible" type="radio" id="visible-true" value="true" name="visible" checked /> <label for="visible-true">표시함</label>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-12">
					<div class="form-group text-center">
						<button class="btn btn-primary" onclick="insertAlert()">
							<i class="xi-check mr-1"></i>등록
						</button>
						<a class="btn btn-secondary" href='/alert/list'> <i class="xi-arrow-left mr-1"></i>돌아가기
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>


<%@include file="/WEB-INF/views/include/footer.jsp"%>
