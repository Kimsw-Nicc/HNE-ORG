<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="check-password-modal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-body">
        <div class="form-group">
          <label for="check-modal-password" class="control-label">정보수정을 위한 비밀번호 확인</label>
          <input type="password" id="check-modal-password" maxlength="40" placeholder="${login.userId}(${login.userName})의 비밀번호 입력" class="form-control" autocomplete="new-password">
        </div>
      </div>
      <div class="modal-footer">
        <button class="btn btn-primary" onclick="checkPassword()">확인</button>
        <button class="btn btn-secondary" data-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>