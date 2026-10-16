<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="insert-staff-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg modal-contents" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="insert-modal-label">
          직원 등록
          <span class="small text-danger ml-2">
            <i class="xi-check input-need"></i>(필수 항목)
          </span>
        </h4>
        <button class="btn btn-default" type="button" data-dismiss="modal">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-3 text-center align-content-center">
            <img id="insert-staff-img" class="staff-img-lg" onError="noImg(this)" src="/resources/assets/images/users/unknow.png" />
            <div>
              사진
              <div>
                <span class="small text-danger">320x400px 권장</span>
              </div>
              <div>
                <span class="small text-danger">PNG 또는 JPG 형식</span>
              </div>
              <div>
                <input accept="image/*" type="file" class="file-input-hidden" id="insert-staff-file" /> <label for="insert-staff-file" class="btn btn-sm btn-primary">사진 등록</label> <label class="btn btn-sm btn-danger delete-insert-staff-file-btn">사진 삭제</label>
              </div>
            </div>
          </div>
          <div class="row col-md-9 insert-modal-contents mb-0">
            <div class="col-md-6">
              <span class="font-weight-bold">부서이름</span>
              <input type="text" class="form-control" id="insert-staff-org-name" readOnly />
            </div>
            <div class="col-md-4 d-none">
              <span class="font-weight-bold">부서코드</span>
              <input type="text" class="form-control" id="insert-staff-org-code" readOnly />
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">소속팀</span>
              <select class="form-control col-md-12 mb-0" id="insert-staff-team-code">
                <option>선택</option>
              </select>
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">이름</span>
              &nbsp;<i class="xi-check input-need"></i> <input type="text" class="form-control" id="insert-staff-name" placeholder="이름" />
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">직위</span>
              <input type="text" class="form-control" id="insert-staff-position" placeholder="직위" />
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">순서</span>
              &nbsp;<i class="xi-check input-need"></i>
              <span class="small text-danger">*(0~98까지 표시, 99 표시안됨)</span>
              <input type="number" class="form-control" id="insert-staff-sort" pattern="[0-9]+" onkeydown="javascript: return event.keyCode == 69 ? false : true" placeholder="순서" />
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">재직상태</span>
              <select class="form-control form-select" id="insert-staff-status">
                <option value="AAA">재직</option>
                <option value="AAB">재직(파견)</option>
                <option value="ABA">휴직</option>
                <option value="ABB">휴직(무급)</option>
                <option value="ACA">신규임용후보</option>
                <option value="BAB">파견(전입)</option>
                <option value="BAF">재직(FIU등)</option>
                <option value="BAN">재직(국과수)</option>
                <option value="BAP">재직(급여)</option>
                <option value="BAR">재직(주재관)</option>
                <option value="DAA">퇴직</option>
                <option value="DAB">전출</option>
                <option value="DAC">파견복귀</option>
                <option value="DAF">전출(FIU등)</option>
                <option value="DAN">전출(국과수)</option>
                <option value="DAO">전출(주재관해제)</option>
                <option value="DAR">전출(주재관)원소속</option>
              </select>
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">재실/부재</span>
              <select class="form-control form-select" id="insert-staff-present">
                <option value="true">재실</option>
                <option value="false">부재</option>
              </select>
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">전화번호</span>
              <input type="text" class="form-control" id="insert-staff-telno" onKeyup="this.value=this.value.replace(/[^0-9\-]/g,'');" placeholder="전화번호" />
            </div>
            <div class="col-md-6">
              <span class="font-weight-bold">팩스번호</span>
              <input type="text" class="form-control" id="insert-staff-faxno" onKeyup="this.value=this.value.replace(/[^0-9\-]/g,'');" placeholder="팩스번호" />
            </div>
            <!--미사용값 -->
            <div class="col-md-6 d-none">
              <span class="font-weight-bold">재실정보</span>
              <select class="form-control form-select" id="insert-staff-work-status-code">
                <option value="재실">재실</option>
                <option value="부재">부재</option>
              </select>
            </div>
            <div class="col-md-6 d-none">
              이메일 <input type="email" class="form-control" id="insert-staff-email" placeholder="이메일" />
            </div>
            <div class="col-md-12">
              <span class="font-weight-bold">담당업무</span>
              <textarea class="form-control" id="insert-staff-duty" placeholder="업무 내용을 적어주세요"></textarea>
            </div>
            <c:if test="${('ADMIN_ROLE' == login.userRole || 'ORG_ROLE' == login.userRole) && org}">
              <div class="col-md-12 row">
                <div class="col-md-12 mb-2">
                  <span class="font-weight-bold">부가정보</span>
                  <span class="small text-danger">*부가정보는 한눈에조직도 장비에 표출되지 않습니다.</span>
                </div>
                <div class="col-md-6">
                  <span class="font-weight-bold">임용일</span>
                  <input class="form-control datepicker" id="insert-staff-appoint-date" placeholder="YYYY-MM-DD"></input>
                </div>
                <div class="col-md-6">
                  <span class="font-weight-bold">현부서 배치일</span>
                  <input class="form-control datepicker" id="insert-staff-org-appoint-date" placeholder="YYYY-MM-DD"></input>
                </div>
                <div class="col-md-6">
                  <span class="font-weight-bold">현직급</span>
                  <input class="form-control" id="insert-staff-grade" placeholder="직급"></input>
                </div>
                <div class="col-md-6">
                  <span class="font-weight-bold">현직급 임용일</span>
                  <input class="form-control datepicker" id="insert-staff-grade-appoint-date" placeholder="YYYY-MM-DD"></input>
                </div>
              </div>
            </c:if>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-outline-primary" onclick="insertStaff()">
          <i class="xi-pen"></i>&nbsp;생성
        </button>
        <button class="btn btn-outline-secondary" type="button" data-dismiss="modal">
          <i class="xi-close-circle-o"></i>&nbsp;취소
        </button>
      </div>
    </div>
  </div>
</div>