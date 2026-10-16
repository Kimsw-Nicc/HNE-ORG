<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/notice/update.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">공지사항 > 공지사항수정</span>
				<span class="text-title h2">
					공지사항수정<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					<i class="xi-check mr-1 input-need"></i>표시가 붙어있는 항목은 필수 입력 항목입니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col-md-12">
				<div class="border rounded p-3">
					<div class="form-horizontal" role="form">
						<!-- notice_ -->
						<div class="form-group">
							<label class="text-lb">1. 부서</label>
							<c:if test="${login.userRole.name() == 'MANAGER_ROLE'}">
								<input type="hidden" id="notice-org">
								<input type="text" id="notice-org-name" readonly="readonly" class="form-control">
							</c:if>
							<c:if test="${login.userRole.name() == 'ADMIN_ROLE'}">
								<select name="orgCd" id="notice-org" class="form-control">
								</select>
							</c:if>
						</div>
						<!-- notice_title -->
						<div class="form-group">
							<label for="notice-name" class="text-lb"> 1. 제목<i class="xi-check ml-1 input-need"></i>
							</label> <input type="text" class="form-control" placeholder="공지사항 제목 입력(50자 이내)" maxlength="50" id="notice-name" autocomplete="off" />
							<span class="small">최대 50자 이내</span>
						</div>
						<!-- noticeContent -->
						<div class="form-group">
							<label for="noticeContent" class="text-lb"> 2. 내용<i class="xi-check ml-1 input-need"></i>
							</label>
							<textarea class="form-control" rows="10" id="notice-content" autocomplete="off"></textarea>
							<span class="small">
								최대 2,500byte 이내 (
								<span id="count">0</span>
								/
								<span id="max-count">2500</span>
								byte)
							</span>
						</div>
						<!-- notice_file -->
						<div class="form-group">
							<label class="text-lb"> 3. 이미지 파일 <span class="small ml-2">제한 : 5MB 이하, PNG 또는 JPG 형식</span>
							</label>
							<div class="input-group">
								<input id="notice-filename" type="hidden" /> <input id="notice-origin-filename" class="file-input-textbox form-control" readonly="readonly" />
								<div class="file-input-div">
									<input type="button" value="파일" class="file-input-button btn btn-dark" /> <input type="file" class="file-input-hidden" data-icon="false" accept="image/*" id="notice-file" />
								</div>
							</div>
							<span id="upload-msg"></span>
						</div>
						<!-- start ~ end date -->
						<div class="form-group">
							<label class="text-lb"> 4. 공지기간<i class="xi-check ml-1 input-need"></i>
							</label>
							<div class="row">
								<div class="col-md-6 input-group">
									<input type="text" class="form-control datepicker start-date" id="notice-start-date" placeholder="0000-00-00" autocomplete="off" />
									<span class="input-group-addon ml-3 mr-3 text-b"> ~ </span>
									<input type="text" class="form-control datepicker end-date" id="notice-end-date" placeholder="0000-00-00" autocomplete="off" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="text-lb"> 5. 순서<i class="xi-check ml-1 input-need"></i>
							</label>
							<div class="row">
								<div class="col-md-6 input-group">
									<input type="text" class="form-control" id="notice-sort" />
								</div>
							</div>
						</div>
						<div class="form-group text-center">
							<button class="btn btn-primary" onclick="updateNotice()">
								<i class="xi-check mr-1"></i>수정
							</button>
							<button id="back" class="btn btn-secondary">
								<i class="xi-arrow-left mr-1"></i> 뒤로가기
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
