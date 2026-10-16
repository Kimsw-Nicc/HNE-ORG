$(()=>{
  getTimes('#alert-start-time')
  getTimes('#alert-end-time')
  
  $('#alert-start-time').change(() => {
    const startTime = moment($('#alert-start-time').val(), 'HH:mm:ss');
    const endTime = moment($('#alert-end-time').val(), 'HH:mm:ss');

    $('#alert-end-time').val(startTime.format('HH:mm:ss'));
    // 종료 시간이 시작 시간보다 과거인 경우만 업데이트
    if (!startTime.isAfter(endTime)) {
    	$('#alert-end-time').val(endTime._i);
    }

    // 종료 시간 옵션을 시작 시간 이후로 업데이트
    getTimes('#alert-end-time', startTime);
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
  	
  	else{
  		
  	}
  })
})

const insertAlert = async () => {
	let alertCategory = $('.alert-category:checked').val();
	let alertType = $('.alert-type:checked').val();
	let alertStartDate = $('#alert-start-date').val();
	let alertEndDate = $('#alert-end-date').val();
	let alertStartTime = $('#alert-start-time').val();
	let alertEndTime = $('#alert-end-time').val();
	let alertContent = $('#alert-content').val();
	let alertVisible = $('.alert-visible:checked').val()
	
	if(!emptyCheck('#alert-start-date', '시작일을 입력해주세요')) return false;
	if(!emptyCheck('#alert-end-date', '종료일을 입력해주세요')) return false;
	if(!emptyCheck('#alert-start-time', '시작시간을 입력해주세요')) return false;
	if(!emptyCheck('#alert-end-time', '종료시간을 입력해주세요')) return false;
	if(!emptyCheck('#alert-content', '내용을 입력해주세요')) return false;
	
	
	let data = {
		alertCategory : alertCategory,
		alertType : alertType,
		alertStartDate : alertStartDate,
		alertEndDate : alertEndDate,
		alertStartTime : alertStartTime,
		alertEndTime : alertEndTime,
		alertContent : alertContent,
		alertVisible : alertVisible
	}
	await axios.post('/alert/insert',data)
	$(location).attr('href', '/alert/list')
}

const getTimes = async (selector, time) => {
  const selectedEndTime = moment($(selector).val(), 'HH:mm:ss'); // 현재 선택된 종료 시간

  $(selector).empty();  // 기존 옵션 제거
  const startTime = time ? time : moment().startOf('day'); // 시작 시간을 설정
  const endTime = moment().endOf('day');  // 하루의 마지막 시간을 설정

  // 30분 간격으로 옵션 추가
  for (let m = moment(startTime); m.isBefore(endTime); m.add(30, 'minutes')) {
      const option = document.createElement('option');
      option.value = m.format('HH:mm:ss');
      option.textContent = m.format('HH:mm:ss');
      $(selector).append(option);
  }

  // 기존에 선택된 종료 시간이 유효하면 유지
  if (selectedEndTime.isSameOrAfter(startTime)) {
      $(selector).val(selectedEndTime.format('HH:mm:ss'));
  } else {
      // 종료 시간이 시작 시간보다 과거면 시작 시간으로 설정
      $(selector).val(startTime.format('HH:mm:ss'));
  }
}