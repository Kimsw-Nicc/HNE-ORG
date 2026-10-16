/* jshint esversion : 6 */
let floorId = -1;
let canvas
let isChanged = false;
$(() => {
	//
	init();

	$('#building-id').change(async () => {
		await getFloors();
		await changeFloor(floorId);
		changeGuide();
	})

	// insert 와 update 에서 team 관련 정보를 컨트롤 하는 내용
	$("#update-text-org-cd").change(() => {
		writeTeams('update')
	})

	$("#insert-text-org-cd").change(() => {
		writeTeams('insert')
	})

	$("#insert-text-check-all-team").click(function() {
		if ($("#insert-text-check-all-team").prop("checked")) { // 클릭되었으면
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			$(".insert-text-team").prop("checked", true);

		} else { // 클릭이 안되있으면
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			$(".insert-text-team").prop("checked", false);
		}
	})
	$("#update-text-check-all-team").click(function() {
		if ($("#update-text-check-all-team").prop("checked")) { // 클릭되었으면
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
			$(".update-text-team").prop("checked", true);
		} else { // 클릭이 안되있으면
			// input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
			$(".update-text-team").prop("checked", false);
		}
	})

	// floor file을 컨트롤 하는 내용
	$("#update-floor-file").change(function() {
		handleFileChange(this, '#update-floor-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, '#update-floor-origin-filename', true, { width: 1280, height: 650 });
	});
})

const init = async () => {
	await getBuildings();
	//  floorId = 
	await getFloors();
	let params = {
		buildingId: Number(urlParams.get("buildingId")),
		floorId: Number(urlParams.get("floorId")),
	}
	floorId = Number(urlParams.get("floorId"));
	setPushState(params);
	
	await getFloor();
	await getTexts();
	if(!canvas) return;
	await setOpenUpdateText();
	await movingEvent();
	await makeTitle();
	viewSaveText('none');
}
const changeFloor = async (id) => {
	if (id == -1) return;
	let params = {
		buildingId: $('#building-id').val(),
		floorId: id
	}
	floorId = id;
	setPushState(params);
	await getFloor();
	await getTexts();
	await setOpenUpdateText();
	await movingEvent();
	await makeTitle();
	viewSaveText('none');
}

const viewSaveText = (view) => {
	$('.save-text').css("display", view);
}

const changeGuide = () => {
	if (floorId === -1) {
		$('.guide').html(`<span class="bg-dark p-4 m-2 rounded text-white w-100"><i class="xi-arrow-up xi-2x mr-2"></i>
      청사를선택하세요. 안내도가 없습니다.
    </span>`)
	} else {
		$('.guide').html('<span class="text-lb mb-2">층별안내</span>')
	}
}

const getBuildings = async () => {
	let { data: buildings } = await axios.get('/guide/building/all');
	let buildingId = Number(urlParams.get("buildingId"));
	$('#building-id').empty()
	$('#building-id').append(`<option value="0">청사를 선택하세요.</option>`)
	for (const building of buildings) {
		$('#building-id').append(`<option value="${building.buildingId}" ${building.buildingId == buildingId ? 'selected' : ''}>${building.buildingName}</option>`)
	}
}

const getFloors = async () => {
	let params = {
		buildingId: $('#building-id').val()
	}
	setPushState(params);
	let floors;
	try {
		({ data: floors } = await axios.get(`/guide/floor/all`, { params }));
	} catch {
		return;
	}
	$('#floor-ids').empty();

	if (floors.length == 0) {
		await $('.tab-content').empty()
		floorId = -1
	}
	if (Number(urlParams.get("floorId")) == 0) {
		let flag = true;
		for (const floor of floors) {
			if (flag) {
				floorId = floor.floorId
			}
			$('#floor-ids').append(`<li class="nav-item">
          <a class="nav-link ${flag ? 'active' : ''}" data-toggle="tab" aria-expanded="false" onclick="changeFloor(${floor.floorId});">${floor.floorName}</a>
      </li>`)
			flag = false
		}
	} else {
		for (const floor of floors) {
			$('#floor-ids').append(`<li class="nav-item">
          <a class="nav-link ${floor.floorId == Number(urlParams.get("floorId")) ? 'active' : ''}" data-toggle="tab" aria-expanded="false" onclick="changeFloor(${floor.floorId});">${floor.floorName}</a>
      </li>`)
		}
	}
}

const getFloor = async () => {
	if (floorId == -1 || floorId == 0) return;
	let floorData = await axios.get(`/guide/floor/${floorId}`)
	let floor = floorData.data
	$('.tab-content').empty()
	$('.tab-content').append(`<div>
    <input type="hidden" id="floor-name" value="${floor.floorName}"/>
    <div class="form-group">
      <label>&bull; <b>${floor.floorName}</b> 마지막 수정일 : ${moment(floor.floorNdt).format('YYYY-MM-DD HH:mm:ss')} (표시순서 : ${floor.floorSort})
      </label>
      <div class="input-group d-flex justify-content-between">
          <button class="btn btn-sm btn-primary mr-2 " onclick="openUpdateFloor(${floor.floorId})">
              <i class="xi-pen mr-1"></i>청사안내도 ${floor.floorName} 수정
          </button>
          <button class="btn btn-sm btn-danger" onclick="deleteFloor()">
              <i class="xi-trash mr-1"></i>청사안내도 ${floor.floorName} 삭제
          </button>
      </div>
    </div>
    <div class="text-center bg-dark" style="width: 1280px;">
      <button class="btn btn-primary " style="margin-right: 10px" onclick="openInsertText()">
          <i class="xi-plus mr-1"></i>텍스트 추가
      </button>
      <button class="btn btn-info save-text" style="margin-left: 10px" onclick="saveText()">
          <i class="xi-cloud-upload-o mr-1"></i>텍스트 저장
      </button>
      <div style="background: url('/resources/upload/building/${floor.floorFilename}')">
          <canvas id="canvas" width="1280" height="650"></canvas>
      </div>
    </div>
  </div>`)
	canvas = new fabric.Canvas("canvas");
}

const deleteFloor = async () => {
	if (!confirm('삭제하시겠습니까?')) return;
	await axios.delete(`/guide/floor/delete/${floorId}`)
	floorId = -1
	await getFloors()
	await changeFloor(floorId)
	changeGuide()
}

// openUpdateFloor
const openUpdateFloor = async (floorId) => {
	let floorData = await axios.get(`/guide/floor/${floorId}`)
	let floor = floorData.data
	$('#update-floor-sort').val(floor.floorSort)
	$('#update-floor-filename').val(floor.floorFilename)
	$('#update-floor-origin-filename').val(floor.floorOriginFilename)
	$('#update-floor-file').val('')
	$('#update-floor-modal').modal('show')
}

const updateFloor = async () => {
	let filename = {
		filename: null,
		originalFilename: null
	}
	try {
		filename = await saveFile('#update-floor-file', 'building')
	} catch {
	}
	let data = {
		floorId: floorId,
		floorSort: $('#update-floor-sort').val(),
		floorFilename: filename.filename ?? $('#update-floor-filename').val(),
		floorOriginFilename: filename.originalFilename ?? $('#update-floor-origin-filename').val(),
	}
	await axios.put('/guide/floor/update', data)
	$('#update-floor-modal').modal('hide')
	await getFloor()
	await getTexts()
}

// updateTextModal
const setOpenUpdateText = async () => {
	canvas.on('mouse:dblclick', async (options) => {
		if (options.target != null) {
			for (let i = 1; i <= 60; i++) {
				$("#update-text-font-size").append(`<option value="${i}" ${i == 30 ? 'selected="selected"' : ''}>${i}</option>`)
			}
			await getOrgs('#update-text-org-cd')
			$("#update-text-org-cd").val(options.target.orgCd).prop("selected", true);
			$('#update-text-font-style').prop('checked', options.target.fontStyle == "oblique");
			$('#update-text-font-weight').prop('checked', options.target.fontFamily == "NanumSquareEB")
			$('#update-text-font-size').val(options.target.fontSize);
			$('#update-text-content').val(options.target.text.replace(/<br>/g, '\n'));
			//      $('#update-text-sort').val(options.target.textSort);

			if (options.target.selectable) {
				$("#update-text-modal").modal({
					show: true,
					backdrop: 'static',
					keyboard: false
				});
			}
			writeTeams("update").then(res => {
				if (res) {
					$('#update-text-teams input').prop('checked', false)
					let teamsData = options.target.teamCds.split(',')
					for (const team of teamsData) {
						$(`#update-text-team-${team}`).prop('checked', true)
					}
				}
			})



			canvas.renderAll();
		}
	});
}

const updateText = () => {
	if (!confirm('등록하시겠습니까?')) {
		return false;
	}
	if (!$("#update-text-content").val()) {
		return alert('텍스트를 입력해주세요');
	}
	isChanged = true;
	viewSaveText('inline')
	let teamsData = [];
	for (const val of $(".update-text-team:checked")) {
		teamsData.push(val.value);
	}
	let options = {
		// fabric 옵션
		lockScalingFlip: true,
		top: canvas.getActiveObject().top,
		left: canvas.getActiveObject().left,
		width: canvas.getActiveObject().width,
		height: canvas.getActiveObject().height,
		fill: "white",
		// fabric 옵션 (Itext Only)
		fontStyle: $('#update-text-font-style').is(":checked") ? 'oblique' : 'normal',
		fontFamily: $('#update-text-font-weight').is(":checked") ? 'NanumSquareEB' : 'NanumSquareB',
		fontSize: $('#update-text-font-size').val(),
		textAlign: 'center',
		// fabric 옵션 (Group Onoly)
		cornerStyle: 'circle',
		cornerSize: 10,
		cornerColor: 'white',
		lockRotation: true,
		// data 옵션
		text: $("#update-text-content").val(),
		orgCd: $('#update-text-org-cd').val(),
		teamCds: teamsData.toString(),
		//    textSort : $('#update-text-sort').val(),
		floorId: floorId,
		textType: "contents",
		buildingId: $('#building-id').val()
	}

	let textBox = new fabric.IText($("#update-text-content").val(), options)
	let group = new fabric.Group([textBox], options);

	group.on('scaling', () => {
		let newWidth = group.width * group.scaleX
		let newHeight = group.height * group.scaleY
		group.set({ width: newWidth, scaleX: 1 })
		group.set({ height: newHeight, scaleY: 1 })
		isChanged = true;
		viewSaveText('inline')
	})


	group.setControlsVisibility({
		mt: true,
		mb: true,
		ml: true,
		mr: true,
		bl: true,
		br: true,
		tl: true,
		tr: true,
		mtr: false,
	});

	canvas.add(group)
	canvas.remove(canvas.getActiveObject());
	$("#update-text-modal").modal('hide');
	// canvas.add(textBox)
	canvas.renderAll();
}

// insertTextModal
const openInsertText = async () => {
	$("#insert-text-modal").modal({
		show: true,
		backdrop: 'static',
		keyboard: false
	});
	$("#insert-text-font-size").empty()
	for (let i = 1; i <= 60; i++) {
		$("#insert-text-font-size").append(`<option value="${i}" ${i == 20 ? 'selected="selected"' : ''}>${i}</option>`)
	}
	getOrgs('#insert-text-org-cd')

	//  $("#insert-text-font-size").val(20)
	$("#insert-text-font-weight").prop('checked', true);
	$("#insert-text-font-style").prop('checked', false);
	$("#insert-text-content").val("")
	$("#insert-text-org-cd").val("")
	$("#insert-text-org-check").val("")
	$("#insert-text-teams").html('')
}

const insertText = async () => {
	if (!confirm('등록하시겠습니까?')) {
		return false;
	}
	if (!$("#insert-text-content").val()) {
		return alert('텍스트를 입력해주세요');
	}
	isChanged = true;
	viewSaveText('inline')
	let teamCds = ''
	let data = $('.insert-text-team:checked')
	data.each(function(index, element) {
		let value = $(element).val();
		teamCds += value + (index < data.length - 1 ? ',' : '');
	});
	let options = {
		// fabric 옵션
		lockScalingFlip: true,
		top: 30,
		left: 590,
		fill: "white",
		// fabric 옵션 (Itext Only)
		fontStyle: $('#insert-text-font-style').is(":checked") ? 'oblique' : 'normal',
		fontFamily: $('#insert-text-font-weight').is(":checked") ? 'NanumSquareEB' : 'NanumSquareB',
		fontSize: $('#insert-text-font-size').val(),
		textAlign: 'center',
		// fabric 옵션 (Group Onoly)
		cornerStyle: 'circle',
		cornerSize: 10,
		cornerColor: 'white',
		lockRotation: true,
		// data 옵션
		text: $("#insert-text-content").val(),
		orgCd: $('#insert-text-org-cd').val(),
		teamCds: teamCds,
		//    textSort : $('#insert-text-sort').val(),
		floorId: floorId,
		buildingId: $('#building-id').val(),
		textType: "contents",
	}

	let textBox = new fabric.IText($("#insert-text-content").val(), options)
	let group = new fabric.Group([textBox], options);

	group.on('scaling', () => {
		let newWidth = group.width * group.scaleX
		let newHeight = group.height * group.scaleY
		group.set({ width: newWidth, scaleX: 1 })
		group.set({ height: newHeight, scaleY: 1 })
		isChanged = true;
		viewSaveText('inline')
	})


	group.setControlsVisibility({
		mt: true,
		mb: true,
		ml: true,
		mr: true,
		bl: true,
		br: true,
		tl: true,
		tr: true,
		mtr: false,
	});

	$("#insert-text-modal").modal('hide');
	canvas.add(group)
	canvas.renderAll();
	setOpenUpdateText()
}

const movingEvent = async () => {
	canvas.on('object:moving', function() {
		isChanged = true;
		viewSaveText('inline')
	});
	canvas.renderAll();
}

const getTexts = async () => {
	let texts;
	try {
		({ data: texts } = await axios.get('/guide/text/all/' + floorId));
	} catch {
		return;
	}
	if (texts.length == 0) return;
	for (let text of texts) {
		let options = {
			// fabric 옵션
			lockScalingFlip: true,
			top: text.textTop,
			left: text.textLeft,
			width: text.textWidth,
			height: text.textHeight,
			fill: "white",
			// fabric 옵션 (Itext Only)
			fontStyle: text.textFontStyle,
			fontFamily: text.textFontFamily,
			fontSize: text.textFontSize,
			textAlign: 'center',
			// fabric 옵션 (Group Onoly)
			cornerStyle: 'circle',
			cornerSize: 10,
			cornerColor: 'white',
			lockRotation: true,
			// data 옵션
			text: text.textContent.replace(/<br>/g, '\n'),
			orgCd: text.orgCd,
			teamCds: text.teamCds,
			textSort: text.textSort,
			floorId: text.floorId,
			textType: "contents",
		}
		let textBox = new fabric.IText(text.textContent, options)
		let group = new fabric.Group([textBox], options);
		group.on('scaling', () => {
			let newWidth = group.width * group.scaleX
			let newHeight = group.height * group.scaleY
			group.set({ width: newWidth, scaleX: 1 })
			group.set({ height: newHeight, scaleY: 1 })
			isChanged = true;
			viewSaveText('inline')
		})


		group.setControlsVisibility({
			mt: true,
			mb: true,
			ml: true,
			mr: true,
			bl: true,
			br: true,
			tl: true,
			tr: true,
			mtr: false,
		});
		canvas.add(group)
		// canvas.add(textBox)
	}
	canvas.renderAll();

}

const deleteText = async () => {
	isChanged = true;
	viewSaveText('inline');
	canvas.remove(canvas.getActiveObject());
	$("#update-text-modal").modal('hide');

	canvas.renderAll()
}

const saveText = async () => {
	isChanged = false;
	viewSaveText('none')
	await axios.delete('/guide/text/delete/all/' + floorId)

	let params = []
	for (const object of canvas._objects) {
		let param
		if (object.textType == 'contents') {
			param = {
				textContent: object.text.replace(/\n/g, '<br>'),
				//        textSort : object.textSort,
				textFontSize: object.fontSize,
				textFontStyle: object.fontStyle,
				textFontFamily: object.fontFamily,
				textWidth: object.width,
				textHeight: object.height,
				textTop: object.top,
				textLeft: object.left,
				textBrX: object.aCoords.br.x,
				textBrY: object.aCoords.br.y,
				textTlX: object.aCoords.tl.x,
				textTlY: object.aCoords.tl.y,
				orgCd: object.orgCd,
				teamCds: object.teamCds,
				floorId: object.floorId,
				buildingId: object.buildingId
			}
			params.push(param)
		}
	}

	await axios.post('/guide/text/insert', params)

}

const writeTeams = async (type) => {
	$(`#${type}-text-teams`).html('')
	let orgCd = $(`#${type}-text-org-cd`).val()
	if (!orgCd) return false;
	let teamsData = await axios.get(`/team/all?orgCd=${orgCd}`)
	let teams = teamsData.data
	let teamDivs = ''
	for (const team of teams) {
		let teamDiv = `<div class="col-md-4">
      <div class="form-check form-check-inline">
        <input class="form-check-input ${type}-text-team" type="checkbox" id="${type}-text-team-${team.teamCd}" autocomplete="off" checked value="${team.teamCd}"></input>
        <label class=form-check-label for="${type}-text-team-${team.teamCd}">${team.teamName}</label>
      </div>
    </div>`
		teamDivs += teamDiv
	}
	$(`#${type}-text-teams`).html(teamDivs)
	return true
}




var result = '${msg}';
if (result == 'SUCCESS') {
	toastr.options = { "positionClass": "toast-top-center" }
	toastr["success"]("등록 되었습니다.");
}
if (result == 'SUCCESS_M') {
	toastr.options = { "positionClass": "toast-top-center" }
	toastr["success"]("수정 되었습니다.");
}
if (result == 'SUCCESS_D') {
	toastr.options = { "positionClass": "toast-top-center" }
	toastr["success"]("삭제 되었습니다.");
}

var fileSizeOK = true;
//------------------------------------------------------------------------------------------------------------------------------------
var text1 = new fabric.IText("", {
	fill: "white",
	top: 0,
	left: 0,
	fontFamily: "NanumSquareEB",
	textAlign: 'center',
	lockScalingFlip: true,
	select: false
});

var text2 = new fabric.IText("", {
	fill: "white",
	top: 0,
	left: 0,
	fontFamily: "NanumSquareB",
	textAlign: 'center',
	lockScalingFlip: true,
	select: false
});

var text3 = new fabric.IText("", {
	fill: "white",
	top: 0,
	left: 0,
	fontFamily: "NanumMyeongjoBold",
	textAlign: 'center',
	lockScalingFlip: true,
	select: false
});

//  canvas1.add(text1);
//  canvas1.add(text2);
//  canvas1.add(text3);

async function start() {

};

// 다른 층 클릭시
$(window).bind('beforeunload', function(e) {
	if (isChanged) {
		return '변경된 사항이 있습니다. 페이지에서 나가시겠습니까?';
	}
});
const makeTitle = async () => {
	if (floorId === -1) return;
	let titleText = await $("#floor-name").val();

	let title = await new fabric.IText(titleText, {
		fill: "#e2ba5a",
		top: 51,
		left: 56,
		fontSize: 55,
		fontFamily: "NanumMyeongjoBold",
		textAlign: 'center',
		textType: "title",
		selectable: false
	});
	await canvas.add(title);
}

// floor



