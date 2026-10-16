$(()=>{
  
	$("#tour-file").change(function() {
    handleFileChange(this, '#tour-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, null, true);
  });
});

const insertTour = async () => {
  let tourName = $('#tour-name').val()
  let orgCd = 'none'
  
  if(!emptyCheck('#tour-name', '내용을 입력해주세요')) return false;
  if(!emptyCheck('#tour-filename', '파일을 등록해주세요')) return false;

  let filename = {
  	filename : null,
  	originalFilename : null
  }
  try{
  	filename = await saveFile('#tour-file', 'tour')
  }catch{
  	errorToast('파일 저장에 실패하였습니다.')
  	return
  }
  let data = {
    tourName : tourName,
    tourFilename : filename.filename,
    tourOriginFilename : filename.originalFilename,
    orgCd : orgCd
  }
  
  await axios.post('/tour/insert',data);
  $(location).attr('href', '/tour/list')
}
