/* jshint esversion : 6 */
$(() => {
	getScheduleMsg();
	getHistory();
})

const getScheduleMsg = async () => {
	let { data: schedule } = await axios.get(`/sync/schedule`);
	let msg;
	if (!schedule) {
		msg = "설정된 시간이 없습니다";
	} else {
		const { scheduleSecond, scheduleMinute, scheduleHour, scheduleDay, scheduleWeek, scheduleMonth } = schedule;

		if (scheduleWeek !== "*") {
			const weeks = ["월", "화", "수", "목", "금", "토", "일"];
			const week = weeks[parseInt(scheduleWeek) - 1];
			msg = `매주 ${week}요일 ${scheduleHour}시 ${scheduleMinute}분`;
		} else if (scheduleDay !== "*") {
			msg = `매달 ${scheduleDay}일 ${scheduleHour}시 ${scheduleMinute}분`;
		} else if (scheduleMonth === "*") {
			msg = `매일 ${scheduleHour}시 ${scheduleMinute}분`;
		} else {
			msg = "";
		}
	}

	$('#schedule-msg').html(msg)
}

const getHistory = async () => {
	try {
		let { data: history } = await axios.get(`/sync/history`);
		const { historyNdt, historySuccess } = history;
		if (historySuccess) {
			$('#sync-history').html(`<div class="form-group">
				<h5 class="text-lb">마지막 동기화 완료 시간</h5>
			</div>
			<label class="text-lb">${moment(historyNdt).format('yyyy년 MM월 DD일 HH시 mm분 ss초')}</label>`)
		} else {
			$('#sync-history').html(`<div class="form-group">
				<h5 class="text-lb">마지막 동기화 완료 시간</h5>
			</div>
			<i class="xi-warning" style="color: #ff0000;"></i>동기화실패<br>
			<label class="text-lb">${moment(historyNdt).format('yyyy년 MM월 DD일 HH시 mm분 ss초')}</label>`)
		}
	} catch {
		$('#sync-history').html(`<div class="form-group">
				<h5 class="text-lb">마지막 동기화 완료 시간</h5>
			</div>
			<label class="text-lb">최근 동기화가 없습니다.</label>`)
	}
}

const resetSync = () => {
	if (!confirm("동기화를 해제하시겠습니까?")) return;
	axios.post("/sync/reset")
	getScheduleMsg()
	getHistory()
}

const openSyncAll = async () => {
	$("#sync-all-modal").modal();
}

const syncAll = async () => {
	if (confirm("선택된 정보의 동기화를 하시겠습니까?")) {
		$("#sync-all-modal").modal("hide");
		openProcessModal('동기화 진행 중...')
		try {
			if ($("#sync-all-org-check").is(":checked")) {
				await axios.post("/sync/org")
			}
			if ($("#sync-all-team-check").is(":checked")) {
				await axios.post("/sync/team")
			}
			if ($("#sync-all-staff-check").is(":checked")) {
				await axios.post("/sync/staff")
			}
		} catch (e) {
			console.error(e)
			errorToast('동기화에 실패하였습니다')
		}
		closeProcessModal()
		$('#sync-all-moda').modal('hide')
		getScheduleMsg()
		getHistory()
	};
}


const openSyncSelection = async () => {
	$('#section-modal').modal();
	getOrgs('#sync-selection-org-cd')
}

const syncSelection = async () => {
	if (confirm("선택된 부서의 직원정보를 동기화를 하시겠습니까?")) {
		$("#section-modal").modal("hide");
		openProcessModal('동기화 중..')
		let options = {
			params: {
				orgCd: $("#sync-selection-org-cd").val()
			}
		}
		try {
			if ($("#sync-selection-org-check").is(':checked')) {
				await axios.post("/sync/org", {}, options)
			}
			if ($("#sync-selection-team-check").is(':checked')) {
				await axios.post("/sync/team", {}, options)
			}
			if ($("#sync-selection-staff-check").is(':checked')) {
				await axios.post("/sync/staff", {}, options)
			}
		} catch (e) {
			console.error(e)
			errorOtast('동기화에 실패하였습니다')
		}
		$('#section-modal').modal('hide');
		closeProcessModal()
		getScheduleMsg()
		getHistory()
	}
}

const openUpdateSchedule = async () => {
	$("#update-schedule-modal").modal('show');
}

$(() => {
	$('#update-every-week').hide()
	$('#update-every-month').hide()
	$('#update-check-every-day').click(() => {
		$('#update-check-every-month').prop('checked', false)
		$('#update-check-every-week').prop('checked', false)
		$('#update-every-week').hide()
		$('#update-every-month').hide()
	})
	$('#update-check-every-month').click(() => {
		$('#update-check-every-day').prop('checked', false)
		$('#update-check-every-week').prop('checked', false)
		$('#update-every-week').hide()
		$('#update-every-month').show()
	})
	$('#update-check-every-week').click(() => {
		$('#update-check-every-month').prop('checked', false)
		$('#update-check-every-day').prop('checked', false)
		$('#update-every-week').show()
		$('#update-every-month').hide()
	})
})

const updateSchedule = async () => {
	let data = {
		scheduleId: 0,
		scheduleSecond: 0,
		scheduleMinute: $('#update-month').val(),
		scheduleHour: $('#update-hour').val(),
		scheduleDay: $('#update-check-every-month').is(":checked") ? $('#update-day').val() : '*',
		scheduleWeek: $('#update-check-every-week').is(":checked") ? $('#update-week').val() : '*',
		scheduleMonth: '*',
		scheduleOrg: $('#update-schedule-org-check').is(":checked"),
		scheduleTeam: $('#update-schedule-team-check').is(":checked"),
		scheduleStaff: $('#update-schedule-staff-check').is(":checked"),
	}
	await axios.post("/sync/schedule", data);
	$("#update-schedule-modal").modal('hide');
	getScheduleMsg()
	getHistory()
}

