<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="update-team-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="update-team-label">
          팀 수정<span class="small text-danger ml-2"><i class="xi-check input-need"></i>(팔수항목)</span>
        </h4>
        <button class="btn btn-default" type="button" data-dismiss="modal" id="update-team-close" onclick="updateTeamCancel()">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-12">
            팀 명칭 <i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="update-team-name" />
          </div>
          <div class="col-md-6">
            팀 코드<i class="xi-check input-need"></i>
            <input type="text" class="form-control" id="update-team-code" readOnly />
          </div>
          <div class="col-md-6">
            순서<i class="xi-check input-need"></i>
            <input type="number" class="form-control" id="update-team-sort" pattern="[0-9]+" onkeydown="javascript: return event.keyCode == 69 ? false : true" />
          </div>
          <div class="col-md-12">
            팀 정보
            <textarea class="form-control" id="update-team-info"></textarea>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <button class="btn btn-outline-primary" onclick="updateTeam()">
          <i class="xi-pen"></i>&nbsp;수정
        </button>
        <button class="btn btn-outline-secondary" type="button" data-dismiss="modal">
          <i class="xi-close-circle-o"></i>&nbsp;취소
        </button>
      </div>
    </div>
  </div>
</div>