<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/tour/detail.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">관광안내 > 관광안내 조회</span>
          <h2 class="text-title">관광안내 조회</h2>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12 text-center">
        <div class="form-horizontal" role="form">
          <div class="form-group">
            <div class="text-box text-center">
              <h3 class="text-m"></h3>
            </div>
          </div>
          <div class="border rounded p-3 text-center">
            <img id="tour-file" class="img-fluid" alt="등록된 사진이 없습니다." />
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="form-group text-center">
          <button id="update" class="btn btn-primary">수정</button>
          <button class="btn btn-danger" onClick="openDeleteTour()">삭제</button>
          <a href="/tour/list" class="btn btn-secondary">목록</a>
        </div>
      </div>
    </div>
  </div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>