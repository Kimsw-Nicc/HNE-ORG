/* jshint esversion : 6 */
let urlParams = new URL(location.href).searchParams;

moment.updateLocale('ko', {
  weekdays: ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"],
  weekdaysShort: ["일", "월", "화", "수", "목", "금", "토"]
});

$(()=>{
  
  limitNumberInput();
  limitEmoji();

  $("#back").click(() => {
    history.back();
  });
});

// 이모지 필터링 함수
const limitEmoji = () => {
  const emojiRegex =
    /(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|[\u0023-\u0039]\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|\ud83c[\ude32-\ude3a]|\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g;
  $("input").on("input", function () {
    const oldValue = $(this).val();
    const newValue = oldValue.replace(emojiRegex, "");
    // 기존 값과 다를 때만 업데이트
    if (oldValue !== newValue) {
      $(this).val(newValue);
      errorToast("이모지는 입력 불가능합니다.");
    }
  });
}

// Number Input 필터링 함수
const limitNumberInput = () => {
  const numberInput = $('input[type="number"]');
  let min = 0;
  let max = 9999;

  numberInput.attr("min", min);
  numberInput.attr("max", max);
  numberInput.on("input", function () {
    const $this = $(this); // 현재 이벤트가 발생한 요소
    let value = parseInt($this.val(), 10);

    if (isNaN(value)) {
      value = min; // 빈값일 경우 기본값 설정
    }

    // 값이 범위를 벗어났을 경우 설정
    if (value < min) {
      $this.val(min);
    } else if (value > max) {
      $this.val(max);
    }
  });
};

const checkText = (event) => {
  const input = event.target.value;
  const specialChars = /[{}[\]()<>?_|~`!@#$%^&*+\\"'\\/]/;
  
  if (specialChars.test(input)) {
    event.preventDefault();
    alert("특수문자는 입력하실 수 없습니다.");
    event.target.value = ""; // 입력창 비우기
  }
}


const getOrgs = async (seleter, text="전체") => {
  let {data : orgs} = await axios.get("/org/all");
  $(seleter).empty();
  $(seleter).append(`<option value="none" selected>${text}</option>`);
  for(const org of orgs) {
    $(seleter).append(`<option value="${org.orgCd}">${org.orgFullname}</option>`);
  }
}

const getStaffStatus = (staffStatus) => {
  switch (staffStatus) {
  case "tenure":
    return "재직";
  case "leave":
    return "휴직";
  case "dispatch":
    return "파견";
  case "retirement":
    return "퇴직";
  case "moving":
    return "전출";
  }
}

const errorToast = (msg, focus) => {
  toastr.options = {
      "positionClass" : "toast-top-center"
  }
  $(focus).focus();
  toastr["error"](msg);
  return false;
}
const warningToast = (msg) => {
  toastr.options = {
    "positionClass" : "toast-top-center"
  }
  toastr["warning"](msg);
  return true;
}
const successToast = (msg) => {
  toastr.options = {
    "positionClass" : "toast-top-center"
  }
  toastr["success"](msg);
  return true;
}

const openProcessModal = async (msg) => {
  return new Promise((resolve) => {
    $("#process-modal").on('shown.bs.modal', () => {
      $('#process-msg').html(msg);
      resolve();
    }).modal({
      show: true,
      backdrop: 'static',
      keyboard: false
    });
  });
}

const closeProcessModal = async () => {
  return new Promise((resolve) => {
    $("#process-modal").on('hidden.bs.modal', () => {
      resolve();
    }).modal('hide');
  });
}

const getUploadSrc = (filename, path) => {
  // staff.staffFilename?.startsWith('http')
  let result;
  if(filename?.startsWith('http')){
    result = filename;
  }else{
    result = filename ? `/resources/upload/${path}/${filename}`
      : path === "staff" ? "/resources/assets/images/users/unknow.png"
      : "";
  }
  return result;
}

const createTd = (content, additionalClass = "", additionalAttributes = "") => `<td class="${additionalClass}" ${additionalAttributes}>${content}</td>`;

const createSwitch = (id, checked, onClick, addClass) =>
`<div class="custom-control custom-switch">
  <input type="checkbox" ${checked ? "checked" : ""} class="custom-control-input ${addClass}" id="${id}" onclick="${onClick}">
  <label class="custom-control-label" for="${id}"></label>
</div>`;

const createButton = (id, content, onClick, addClass) => `
  <button id="${id}" type="button" class="btn btn-sm ${addClass}" onclick="${onClick}">
    ${content}
  </button>
`;

const createA = (id, content, url, addClass) => `
  <a id="${id}" href="${url}" class="btn btn-sm ${addClass}">
    ${content}
  </a>
`;

const createXi = (addClass) => `<i class="xi-${addClass}"></i>`;
