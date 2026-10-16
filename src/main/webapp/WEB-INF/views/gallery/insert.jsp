<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/gallery/insert.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">포토갤러리 > 포토갤러리 등록</span>
				<span class="text-title h2">
					포토갤러리등록 <i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					포토갤러리 이미지를 등록하는 페이지 입니다. 제목, 이미지 파일을 반드시 입력 후 등록 하시기 바랍니다. <br /> <i class="xi-check input-need"></i> 표시가 붙어있는 항목은 필수 입력 항목입니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="border rounded p-3">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<label class="text-lb">1. 구분</label>
							<c:if test="${login.userRole.name() == 'MANAGER_ROLE'}">
								<input type="hidden" id="gallery-org" value="${login.orgCd}">
								<input type="text" readonly="readonly" class="form-control" value="${login.orgFullname}">
							</c:if>
							<c:if test="${login.userRole.name() == 'ADMIN_ROLE'}">
								<select id="gallery-org" class="form-control">
								</select>
							</c:if>
						</div>
						<!-- img title -->
						<div class="form-group">
							<label class="text-lb"> 2. 제목 <i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">최대 20자 이내 (특수문자 제외)</span>
							</label> <input type="text" class="form-control" placeholder="사진 제목" id="gallery-name" maxlength="20" oninput="checkText(event)" autocomplete="off" />
						</div>
						<!--  img sort -->
						<div class="form-group">
							<label class="text-lb"> 3. 순서 <i class="xi-check-min ml-1 mr-1 input-need"></i>
							</label> <input type="number" class="form-control" placeholder="사진 순서" id="gallery-sort" maxlength="20" autocomplete="off" />
						</div>
						<!-- gallery-file -->
						<div class="form-group">
							<label class="text-lb"> 4. 이미지 파일 <i class="xi-check-min ml-1 input-need"></i> <span class="small ml-2">제한 : 5MB 이하, PNG 또는 JPG 형식</span>
							</label>
							<div class="input-group">
								<input id="gallery-filename" class="file-input-textbox form-control" readonly />
								<div class="file-input-div">
									<input type="button" value="파일" class="file-input-button btn btn-dark" /> <input type="file" class="file-input-hidden" data-icon="false" accept="image/*" id="gallery-file" />
								</div>
							</div>
							<div id="upload-msg"></div>
						</div>
						<div class="form-group text-center">
							<button class="btn btn-primary" onclick="insertGallery()">
								<i class="xi-check mr-1"></i> 등록
							</button>
							<a href="/gallery/list" class="btn btn-secondary"> <i class="xi-arrow-left mr-1"></i> 뒤로가기
							</a>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
