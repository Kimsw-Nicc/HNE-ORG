$(()=>{
  getOrgs('select')
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });
  
  $("#movie-file").change(function() {
    handleFileChange(this, '#movie-filename', ["mp4", "png", "jpg"], '#upload-msg', 350 * 1024 * 1024);
  });
});

const insertMovie = async () => {
	let movieName = $('#movie-name').val();
	let movieSort = $('#movie-sort').val();
	let orgCd = $('#movie-org').val();
	
	if(!emptyCheck('#movie-filename', '파일을 등록해주세요.')) return false;
	if(!emptyCheck('#movie-name', '이름을 입력해주세요.')) return false;
	
	await openProcessModal('파일 업로드 중...')
	
	let filename = {
  	filename : null,
  	originalFilename : null,
  	thumbnail : null
  }
  
  try{
  	filename = await saveFile('#movie-file', 'movie')
  }catch(e){
  	if(e.response.status == 500){
  		// jcodec 관련 에러 발생
  		errorToast('encoding 문제로 파일 저장에 실패하였습니다. 관리자에 문의 바랍니다.')
    	await closeProcessModal()
    	return
  	}else if(e.response.status == 400){
  		errorToast('파일 저장에 실패하였습니다.')
  		await closeProcessModal()
  		return
  	}
  }
  
	
  data = {
		movieName : movieName,
		movieOriginFilename : filename.originalFilename,
    movieFilename : filename.filename,
    movieThumbnail : filename.thumbnail,
    movieSort : movieSort,
    orgCd : orgCd
  }
  try{
  	await axios.post('/movie/insert',data);
  }catch{
  	errorToast('파일 등록에 실패하였습니다.')
  	await closeProcessModal()
  	return;
  }
  $(location).attr('href', '/movie/list')
	
}