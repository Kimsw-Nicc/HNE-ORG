/* jshint esversion : 6 */
let patchType = 'client';
$(document).ready(function(){
	makeNav()
  makeList('client')
  
  $("#patch-file").change(function() {
    handleFileChange(this, '#patch-filename', ["apk"], '#upload-msg', 300 * 1024 * 1024);
  });
})

const allTypeList = [
  {type:'client', name:'클라이언트'},
  {type:'luncher', name:'런처'},
  {type:'manager', name:'매니저'},
  // {type:'conf', name:'회의실'},
  // {'food', '식단표'},
]

const makeNav = () => {
  let active = true
  for (const allType of allTypeList) {
    const li = `<li class="nav-item"><a class="nav-link ${active?'active':''}" onclick="makeList('${allType.type}')" data-toggle="tab" aria-expanded="false">${allType.name}</a></li>`
    $('#patch-nav').append(li)
    active=false
  }
}

const makeList = async (type) => {
	patchType = type
  $('#patch-list').empty();
  let {data : patchs} = await axios.get(`/patch/type?type=${type}`);
  for (const patch of patchs) {
    const tr = `<tr>
      <td>${patch.patchAndroidVersion}</td>
      <td>${patch.patchName}</td>
      <td>${patch.patchInfo}</td>
      <td>${moment(patch.patchEdt).format('YYYY-MM-DD HH:mm:ss')}</td>
      <td><button type="button" class="btn btn-sm btn-outline-danger" onclick="deletePatch('${patch.patchCd}','${patch.patchName}')"><i class="xi-trash"></i>삭제</button></td>
    </tr>`
    $('#patch-list').append(tr);
  }
  for (const allType of allTypeList){
    if(allType.type == type){
      let openInsertModalBtn = `<button class="btn btn-primary change-btn" type = "button" onclick="openInsertModal('${allType.type}')"><i class="xi-folder-add"></i>&nbsp;${allType.name} 등록</button>`
      $('#insert-modal-box').html(openInsertModalBtn);
    }
  }
}

const openInsertModal = (type) => {
  $("#insert-modal").modal({
    show: true,
    backdrop: 'static',
    keyboard: false
  });
  $("#insert-patch-type").val(type);
  $("#patch-file").val("")
  $("#patch-filename").val("")
}

const insertPatch = async()=>{
	let patchType = $("#insert-patch-type").val();
	
	if(!emptyCheck('#patch-filename', '파일을 등록해주세요.')) return false;
	
  await openProcessModal('파일 업로드중입니다...')
  
  let filename = {
  	filename : null,
  	originalFilename : null,
  	thumbnail : null
  }
  
  try{
  	filename = await saveApkFile('#patch-file', 'patch', patchType)
  }catch{
  	errorToast('파일 저장에 실패하였습니다.')
  	await closeProcessModal()
  	return
  }
  
	data = {
		patchType : $('#insert-patch-type').val(),
		patchName : filename.filename,
		patchInfo : $('#insert-patch-info').val()
	}
  try{
  	await axios.post('/patch/insert',data);
  }catch{
  	errorToast('파일 등록에 실패하였습니다.')
  	await closeProcessModal()
  	return;
  }
  makeList(patchType)
  $("#insert-modal").modal('hide');
  await closeProcessModal()
}

const deletePatch = async (patchId, patchName) => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let options = {
    params : {
      patchId : patchId,
      patchName : patchName
    }
  }
  await axios.delete('/patch/delete',options);
  makeList(patchType)
}



