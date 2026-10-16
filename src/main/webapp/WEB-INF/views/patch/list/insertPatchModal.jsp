<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="insert-modal" tabindex="-1" role="dialog" aria-labelledby="imageModalModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="imageModalLabel">패치 파일 등록</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="col-md-12">
          <div class="form-group">
            <label class="text-lb">1. 파일</label>
            <div class="input-group">
              <input id="patch-filename" class="file-input-textbox form-control" readonly />
              <div class="file-input-div">
                <input type="button" value="파일" class="file-input-button btn btn-dark" />
                <input type="file" class="file-input-hidden" id="patch-file" accept=".apk" />
              </div>
            </div>
            <div class="form-group">
              <label class="text-lb">2. 메모 입력</label>
              <textarea class="form-control" placeholder="메모 입력" id="insert-patch-info" required></textarea>
            </div>
            <input type="hidden" id="insert-patch-type" />
          </div>
        </div>
      </div>
      <div class="col-md-12">
        <div class="form-group text-center">
          <button type="button" class="btn btn-primary" onclick="insertPatch()">
            <i class="xi-check mr-1"></i>등록
          </button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"> <i class="xi-close mr-1"></i>취소
            </span>
          </button>
        </div>
      </div>
    </div>
  </div>
</div>
