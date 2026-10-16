/* jshint esversion : 6 */
$(()=>{
  getTour()
  
  $("#tour-file").change(function() {
    handleFileChange(this, '#tour-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, '#tour-origin-filename', true);
  });
});

const getTour = async () => {
  let tourCd = urlParams.get('tourCd');
  let {data : tour} = await axios.get(`/tour/${tourCd}`);
  
  $("#tour-name").val(tour.tourName);
  $("#tour-filename").val(tour.tourFilename);
  $("#tour-origin-filename").val(tour.tourOriginFilename);
}

const updateTour = async () =>{
  let tourName = $('#tour-name').val();
  let orgCd = 'none';
  
  if(!emptyCheck('#tour-name', '내용을 입력해주세요')) return false;
  if(!emptyCheck('#tour-filename', '파일을 등록해주세요')) return false;
  
  let filename = {
    filename : null,
    originalFilename : null
  }
  try{
    filename = await saveFile('#tour-file', 'tour')
  }catch{
  }
  let data = {
    tourCd : urlParams.get('tourCd'),
    tourName : tourName,
    tourFilename : filename.filename??$('#tour-filename').val(),
    tourOriginFilename : filename.originalFilename??$('#tour-origin-filename').val(),
    orgCd : orgCd
  }

  await axios.put('/tour/update',data);
  $(location).attr('href', '/tour/list')
}