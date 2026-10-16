<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/assets/js/sockjs-0.3.min.js"></script>
<script src="/resources/assets/js/stomp.min.js"></script>
<script src="/resources/js/equipment/list.js"></script>
<style>
.tab-content {
	padding: 0 12px;
}

.input-container {
	display: flex;
	gap: 2em;
	padding-right: 2em;
}

.input-box {
	display: grid;
	grid-template-columns: 0.3fr 0.7fr;
	width: 310px;
	border: 1px solid #ccc;
	border-radius: 12px;
}

.select-wrapper {
	border-radius: 12px;
	position: relative;
	border: none;
}

.select-wrapper::after {
	content: '\25BC';
	position: absolute;
	top: 50%;
	right: 10px;
	transform: translateY(-50%);
}

.search-type {
	width: 100%;
	padding: 8px;
	padding-right: 30px;
	border: none;
	border-radius: 12px;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	background-color: #fff;
	background-image:
		url('data:image/svg+xml;utf8,<svg fill="%23000000" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24"><path d="M7 10l5 5 5-5z"/></svg>');
	background-repeat: no-repeat;
	background-position: right 8px center;
	background-size: 16px;
}

.search-type option {
	width: calc(100% + 20px); /* option의 너비를 select와 동일하게 맞춤 */
	padding: 8px;
	border-radius: 12px;
	border: none;
	position: relative; /* position 추가 */
	left: -10px; /* option이 select 내부로 들어가도록 함 */
}

.search-keyword {
	border-radius: 12px;
	padding: 8px;
	border: none;
}

.search-keyword:focus {
	border: none;
}

.equipment-container {
	margin-top: 26px;
	display: grid;
	grid-template-columns: repeat(5, 1fr);
	column-gap: 13px;
	row-gap: 31px;
}

.equipment-box {
	border-radius: 12px;
	border: 1px solid #202124;
	width: 250px;
	height: 120px;
	display: grid;
	padding: 9px 13px;
	grid-template-columns: 208px 17px;
	grid-template-rows: 35px auto;
	row-gap: 13px;
	cursor: pointer;
}

.equipment-box-name {
	font-weight: bold;
	font-size: 20px;
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
}

.equipment-box-status {
	
}

.equipment-box-desc {
	font-size: 14px;
	text-overflow: ellipsis;
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 3;
	-webkit-box-orient: vertical;
	word-break: break-word;
}

.equipment-box:hover {
	background-color: #F1F3F5;
	border: 1px solid #F1F3F5;
}

.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
	margin: 0;
}

/* Hide default HTML checkbox */
.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

/* The slider */
.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}

#switch-msg {
	font-weight: bold;
	font-size: 1.1em;
	display: flex;
	justify-content: center;
	align-items: center;
}

.switch-box {
	display: flex;
	align-items: center;
	gap: 0.5em;
}
</style>
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">단말기 관리 > 단말기 조회</span>
				<span class="text-title h2">
					단말기 관리<i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					단말기가 켜져있을 경우 <img alt="ON" class="mr-2" src="/resources/assets/images/ON.png"> 표시, 단말기가 꺼져있을 경우 <img alt="OFF" class="mr-2" src="/resources/assets/images/OFF.png"> 표시가 됩니다.<br>
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />

		<div class="row">
			<div class="tab-content">
				<div class="input-container">
					<div class="input-box">
						<select class="search-type">
							<option value="name">이름</option>
							<option value="ip">IP</option>
							<option value="desc">설명</option>
						</select> <input class="search-keyword" type="text" placeholder="입력후 엔터를 눌러주세요">
					</div>
					<div class="switch-box">
						<span id="switch-msg">종료된 단말기 보기</span>
						<label class="switch"> <input type="checkbox" id="switch-input"> <span class="slider round"></span>
						</label>
					</div>
				</div>
				<div class="equipment-container"></div>
			</div>
		</div>
	</div>
	<div class="row"></div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>
