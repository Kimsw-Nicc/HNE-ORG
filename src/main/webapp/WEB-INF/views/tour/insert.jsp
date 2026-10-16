<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/tour/insert.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">관광안내 > 관광안내 등록</span>
				<span class="text-title h2">
					관광안내 등록<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					관광안내를 등록하는 페이지 입니다. 제목, 이미지 파일을 반드시 입력 후 등록 하시기 바랍니다. <br /> <i class="xi-check input-need"></i> 표시가 붙어있는 항목은 필수 입력 항목입니다.
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
						<!-- vi_name-->
						<div class="form-group">
							<label class="text-lb"> 1. 제목<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">최대 15자 이내 (특수문자 제외)</span>
							</label> <input type="text" class="form-control" maxlength="15" placeholder="제목" id="tour-name" oninput="checkText(event)" autocomplete="off" />
						</div>
						<!-- in_image -->
						<div class="form-group">
							<label class="text-lb"> 2. 이미지 파일<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">파일 : 5MB 이하 / 가로 1,080px, 가로 1,920px / PNG 또는 JPG 형식</span>
							</label>
							<div class="input-group">
								<input id="tour-filename" class="file_input_textbox form-control" readonly />
								<div class="file_input_div">
									<input type="button" value="파일" class="file_input_button btn btn-dark" /> <input id="tour-file" type="file" class="file-input-hidden" data-icon="false" accept="image/*" data-width="1920" />
								</div>
							</div>
							<span id="upload-msg"></span>
						</div>
					</div>
				</div>
				<div class="col-md-3"></div>
				<div class="col-md-12">
					<div class="form-group text-center">
						<button class="btn btn-primary" onclick="insertTour()">
							<i class="xi-check mr-1"></i>등록
						</button>
						<a class="btn btn-secondary" href='/tour/list'> <i class="xi-arrow-left mr-1"></i>돌아가기
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
