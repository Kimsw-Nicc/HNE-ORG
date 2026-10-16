<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/movie/detail.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">홍보영상 > 홍보영상 조회</span>
          <h2 class="text-title">홍보영상 조회</h2>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="form-horizontal" role="form">
          <div class="form-group">
            <div class="text-box text-center">
              <div id="movie-title" class="d-flex flex-column"></div>
            </div>
          </div>

          <div id="movie-content" class="embed-responsive embed-responsive-16by9"></div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div id="movie-buttons" class="form-group d-none">
          <a href="/movie/list" class="btn btn-secondary">
            <i class="xi-arrow-left mr-1"></i>목록
          </a>
        </div>
      </div>
    </div>
  </div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>