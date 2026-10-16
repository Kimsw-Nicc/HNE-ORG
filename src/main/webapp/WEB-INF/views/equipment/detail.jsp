<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/assets/js/sockjs-0.3.min.js"></script>
<script src="/resources/assets/js/stomp.min.js"></script>
<script src="/resources/js/equipment/detail.js"></script>
<style>
.equipment-container{
  border: 1px solid #D3D5DC;
  width: 100%;
  border-radius: 12px;
  margin: 0 12px;
  padding: 30px 58px 70px 58px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 50px auto;
  row-gap: 36px;
}
.equipment-header{
  grid-column: 1/3;
  font-size: 32px;
  font-weight: 500;
}
/*info*/
.equipment-info-box{
  display: grid;
  gap: 20px;
}
.equipment-info{
  font-size: 20px;
  display:grid;
  grid-template-columns: 180px 400px 40px;
}
.equipment-info-org{
  font-size: 20px;
  display:grid;
  grid-template-columns: 180px 400px 40px;
  row-gap: 20px;
}
.equipment-info-org-label{
  grid-row: 1/3;
}
.org-info-box{
  display: flex;
  gap: 20px;
  flex-direction: column;
}
.org-info{
  background-color: white;
  border: solid #19BCB9 1px;
  border-radius: 12px;
  padding: 0 1em;
  cursor: pointer;
  height: 34px;
  color: #19BCB9;
}
.org-info:hover{
  background-color: #19BCB9;
  border: solid #19BCB9 1px;
  color: white;
}
.none{
  display: none;
}

/*button*/
.equipment-button-box{
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-template-rows: repeat(auto-fill, 50px);
  column-gap: 20px;
  row-gap: 25px;
  margin-left: 30px;
}
.equipment-button-box button{
  height: 50px;
  border-radius: 12px;
  background-color: white;
  outline: none;
}
.equipment-button-box button:focus{
/*   outline: none; */
}
.normal{
  border: solid 1px black;
}
.normal:hover{
  color: white;
  background-color: black;
}
.critical{
  border: solid 1px #FF5621;
  color: #FF5621;
}
.critical:hover{
  color: white !important;
  background-color: #FF5621 !important; 
}
.test{
  border: solid 1px #19BCB9;
  color: #19BCB9;
}
.test:hover{
  color: white !important;
  background-color: #19BCB9 !important; 
}
.hidden{
  visibility: hidden
}
#insert-orgs{
  padding: 0 1em;
  border-radius: 12px;
  -webkit-appearance:none; /* 크롬 화살표 없애기 */
  -moz-appearance:none; /* 파이어폭스 화살표 없애기 */
  appearance:none; /* 화살표 없애기 */
  font-size: 20px;
  cursor: pointer;
  border: solid #19BCB9 1px;
  height: 34px;
  color: #19BCB9;
}
#insert-orgs:focus-visible{
  outline: none;
}
#insert-org-button{
  border-radius: 12px;
}

</style>
<section class="container">
	<div id="app">
		<div id="title">
			<div class="row">
				<div class="col-md">
					<span class="small float-right text-underline-bold">단말기 관리 > 단말기 조회</span>
					<h2 class="text-title">단말기 관리</h2>
				</div>
			</div>
		</div>
		<!-- end page title end breadcrumb -->
		<div class="row">
			<div class="col">
				<div class="media bg-light p-3">
					<i class="xi-info-o xi-4x text-light"></i>
					<div class="ml-3 media-body">
						<h5 class="mt-0">이용안내</h5>
						
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="equipment-container">
				
			</div>
		</div>
	</div>
	<div class="row">
		
	</div>
</section>

<jsp:include page="detail/layoutModal.jsp" flush="true" />

<%@include file="/WEB-INF/views/include/footer.jsp"%>
