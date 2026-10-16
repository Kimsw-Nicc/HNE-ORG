let backgroundCd = urlParams.get('backgroundCd');
$(()=>{
  getBackground();
  
  $('#update').click(()=>{
    $(location).attr('href', `/background/update?backgroundCd=${backgroundCd}`);
  })
})

const getBackground = async () => {
  let {data : background} = await axios.get(`/background/${backgroundCd}`);
  
  $('h3').html(`${background.backgroundName}`);
  $('#background-file').attr("src", `/resources/upload/backGround/${background.backgroundFilename}`);
}

const deleteBackground = async () => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
    backgroundCd : urlParams.get('backgroundCd')
  }
  await axios.delete(`/background/delete`, {params});
  $(location).attr('href', '/background/list');
}