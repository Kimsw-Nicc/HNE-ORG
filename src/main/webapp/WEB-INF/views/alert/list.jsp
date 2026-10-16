<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/alert/list.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">알리미 > 알리미 조회</span>
        <span class="text-title h2">
          알리미<i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          알리미는 장비의 화면 상단에 표출되는 메시지를 관리 합니다.<br /> 가장 최근에 등록 된 메시지만 표시되며, 정보알리미와 메시지알림으로 구분되어 있습니다.<br /> 정보 알리미의 경우 화면 상단에, 메시지 등록의 경우 메인화면 중앙에 표출됩니다.
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />
    <!-- Page-Title -->

    <div class="row">
      <div class="col">
        <div class="input-group">
          <input type="text" id="search-input" class="form-control" placeholder="검색 제목 입력" autocomplete="off" oninput="checkText(event)" />
          <div class="input-group-append mr-3">
            <button id="search-btn" class="btn btn-outline-secondary">
              <i class="xi-search mr-1" id="serch">검색</i>
            </button>
          </div>
          <div class="input-group-append">
            <a href="/alert/insert" class="btn btn-dark">
              <i class="xi-plus mr-1"></i>알리미 등록
            </a>
          </div>
        </div>
      </div>
    </div>
    <table class="table table-hover mails m-0 table table-actions-bar">
      <thead>
        <tr>
          <th class="text-center">수정</th>
          <!-- <th>적용부서</th> -->
          <th class="text-center">구분</th>
          <th class="text-center">중요</th>
          <th class="text-center">표출문구</th>
          <th class="text-center">표시여부</th>
          <th class="text-center">시작~종료</th>
          <th class="text-center">등록일</th>
          <th class="text-center">삭제</th>
        </tr>
      </thead>
      <tbody></tbody>
      <tfoot>
        <tr>
          <td colspan="8"></td>
        </tr>
      </tfoot>
    </table>
  </div>
  <div class="row">
    <div class="col-lg-12">
      <div class="">
        <ul class="pagination justify-content-center paging"></ul>
      </div>
    </div>
  </div>
</section>


<%@include file="/WEB-INF/views/include/footer.jsp"%>
