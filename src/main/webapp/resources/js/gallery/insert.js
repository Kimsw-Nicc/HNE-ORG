$(()=>{
  getOrgs('select')
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });

  // 이미지 추가시 정합성 검사
  $("#gallery-file").change(function() {
    handleFileChange(this, '#gallery-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024);
  });
});

const insertGallery = async () => {
  let galleryName = $('#gallery-name').val();
  let gallerySort = $('#gallery-sort').val();
  let orgCd = $("#gallery-org").val();
  
  if(!emptyCheck('#gallery-name', '이름을 입력해주세요')) return false;
  if(!emptyCheck('#gallery-sort', '순서를 입력해주세요')) return false;
  if(!emptyCheck('#gallery-filename', '파일을 등록해주세요')) return false;
  
  let filename = {
  	filename : null,
  	originalFilename : null
  }
  
  try{
  	filename = await saveFile('#gallery-file', 'gallery')
  }catch{
  	errorToast('파일 저장에 실패하였습니다.')
  	return
  }
  
  let data = {
    galleryName : galleryName,
    gallerySort : gallerySort,
    galleryOriginFilename : filename.originalFilename,
    galleryFilename : filename.filename,
    orgCd : orgCd
  }
  await axios.post('/gallery/insert',data);
  $(location).attr('href', '/gallery/list');
}