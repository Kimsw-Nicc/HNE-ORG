<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/guide/main.js"></script>
<div class="invisible fixed-top" style="font-family: NanumSquareB;">.</div>
<div class="invisible fixed-top" style="font-family: NanumSquareEB;">.</div>
<div class="invisible fixed-top" style="font-family: NanumMyeongjoBold;">.</div>
<section class="container">
	<div id="app">
		<!-- Page-Title -->
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">청사안내도 > 청사안내도 조회</span>
				<span class="text-title h2">
					청사안내도 <i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					청사안내도를 관리합니다. <br /> * 청사관리 : 건물이 본청, 별관, 신관 등으로 구분되어있는 경우 "청사관리"를 통해 등록하여주세요. <br /> * 안내도 등록 : 청사관리에 등록 된 청사별 안내도를 등록 합니다. <br /> * 안내도 편집기능 : 청사 선택 후 각 층별 안내도 내부의 명칭을 편집 할 수 있습니다. (텍스트추가, 수정/삭제, 저장) <br /> * 층수를 변경하시면 자동으로 저장됩니다.
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<div class="row">
			<div class="col-md-6">
				<form role="form" action="/guide/main">
					<input type="hidden" name="keyword" id="keyword" value="1" /> <label class="text-lb" for="select-building">청사 선택</label>
					<div class="input-group">
						<select id="building-id" class="form-control mr-2">
						</select>
						<div class="input-group-append">
							<a href="/guide/floor/insert" class="btn btn-primary mr-2" role="button"> <i class="xi-plus mr-1"></i>안내도 등록
							</a> <a href="/guide/building/list" class="btn btn-dark" role="button"> <i class="xi-bank mr-1"></i>청사관리
							</a>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row guide">
			<!--  청사 선택x일시 값을 위한 칸. changeGuide 참고-->
		</div>
		<div class="row">
			<div class="col-md-12">
				<ul class="nav nav-tabs" id="floor-ids">
					<!-- 여기에 층 목록. makeFloorList 참고 -->
				</ul>
			</div>
			<div class="col-md-12">
				<div class="tab-content">
					<!-- 여기에 층. maekFloor 참고 -->
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page="main/insertTextModal.jsp" flush="true" />
<jsp:include page="main/updateFloorModal.jsp" flush="true" />
<jsp:include page="main/updateTextModal.jsp" flush="true" />
<%@include file="/WEB-INF/views/include/footer.jsp"%>