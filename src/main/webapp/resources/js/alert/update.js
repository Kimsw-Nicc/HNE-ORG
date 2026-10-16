/* jshint esversion : 6 */
$(()=>{
  getTimes('#alert-start-time')
  getTimes('#alert-end-time')
  
  $('#alert-start-time').change(() => {
    const startTime = moment($('#alert-start-time').val(), 'HH:mm:ss');
    const endTime = moment($('#alert-end-time').val(), 'HH:mm:ss');

    // 시작 시간이 종료 시간보다 과거인 경우
    getTimes('#alert-end-time', startTime);
    if (!startTime.isAfter(endTime)) {
    	$('#alert-end-time').val(endTime._i);
    }
  });
  $("input[name='category']").change((event)=>{
  	// 알리미로 바뀌면
  	if($(event.target).val() == "alert"){
  		$('.type-group').removeClass('d-none')
  	}
  	// 메시지로 바뀌면
  	else if($(event.target).val() == "message"){
  		$('.type-group').addClass('d-none')
  	}
  })
  
  getAlert()
})

const getAlert = async () => {
	let alertCd = urlParams.get('alertCd');
	let {data : alert} = await axios.get(`/alert/${alertCd}`);
	
	if(alert.alertCategory == "alert") {
		$('.type-group').removeClass('d-none');
	}else if(alert.alertCategory == "message"){
		$('.type-group').addClass('d-none');
	}
	
	$(`#category-${alert.alertCategory}`).prop('checked', true);
	$(`#type-${alert.alertType}`).prop('checked', true);
	$('#alert-start-date').datepicker('setDate',moment(alert.alertStartDate).format('YYYY-MM-DD'));
	$('#alert-end-date').datepicker('setDate',moment(alert.alertEndDate).format('YYYY-MM-DD'));
	$('#alert-start-time').val(alert.alertStartTime);
	$('#alert-end-time').val(alert.alertEndTime);
	$('#alert-content').val(alert.alertContent);
	$(`#visible-${alert.alertVisible}`).prop('checked',true);
}

const updateAlert = async () => {
	let alertCategory = $('.alert-category:checked').val();
	let alertType = $('.alert-type:checked').val();
	let alertStartDate = $('#alert-start-date').val();
	let alertEndDate = $('#alert-end-date').val();
	let alertStartTime = $('#alert-start-time').val();
	let alertEndTime = $('#alert-end-time').val();
	let alertContent = $('#alert-content').val();
	let alertVisible = $('.alert-visible:checked').val()
	// 시작일
	if(!emptyCheck('#alert-start-date', '시작일을 입력해주세요')) return false;
	if(!emptyCheck('#alert-end-date', '종료일을 입력해주세요')) return false;
	if(!emptyCheck('#alert-start-time', '시작시간을 입력해주세요')) return false;
	if(!emptyCheck('#alert-end-time', '종료시간을 입력해주세요')) return false;
	if(!emptyCheck('#alert-content', '내용을 입력해주세요')) return false;
	
	let data = {
		alertCd : urlParams.get('alertCd'),
		alertCategory : alertCategory,
		alertType : alertType,
		alertStartDate : alertStartDate,
		alertEndDate : alertEndDate,
		alertStartTime : alertStartTime,
		alertEndTime : alertEndTime,
		alertContent : alertContent,
		alertVisible : alertVisible
	}
	await axios.put('/alert/update',data)
	$(location).attr('href', '/alert/list')
}

const getTimes = async (selector, time) => {
	$(selector).empty()
	const startTime = time?time:moment().startOf('day'); // 하루의 시작 시간을 설정 (00:00:00)
  const endTime = moment().endOf('day'); // 하루의 마지막 시간을 설정 (23:59:59)

  for (let m = moment(startTime); m.isBefore(endTime); m.add(30, 'minutes')) {
    // 새로운 option 요소를 생성
    const option = document.createElement('option');
    option.value = m.format('HH:mm:ss');
    option.textContent = m.format('HH:mm:ss');
    
    // 생성된 option을 select 요소에 추가
    $(selector).append(option);
  }
}