<%@page pageEncoding="UTF-8"%>
<div class="modal fade" id="insert-building-modal" tabindex="-1" role="dialog" aria-labelledby="buildingModalModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="image-modal-label">청사추가</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <img src="/resources/assets/images/icons/close.svg" width="16" height="16">
        </button>
      </div>
      <div></div>
      <div class="modal-body">
        <div class="form-group">
          <label for="building-name" class="text-lb">청사명</label>
          <input type="text" class="insert-building-name form-control" maxlength="20" placeholder="청사명" oninput="checkText(event)" autocomplete="off" />
          <span style="font-size: 0.8em">최대 20자 이내 (특수문자 제외)</span> <br /> <label for="building-sort" class="text-lb">순서</label>
          <input type="number" class="insert-building-sort form-control" placeholder="순서" oninput="checkText(event)" autocomplete="off" />
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-dark" onclick="insertBuilding()">
          <i class="xi-check mr-1"></i>청사추가
        </button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>
