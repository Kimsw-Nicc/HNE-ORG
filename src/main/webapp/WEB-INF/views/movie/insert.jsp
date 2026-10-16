<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/movie/insert.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">홍보콘텐츠 > 홍보콘텐츠 등록</span>
        <span class="text-title h2">
          홍보콘텐츠 등록<i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          <i class="xi-check input-need"></i> 표시가 붙어있는 항목은 필수 입력 항목입니다. <br />
          <span class="text-danger">
            <b>"등록" 클릭 시 파일 업로드 진행 중 다른아무작업도 하지마시기 바랍니다.</b> 정상적으로 업로드가 완료되면 목록화면으로 자동 이동됩니다.
          </span>
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />
    <!-- Page-Title -->
    <div class="border rounded p-3">
      <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
          <div class="form-horizontal" role="form">
            <div class="form-group">
              <label class="text-lb">1. 등록부서</label>
              <c:if test="${'MANAGER_ROLE' == login.userRole.name()}">
                <input type="hidden" id="movie-org" value="${login.orgCd}">
                <input type="text" readonly="readonly" class="form-control" value="${login.orgFullname}">
              </c:if>
              <c:if test="${'ADMIN_ROLE' == login.userRole.name()}">
                <select id="movie-org" class="form-control">
                </select>
              </c:if>
            </div>
            <div class="form-group">
              <label class="text-lb"> 2. 제목<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">최대 20자 이내 (특수문자 제외)</span>
              </label> <input type="text" class="form-control" maxlength="20" placeholder="제목" id="movie-name" oninput="checkText(event)" autocomplete="off" />
            </div>
            <div class="form-group">
              <label class="text-lb"> 3. 순서<i class="xi-check-min ml-1 mr-1 input-need"></i> <span class="small">최대 20자 이내 (특수문자 제외)</span>
              </label> <input type="number" class="form-control" placeholder="순서" id="movie-sort" autocomplete="off" />
            </div>
            <div class="form-group">
              <label class="text-lb"> 4. 파일<i class="xi-check-min ml-1 mr-1 input-need"></i> <span id="uploadInfo" class="small"></span>
              </label>
              <div class="input-group">
                <input id="movie-filename" class="file-input-textbox form-control" readonly />
                <div class="file-input-div">
                  <input type="button" value="파일" class="file-input-button btn btn-dark" /> <input type="file" class="file-input-hidden" data-icon="false" accept=".mp4, .jpg, .png, .jpeg" id="movie-file" />
                </div>
              </div>
              <span id="upload-msg">파일을 선택하세요.</span>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-3"></div>
      <div class="col-md-12">
        <div class="form-group text-center">
          <button class="btn btn-primary" onclick="insertMovie()">
            <i class="xi-check mr-1"></i>등록
          </button>
          <a href="/movie/list" class="btn btn-secondary"> <i class="xi-arrow-left mr-1"></i>목록
          </a>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
