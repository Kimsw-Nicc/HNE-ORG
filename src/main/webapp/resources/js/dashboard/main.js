$(() => {
	
	getDate();

	getOrgCount();
	getTeamCount();
	getStaffCount();
	
	getStaffs()
});

// 날짜 가져오기
const getDate = async () => {
	const formattedDate = moment(new Date()).format('YYYY.MM.DD.(ddd)');
	$("#today").html(formattedDate);
}

// 부서 개수 가져오기
const getOrgCount = async () => {
	let {data : count} = await axios.get('/org/count');
	$("#org-count").html(count);
	
}

// 팀 개수 가져오기
const getTeamCount = async () => {
	let {data : count} = await axios.get('/team/count');
	$("#team-count").html(count);
}

// 직원 수 가져오기
const getStaffCount = async () => {
	let {data : count}  = await axios.get('/staff/count');
	$("#staff-count").html(count);
}

// 최근 직원 8명 가져오기
const getStaffs = async () => {
	let {data : staffs} = await axios.get('/staff/edited');
	for(let staff of staffs){
		makeStaff(staff);
	}
}

const makeStaff = async (staff) => {
	const imgSrc = getUploadSrc(staff.staffFilename, 'staff');
	let staffDiv = `<div class="col-md-3 d-flex flex-wrap">
    <div class="card border-white shadow-sm w-100">
      <div class="card-body text-center">
        <div class="card-title">
          <img class="staff-img-sm" onError="noImg(this);" src="${imgSrc}" />
          <div style="font-size: 13pt; font-weight: bold; margin-top: 3px;">${staff.staffName}</div>
          <div style="font-size: 11pt;" class="">${staff.staffPosition??''}</div>
          <div style="font-size: 9pt;">${staff.orgName}${staff.staffPosition?'/'+staff.staffPosition:''}</div>
          <div class="border p-1 text-center mt-2" style="width: 100%; font-size: 9pt">${moment(staff.staffEdt).format('YYYY-MM-DD HH:mm:ss')}</div>
        </div>
      </div>
    </div>
  </div>`
	$("#edited-staff").append(staffDiv)
}
