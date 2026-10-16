<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/guide/building/list.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <!-- Page-Title -->
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">청사안내도 > 청사관리</span>
        <span class="text-title h2">
          청사관리 <i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          * 청사관리 : 건물이 본청, 별관, 신관 등으로 구분되어있는 경우 각각 등록해서 관리할 수 있습니다. <br /> ※ 특수문자 입력 제한
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />
    <table class="table">
      <thead>
        <tr>
      <th>청사명</th>
      <th>이미지</th>
      <th>순서</th>
      <th>삭제</th>
    </tr>
      </thead>
      <tbody></tbody>
      <tfoot>
        <tr>
          <td colspan="4">
            <div class="form-group text-center">
              <button class="btn btn-primary" onclick="updateBuildings()">
                <i class="xi-check mr-1"></i>저장
              </button>
              <button class="btn btn-dark" onclick="openInsertBuilding()">
                <i class="xi-plus mr-1"></i>청사추가
              </button>
              <button class="btn btn-warning" onclick="sortBuildings()">
                <i class="xi-plus mr-1"></i>청사정렬
              </button>
              <a href="/guide/main?buildingId=0" class="btn btn-secondary">
                <i class="xi-arrow-left mr-1"></i>뒤로가기
              </a>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
</section>
<jsp:include page="list/insertBuilding.jsp" flush="true" />
<%@include file="/WEB-INF/views/include/footer.jsp"%>