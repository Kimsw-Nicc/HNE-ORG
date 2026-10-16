<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="section-modal" tabindex="-1" role="dialog" aria-labelledby="imageModalModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="imageModalLabel">선택부서 동기화</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="m-3">
          <p>
            선택 한 부서만 동기화를 진행합니다.<br /> 아래 부서를 선택하세요.
          </p>
        </div>
        <div class="m-3">
          <div>
            <label class="text-lb">분류</label>
          </div>
          <div class="form-check form-check-inline custom-checkbox">
            <input type="checkbox" class="form-check-input" id="sync-selection-org-check" checked />
            <label class="form-check-label" for="sync-selection-org-check">부서</label>
          </div>
          <!--           <div class="form-check form-check-inline custom-checkbox"> -->
          <!--             <input type="checkbox" class="form-check-input" id="sync-selection-team-check" checked /> -->
          <!--             <label class="form-check-label" for="sync-selection-team-check">팀</label> -->
          <!--           </div> -->
          <div class="form-check form-check-inline custom-checkbox">
            <input type="checkbox" class="form-check-input" id="sync-selection-staff-check" checked />
            <label class="form-check-label" for="sync-selection-staff-check">직원</label>
          </div>
        </div>
        <div class="m-3">
          <label class="text-lb">부서</label> <select name="dep_code" id="sync-selection-org-cd" class="form-control section_cd">
          </select>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="syncSelection()">
          <i class="xi-share mr-1"></i>동기화 실행
        </button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>
