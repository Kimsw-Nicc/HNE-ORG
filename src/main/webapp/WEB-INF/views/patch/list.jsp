<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/patch/list.js"></script>
<!-- Main content -->
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">패치파일 관리 > 패치파일 조회</span>
				<span class="text-title h2">
					패치파일관리<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					패치파일을 조회, 등록, 삭제가 가능한 페이지입니다. <br /> 최신 패치파일 등록 시 아래 좌측의 "등록"버튼을 클릭하여 파일을 등록할 수 있습니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<!-- Page-Title -->
		<div class="row">
			<div class="col-md-12">
				<ul id="patch-nav" class="nav nav-tabs"></ul>
			</div>
			<div class="col-md-12">
				<div class="tab-content">
					<div class="tab-pane active">
						<div class="form-group" id="patch-table">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>버전</th>
											<th>파일</th>
											<th>메모</th>
											<th>수정일</th>
											<th>삭제</th>
											<!-- <th>다운로드</th> -->
										</tr>
									</thead>
									<tbody id="patch-list"></tbody>
									<tfoot>
										<tr>
											<td colspan="6"></td>
										</tr>
									</tfoot>
								</table>
								<div id="insert-modal-box"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="list/insertPatchModal.jsp"%>
<%@include file="/WEB-INF/views/include/footer.jsp"%>