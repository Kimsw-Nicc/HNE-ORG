<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/gallery/list.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">포토갤러리 > 포토갤러리 조회</span>
        <span class="text-title h2">
          포토갤러리 <i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          갤러리의 사진 등록은 상단 메뉴 "갤러리관리>갤러리등록"을 통해 하실 수 있으며, 갤러리 이미지의 수정 및 삭제는 아래 목록 화면에서 관리 할 수 있습니다.
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />
    <div class="row">
      <div class="col-6">
      <c:if test="${'ADMIN_ROLE' == login.userRole}">
					<select id="org-cd"></select>
				</c:if>
      </div>
      <div class="col-6">
        <a href="/gallery/insert" class="float-right btn btn-dark">
          <i class="xi-plus mr-1"></i>갤러리 사진 등록
        </a>
      </div>
    </div>
    <div class="row" id="gallery-container"></div>
  </div>
  <div class="row">
    <div class="col-lg-12">
      <ul class="pagination justify-content-center paging"></ul>
    </div>
  </div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
