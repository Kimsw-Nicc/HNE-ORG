<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/background/detail.js"></script>
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">배경이미지 > 배경이미지 조회</span>
          <h2 class="text-title">배경이미지 조회</h2>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="form-horizontal" role="form">
          <!-- img_title -->
          <div class="form-group">
            <div class="text-box text-center">
              <h3></h3>
            </div>
          </div>
          <!-- img -->
          <div class="border rounded p-3 text-center">
            <img id="background-file" class="img-fluid" alt="등록된 사진이 없습니다." />
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="form-group text-center" id="background-buttons">
          <button type="button" class="btn btn-primary" id="update">
            <i class="xi-pen mr-1"></i> 수정
          </button>
          <button type="button" class="btn btn-danger" id="delete" onclick="deleteBackground()">
            <i class="xi-trash mr-1"></i> 삭제
          </button>
          <a href="/background/list" class="btn btn-secondary">
            <i class="xi-arrow-left mr-1"></i> 목록
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
