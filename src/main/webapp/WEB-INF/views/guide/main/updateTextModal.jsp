<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="update-text-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <form class="form-horizontal" action="/guide/text/update" method="post" autocomplete="off">
        <div class="modal-header">
          <h4 class="modal-title" id="image-modal-label">텍스트상자 수정</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
          </button>
        </div>
        <div class="modal-body">
          <p>
            텍스트상자를 수정합니다.<br />
          </p>
          <div class="row">
            <div class="col-md-6">
              <label class="text-lb">글자 크기<i class="xi-check-min ml-1 input-need"></i></label>
              <div>
                <select class="form-control mr-2" id="update-text-font-size">
                </select>
              </div>
            </div>

            <div class="col-md-6">
              <label class="text-lb">글자 스타일<i class="xi-check-min ml-1 input-need"></i></label>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="update-text-font-weight" />
                <label class="form-check-label" for="update-text-font-weight"> 굵게 </label>
              </div>
              <div class="form-check">
                <input class="form-check-input" type="checkbox" value="" id="update-text-font-style" />
                <label class="form-check-label" for="update-text-font-style"> 기울기 </label>
              </div>
            </div>
            <div class="col-md-4">
              <!--               <label class="text-lb">순서<i class="xi-check-min ml-1 input-need"></i></label> -->
              <!--               <div> -->
              <!--                 <input class="form-control" type="text" id="update-text-sort" disabled/> -->
              <!--               </div> -->
            </div>
          </div>
          <div class="form-group">
            <label class="text-lb">텍스트 입력<i class="xi-check-min ml-1 input-need"></i></label>
            <textarea class="form-control" placeholder="텍스트 입력" id="update-text-content" required></textarea>
          </div>
          <label class="text-lb">부서</label> <select id="update-text-org-cd" class="form-control">
          </select>

          <div class="form-check form-check-inline">
            <input class="form-check-input" type="checkbox" id="update-text-check-all-team" autocomplete="off" checked />
            <label class="form-check-label text-lb" for="update-text-check-all-team">팀</label>
          </div>
          <div class="row" id="update-text-teams"></div>
        </div>
        <div class="col-md-12">
          <div class="form-group text-center">
            <button type="button" class="btn btn-primary" id="update-text" onclick="updateText()">
              <i class="xi-check mr-1"></i>수정
            </button>
            <button type="button" class="btn btn-danger" id="delete-text" onclick="deleteText()">
              <i class="xi-trash mr-1"></i>삭제
            </button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal" aria-label="Close">취소</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
