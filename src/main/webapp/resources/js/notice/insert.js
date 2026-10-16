/* jshint esversion : 6 */
const MAX_MESSAGE_BYTE = 2500;
$(()=>{
  let message = '';

  getOrgs('select')
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });

  // content 의 길이를 제한하기위한 내용
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
    handleFileChange(this, '#notice-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024);
  });
});

const insertNotice = async () => {
  let file = $('#notice-file')[0].files[0]
  
  let noticeName = $('#notice-name').val()
  let noticeContent = $('#notice-content').val()
  let noticeStartDate = $('#notice-start-date').val()
  let noticeEndDate = $('#notice-end-date').val()
  let noticeSort = $('#notice-sort').val()
  let orgCd = $('#notice-org').val()
  
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
  	filename = await saveFile('#notice-file', 'notice')
  }catch{
  }
  data = {
    noticeFilename : filename.filename,
    noticeOriginFilename : filename.originalFilename,
    noticeName : noticeName,
    noticeContent : noticeContent,
    noticeSort : noticeSort,
    noticeStartDate : noticeStartDate,
    noticeEndDate : noticeEndDate,
    orgCd : orgCd
  }

  await axios.post('/notice/insert',data);
  $(location).attr('href', '/notice/list')
}
