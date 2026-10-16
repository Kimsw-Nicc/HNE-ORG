const MAX_MESSAGE_BYTE = 2500;
$(()=>{
  let message = '';
  
  getNotice();
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });
  
  $('#max-count').text(MAX_MESSAGE_BYTE)

//content 의 길이를 제한하기위한 내용
  $('#notice-content').keyup((event)=>{
    let totalByte = new TextEncoder().encode(event.target.value).length
    if (totalByte <= MAX_MESSAGE_BYTE) {
      $('#count').text(totalByte)
      message = event.target.value;
    } else {
      alert(`${MAX_MESSAGE_BYTE}바이트까지 전송가능합니다.`);
      $('#count').text(totalByte)
      event.target.value = message;
    }
  })
  // 이미지 추가시 정합성 검사
  $("#notice-file").change(function() {
    handleFileChange(this, '#notice-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, '#notice-origin-filename');
  });
});

const getNotice = async () => {
  await getOrgs('select');
  let noticeCd = urlParams.get('noticeCd');
  let {data : notice} = await axios.get(`/notice/${noticeCd}`);
  
  $('select').val(notice.orgCd??'none').trigger('change');
  $('#notice-org').val(notice.orgCd??'none')
  $('#notice-org-name').val(notice.orgFullname??'전체')
  $("#notice-name").val(notice.noticeName)
  $("#notice-content").html(notice.noticeContent)
  $("#notice-filename").val(notice.noticeFilename)
  $("#notice-origin-filename").val(notice.noticeOriginFilename)
  $("#notice-start-date").datepicker('setDate',moment(notice.noticeStartDate).format('YYYY-MM-DD'))
  $("#notice-end-date").datepicker('setDate',moment(notice.noticeEndDate).format('YYYY-MM-DD'))
  $("#notice-sort").val(notice.noticeSort)
}

const updateNotice = async () => {
  let noticeName = $('#notice-name').val();
  let noticeContent = $('#notice-content').val();
  let noticeStartDate = $('#notice-start-date').val();
  let noticeEndDate = $('#notice-end-date').val();
  let noticeSort = $('#notice-sort').val();
  let orgCd = $('#notice-org').val();
  
  if(!emptyCheck('#notice-name', '제목을 입력해주세요')) return false;
  if(!emptyCheck('#notice-content', '내용을 입력해주세요')) return false;
  if(!emptyCheck('#notice-start-date', '공지 시작일을 입력해주세요')) return false;
  if(!emptyCheck('#notice-end-date', '공지 종료일을 입력해주세요')) return false;
  if(!emptyCheck('#notice-sort', '순서를 입력해주세요')) return false;
  
  let filename = {
    filename : null,
    originalFilename : null
  }
  
  try{
    filename = await saveFile('#notice-file', 'notice');
  }catch{
  }
  
  let data = {
    noticeCd : urlParams.get('noticeCd'),
    noticeFilename : filename.filename??$('#notice-filename').val(),
    noticeOriginFilename : filename.originalFilename??$('#notice-origin-filename').val(),
    noticeName : noticeName,
    noticeSort : noticeSort,
    noticeContent : noticeContent,
    noticeStartDate : noticeStartDate,
    noticeEndDate : noticeEndDate,
    orgCd : orgCd
  }
  await axios.put('/notice/update',data);
  $(location).attr('href', document.referrer);
}
