<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="sync-all-modal" tabindex="-1" role="dialog" aria-labelledby="imageModalModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="imageModalLabel">일괄 동기화</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<img src="/resources/assets/images/icons/close.svg" width="16" height="16">
				</button>
			</div>
			<div class="modal-body">
				<div class="m-3">
					<div>
						<label class="text-lb">분류</label>
					</div>
					<div class="form-check form-check-inline custom-checkbox">
						<input type="checkbox" class="form-check-input" id="sync-all-org-check" checked>
						<label class="form-check-label" for="sync-all-org-check">부서</label>
					</div>
					<div class="form-check form-check-inline custom-checkbox">
						<input type="checkbox" class="form-check-input" id="sync-all-team-check" checked>
						<label class="form-check-label" for="sync-all-team-check">팀</label>
					</div>
					<div class="form-check form-check-inline custom-checkbox">
						<input type="checkbox" class="form-check-input" id="sync-all-staff-check" checked>
						<label class="form-check-label" for="sync-all-staff-check">직원</label>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary" onclick="syncAll()">
					<i class="xi-share mr-1"></i>동기화 실행
				</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					<i class="xi-close mr-1"></i>취소
				</button>
			</div>
		</div>
	</div>
</div>