<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="insert-setting-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="image-modal-label">기본설정이미지 등록</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <p>기본설정이미지를 등록합니다.</p>
        <div class="row">
          <div class="col-md-12">
            <label class="text-lb"> 등록 유형<i class="xi-check-min ml-1 input-need"></i>
            </label>
            <div id="insert-type"></div>
          </div>
          <div class="col-md-12">
            <label class="text-lb">파일</label>
            <div class="input-group">
              <input id="insert-setting-filename" class="file-input-textbox form-control" readonly />
              <div class="file-input-div">
                <input type="button" value="파일" class="file-input-button btn btn-dark" />
                <input type="file" class="file-input-hidden" data-icon='false' accept="image/*" id="insert-setting-file" />
              </div>
            </div>
            <div id="insert-upload-msg"></div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary" onclick="insertSetting()">
          <i class="xi-check mr-1"></i>등록하기
        </button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>