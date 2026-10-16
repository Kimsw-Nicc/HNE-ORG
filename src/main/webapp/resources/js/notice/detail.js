/* jshint esversion : 6 */
$(()=>{
  getNotice()
  
})

const getNotice = async () => {
  let loginOrgCd = $('#login-org-cd').val();
  let noticeCd = urlParams.get('noticeCd');
  let {data : notice} = await axios.get(`/notice/${noticeCd}`);
  
  $('#notice-org').html(`${notice.orgName??'전체'} `);
  $('#notice-name').html(`${notice.noticeName}`);
  $('#notice-content').html(`${notice.noticeContent}`);
  $('#notice-sort').html(`${notice.noticeSort}`);
  $('#notice-date').html(`${moment(notice.noticeStartDate).format('YYYY-MM-DD')} ~ ${moment(notice.noticeEndDate).format('YYYY-MM-DD')}`);
  $('#notice-file').attr("src", `/resources/upload/notice/${notice.noticeFilename}`);
  
  if(loginOrgCd === '' || loginOrgCd === notice.org_cd){
    $('#notice-buttons').prepend(`<a class="btn btn-primary" href="/notice/update?noticeCd=${notice.noticeCd}"><i class="xi-pen mr-1">수정</i></a>
    <button class="btn btn-danger" onclick="openDeleteNotice(${noticeCd})"><i class="xi-trash mr-1">삭제</i></button>`);
  }
}

const openDeleteNotice = async (noticeCd) => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
    noticeCd : urlParams.get('noticeCd')
  }
  await axios.delete(`/notice/delete`, {params});
  $(location).attr('href', '/notice/list');
}