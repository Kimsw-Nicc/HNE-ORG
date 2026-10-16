/* jshint esversion : 6 */
const types = {
  logo : '로고',
  slogan : '슬로건',
//  logo_parliament : '의회 로고',
//  slogan_parliament : '의회 슬로건',
}

$(()=>{
  $("#insert-setting-file").change(function() {
    handleFileChange(this, '#insert-setting-filename', ["jpg", "png"], '#insert-upload-msg', 5 * 1024 * 1024);
  });
  $("#update-setting-file").change(function() {
    handleFileChange(this, '#update-setting-filename', ["jpg", "png"], '#update-upload-msg', 5 * 1024 * 1024);
  });
  
  getSettingFormGroups();
  getSettings();
  getInsertSettingTypes();
})

// manage.jsp 내의 로고/슬로건 탭을 생성
const getSettingFormGroups = () => {
  Object.entries(types).forEach(([key, value]) => {
    getSettingFormGroup(key, value);
  })
}

const getSettingFormGroup = (type, typeKor) => {
  let div = `<div class="form-group">
    <h4 class="text-title">${typeKor}</h4>
    <div id="${type}-container" class="border rounded p-3 text-center"></div>
  </div>`;
  $('#main').append(div);
}

// manage.jsp 내의 로고/슬로건 탭 정보를 가져옴
const getSettings =  () => {
  Object.entries(types).forEach(([key, value]) => {
    getSetting(key);
  })
}

const getSetting = async (type) => {
  let {data : data} = await axios.get(`/basicsetting?settingType=${type.toUpperCase()}`);
  $(`#${type}-container`).empty()
  if(data){
    $(`#${type}-container`).html(getSettingDiv(data))
  }else{
    $(`#${type}-container`).html('등록된 사진이 없습니다.')
  }
}

const getSettingDiv = (setting) => {
  let settingDiv = `<img src="/resources/upload/basicSetting/${setting.settingFilename}" class="img-fluid" alt="등록된 사진이 없습니다.">
  <div class="p-2">
    <div class="text-center">
      <button class="btn btn-sm btn-outline-primary" onclick="openUpdateSettingModal('${setting.settingType}')">
        <i class="xi-pen mr-1"></i>수정
      </button>
      <button class="btn btn-sm btn-outline-danger" onclick="deleteSetting('${setting.settingCd}')">
        <i class="xi-trash mr-1">삭제</i>
      </button>
    </div>
  </div>`;
  return settingDiv;
}

// insertBasicsettingModal.jsp 내의 checkbox 탭을 생성
const getInsertSettingTypes = () => {
  Object.entries(types).forEach(([key, value]) => {
    getInsertSettingType(key, value);
  })
}

const getInsertSettingType = (type, typeKor) => {
  let div = `<div class="form-check form-check-inline">
    <input class="form-check-input insert-setting-type" type="radio" id="insert-setting-type-${type}" value="${type.toUpperCase()}" name="setting-type">
    <label class="form-check-label" for="insert-setting-type-${type}">${typeKor}</label>
  </div>`;
  $('#insert-type').append(div);
}

const openInsertSettingModal = () => {
  $("#insert-upload-msg").html("")
  $("#insert-setting-file").val("")
  $("#insert-setting-filename").val("")
  $("#insert-setting-modal").modal({
      show : true,
      backdrop : 'static',
      keyboard : false
  });
}

const insertSetting = async () => {
  let settingType = $("input[name='setting-type']:checked").val()
  
  if(!emptyCheck('#insert-setting-filename', '파일을 등록해주세요')) return false;
  
  let filename = {
    filename : null,
    originalFilename : null
  }
  
  try{
    filename = await saveFile('#insert-setting-file', 'basicSetting')
  }catch{
    errorToast('파일 저장에 실패하였습니다.')
    return
  }
  
  let data = {
    settingType : settingType,
    settingFilename : filename.filename,
    settingOriginFilename : filename.originalFilename,
  }
  try{
    await axios.post('/basicsetting/insert',data);
  }catch{
    errorToast('파일 저장에 실패했습니다.<br/>슬로건/로고는 1개씩만 등록 가능합니다.')
    return
  }
  $("#insert-setting-modal").modal('hide');
  getSettings()
}

const openUpdateSettingModal = async (settingType) => {
  let {data : setting} = await axios.get(`/basicsetting?settingType=${settingType}`);
  $("#update-setting-cd").val(setting.settingCd)
  $("#update-setting-type").val(setting.settingType)
  $("#update-setting-filename").val(setting.settingFilename)
  $("#update-setting-origin-filename").val(setting.settingOriginFilename)
  $("#update-upload-msg").html("")
  $("#update-setting-modal").modal({
      show : true,
      backdrop : 'static',
      keyboard : false
  });
}

const updateSetting = async () => {
  let settingCd = $("#update-setting-cd").val()
  let settingType = $("#update-setting-type").val()
  
  if(!emptyCheck('#update-setting-filename', '파일을 등록해주세요')) return false;
  
  let filename = {
    filename : null,
    originalFilename : null
  }
  
  try{
    filename = await saveFile('#update-setting-file', 'basicSetting')
  }catch{
  }
  
  let data = {
    settingCd : settingCd,
    settingType : settingType,
    settingFilename : filename.filename??$("#update-setting-filename"),
    settingOriginFilename : filename.originalFilename??$("#update-setting-origin-filename"),
  }
  try{
    await axios.put('/basicsetting/update',data);
  }catch{
  }
  $("#update-setting-modal").modal('hide');
  getSettings()
}

const deleteSetting = async (settingCd) => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
    settingCd : settingCd
  }
  await axios.delete(`/basicsetting/delete`, {params})
  getSettings()
}