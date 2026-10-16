$(()=>{
  $("#background-file").change(function() {
    handleFileChange(this, '#background-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, null, true);
  });
});

const insertBackground = async () => {
  let backgroundName = $('#background-name').val();
  let backgroundSort = $('#background-sort').val();
  let orgCd = $("#background-org").val();
  
  if(!emptyCheck('#background-name', '이름을 입력해주세요')) return false;
  if(!emptyCheck('#background-sort', '순서를 입력해주세요')) return false;
  if(!emptyCheck('#background-filename', '파일을 등록해주세요')) return false;
  
  let filename = {
  	filename : null,
  	originalFilename : null
  }
  
  try{
  	filename = await saveFile('#background-file', 'backGround');
  }catch{
  	errorToast('파일 저장에 실패하였습니다.');
  	return;
  }
  
  data = {
    backgroundName : backgroundName,
    backgroundSort : backgroundSort, 
    backgroundOriginFilename : filename.originalFilename,
    backgroundFilename : filename.filename,
  }
  await axios.post('/background/insert',data);
  $(location).attr('href', '/background/list');
}