<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="update-schedule-modal" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="imageModalLabel">시간 동기화</h4>
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
						<input type="checkbox" class="form-check-input" id="update-schedule-org-check" checked> <label class="form-check-label" for="update-schedule-org-check">부서</label>
					</div>
					<div class="form-check form-check-inline custom-checkbox">
						<input type="checkbox" class="form-check-input" id="update-schedule-team-check" checked> <label class="form-check-label" for="update-schedule-team-check">팀</label>
					</div>
					<div class="form-check form-check-inline custom-checkbox">
						<input type="checkbox" class="form-check-input" id="update-schedule-staff-check" checked> <label class="form-check-label" for="update-schedule-staff-check">직원</label>
					</div>
				</div>

				<div class="m-3">
					<div>
						<label class="text-lb">주기</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input update-check" id="update-check-every-day" checked="checked"> <label class="form-check-label" for="update-check-every-day">매일</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input update-check" id="update-check-every-week"> <label class="form-check-label" for="update-check-every-week">매주</label>
					</div>
					<div class="form-check form-check-inline">
						<input type="checkbox" class="form-check-input update-check" id="update-check-every-month"> <label class="form-check-label" for="update-check-every-month">매월</label>
					</div>
				</div>
				<div id="update-every-week" class="m-3">
					<label class="text-lb">요일</label> <select class="form-control" id="update-week">
						<option value="">요일을 선택하세요</option>
						<option value="1">월요일</option>
						<option value="2">화요일</option>
						<option value="3">수요일</option>
						<option value="4">목요일</option>
						<option value="5">금요일</option>
						<option value="6">토요일</option>
						<option value="7">일요일</option>
					</select>
				</div>
				<div id="update-every-month" class="m-3">
					<label class="text-lb">일</label> <select class="form-control" id="update-day">
						<option value="">일을 선택하세요</option>
						<%
						for (int i = 1; i <= 31; i++) {
						%>
						<option value="<%=i%>"><%=i%>일
						</option>
						<%
						}
						%>
					</select>
				</div>
				<div class="m-3">
					<label class="text-lb" for="HH">시간</label> <select class="form-control" id="update-hour">
						<%
						for (int i = 1; i < 24; i++) {
						%>
						<option value="<%=i%>"><%=i%>시
						</option>
						<%
						}
						%>
						<option value="0">24시</option>
					</select>
				</div>
				<div class="m-3">
					<label class="text-lb" for="mm">분</label> <select class="form-control" id="update-month">
						<option value="0">0분</option>
						<%
						for (int i = 1; i <= 59; i++) {
						%>
						<option value="<%=i%>"><%=i%>분
						</option>
						<%
						}
						%>
					</select>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="update-schedule" class="btn btn-primary" onclick="updateSchedule()">
					<i class="xi-share mr-1"></i>동기화 실행
				</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">
					<i class="xi-close mr-1"></i>취소
				</button>
			</div>
		</div>
	</div>
</div>