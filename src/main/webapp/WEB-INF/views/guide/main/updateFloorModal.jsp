<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="update-floor-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="image-modal-label">청사안내도 수정</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div></div>
      <div class="modal-body">
        <input type="hidden" id="update-floor-id" />
        <div class="form-group">
          <label class="text-lb"> 이미지를 선택하세요. <br /> <span class="ml-1 text-l">파일 : 5MB 이하 / 가로 1,280 x 650px / PNG 또는 JPG 형식</span>
          </label>
          <div class="input-group">
            <input id="update-floor-origin-filename" class="file-input-textbox form-control" readonly="readonly" />
            <input id="update-floor-filename" type="hidden" class="file-input-textbox form-control" readonly="readonly" />
            <div class="file-input-div">
              <input type="button" value="이미지 선택" id="update-file-button" class="file-input-button btn btn-dark" />
              <input type="file" class="file-input-hidden" data-icon="false" accept="image/*" id="update-floor-file" />
            </div>
          </div>
          <div id="upload-msg"></div>
          <div class="form-group">
            <label> 순서 <span class="small">※ 숫자만 입력하세요.</span>
            </label>
            <input type="number" class="form-control" id="update-floor-sort" autocomplete="off" value="" />
          </div>
        </div>
        <div class="modal-footer">
          <button onClick="updateFloor()" class="btn btn-primary">
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
