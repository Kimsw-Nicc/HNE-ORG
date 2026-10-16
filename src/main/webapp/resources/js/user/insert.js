let idCheckFlag = false;
$(() => {
  getOrgs('select');
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });
  
  $('select').change(()=>{
  	let orgCd = $('select').val()
  	if(orgCd == 'none'){
  		$('input[name="user-role"]').filter('[value="ADMIN_ROLE"]').prop('checked', true);
  	}else{
  		$('input[name="user-role"]').filter('[value="MANAGER_ROLE"]').prop('checked', true);
  	}
  })

  $("#user-name").focusout(()=>{
    let id = $("#user-name").val()
    $("#user-id").val(inko.ko2en(id))
  })

  $("#user-id-check").click(()=>{
  	duplCheck()
  });
});
 

const duplCheck = async () => {
	let userId = $("#user-id").val()
	const reg = /^[a-zA-Z0-9]+$/;
  if (userId == "") {
    $('#user-id-msg').html('<p style="color:red">계정ID를 입력해주세요.</p>');
    return;
  }
  if (userId.length < 4) {
    $('#user-id-msg').html('<p style="color:red">계정ID는 4자리 이상 입력하세요.</p>');
    return;
  }
  if(!reg.test(userId)){
		$('#user-id-msg').html('<p style="color:red">계정ID는 영어와 숫자만 입력해주세요.</p>');
    return;
	}
  
  let {data : flag} = await axios.get(`/user/duplCheck?userId=${userId}`);
  idCheckFlag = flag;
  if (flag) {
    $('#user-id-msg').html(`<span class="small"><p style="color:blue">${userId}는 사용할 수 있습니다.</p></span>`);
	} else {
    $('#user-id-msg').html('<span class="small"><p style="color:red">이미 존재하는 ID 입니다.</p></span>');
	}
}

const insertUser = async () => {
	let userId = $("#user-id").val()
	let userName = $("#user-name").val()
	let userRole = $("input[name='user-role']:checked").val()
	let userPassword = $("#user-password").val()
	let userCheckPassword = $("#user-check-password").val()
	let orgCd = $('select').val()
	
	if(!emptyCheck('#user-id', '계정ID를 입력하세요.')) return false;
	if(!emptyCheck('#user-name', '이름을 입력하세요.')) return false;
	if(!emptyCheck('#user-password', '비밀번호를 입력하세요.')) return false;
  if (!idCheckFlag) {
    errorToast("ID 중복체크를 확인해 주시기 바랍니다.");
    return;
  }
  if (userPassword.length < 8) {
    $('#user-password-msg').html('<span class="small"><p style="color:red">비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 8~16자까지, 영문은 대소문자를 구분합니다.</p></span>');
    return;
  }
  if (!userPassword.match(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*]).+$/)) {
    $('#user-password-msg').html('<span class="small"><p style="color:red">비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 8~16자까지, 영문은 대소문자를 구분합니다.</p></span>');
    return;
  }
  if (userPassword != userCheckPassword) {
    $('#user-check-password-msg').html('<span class="small"><p style="color:red">비밀번호가 일치하지 않습니다.</p></span>');
    return;
  }
  
  let data = {
  	userId: userId,
  	userName: userName,
  	userRole: userRole,
  	userPassword: userPassword,
  	orgCd: orgCd
  }
  try{
  	await axios.post('/user/insert', data)
  }catch (e) {
    errorToast("예기치 않은 오류 발생, 관리자에게 문의 바랍니다.");
	}
  $(location).attr('href', '/user/list')
}