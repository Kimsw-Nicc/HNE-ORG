<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/gallery/detail.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">포토갤러리 > 포토갤러리 정보</span>
          <h2 class="text-title">포토갤러리정보</h2>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="form-horizontal" role="form">
          <!-- img_title -->
          <div class="form-group">
            <div class="text-box text-center">
              <h3 id="gallery-name"></h3>
            </div>
          </div>
          <!-- img -->
          <div class="border rounded p-3 text-center">
            <img id="gallery-file" class="img-fluid" alt="등록된 사진이 없습니다." />
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div id="gallery-buttons" class="d-flex justify-content-center">
          <a href="/gallery/list" class="btn btn-secondary">
            <i class="xi-arrow-left mr-1"></i> 뒤로가기
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
