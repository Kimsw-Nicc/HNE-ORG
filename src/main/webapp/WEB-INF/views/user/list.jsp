<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/user/list.js"></script>
<!-- Main content -->
<section class="container">
  <input type="hidden" value="${login.userId}" id="user-id" />
  <input type="hidden" value="${login.userRole}" id="user-role" />
  <div id="app">
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">계정관리 > 계정조회</span>
        <span class="h2 text-title">
          계정조회 <i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          전체 및 부서별 관리자를 등록 관리 할 수 있습니다. 아래 "계정생성" 버튼을 통해 전체관리자 또는 부서별 관리자를 등록할 수 있습니다. <br /> 관리자 삭제 시 한번 더 확인하시기 바랍니다.
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />
    <div class="row">
      <div class="col">
        <div class="input-group">
          <input type="text" id="keyword" class="form-control" placeholder="이름 검색" oninput="checkText(event)" autocomplete="off" />
          <button class="btn btn-outline-secondary" id="search-btn">
            <i class="xi-search"> 검색</i>
          </button>
          <div class="input-group-append ml-2">
            <a href="/user/insert" class="btn btn-dark">
              <i class="xi-user-plus mr-1"></i>계정생성
            </a>
          </div>
          <c:if test="${login.userId.equals('hneorg')}">
            <div class="input-group-append ml-2">
              <a href="#" class="btn btn-dark" onClick="openResetAllPassword()">
                <i class="xi-redo"></i>비밀번호 일괄 초기화
              </a>
            </div>
          </c:if>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <div class="table-responsive">
          <table class="table table-hover">
            <thead>
              <tr>
                <th width="5%" class="text-center">정보 <br /> 수정
                </th>
                <th width="7%" class="text-center">비밀번호 <br /> 변경
                </th>
                <th width="35%">아이디 (이름)</th>
                <th>권한</th>
                <th>부서구분</th>
                <th width="5%">삭제</th>
                <th width="10%">비밀번호 초기화</th>
              </tr>
            </thead>
            <tbody></tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="">
          <ul class="pagination justify-content-center paging"></ul>
        </div>
      </div>
    </div>
    <!-- end row -->
  </div>
</section>
<%@include file="./list/checkPasswordModal.jsp"%>
<%@include file="./list/resetAllPasswordModal.jsp"%>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
