let userCd;
$(() => {
  getUser();
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });
  
  $('select').change(()=>{
  	let orgCd = $('select').val();
  	if(orgCd == 'none'){
  		$('input[name="user-role"]').filter('[value="ADMIN_ROLE"]').prop('checked', true);
  	}else{
  		$('input[name="user-role"]').filter('[value="MANAGER_ROLE"]').prop('checked', true);
  	}
  })

  
});
 
const getUser = async () => {
	await getOrgs('select');
	let {data : user} = await axios.get(`/user/${urlParams.get('userId')}`)
	if (user.userLock) {
		alert("잠긴 계정입니다.");
		location.href = "/user/list";
	}
	userCd = user.userCd
	$('select').val(user.orgCd??'none').trigger('change');
	$('input[name="user-role"]').filter(`[value="${user.userRole}"]`).prop('checked', true);
	$('#user-name').val(user.userName);
	$('#user-id').val(user.userId);
}

const updateUser = async () => {
	let userId = $("#user-id").val();
	let userName = $("#user-name").val();
	let userRole = $("input[name='user-role']:checked").val();
	let orgCd = $('select').val();
	
	if(!emptyCheck('#user-name', '계정ID를 입력하세요.')) return false;
	
	let data = {
		userCd : userCd,
		userId: userId,
		userName: userName,
		userRole: userRole,
		orgCd: orgCd??''
  }
  await axios.put('/user/update', data);
  $(location).attr('href', '/user/list');
}