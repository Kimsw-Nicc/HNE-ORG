<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/guide/floor/insert.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<!-- Page-Title -->
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">청사안내도 > 청사안내도 등록</span>
				<span class="text-title h2">
					청사안내도 등록 <i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					안내도 등록을 하는 페이지 입니다. 청사 구분에 따라 1층, 2층 ... 지하층 등으로 구분하여 이미지를 등록하십시요. <br> <i class="xi-check-min input-need"></i> 표시가 붙어있는 항목은 필수 입력 항목입니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<div class="border rounded p-3">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="form-horizontal" role="form">
						<!-- buildingId -->
						<div class="form-group">
							<label class="text-lb"> 1. 청사구분<i class="xi-check-min ml-1 mr-1 input-need"></i>
							</label> <select id="building-id" class="form-control">
							</select>
						</div>
						<input style="display: none" aria-hidden="true"> <input type="text" style="display: none" aria-hidden="true">
						<!-- floor_name -->
						<div class="form-group">
							<label class="text-lb"> 2. 구분명<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">최대 10자 이내 (특수문자 제외)</span>
							</label> <input type="text" class="form-control" maxlength="10" placeholder="1층, 2층, ... 지하 등" id="floor-name" oninput="checkText(event)" autocomplete="off">
						</div>
						<!-- img_file -->
						<div class="form-group">
							<label class="text-lb"> 3. 이미지 파일<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">파일 : 5MB 이하 / 1,280 x 650px / PNG 또는 JPG 형식</span>
							</label>
							<div class="input-group">
								<input id="floor-filename" class="file-input-textbox form-control" readonly />
								<div class="file-input-div">
									<input type="button" value="파일" class="file-input-button btn btn-dark" /> <input type="file" class="file-input-hidden" data-icon='false' accept="image/*" id="floor-file" data-width="1280" data-height="650" /> <input type="hidden" id="img-width" value="" /> <input type="hidden"
										id="img-height" value="" />
								</div>
							</div>
							<div id="upload-msg" class="small"></div>
						</div>
						<div class="form-group">
							<label class="text-lb"> 4. 순서<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">숫자만 입력하세요.</span>
							</label> <input type="number" class="form-control" id="floor-sort" oninput="checkText(event)" autocomplete="off" value="1">
						</div>
					</div>
					<div class="form-group" style="text-align: center">
						<button onClick="insertFloor()" class="btn btn-primary">
							<i class="xi-check mr-1"></i>등록
						</button>
						<a href="/guide/main" class="btn btn-secondary"> <i class="xi-arrow-left mr-1"></i> 뒤로가기
						</a>
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>
	</div>
</section>
<!-- end wrapper -->
<%@include file="/WEB-INF/views/include/footer.jsp"%>
