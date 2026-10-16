/* jshint esversion : 6 */
$(()=>{
  $("#ip-used").change(()=>changeIpCheck())
  getAcls()
})

const changeIpCheck = async () => {
	let isCheckedData = await axios.get("/toggle/ipCheck")
	let isChecked = isCheckedData.data
	if(isChecked){
		if(!confirm('접근 제한하시겠습니까?')) {
			$('#ip-used').val(isChecked?1:0)
			return;
		}
	}else{
		if(!confirm('접근 허용하시겠습니까?')) {
			$('#ip-used').val(isChecked?1:0)
			return;
		}
	}
	
	isCheckedData = await axios.put("/toggle/ipCheck")
  isChecked = isCheckedData.data
  if(isChecked){
  	successToast("접근 허용합니다.")
  }else{
  	warningToast("접근 제한합니다.")
  }
}

const getIpCheck = async () => {
	let isCheckedData = await axios.get("/toggle/ipCheck")
	let isChecked = isCheckedData.data
	$('#ip-used').val(isChecked?1:0)
}

const getAcls = async () => {
	let aclsData = await axios.get("/acl/all");
	let acls = aclsData.data
	
	$('tbody').empty()
	for(let acl of acls){
		$('tbody').append(getAclTr(acl))
	}
}

const getAclTr = (acl) => {
	let aclTr = `<tr>
		<td style="text-align: center">${acl.aclIp}</td>
		<td style="text-align: center">${acl.aclName}</td>
		<td style="text-align: center">${getAclType(acl.aclType)}</td>
		<td style="text-align: center">
			<button class="btn btn-sm btn-outline-primary" onclick="openUpdateAcl('${acl.aclId}')">
        <i class="xi-pen mr-1"></i>수정
      </button>
		</td>
		<td style="text-align: center;">
      <button class="btn btn-sm btn-outline-danger" onclick="deleteAcl('${acl.aclId}')">
        <i class="xi-trash mr-1"></i>삭제
      </button>
    </td>
	</tr>`
	return aclTr;
}

const getAclType = (aclType) => {
	switch (aclType) {
	case 'admin':
		return '총괄관리자';
	case 'manager':
		return '관리자';
	case 'equipment':
		return '장비';
	default:
		return ''
	}
}

function openInsertAcl(){
	$("#insert-acl-typ-manager").prop("checked", true); 
	$("#insert-acl-ip").val();
	$("#insert-acl-name").val();
  $("#insert-acl-modal").modal("show");
}

const insertAcl = async () => {
  let data = {
    aclIp : $("#insert-acl-ip").val(),
    aclName : $("#insert-acl-name").val(),
    aclType : $(".insert-acl-type:checked")[0].value,
  }
  await axios.post("/acl/insert",data)

  successToast("등록 되었습니다.")
  $("#insert-acl-modal").modal("hide");
  getAcls()
}

const openUpdateAcl = async (aclId) => {
  let aclData = await axios.get(`/acl/${aclId}`)
  let acl = aclData.data

  $("#update-acl-modal").modal("show");
  $("#update-acl-id").val(aclId)
  $("#update-acl-ip").val(acl.aclIp)
  $("#update-acl-name").val(acl.aclName)
  $(`#update-acl-type-${acl.aclType}`).prop('checked', true);

}

const updateAcl = async () =>{
  if(!confirm('수정하시겠습니까?')) return false;
  let data = {
    aclId : $("#update-acl-id").val(),
    aclIp : $("#update-acl-ip").val(),
    aclName : $("#update-acl-name").val(),
    aclType : $(".update-acl-type:checked")[0].value,
  }
  await axios.post("/acl/update",data)

  successToast("등록 되었습니다.")
  $("#update-acl-modal").modal("hide");
  getAcls()
}

const deleteAcl = async (aclId) =>{
  if(!confirm('삭제하시겠습니까?')) return false;
  await axios.delete(`/acl/delete?aclId=${aclId}`)
  errorToast("삭제되었습니다.")
  getAcls()
}
