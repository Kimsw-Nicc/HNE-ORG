<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="reset-all-password-modal" tabindex="-1" role="dialog" aria-labelledby="image-modal-label" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="image-modal-label">비밀번호 일괄 초기화</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
          </button>
        </div>
        <div class="modal-body">
          <p>
            입력한 비밀번호로 일괄 초기화 합니다.<br>
          </p>
          <div class="form-group">
            <label class="text-lb">초기화 비밀번호 변경<i class="xi-check-min ml-1 input-need"></i></label>
            <input type="password" class="form-control" placeholder="변경할 비밀번호 입력" id="reset-password"required>
            <div id="reset-passowrd-msg">영문, 숫자, 특수문자(!@#$%^&amp;*+=-)를 조합한 8자 이상</div>
          </div>
          <div class="form-group">
            <label class="text-lb">초기화 비밀번호 확인<i class="xi-check-min ml-1 input-need"></i></label>
            <input type="password" class="form-control" placeholder="비밀번호 확인" id="reset-password-check" maxlength="30" autocomplete="off">
            <div id="reset-passowrd-check-msg"></div>
          </div>
        </div>
        <div class="col-md-4"></div>
        <div class="col-md-12">
          <div class="form-group text-center">
            <button type="button" class="btn btn-primary" onclick="resetAllPassword()">
              <i class="xi-check mr-1"></i>수정
            </button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal"><i class="xi-close mr-1"></i>취소</button>
          </div>
        </div>
    </div>
  </div>
</div>