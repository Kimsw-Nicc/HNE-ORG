$(document).ready(function() {

	$.fn.datepicker.dates['kr'] = {
		days : [ "일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일" ],
		daysShort : [ "일", "월", "화", "수", "목", "금", "토", "일" ],
		daysMin : [ "일", "월", "화", "수", "목", "금", "토", "일" ],
		months : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		monthsShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	};

	// Notice Date Picker
	$('.datepicker').datepicker({
		format : "yyyy-mm-dd",
		autoclose : true,
		todayHighlight : true,
		language : "kr",
	});
  
	$('.start-date').on('change', function(e){
    let sDate = $(this).val()
    let eDate = $('.end-date').val()
    
    // end-date의 시작 가능한 날짜를 start-date로 설정
    $('.end-date').datepicker("setStartDate", sDate);
    
    // 만약 end-date가 start-date보다 과거이면 start-date로 설정
    if (eDate < sDate) {
        $('.end-date').datepicker("setDate", sDate); // end-date를 start-date로 업데이트
    }
});

});