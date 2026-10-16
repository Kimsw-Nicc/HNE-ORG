/* jshint esversion : 6 */
let userId = $('#user-id').val();
let userRole = $('#user-role').val();
let currentPage = 0;
$(() => {
	userId = $('#user-id').val()
	userRole = $('#user-role').val()
	getUsers();

	$("#keyword").on("keyup",(key)=>{
		if(key.keyCode==13) {
			currentPage = 0;
			getUsers();
		}
	});
	$("#search-btn").click(()=>{
		currentPage = 0;
		getUsers();
	})
});

// 유저 생성
const getUsers = async () => {
  let keyword = $("#keyword").val()
  let pageSize = 10

  let params = {
		keyword : keyword,
		size : pageSize,
		page : currentPage
	}
  let {data : users} = await axios.get("/user/all", {params});
  
  $("tbody").empty()
  for(const user of users) {
    $("tbody").append(getUser(user))
  }
  getUserPaging();
}

// 비밀번호 확인창 열기
const openCheckPassword = () => {
	$('#check-password-modal').modal()
	$('#check-modal-password').val('')
}

// 비밀번호 확인. 성공시 유저 수정 페이지로 이동
const checkPassword = async () => {
	let userPassword = $('#check-modal-password').val();
	let params = {
		userId : userId,
		userPassword : userPassword
	}
	let {data : flag} = await axios.get("/user/check", {params});
	if(!flag){
		errorToast("비밀번호가 틀렸습니다.","#user-old-password");
		return;
	}
	
	$(location).attr('href', `/user/update?userId=${userId}`);
}

// 직원 얻기
const getUser = (user) => {
	if(user.userId === 'nicc') return;
  let userTr = ''
	if (!user.userLock) {
	  let updateButton = '';
	  let updatePasswordButton = '';

	  const isSelf = userId === user.userId;
	  const isSpecialAdmin = userId === 'hneorg' || userId === 'nicc';
	  const isAdminRole = user.userRole === 'ADMIN_ROLE';

	  if (isSelf) {
	    updateButton = createButton('', createXi('pen'), `openCheckPassword()`, 'btn-outline-primary');
	    updatePasswordButton = createA('', createXi('key'), `/user/updateOrgPassword?&userId=${user.userId}`, 'btn-outline-primary');
	  } else if (isSpecialAdmin || !isAdminRole) {
	    updateButton = createA('', createXi('pen'), `/user/update?userId=${user.userId}`, 'btn-outline-secondary');
	    updatePasswordButton = createA('', createXi('key'), `/user/updateOrgPassword?&userId=${user.userId}`, 'btn-outline-secondary');
	  }

	  userTr += createTd(updateButton, 'text-center');
	  userTr += createTd(updatePasswordButton, 'text-center');
	} else {
	  userTr += createTd('');
	  userTr += createTd('');
	}
  const unlockDiv = user.userLock?`<div class="input-group">
    <input id="lock-password-input-${user.userId}" type="password" class="form-control form-control-sm" placeholder="'${user.userId}' 계정ID의 재설정 비밀번호 입력"/>
    <span class="input-group-btn">
      ${createButton('', createXi('key')+'잠김해제', `updateUserLock('${user.userId}')`, 'btn-danger')}
    </span>
  </div>
  <div id="lock-password-msg-${user.userId}">영문, 숫자, 특수문자(!@#$%^&*+=-)를 조합한 8자 이상</div>`:'';
  userTr += createTd(`${user.userId} (${user.userName}) ${unlockDiv}`);
  // 권한
  const roleName = user.userRole == 'ADMIN_ROLE' ? '전체관리자'
			: user.userRole == 'MANAGER_ROLE' ? '부서관리자'
			: user.userRole == 'USER_ROLE' ? '부서관리자'
			: user.userRole == 'ORG_ROLE' ? '기구표관리자' : '';
  userTr += createTd(roleName, 'text-center');
  // 부서구분
	userTr += createTd(user.orgName?user.orgName:'', 'text-center');
  if(user.userId === 'hneorg' || user.userId === 'nicc'){
  	userTr += createTd('');
		userTr += createTd('');
  }else {
  	const deleteButton = createButton('', createXi('trash'), `deleteUser('${user.userId}')`, 'btn-outline-danger');
  	const resetButton = createButton('', createXi('redo'), `resetUserPassword('${user.userId}')`, 'btn-outline-warning');
		userTr += createTd(deleteButton, 'text-center');
		userTr += createTd(resetButton, 'text-center');
  }

  return `<tr>${userTr}</tr>`
}
// ## 유저 페이징
const getUserPaging = async () => {
  const params = {
		keyword : $("#keyword").val()
	}
  
  getPaging('/user/count',params,10,currentPage);
}
	 
const movePage = (page) => {
	currentPage = page
	getUsers()
	getUserPaging()
}

// # 비밀번호 일괄 초기화 파트
const openResetAllPassword = () => {
  $('#reset-all-password-modal').modal();
  $("#reset-password").val('')
  $("#reset-password-check").val('')
  $('#reset-passowrd-msg').html('')
  $('#reset-passowrd-check-msg').html('')
}

// 패스워드 일괄 초기화
const resetAllPassword = async () => {
	let resetPassword = $("#reset-password").val()
	let resetPasswordCheck = $("#reset-password-check").val()
	if($.trim(resetPassword) === ""){
		errorToast("변경 비밀번호를 입력해주세요")
		return;
	}
	if(resetPassword.length < 8){
		$('#reset-passowrd-msg').html('<p style="color:red">비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 8~16자까지, 영문은 대소문자를 구분합니다.</p>');
		return;
	}
	if(!resetPassword.match(/^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*]).+$/)){
		$('#reset-passowrd-msg').html('<p style="color:red">비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 8~16자까지, 영문은 대소문자를 구분합니다.</p>');
		return;
	}
	$('#reset-passowrd-msg').html('');
	
	if(resetPassword !== resetPasswordCheck){
		$('#reset-passowrd-check-msg').html('<p style="color:red">비밀번호가 일치하지 않습니다.</p>');
		return;
	}
	$('#reset-passowrd-check-msg').html('');
	
	let data = {
		userPassword : resetPassword
	}
	
	await axios.put(`/user/resetAll`, data)
	$('#reset-all-password-modal').modal('hide');
	successToast('초기화되었습니다.')
}

// 유저 삭제
const deleteUser = async (userId) => {
	if(!confirm('삭제하시겠습니까?')) return false;
	await axios.delete(`/user/delete/${userId}`)
	getUsers();
	successToast('삭제되었습니다.')
	if($('#user-id').val() == userId){
		$(location).attr('href', '/')
	}
}

// 패스워드 초기화
const resetUserPassword = async (userId) => {
	if(!confirm('초기화하시겠습니까?')) return false;
	await axios.put(`/user/reset?userId=${userId}`)
	successToast('초기화되었습니다.')
}

const updateUserLock = async (userId) => {
	let password = $(`#lock-password-input-${userId}`).val()
	let data = {
		userId : userId,
		userPassword : password,
		userFailcount : 0,
		userLock : false
	}
	await axios.put('/user/updatePassword', data);
	await axios.put('/user/updateLock', data);
	getUsers();
	successToast('잠금해제되었습니다.')
}