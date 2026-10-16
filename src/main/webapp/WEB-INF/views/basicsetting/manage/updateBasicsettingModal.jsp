<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="update-setting-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="image-modal-label">기본설정이미지 수정</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <p>
          기본설정이미지를 수정합니다. <br>
        </p>
        <div class="row">
          <div class="col-md-12">
            <label class="text-lb">파일</label>
            <div class="input-group">
              <input type="hidden" id="update-setting-cd" />
              <input type="hidden" id="update-setting-type" />
              <input id="update-setting-filename" type="hidden" />
              <input id="update-setting-origin-filename" class="file-input-textbox form-control" readonly="readonly" />
              <div class="file-input-div">
                <input type="button" value="파일" class="file-input-button btn btn-dark" />
                <input type="file" class="file-input-hidden" data-icon='false' accept="image/*" id="update-setting-file" />
              </div>
            </div>
            <div id="update-upload-msg"></div>
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="updateSetting()">
          <i class="xi-check mr-1"></i>수정하기
        </button>
        <button class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>