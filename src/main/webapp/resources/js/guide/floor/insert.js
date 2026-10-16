$(()=>{
	$("#floor-file").change(function() {
    handleFileChange(this, '#floor-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, null, true, {width: 1280, height: 650});
  });
	getBuilding();
});

const getBuilding = async () => {
	let {data : buildings} = await axios.get('/guide/building/all');
	let buildingId = Number(urlParams.get("buildingId"));
	$('select').empty();
	for(let building of buildings){
		$('select').append(`<option value="${building.buildingId}" ${building.buildingId == buildingId?'selected':''}>${building.buildingName}</option>`);
	}
}

const insertFloor = async ()=> {
	let buildingId = $('#building-id').val();
	let floorName = $('#floor-name').val();
	let floorSort = $("#floor-sort").val();
	
	if(!emptyCheck('#floor-name', '이름을 입력해주세요')) return false;
	if(!emptyCheck('#floor-filename', '이미지파일을 첨부해주세요')) return false;
	if(!emptyCheck('#building-id', '청사구분을 선택하세요')) return false;
	if(!emptyCheck('#floor-sort', '순서를 입력하세요')) return false;

	let filename = {
  	filename : null,
  	originalFilename : null
  }
	try{
  	filename = await saveFile('#floor-file', 'building');
  }catch{
  	errorToast('파일 저장에 실패하였습니다.');
  	return;
  }
  let data = {
		floorName : $('#floor-name').val(),
		floorSort : $('#floor-sort').val(),
    floorFilename : filename.filename,
    floorOriginFilename : filename.originalFilename,
    buildingId : $('#building-id').val()
  }
  await axios.post('/guide/floor/insert',data);
  $(location).attr('href', `/guide/main`);
}