/* jshint esversion : 6 */
$(() => {
    getUser()
});

const getUser = async () => {
    let {data : user} = await axios.get(`/user/${urlParams.get('userId')}`);
    if (user.userLock) {
        alert("잠긴 계정입니다.");
        location.href = "/user/list";
    }
    $('#user-id').val(user.userId);
}

const updatePassword = async () => {
    let userId = $("#user-id").val();
    let userPassword = $("#user-password").val();
    let userCheckPassword = $("#user-check-password").val();
    let userOldPassword = $("#user-old-password").val();
    let url = `/user/check?userId=${userId}&userPassword=${userOldPassword}`;
    url = url.replaceAll('#', '%23');
    let {data : flag} = await axios.get(url);
    if(!flag){
        errorToast("기존 비밀번호가 틀렸습니다.","#user-old-password");
        return;
    }
  if ($.trim(userPassword) == "") {
    errorToast("비밀번호를 입력하세요.",'#user-password');
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
    userPassword: userPassword,
  }
  await axios.put('/user/updatePassword', data);
  $(location).attr('href', '/dashboard/main');
}