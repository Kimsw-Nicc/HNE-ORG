/* jshint esversion : 6 */
let currentPage = 0;
$(()=>{
  currentPage = Number(urlParams.get("page"));
  $('#search-input').val(urlParams.get("keyword") ?? '');
  getAlerts();
  getAlertPaging();
  $('#search-input').on("keyup",(key)=>{
    if(key.keyCode==13) {
      currentPage = 0;
      getAlerts();
      getAlertPaging();
    }
  });
  $('#search-btn').click(()=>{
    currentPage = 0;
    getAlerts();
    getAlertPaging();
  })
});
const getAlerts = async () => {
  let keyword = $('#search-input').val();
  let pageSize = 10;
  let params = {
    size : pageSize,
    page : currentPage,
    keyword : keyword
  }
  setPushState(params);
  let {data : alerts} = await axios.get(`/alert/all`,{params})
  $("tbody").empty();
  for(const alert of alerts){
    $("tbody").append(getAlertTr(alert));
  }
}

const getAlertTr = (alert) => {
  let alertTr = '';
  // 수정
  let updateA = createA('', `${createXi('pen')}수정`, `/alert/update?alertCd=${alert.alertCd}`, 'btn-outline-secondary');
  alertTr += createTd(updateA, 'text-center align-middle');
  // 카테고리
  let category = alert.alertCategory == 'alert' ? '<span class="badge badge-primary">알리미</span>' :
    alert.alertCategory == 'lunch' ? '<span class="badge badge-warning">점심안내</span>' :
    alert.alertCategory == 'message' ? '<span class="badge badge-info">메시지</span>' : '';
  alertTr += createTd(category, 'text-center align-middle');
  // 타입
  let type = alert.alertCategory != 'alert' ? '<span class="badge badge-info">-</span>' :
    alert.alertType == 'info' ? '<span class="badge badge-primary">낮음</span>' :
    alert.alertType == 'warning' ? '<span class="badge badge-warning">중간</span>' :
    alert.alertType == 'ciritical' ? '<span class="badge badge-info">높음</span>' : '';
  alertTr += createTd(type, 'text-center align-middle');
  // 컨텐츠
  let content = alert.alertCategory == 'lunch' ? '<span class="bg-light">※ 점심안내 내용이 표시됩니다.</span>' : 
    alert.alertContent;
  alertTr += createTd(content, 'text-center align-middle');
  // 표시여부
  let visible = alert.alertVisible?'<span class="text-success text-lb">ON</span>':'<span class="text-danger text-lb">OFF</span>';
  alertTr += createTd(visible, 'text-center align-middle');
  // 기간
  let period =  `${moment(alert.alertStartDate).format('YYYY-MM-DD')} ~ ${moment(alert.alertEndDate).format('YYYY-MM-DD')}<br>
    ${alert.alertStartTime} ~ ${alert.alertEndTime}`;
  alertTr += createTd(period, 'text-center align-middle');
  // 수정일
  let ndt =  `${moment(alert.alertNdt).format('YYYY-MM-DD HH:mm:ss')}`;
  alertTr += createTd(ndt, 'text-center align-middle');
  // 삭제버튼
  let deleteButton = createButton('', createXi('trash'), `openDeleteAlert(${alert.alertCd})`, 'btn-outline-danger');
  alertTr += createTd(deleteButton, 'text-center align-middle');
  
  return `<tr>${alertTr}</tr>`;
}

const getAlertPaging = async () => {
  const params = {
    keyword : $('#search-input').val()
  }
  await getPaging('/alert/count', params, 10, currentPage);
}

const movePage = (page) => {
  currentPage = page
  getAlerts();
  getAlertPaging();
}

const openDeleteAlert = async (alertCd) => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
      alertCd : alertCd
  }
  await axios.delete(`/alert/delete`,{params});
  getAlerts();
  getAlertPaging();
}