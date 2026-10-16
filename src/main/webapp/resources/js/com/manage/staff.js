/* jshint esversion : 6 */
let currentStaffPage = 0;
// STAFF TAB
$(() => {
	$('#search-staff-input').keypress(function(e) {
		if (e.which == 13) {
			currentStaffPage = 0
			keydown = true;
			getStaffs(currentOrgCd)
			keydown = false;
		}
	});
	$('#search-staff-button').click(function() {
		currentStaffPage = 0
		getStaffs(currentOrgCd)
	});
	$('#search-type').change(function() {
		currentStaffPage = 0;
		getStaffs(currentOrgCd)
	});

	$("#insert-staff-file").change(function() {
		handleFileChange(this, null, ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024);
		$('#insert-staff-img').attr('src', URL.createObjectURL(this.files[0]));
	});
	$("#update-staff-file").change(function() {
		handleFileChange(this, null, ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024);
		$('#update-staff-img').attr('src', URL.createObjectURL(this.files[0]));
	});

	$('.delete-insert-staff-file-btn').click(() => {
		$('#insert-staff-file').val('')
		$('#insert-staff-img').attr('src', '/resources/assets/images/users/unknow.png');
	})

	$('.delete-update-staff-file-btn').click(() => {
		fileInputFlag = true
		$('#update-staff-file').val('')
		$('#update-staff-filename').val('')
		$('#update-staff-origin-filename').val('')
		$('#update-staff-img').attr('src', '/resources/assets/images/users/unknow.png');
	})
	$('#update-staff-org-code').change(() => {
		updateStaffTeam($('#update-staff-org-code').val(), null);
	});
})
const getStaffs = async (orgCd) => {
	let keyword = $('#search-staff-input').val();
	let searchType = $('#search-type').val() == 'org';
	let params = {
		orgCd: searchType ? orgCd : '',
		teamCd: '',
		keyword: keyword,
		keywordType: 'staffName',
		size: 10,
		page: currentStaffPage,
		sort: '',
		status: ''
	};
	setPushState(params);
	let { data: staffs } = await axios.get("/staff/all", { params });
	if (staffs.length == 0 && currentStaffPage != 0) {
		currentStaffPage--;
		getStaffs(orgCd);
		getStaffPaging(orgCd);
		return;
	}
	$('#staff-tbody').empty();
	for (const staff of staffs) {
		$('#staff-tbody').append(getStaffTr(staff));
	}
	getStaffPaging(orgCd);
}
// staff 정보를 tr로 변환
const getStaffTr = (staff) => {
	let staffTr = "";

	// 표시
	staffTr += staff.staffDeleted ? createTd("") : createTd(createSwitch(`${staff.staffCd}-visible`, staff.staffVisible, `updateStaffVisible(event, '${staff.staffCd}')`));
	// 동기화
	staffTr += staff.staffSync === "manual" ?
		createTd("수동", 'text-center')
		: createTd(createSwitch(`${staff.staffCd}-sync`, staff.staffSync === "sync", `updateStaffSync(event, '${staff.staffCd}')`));
	// 사진
	const imgSrc = getUploadSrc(staff.staffFilename, 'staff');
	const imgTag = `<img class="staff-img-sm" onError="noImg(this);" src="${imgSrc}">`;
	staffTr += createTd(imgTag, "", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 순서
	staffTr += createTd(staff.staffSort, "cursor", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 부서
	staffTr += createTd(staff.orgName, "cursor", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 팀
	staffTr += createTd(staff.teamName ? staff.teamName : "선택 필요", `cursor ${staff.teamName ? "" : "text-danger"}`, `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 이름
	staffTr += createTd(staff.staffName, "cursor", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 직위
	staffTr += createTd(staff.staffPosition || "", "cursor", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 상태
	staffTr += createTd(staff.staffStatusString, "cursor", `onclick="openDetailStaff('${staff.staffCd}')"`);
	// 수정
	staffTr += createTd(`<button class="btn btn-outline-warning btn-sm" onclick="openUpdateStaff('${staff.staffCd}')"><i class="xi-pen"></i></button>`);
	// 삭제
	const deleteClick = staff.staffDeleted ? `openRestoreStaffModal('${staff.staffCd}')` : `deleteStaff('${staff.staffCd}')`;
	staffTr += createTd(
		`<button class="btn btn-outline-danger btn-sm" onclick=${deleteClick}>
      <i class="xi-trash"></i>
    </button>`
	);
	return `<tr ${staff.staffDeleted ? 'style="color: #c9c9c9;"' : ""}>${staffTr}</tr>`;
};

// INSERT STAFF //
const openInsertStaff = async () => {
	$('#insert-staff-modal').modal('show')
	// 부서 설정
	let { data: org } = await axios.get(`/org/${currentOrgCd}`);
	$('#insert-staff-org-name').val(org.orgName)
	$('#insert-staff-org-code').val(org.orgCd)

	// 팀 설정
	let { data: teams } = await axios.get(`/team/all?orgCd=${currentOrgCd}`);
	$('#insert-staff-team-code').empty();
	$('#insert-staff-team-code').append(`<option value="null">선택</option>`);
	for (const team of teams) {
		$('#insert-staff-team-code').append(`<option value="${team.teamCd}" >${team.teamName}</option>`);
	}
	$('#insert-staff-img').attr('src', '/resources/assets/images/users/unknow.png');
	$('#insert-staff-file').val('')
	$('#insert-staff-name').val('')
	$('#insert-staff-position').val('')
	$('#insert-staff-sort').val('')
	$('#insert-staff-status').val('AAA')
	$('#insert-staff-present').val('true')
	$('#insert-staff-telno').val('')
	$('#insert-staff-faxno').val('')
	$('#insert-staff-duty').val('')
}

const insertStaff = async () => {
	if (!emptyCheck('#insert-staff-org-code', '부서 선택 후 실행해주세요')) return false;
	if (!emptyCheck('#insert-staff-name', '직원 이름을 적어주세요')) return false;
	if (!emptyCheck('#insert-staff-sort', '직원 순서를 적어주세요')) return false;
	if (!emptyCheck('#insert-staff-status', '재직정보를 선택해주세요')) return false;

	let filename = {
		filename: null,
		originalFilename: null
	}
	try {
		filename = await saveFile('#insert-staff-file', 'staff')
	} catch { }
	data = {
		orgCd: $('#insert-staff-org-code').val(),
		teamCd: $('#insert-staff-team-code').val() == 'null' ? null : $('#insert-staff-team-code').val(),
		teamCd: $('#insert-staff-team-code').val(),
		staffCd: $('#insert-staff-code').val(),
		staffName: $('#insert-staff-name').val(),
		staffPosition: $('#insert-staff-position').val(),
		staffTelno: $('#insert-staff-telno').val(),
		staffFaxno: $('#insert-staff-faxno').val(),
		staffEmail: $('#insert-staff-email').val(),
		staffSort: $('#insert-staff-sort').val(),
		staffDuty: $('#insert-staff-duty').val(),
		staffStatus: $('#insert-staff-status').val(),
		staffPresent: $('#insert-staff-present').val() == 'true',
		staffOriginFilename: filename.originalFilename,
		staffFilename: filename.filename
	}

	await axios.post('/staff/insert', data);
	getStaffs(currentOrgCd)
	$('#insert-staff-modal').modal('hide');
}

const openDetailStaff = async (staffCd) => {
	$('#detail-staff-modal').modal('show');
	let { data: staff } = await axios.get(`/staff/${staffCd}`);
	$('#detail-staff-org').html(staff.orgName);
	$('#detail-staff-org-code').html(staff.orgCd);
	$('#detail-staff-sort').html(staff.staffSort);
	$('#detail-staff-status').html(staff.staffStatusString);
	$('#detail-staff-present').html(staff.staffPresent?"재실":"부재");
	$('#detail-staff-telno').html(staff.staffTelno);
	$('#detail-staff-duty').html(staff.staffDuty);
	$('#detail-staff-position').html(staff.staffPosition);
	$('#detail-staff-name').html(staff.staffName);
	$('#detail-staff-faxno').html(staff.staffFaxno);
	$('#detail-staff-email').html(staff.staffEmail);
	$('#detail-staff-edt').html(moment(staff.staffEdt).format('YYYY-MM-DD HH:mm:ss'));
	const imgSrc = getUploadSrc(staff.staffFilename, 'staff');
	$("#detail-staff-img").attr("src", imgSrc);
}

const openUpdateStaff = async (staffCd) => {
	$('#update-staff-modal').modal('show');
	let { data: staff } = await axios.get(`/staff/${staffCd}`);
	$('#update-staff-org').val(staff.orgName);
	$('#update-work-status').val(staff.staffStatus);
	$('#update-staff-name').val(staff.staffName);
	$('#update-staff-status').val(staff.staffStatus);
	$('#update-staff-sort').val(staff.staffSort);
	$('#update-staff-position').val(staff.staffPosition);
	$('#update-staff-email').val(staff.staffEmail);
	$('#update-staff-telno').val(staff.staffTelno);
	$('#update-staff-duty').val(staff.staffDuty)
	$('#update-staff-faxno').val(staff.staffFaxno);
	$('#update-staff-code').val(staff.staffCd);
	$('#update-staff-file').val('');
	$('#update-staff-filename').val(staff.staffFilename);
	$('#update-staff-origin-filename').val(staff.staffOriginFilename);
	const imgSrc = getUploadSrc(staff.staffFilename, 'staff');
	$("#update-staff-img").attr("src", imgSrc);
	$('#update-staff-present').val(staff.staffPresent ? 'true' : 'false');
	let { data: orgs } = await axios.get(`/org/all`);
	$(`#update-staff-org-code`).empty();
	for (const org of orgs) {
		let selected = org.orgCd == staff.orgCd ? "selected" : ""
		$('#update-staff-org-code').append(`<option ${selected} value="${org.orgCd}">${org.orgName}</option>`);
	}
	// team 드롭다운
	updateStaffTeam(staff.orgCd, staff.teamCd)
}

const updateStaffTeam = async (orgCd, teamCd) => {
	let { data: teams } = await axios.get(`/team/all?orgCd=${orgCd}`);
	$('#update-staff-team-code').empty();
	$('#update-staff-team-code').append('<option value="null">없음</option>');
	for (const team of teams) {
		let selected = teamCd == team.teamCd ? "selected" : "";
		$('#update-staff-team-code').append(`<option value="${team.teamCd}" ${selected}>${team.teamName}</option>`);
	}
}

const updateStaff = async () => {
	if (!emptyCheck('#update-staff-org-code', '부서 선택 후 실행해주세요')) return false;
	if (!emptyCheck('#update-staff-name', '직원 이름을 적어주세요')) return false;
	if (!emptyCheck('#update-staff-sort', '직원 순서를 적어주세요')) return false;
	if (!emptyCheck('#update-staff-status', '재직정보를 선택해주세요')) return false;

	let filename = {
		filename: $('#update-staff-filename').val(),
		originalFilename: $('#update-staff-origin-filename').val()
	}
	try {
		filename = await saveFile('#update-staff-file', 'staff');
	} catch { }
	let data = {
		orgCd: $('#update-staff-org-code').val(),
		teamCd: $('#update-staff-team-code').val() == 'null' ? null : $('#update-staff-team-code').val(),
		staffCd: $('#update-staff-code').val(),
		staffName: $('#update-staff-name').val(),
		staffPosition: $('#update-staff-position').val(),
		staffTelno: $('#update-staff-telno').val(),
		staffFaxno: $('#update-staff-faxno').val(),
		staffEmail: $('#update-staff-email').val(),
		staffSort: $('#update-staff-sort').val(),
		staffDuty: $('#update-staff-duty').val(),
		staffStatus: $('#update-staff-status').val(),
		staffPresent: $('#update-staff-present').val() == "true",
		staffFilename: filename.filename ?? $("#update-staff-filename").val(),
		staffOriginFilename: filename.originalFilename ?? $("#update-staff-origin-filename").val(),
	};

	await axios.put('/staff/update', data);
	getStaffs(currentOrgCd)
	$('#update-staff-modal').modal('hide');
}

const updateStaffVisible = async (event, staffCd) => {
	let data = {
		staffCd: staffCd,
		staffVisible: event.target.checked
	}
	await axios.put(`/staff/update/visible`, data)
}

const updateStaffSync = async (event, staffCd) => {
	let data = {
		staffCd: staffCd,
		staffSync: event.target.checked ? 'sync' : 'async'
	}
	await axios.put(`/staff/update/sync`, data)
}

const deleteStaff = async (staffCd) => {
	if(!confirm("삭제하시겠습니까?")) return;
	await axios.delete(`/staff/delete/${staffCd}`);
	getStaffs(currentOrgCd);
	successToast("삭제하였습니다");
}

const getStaffPaging = async (orgCd) => {
	let keyword = $("#search-staff-input").val();
	let searchType = $("#search-type").val() == "org";
	let params = {
		orgCd: searchType ? orgCd : "",
		teamCd: "",
		keyword: keyword,
		keywordType: "staffName",
		size: 10,
		page: currentStaffPage,
		sort: "",
		status: "",
	};

	getPaging("/staff/count", params, 10, currentStaffPage, "#staff-paging");
};

const movePage = (count) => {
	currentStaffPage = count;
	getStaffs(currentOrgCd);
	getStaffPaging(currentOrgCd)
}

const sortStaff = async () => {
	await axios.put(`/staff/sort?orgCd=${currentOrgCd}`)
	await getStaffs(currentOrgCd)
	successToast('정렬되었습니다.')
}