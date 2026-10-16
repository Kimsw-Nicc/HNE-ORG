$(()=>{
	getBackground();
  $("#background-file").change(function() {
    handleFileChange(
      this,
      '#background-filename', 
      ["jpg", "png"], 
      '#upload-msg', 
      5 * 1024 * 1024, 
      '#background-origin-filename',
      true, 
    );
  });
});

const getBackground = async () => {
  let backgroundCd = urlParams.get('backgroundCd');
  let {data : background} = await axios.get(`/background/${backgroundCd}`);
  
  $("#background-name").val(background.backgroundName);
  $("#background-sort").val(background.backgroundSort);
  $("#background-filename").val(background.backgroundFilename);
  $("#background-origin-filename").val(background.backgroundOriginFilename);
}

const updateBackground = async () => {
  let backgroundName = $('#background-name').val();;
  let backgroundSort = $('#background-sort').val();
  
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
  }
  
  let data = {
    backgroundCd : urlParams.get('backgroundCd'),
    backgroundName : backgroundName,
    backgroundSort : backgroundSort,
    backgroundOriginFilename : filename.originalFilename??$('#background-origin-filename').val(),
    backgroundFilename : filename.filename??$('#background-filename').val(),
  }
  await axios.put('/background/update',data);
  $(location).attr('href', '/background/list');
}