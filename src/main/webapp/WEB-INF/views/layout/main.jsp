<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/layout/main.js"></script>
<script src="/resources/js/layout/main/main_staff.js"></script>
<script src="/resources/js/layout/main/main_furniture.js"></script>
<script src="/resources/js/layout/main/main_text.js"></script>
<style>
.black-round {
	border: 1px solid black;
	border-radius: 12px;
}

.middle-text {
	justify-content: center;
	display: flex;
	align-items: center;
}

.gray-background {
	background-color: #C5C5C5C5;
}

.layout-button {
	width: 218px;
	height: 40px;
	margin-right: 18px;
	font-size: 18px;
	border: 1px solid #19BCB9;
	border-radius: 12px;
	color: #19BCB9;
	cursor: pointer;
	font-weight: bold;
}

.layout-button-selected, .layout-button:hover {
	background-color: #19BCB9;
	color: white;
}

.layout-button:last-child {
	margin-left: auto;
	border-color: #236CB7;
	color: #236CB7;
}

.layout-button:last-child:hover {
	background-color: #236CB7;
	color: white;
}

.staff-menu, .staff_container {
	margin: 0 auto;
	display: flex;
}

.staff-container {
	height: 675px;
	width: 1344px;
	display: grid;
	grid-template-columns: 0.125fr 0.975fr;
	margin-top: 30px;
}

.staff-ready-box, .text-ready-box {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 10px;
	overflow-y: auto;
	overflow-x: hidden;
}

.staff-ready-box::-webkit-scrollbar {
	display: none; /* Chrome, Safari, Opera*/
}

.staff-layout-box {
	
}

.staff {
	display: grid;
	width: 84px;
	height: 45px;
	flex-basis: 45px;
	flex-shrink: 0;
	background-color: white;
}

.team {
	width: 133px;
	height: 34px;
	font-weight: bold;
	font-size: 18px;
	background-color: white;
}

.staff-layout-box .team, .staff-layout-box .staff, .staff-layout-box .furniture
	{
	position: absolute;
}

.staff-ready-box input {
	width: 168px;
	outline: none;
	padding: 0.1em 1em;
}

.furniture-ready-box {
	display: flex;
	flex-direction: column;
	align-items: center;
	gap: 20px;
}

.furniture {
	
}

.furniture-big-table {
	background-image: url('/resources/upload/layout/bigTable.png');
	width: 126px;
	height: 192px;
}

.furniture-small-table {
	background-image: url('/resources/upload/layout/smallTable.png');
	width: 139px;
	height: 139px;
}

.furniture-flower-pot {
	background-image: url('/resources/upload/layout/flowerPot.png');
	width: 44px;
	height: 44px;
}

.furniture-print {
	background-image: url('/resources/upload/layout/print.png');
	width: 80px;
	height: 91px;
}

.furniture-water {
	background-image: url('/resources/upload/layout/water.png');
	width: 61px;
	height: 75px;
}

.text-ready-box{
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
  overflow-y: auto;
  overflow-x: hidden;
}

.text {
  font-size: 18px;
  font-weight: bold;
  width: 84px;
  height: 45px;
  flex-basis: 45px;
  flex-shrink: 0;
}

.text-ready {
  
}

.text-layout {
	
}
.switch-box{
  display: flex;
  align-items: center;
  gap: 0.5em;
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
  background-color: #19BCB9;
}

input:focus+.slider {
  box-shadow: 0 0 1px #19BCB9;
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
</style>
<div id="app">
  <div class="staff-menu">
    <div class="layout-button layout-button-staff middle-text layout-button-selected">직원 추가</div>
    <div class="layout-button layout-button-furniture middle-text">가구 추가</div>
    <div class="layout-button layout-button-text middle-text">텍스트 추가</div>
    <div class="switch-box">
      <span id="switch-msg">정밀모드</span>
      <label class="switch"> 
        <input type="checkbox" id="switch-input"> 
        <span class="slider round"></span>
      </label>
    </div>
    <div class="layout-button layout-button-save middle-text">저장</div>
  </div>
  <div class="staff-container">
    <div class="staff-ready-box black-round gray-background ">
      <input class="black-round gray-background" id="staff-search-input" placeholder="이름검색" />
    </div>
    <div class="furniture-ready-box black-round gray-background d-none">
      <div class="furniture furniture-ready furniture-big-table"></div>
      <div class="furniture furniture-ready furniture-small-table"></div>
      <div class="furniture furniture-ready furniture-flower-pot"></div>
      <div class="furniture furniture-ready furniture-print"></div>
      <div class="furniture furniture-ready furniture-water"></div>
    </div>
    <div class="text-ready-box black-round gray-background d-none">
      <input class="black-round gray-background" id="text-insert-input" placeholder="텍스트 추가" />
    </div>
    <div class="staff-layout-box black-round gray-background"></div>
  </div>
</div>




<%@include file="/WEB-INF/views/include/footer.jsp"%>

