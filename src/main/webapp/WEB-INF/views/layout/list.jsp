<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/assets/js/sockjs-0.3.min.js"></script>
<script src="/resources/assets/js/stomp.min.js"></script>
<script src="/resources/js/layout/list.js"></script>
<script src="/resources/jstree/jstree.js"></script>
<link rel="stylesheet" href="/resources/jstree/style.css" />
<style>
#layout-box {
	width: 965px;
	height: 540px;
	/*   width: 1920px !important; */
	/*   height: 975px !important; */
	position: relative;
	border: 2px solid #ccc;
	background-color: #f9f9f9;
	background-size: cover;
	overflow: hidden;
}

#layout-box div {
	position: absolute;
	border: 1px solid #ccc;
	background-color: rgba(255, 255, 255, 0.9); /* 약간 투명한 흰색 */
	color: #333;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	line-height: 1.2;
	border-radius: 5px; /* 부드러운 모서리 */
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 */
	transition: transform 0.2s, box-shadow 0.2s; /* 마우스 오버 효과 */
}

#layout-box .team-div {
	width: 112px;
	height: 33px;
	/*   width: 224px !important; */
	/*   height: 65px !important; */
	background-color: #007bff; /* 파란색 배경 */
	color: white; /* 흰색 글자 */
	display: flex;
	align-items: center;
	justify-content: center;
}

#layout-box .staff-div {
	width: 70px;
	height: 38px;
	/*   width: 140px !important; */
	/*   height: 76px !important; */
	background-color: rgba(255, 255, 255, 0.7); /* 약간 투명한 흰색 */
	color: #333; /* 진한 글자 */
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
<section class="container">
	<div>
		<details>
			<summary class="text-title col-md-12" style="font-size: 1.875rem; list-style: none; padding-left: 0px; margin-bottom: 0px;">
				사무실배치도<i class="xi-info-o xi-1x text-light"></i>
			</summary>
			<div class="row">
				<div class="col" style="margin-bottom: 5px;">
					<div class="media bg-light p-3">
						<i class="xi-info-o xi-4x text-light"></i>
						<div class="ml-3 media-body">
							<span>
								부서별 사무실배치도를 확인할 수 있는 페이지입니다.<br /> 직원 배치는 단말기에서 할 수 있습니다.
							</span>
						</div>
					</div>
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
	</div>
	<div class="row">
		<div class="col-md-3">
			<div class="card">
				<div class="input-group">
					<button class="btn btn-outline-secondary " onclick="treeAll()" type="button">
						<i class="xi-home-o" style="padding-top: 0.2rem;"></i>
					</button>
					<input type="text" id="search-tree" class="form-control " placeholder="부서명 검색" aria-label="Example text with button addon" />
					<button class="btn btn-outline-secondary tree-refresh" type="button">
						<i class="xi-renew" style="padding-top: 0.2rem;"></i>
					</button>
				</div>
				<div class="col-md-3">
					<div id="tree"></div>
				</div>
			</div>
		</div>
		<div class="col">
			<div class="d-flex flex-row-reverse">
				<button class="btn btn-outline-secondary float-right mb-3" onclick="openBackgroundModal()" type="button">
					<i class="xi-plus" style="padding-top: 0.2rem;"></i> 배경이미지 변경
				</button>
			</div>
			<div id="layout-box"></div>
		</div>
	</div>
	<div class="row"></div>
</section>
<%@include file="list/updateBackgroundModal.jsp"%>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
