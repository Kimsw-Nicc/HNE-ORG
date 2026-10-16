<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="update-background-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="image-modal-label">배경화면 수정</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <label class="text-lb">
            이미지를 선택하세요.
            <br />
            <span class="ml-1 text-l">파일 : 5MB 이하 / 가로 1,920 x 1,080px / PNG 또는 JPG 형식</span>
          </label>
          <div class="input-group">
            <input id="update-background-origin-filename" class="file-input-textbox form-control" readonly="readonly" />
            <input id="update-background-filename" type="hidden" class="file-input-textbox form-control" readonly="readonly" />
            <div class="file-input-div">
              <input type="button" value="이미지 선택" id="update-file-button" class="file-input-button btn btn-dark" />
              <input type="file" class="file-input-hidden" data-icon="false" accept="image/*" id="update-background-file" />
            </div>
          </div>
          <div id="upload-msg" style="font-size: 0.8em"></div>
        </div>
        <div class="modal-footer">
          <button onClick="updateBackground()" class="btn btn-primary">
            <i class="xi-check mr-1"></i>수정하기
          </button>
          <button type="button" class="btn btn-secondary" data-dismiss="modal">
            <i class="xi-close mr-1"></i>취소
          </button>
        </div>
      </div>
    </div>
  </div>
</div>
