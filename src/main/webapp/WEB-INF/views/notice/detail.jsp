<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/notice/detail.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">공지사항 > 세부내용</span>
          <h2 class="text-title">공지사항</h2>
        </div>
      </div>
    </div>
    <div class="">
      <div class="row">
        <div class="col-md-12">
          <table class="table table-bordered">
            <tr>
              <td class="bg-light text-m" width="100px">구분</td>
              <td id="notice-org" width="90%"></td>
            </tr>
            <tr>
              <td class="bg-light text-m">제목</td>
              <td>
                <span id="notice-name" class="text-lb"></span>
              </td>
            </tr>
            <tr>
              <td class="bg-light text-m">내용</td>
              <td id="notice-content" class="text-ws"></td>
            </tr>
            <tr>
              <td class="bg-light text-m">순서</td>
              <td id="notice-sort"></td>
            </tr>
            <tr>
              <td class="bg-light text-m">공지기간</td>
              <td id="notice-date"></td>
            </tr>
            <tr>
              <td class="bg-light text-m">첨부</td>
              <td>
                <img id="notice-file" class="img-fluid" alt="등록된 사진이 없습니다.">
              </td>
            </tr>
          </table>
        </div>
        <div class="col-md-12">
          <div id="notice-buttons" class="form-group text-center">
            <a href="/notice/list" class="btn btn-secondary">
              <i class="xi-arrow-left mr-1"></i> 목록
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
