<%@page pageEncoding="UTF-8"%>
<script src="/resources/js/equipment/detail/layoutModal.js"></script>
<script src="/resources/js/equipment/detail/layoutStaff.js"></script>
<script src="/resources/js/equipment/detail/layoutFurniture.js"></script>
<style>
#layout-title {
	display: flex;
	flex-direction: column;
}

#laoyut-title-buttons {
  display: flex;
  gap: 0.5em;
  flex-direction: row; 
  border : 1px #EAEAEA solid; 
  border-radius : 2em;
  padding: 0.3em 0.6em;
}

#laoyut-title-buttons button {
	border-radius: 1.2em;
	width: 2.4em;
	height: 2.4em;
	padding: 0;
	background-color: white;
	border: 1px #19BCB9 solid;
	color: #19BCB9;
}

#laoyut-title-buttons .active, #laoyut-title-buttons button:hover {
	background-color: #19BCB9;
	color: white;
}

#layout-ready-container {
	display: flex;
	flex-direction: row;
	align-items: center;
	gap: 2em;
}

#staff-ready-box, #furniture-ready-box , #text-ready-box  {
	gap: 1em;
	border: 1px #EAEAEA solid;
	border-radius: 6px;
	padding: 1em 1em 0.3em 1em;
	overflow-x: scroll;
	width: 100%;
	display: grid;
	grid-template-columns: repeat(100, minmax(130px, 1fr));
	align-items: center;
	height: 120px;
}
/* 스크롤 바 */
::-webkit-scrollbar {
	height: 10px; /* 스크롤 바의 너비 */
}

/* 스크롤 바의 track */
::-webkit-scrollbar-track {
	background: white; /* 스크롤 바의 배경색 */
	border-radius: 6px;
}

/* 스크롤 바의 thumb */
::-webkit-scrollbar-thumb {
	background: #888; /* 스크롤 바의 색상 */
	border-radius: 4px; /* 스크롤 바의 모서리 반경 */
}

/* 마우스 오버 상태의 thumb */
::-webkit-scrollbar-thumb:hover {
	background: #555; /* 마우스 오버 시 스크롤 바의 색상 */
}

.staff-container {
	width: 140px;
	height: 80px;
	border-radius: 12px;
	box-shadow: 0px 0px 2px gray;
	display: flex;
	justify-content: center;
	flex-direction: column;
	align-items: center;
}

.staff-box {
	width: 130px;
	height: 70px;
	box-shadow: 0px 0px 2px gray;
	border-radius: 12px;
	display: grid;
	grid-template-rows: 3.6em 1.1em;
}

.staff-box-top-img {
	display: grid;
	grid-template-columns: 0.5fr 1fr;
	grid-template-rows: 0.6fr 1fr;
}

.staff-box-top-noimg {
	display: grid;
	grid-template-rows: 0.6fr 1fr;
}

.staff-filename {
	width: 100%;
	height: 100%;
	border-radius: 6px;
}

.staff-position {
	font-size: 0.8em;
	color: gray;
}

.staff-name {
	font-weight: bold;
}

.staff-duty {
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
  font-size: 0.7em;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  padding: 0 1em;
}

.team-container {
	background-color: #19BCB9;
	color: white;
	font-weight: bold;
	border-radius: 14px;
	width: 140px;
	height: 35px;
	display: flex;
	justify-content: center;
	flex-direction: column;
	text-align: center;
}

#staff-layout-box {
	width: 1500px;
	height: 680px;
}

.staff-layout {
	position: absolute;
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
</style>
<div class="modal fade" id="layout-modal" tabindex="-1" role="dialog" aria-labelledby="layout-modal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 1500px" role="document">
    <div class="modal-content">
      <div id="layout-ready-container" class="modal-body d-flex">
        <div id="layout-title" class="text-center">
          <div class=""><h4>좌석배치</h4></div>
          <div id="laoyut-title-buttons" class="">
            <button id="layout-staff-btn" class="btn active"><i class="xi-user"></i></button>
            <button id="layout-furniture-btn" class="btn"><i class="xi-package"></i></button>
        </div>
      </div>
        <div id="staff-ready-box"></div>
        <div id="furniture-ready-box" class="d-none">
          <div class="furniture furniture-ready furniture-big-table"></div>
          <div class="furniture furniture-ready furniture-small-table"></div>
          <div class="furniture furniture-ready furniture-flower-pot"></div>
          <div class="furniture furniture-ready furniture-print"></div>
          <div class="furniture furniture-ready furniture-water"></div>
        </div>
        <div id="text-ready-box" class="d-none"></div>
        <div>
          <button id="layout-close-btn" class="btn btn-outline-secondary w-75 ml-3 mr-3">저장하지 않고 닫기</button>
          <button id="layout-save-btn" class="btn btn-primary w-75 ml-3 mr-3 mt-2 mb-2">저장하기</button> 
          <input id="layout-snapping-btn" type="checkbox" data-style="w-75 ml-3 mr-3 border">
      </div>
    </div>
      <div id="staff-layout-box" class="modal-body d-flex"></div>
  </div>
</div>
</div>
