<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/com/manage.js"></script>
<script src="/resources/js/com/manage/staff.js"></script>
<script src="/resources/js/com/manage/team.js"></script>
<script src="/resources/js/com/manage/org.js"></script>

<link rel="stylesheet" href="/resources/jstree/style.css" />
<!-- 메인라인 -->
<style>
.update-modal-contents>.col-md-6, .insert-modal-contents>.col-md-6 {
  margin-bottom: 6px !important;
}

.modal-title {
  font-weight: 600;
}

.modal-body {
  /* 		font-weight:600; */
  
}

.modal-sm {
  max-width: 500px;
}

.modal-content {
  border-radius: 20px;
}

::-webkit-file-upload-button {
  cursor: pointer;
}

td {
  align-content: center;
}
</style>
<section class="container">
  <div class="app">
    <div>
      <details>
        <summary class="h2 text-title col-md-12 mb-0">
          조직관리<i class="xi-info-o xi-1x text-light"></i>
        </summary>
        <div class="row">
          <div class="col mb-2">
            <div class="media bg-light p-3">
              <i class="xi-info-o xi-4x text-light"></i>
              <div class="ml-3 media-body">
                <span> 생성, 수정 시 <i class="xi-check-min input-need"></i>표시가 붙어있는 항목은 필수 입력 항목입니다.
                </span> <br /> 직원 순서가 99로 설정된 경우, 단말기에서 표시되지 않습니다.<br /> 표시 버튼이 비활성화 될 경우, 단말기에서 표시되지 않습니다. <span></span>
              </div>
            </div>
          </div>
        </div>
      </details>
      <hr class="dropdown-divider mb-3" />
    </div>
    <div>
      <div class="row">
        <div class="col-md-3">
          <div class="card">
            <div class="input-group">
              <button class="btn btn-outline-secondary" onclick="treeAll()" type="button">
                <i class="xi-home-o pt-1"></i>
              </button>
              <input type="text" id="search-tree" class="form-control " placeholder="부서명 검색" aria-label="Example text with button addon" />
              <button class="btn btn-outline-secondary" type="button" id="tree-refresh">
                <i class="xi-renew pt-1"></i>
              </button>
            </div>
            <div class="col-md-3">
              <div id="tree"></div>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="col">
            <ul class="nav nav-tabs mb-3" role="tablist">
              <li class="nav-item col-md-4 text-center" role="presentation">
                <a class="nav-link active" id="contents-tab" data-toggle="tab" href="#contents" role="tab" aria-controls="contents" aria-selected="true">
                  <span class="font-weight-bold">부서정보</span>
                </a>
              </li>
              <li class="nav-item col-md-4 text-center" role="presentation">
                <a class="nav-link" id="team-tab" data-toggle="tab" href="#team" role="tab" aria-controls="team" aria-selected="false">
                  <span class="font-weight-bold">팀관리</span>
                </a>
              </li>
              <li class="nav-item col-md-4 text-center" role="presentation">
                <a class="nav-link" id="staff-tab" data-toggle="tab" href="#staff" role="tab" aria-controls="staff" aria-selected="false">
                  <span class="font-weight-bold">직원관리</span>
                </a>
              </li>
            </ul>
            <div class="tab-content">
              <jsp:include page="manage/orgTab.jsp" flush="true" />
              <jsp:include page="manage/teamTab.jsp" flush="true" />
              <jsp:include page="manage/staffTab.jsp" flush="true" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<jsp:include page="manage/insertOrgModal.jsp" flush="true" />
<jsp:include page="manage/detailStaffModal.jsp" flush="true" />
<jsp:include page="manage/insertStaffModal.jsp" flush="true" />
<jsp:include page="manage/updateStaffModal.jsp" flush="true" />
<jsp:include page="manage/insertTeamModal.jsp" flush="true" />
<jsp:include page="manage/updateTeamModal.jsp" flush="true" />
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script src="/resources/jstree/jstree.js"></script>
