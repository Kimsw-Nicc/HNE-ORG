<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="insert-team-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="insert-team-label">
          팀 추가<span class="small text-danger ml-2"><i class="xi-check input-need"></i>(필수 항목)</span>
        </h4>
        <button class="btn btn-default" type="button" data-dismiss="modal" id="insert-team-close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            팀 명칭<i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="insert-team-name" placeholder="팀 명칭 입력" />
          </div>
          <div class="col-md-6">
            팀 코드<span class="text-danger x-small">(부서코드 + 01, 02 ... n 순차부여)</span><i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="insert-team-code" placeholder="팀 코드 입력" />
          </div>
          <div class="col-md-6">
            순서<i class="xi-check input-need"></i>
            <input type="number" class="form-control" id="insert-team-sort" pattern="[0-9]+" onkeydown="javascript: return event.keyCode == 69 ? false : true" placeholder="팀 순서 입력" />
          </div>
          <div class="col-md-12">
            팀 정보
            <textarea class="form-control" id="insert-team-info" placeholder="팀 정보 입력"></textarea>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-outline-primary" onclick="insertTeam()">
          <i class="xi-pen"></i>&nbsp;생성
        </button>
        <button class="btn btn-outline-secondary" type="button" data-dismiss="modal">
          <i class="xi-close-circle-o"></i>&nbsp;취소
        </button>
      </div>
    </div>
  </div>
</div>