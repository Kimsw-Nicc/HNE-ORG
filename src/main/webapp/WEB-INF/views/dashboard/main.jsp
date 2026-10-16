<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/dashboard/main.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <div id="body">
      <div class="row">
        <div class="col-md">
          <h2 class="text-title">
            <span class="font-weight-bold">Main</span> <span id="today" class="float-right text-underline-bold large"></span>
          </h2>
        </div>
      </div>
    </div>
    <c:if test="${login.userRole eq 'ADMIN_ROLE'}">
      <div class="row">
        <div class="col-md-12">
          <div class="bg-light pl-3 pr-3">
            <div class="card-body">
              <!-- <i class="xi-walk xi-4x text-secondary"></i> -->
              <span class="text-secondary"> <c:if test="${'ADMIN_ROLE' == login.userRole.name()}">
                  상단 메뉴 중 <i class="xi-check-circle text-primary"></i> 표시가 있는 항목은 권한이 전체관리자만 접근이 가능합니다.<br>
                </c:if> 이용에 불편하신 사항은 시스템 관리자 및 해당 영업처 또는 제품공급업체로 문의바랍니다.
              </span>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <c:if test="${login.userRole eq 'MANAGER_ROLE'}">
      <div class="row">
        <div class="col-md-8">
          <div class="bg-light pl-3 pr-3">
            <div class="card-body">
              <span class="text-secondary"> 이용에 불편하신 사항은 시스템 관리자 및 해당 영업처 또는 제품공급업체로 문의바랍니다. </span>
            </div>
          </div>
        </div>
      </div>
    </c:if>
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-4">
          <div class="card shadow-sm pl-1 pr-1 border-white">
            <div class="card-body">
              <i class="xi-sitemap xi-4x text-secondary"></i>
              <div class="float-right text-secondary">
                <span>등록 부서 수</span>
                <p class="h2">
                  <span id="org-count" class="text-dark font-weight-bold"></span> <span class="medium">개</span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card shadow-sm pl-1 pr-1 border-white">
            <div class="card-body">
              <i class="xi-sitemap-o xi-4x text-secondary"></i>
              <div class="float-right text-secondary">
                <span>등록 팀 수</span>
                <p class="h2">
                  <span id="team-count" class="text-dark font-weight-bold"></span> <span class="medium">개</span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card shadow-sm pl-1 pr-1 border-white">
            <div class="card-body">
              <i class="xi-users xi-4x text-secondary"></i>
              <div class="float-right text-secondary">
                <span>등록 직원 수</span>
                <p class="h2">
                  <span id="staff-count" class="text-dark font-weight-bold"></span> <span class="medium">명</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-12">
      <div class="row">
        <div class="col-md-8">
          <h4 class="card-title p-2">
            <span class="large">Recently edited</span> <br /> <span class="small">최근 수정</span>
          </h4>
          <div class="row" id="edited-staff"></div>
        </div>
        <div class="col-md-4">
          <h4 class="card-title p-2">
            <span class="large">Links</span> <br /> <span class="small">바로 가기</span>
          </h4>
          <div class="row">
            <div class="col-sm-12">
                <a href="/com/manage" class="main-menu btn btn-block btn-outline-primary" > 조직관리 </a>
              </div>
              <div class="col-sm-12">
                <a href="/notice/list" class="main-menu btn btn-block btn-outline-info" > 공지사항 </a>
              </div>
              <div class="col-sm-6">
                <a href="/gallery/list" class="main-menu btn btn-block btn-outline-warning" > 포토갤러리 </a>
              </div>
              <div class="col-sm-6">
                <a href="/movie/list" class="main-menu btn btn-block btn-outline-success" > 홍보영상 </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
