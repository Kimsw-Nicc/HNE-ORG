<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="detail-staff-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-sm modal-contents" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="example-modal-label">직원 상세보기</h4>
        <button class="btn btn-default" type="button" data-dismiss="modal">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-4 mb-0 align-content-center">
            <img id="detail-staff-img" class="staff-img-lg" onError="noImg(this);">
          </div>
          <div class="col-md-8 mb-0">
            <div class="col-md-12 mb-0" style="border-bottom: 1px dashed #ededed;">
              <div style="margin-bottom: -9px;">
                <span id="detail-staff-org" class="large"></span>
              </div>
              <div>
                <span class="x-small">
                  부서코드 :
                  <span id="detail-staff-org-code"></span>
                </span>
              </div>
            </div>
            <div class="col-md-12 mb-1">
              <span id="detail-staff-name" class="x-large"></span>
              &nbsp;
              <span id="detail-staff-position"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>순서</span>
              :
              <span id="detail-staff-sort" class="font-weight-bold"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>전화번호</span>
              :
              <span id="detail-staff-telno" class="font-weight-bold"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>팩스번호</span>
              :
              <span id="detail-staff-faxno" class="font-weight-bold"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>재직상태</span>
              :
              <span id="detail-staff-status" class="font-weight-bold"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>재실상태</span>
              :
              <span id="detail-staff-present" class="font-weight-bold"></span>
            </div>
            <div class="col-md-12 mb-0">
              <span>수정날짜</span>
              :
              <span id="detail-staff-edt"></span>
            </div>
            <div class="col-md-4 d-none">
              <span class="font-weight-bold">재실 정보</span>
              <input type="text" class="form-control" id="detail-staff-status" readOnly>
            </div>
            <div class="col-md-4 d-none">
              <span class="font-weight-bold">이메일</span>
              <input type="text" class="form-control" id="detail-staff-email" readOnly>
            </div>
            <c:if test="${org && 'ADMIN_ROLE' == login.userRole || 'ORG_ROLE' == login.userRole}">
              <div class="col-md-12 mb-0">
                <span class="font-weight-bold">현직급</span>
                :
                <span id="detail-staff-grade"></span>
              </div>
              <div class="col-md-12 mb-0">
                <span class="font-weight-bold">현직급 임용일</span>
                :
                <span id="detail-staff-grade-appoint-date"></span>
              </div>
              <div class="col-md-12 mb-0">
                <span class="font-weight-bold">임용일</span>
                :
                <span id="detail-staff-appoint-date"></span>
              </div>
              <div class="col-md-12 mb-0">
                <span class="font-weight-bold">현부서 배치일</span>
                :
                <span id="detail-staff-org-appoint-date"></span>
              </div>
            </c:if>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <span class="large">담당업무</span>
            <textarea class="form-control mt-1" id="detail-staff-duty" readOnly></textarea>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>