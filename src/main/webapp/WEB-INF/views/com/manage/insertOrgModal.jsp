<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="insert-org-modal" tabindex="-1" role="dialog" aria-labelledby="insert-org-label" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="insert-org-label">
          부서 추가<span class="small text-danger ml-2"><i class="xi-check input-need"></i>(필수 항목)</span>
        </h4>
        <button class="btn btn-default" type="button" data-dismiss="modal">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-6">
            상위부서이름
            <input type="text" class="form-control" id="insert-org-upr-name" readonly />
          </div>
          <div class="col-md-6">
            상위부서코드
            <input type="text" class="form-control" id="insert-org-upr-cd" readonly />
          </div>
          <div class="col-md-4">
            이름<i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="insert-org-name" placeholder="부서 이름" />
          </div>
          <div class="col-md-4">
            코드<i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="insert-org-cd" placeholder="부서 코드" />
          </div>
          <div class="col-md-4">
            순서<i class="xi-check input-need"></i>
            <input type="number" class="form-control" id="insert-org-sort" placeholder="순서" pattern="[0-9]+" onkeydown="javascript: return event.keyCode == 69 ? false : true" />
          </div>
          <%--
          <div class="col-md-12">
            부서전체이름
            <textarea class="form-control" id="insert-org-full-name" placeholder="부서 전체 이름&#13;&#10;예) @@시 @@구 @@국 @@과"></textarea>
          </div>
          --%>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-outline-primary" onclick="insertOrg()">
          <i class="xi-pen"></i>&nbsp;생성
        </button>
        <button class="btn btn-outline-secondary" type="button" data-dismiss="modal">
          <i class="xi-close-circle-o"></i>&nbsp;취소
        </button>
      </div>
    </div>
  </div>
</div>
