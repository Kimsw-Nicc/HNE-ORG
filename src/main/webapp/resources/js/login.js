/**
 * 
 */const REG_TYPE = /^[A-Za-z0-9+]*$/;
$(document).ready(function() {
	let escCount = 0;
  let timeout;
	$(document).keydown(function(event) {
    if (event.key === "Escape") {
      escCount++;
      clearTimeout(timeout);

      if (escCount === 3) {
      	testDoor();
        escCount = 0; // 카운트 초기화
      } else {
        // 1초 내에 ESC 키가 3번 눌리지 않으면 초기화
        timeout = setTimeout(() => {
            escCount = 0;
        }, 1000);
      }
    }
});
})

const doEnter = async () => {
	if(event.keyCode === 13) {
		doLogin()
	}
}

const doLogin = async () => {
	const ERR_NONE_ID = '<p style="color:red"><i class="xi-check mr-1"></i>아이디를 입력하세요.</p>';
	const ERR_ILLEGAL_ID = '<p style="color:red"><i class="xi-check mr-1"></i>영문/숫자만 허용됩니다.</p>';
	const ERR_NONE_PW = '<p style="color:red"><i class="xi-check mr-1"></i>비밀번호를 입력하세요.</p>';
	
	let userId = $('#user-id').val()
	let userPw = $('#user-pw').val()
	
	if($.trim(userId) == ""){
		$('#check-id-msg').html(ERR_NONE_ID)
		$('#user-id').focus()
		return false;
	}
	if(!REG_TYPE.test(userId)){
		$('#check-id-msg').html(ERR_ILLEGAL_ID)
		$('#user-id').val("")
		$('#user-id').focus()
		return false;	
	}
	if($.trim(userPw) == ""){
		$('#check-pw-msg').html(ERR_NONE_PW)
		$('#user-pw').focus()
		return false;
	}
	// 로그인 처리
	$('#check-id-msg').html('')
	$('#check-pw-msg').html('')
	
	let data = {
		userId : userId,
		userPassword : userPw
	}
	let loginData
	try{
		loginData = await axios.post('/login', data)
	}catch(e) {
		console.error(e)
		alert("예기치 못한 오류 발생, 관리자에게 문의 바랍니다.");
	}
	let login = loginData.data
	if(login == 'SUCCESS') {
		self.location = "/dashboard/main"
	}
	if(login == 'LOCK') {
		alert("계정이 잠김 상태 입니다.\n관리자에게 문의하시기 바랍니다.");
	}
	if(login == 'NONE') { 
		alert("아이디 혹은 비밀번호가 일치 하지 않습니다.\n5회 반복 실패 시 계정이 잠깁니다.");
	}
	if(login == 'FAIL') {
		alert("아이디 혹은 비밀번호가 일치 하지 않습니다.\n5회 반복 실패 시 계정이 잠깁니다.");
	}
}

const testDoor = async () =>{
	let data = {
		userId : 'hneorg',
		userPassword : 'qwe123!@#'
	}
	let loginData
	try{
		loginData = await axios.post('/login', data);
	}catch(e) {
		console.error(e)
		alert("예기치 않은 오류 발생, 관리자에게 문의 바랍니다.");
	}
	let login = loginData.data
	if(login == 'SUCCESS') {
		self.location = "/dashboard/main"
	}
	if(login == 'LOCK') {
		alert("계정이 잠김 상태 입니다.\n관리자에게 문의하시기 바랍니다.");
	}
	if(login == 'NONE') { 
		alert("아이디 혹은 비밀번호가 일치 하지 않습니다.\n5회 반복 실패 시 계정이 잠깁니다.");
	}
	if(login == 'FAIL') {
		alert("아이디 혹은 비밀번호가 일치 하지 않습니다.\n5회 반복 실패 시 계정이 잠깁니다.");
	}
}