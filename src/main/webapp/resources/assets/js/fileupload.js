const saveFile = async (selector, dirPath) => {
  let file = $(selector)[0].files[0];
  let formData = new FormData();
  formData.append("file", file);
  formData.append("dirPath", dirPath);
  let {data : filename} = await axios.post("/file/save", formData);
  return filename;
};

const saveApkFile = async (selector, dirPath, patchType) => {
	let file = $(selector)[0].files[0]
	let formData = new FormData();
	formData.append("file",file);
	formData.append("dirPath",dirPath);
	formData.append("patchType",patchType);
	let {data : filename} = await axios.post('/file/saveApk', formData);
	return filename;
}

const emptyCheck = (selector, message) => {
  if ($.trim($(selector).val()) == "") {
    errorToast(message, selector);
    return false;
  }
  return true;
};

const noImg = (node) => {
  $(node).attr("src", "/resources/assets/images/users/unknow.png");
}

const handleFileChange = (
  inputElement, // 실제 input 요소
  fileNameSelector, // 파일명 표시를 위한 input 요소 셀렉터
  extList = ["jpg", "png"], // 허용되는 확장자 목록
  msgSelector = "#upload-msg", // 메시지를 표시할 요소 셀렉터
  maxSize = 5 * 1024 * 1024, // 최대 파일 크기 (기본값 5MB)
  originFileNameSelector = null, // 원래 파일명 표시를 위한 추가 input 요소 셀렉터 (옵션)
  checkDimensions = false, // 이미지 크기 검사 여부
  dimensions = { width: 1920, height: 1080 }
) => {
  if ($(inputElement).val() != "") {
    let file = inputElement.files[0];
    let ext = $(inputElement).val().split(".").pop().toLowerCase();

    // 파일 형식 검사
    if ($.inArray(ext, extList) == -1) {
      $(msgSelector).html(`<span class="small text-danger">${extList.join(", ")} 파일만 선택해 주세요.</span>`);
      $(fileNameSelector).val("");
      if (originFileNameSelector) $(originFileNameSelector).val("");
      return false;
    }

    // 파일 사이즈 검사
    if (file.size > maxSize) {
      $(msgSelector).html(`<span class="small text-danger">첨부파일 사이즈는 ${maxSize / (1024 * 1024)}MB 이내로 등록 가능합니다.</span>`);
      $(fileNameSelector).val("");
      if (originFileNameSelector) $(originFileNameSelector).val("");
      return false;
    }

    // 파일 크기 검사 (선택적)
    if (checkDimensions) {
      let img = new Image();
      let _URL = window.URL || window.webkitURL;
      img.src = _URL.createObjectURL(file);

      img.onload = function () {
        if (img.width != dimensions.width || img.height != dimensions.height) {
          $(msgSelector).html(`<span class="small text-danger">이미지 크기가 ${dimensions.width}px * ${dimensions.height}px 과 같지 않습니다.</span>`);
          $(fileNameSelector).val("");
          if (originFileNameSelector) $(originFileNameSelector).val("");
          return false;
        }
        // 파일 이름 등록
        $(msgSelector).html('<span class="small text-success">등록가능한 이미지 입니다.</span>');
        $(fileNameSelector).val(file.name);
        if (originFileNameSelector) $(originFileNameSelector).val(file.name);
      };
    } else {
      // 파일 이름 등록
      $(msgSelector).html('<span class="small text-success">등록가능한 이미지 입니다.</span>');
      $(fileNameSelector).val(file.name);
      if (originFileNameSelector) $(originFileNameSelector).val(file.name);
    }
  }
};
