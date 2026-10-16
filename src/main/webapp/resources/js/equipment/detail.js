/* jshint esversion : 6 */
const SECRET_IP = "***.***.***.***"
const SECRET_MAC = "**-**-**-**-**-**"
	
let equipment

$(document).ready(()=>{
	getEquipment()
})

const getEquipment = async () => {
	let equipment_id = urlParams.get('equipmentId')
	let equipment_data = await axios.get(`/equipment/${equipment_id}`)
	equipment = equipment_data.data
	$('.equipment-container').empty();
	$('.equipment-container').append(makeEquipmentBox(equipment));
	await makeSelect()
//edit event
	$('.name-edit').click(()=>{
		$('.name-edit').addClass('none')
		$('.name-info').addClass('none')
		$('.name-input').removeClass('none')
		$('.name-input').focus()
	})
	$('.name-input').on("keyup",async (key) => {
		if(key.keyCode!=13) return false;
		updateEquipmentName()
	})
	$('.name-input').on( "focusout", ()=>{
		updateEquipmentName()
	})
	$('.desc-edit').click(()=>{
		$('.desc-edit').addClass('none')
		$('.desc-info').addClass('none')
		$('.desc-input').removeClass('none')
		$('.desc-input').focus()
	})
	$('.desc-input').on("keyup",async (key) => {
		if(key.keyCode!=13) return false;
		updateEquipmentDesc()
	})
	$('.desc-input').on( "focusout", ()=>{
		updateEquipmentDesc()
	})
	// eye event
	$('.ip-eye').mousedown(()=>{
		$('.ip-info').html(equipment.equipment_ip)
	})
	$('.ip-eye').mouseup(()=>{
		$('.ip-info').html(SECRET_IP)
	})
	$('.ip-eye').focusout(()=>{
		$('.ip-info').html(SECRET_IP)
	})
	$('.mac-eye').mousedown(()=>{
		$('.mac-info').html(equipment.equipment_mac)
	})
	$('.mac-eye').mouseup(()=>{
		$('.mac-info').html(SECRET_MAC)
	})
	$('.mac-eye').focusout(()=>{
		$('.mac-info').html(SECRET_MAC)
	})
}

const makeEquipmentBox = (equipment) => {
	let equipment_org_option = ''
	for(let equipment_org of equipment.layout_org_dtos){
		equipment_org_option += `<div class="org-info" onclick="openLayout(${equipment_org.org_cd})">
			${equipment_org.org_name}
			</div>`
	}
	let equipment_box = `<div class="equipment-header">
		${equipment.equipment_name}
	</div>`
	equipment_box += `<div class="equipment-info-box">
		<div class="equipment-info">
			<b>이름</b>
			<span class="name-info">${equipment.equipment_name}</span>
			<input class="name-input none" value="${equipment.equipment_name}">
			<i class="xi-pen-o name-edit"></i>
		</div>
		<div class="equipment-info">
			<b>설명</b>
			<span class="desc-info">${equipment.equipment_desc}</span>
			<input class="desc-input none" value="${equipment.equipment_desc}">
			<i class="xi-pen-o desc-edit"></i>
		</div>
		<div class="equipment-info">
			<b>IP</b>
			<span class="ip-info">${SECRET_IP}</span>
			<i class="xi-eye-off-o ip-eye"></i>
		</div>
		<div class="equipment-info">
			<b>MAC</b>
			<span class="mac-info">${SECRET_MAC}</span>
			<i class="xi-eye-off-o mac-eye"></i>
		</div>
		<div class="equipment-info-org">
			<b class="equipment-info-org-label">표시부서</b>
			<!-- TODO 여기에 부서 추가하는 코드가 있음 -->
			<!-- 추후 해당 코드를 통해 웹=>앱으로 컨트롤 하는 기능 필요 -->
			<!--<select id="insert-orgs"></select>
			<button id="insert-org-button" onclick="insertLayoutOrgs()">+</button>-->
			<div class="org-info-box">
			${equipment_org_option}
			</div>
		</div>
	</div>`
	equipment_box += `<div class="equipment-button-box">
		<button class="normal">데이터 수동갱신</button>
		<button class="normal">수동 패치</button>
		<button class="normal">직원사진모드 ON/OFF</button>
		<button class="normal">-</button>
		
		<button class="critical">단말기 종료</button>
		<button class="critical">단말기 재부팅</button>
		<button class="hidden">미사용</button>
		<button class="hidden">미사용</button>
		
		<button class="test">네트워크 테스트</button>
		<button class="test">~~ 테스트</button>
		<button class="test">ON/OFF 설정</button>
		<button class="hidden">미사용</button>
	</div>`
		return equipment_box;
}

const makeSelect = async () => {
	let orgs_data = await axios.get('/org/all')
	let orgs = orgs_data.data
	$("select").empty()
	$("select").append('<option value="none" selected>부서를 선택하세요.</option>')	
	for(const org of orgs) {
		let orgOption = `<option value="${org.org_cd}">
			${org.org_fullname}
		</option>`
		$("select").append(orgOption)
	}
}

const updateEquipmentName = async () => {
	let data = {
		equipment_id : equipment.equipment_id,
		equipment_mac : equipment.equipment_mac,
		equipment_name : $('.name-input').val(),
	}
	await axios.put(`/equipment/update/name`,data)
	// show hide
	$('.equipment-header').html($('.name-input').val())
	$('.name-info').html($('.name-input').val())
	$('.name-edit').removeClass('none')
	$('.name-info').removeClass('none')
	$('.name-input').addClass('none')
}

const updateEquipmentDesc = async () => {
	let data = {
		equipment_id : equipment.equipment_id,
		equipment_mac : equipment.equipment_mac,
		equipment_desc : $('.desc-input').val(),
	}
	await axios.put(`/equipment/update/desc`,data)
	// show hide
	$('.desc-info').html($('.desc-input').val())
	$('.desc-edit').removeClass('none')
	$('.desc-info').removeClass('none')
	$('.desc-input').addClass('none')
}

const insertLayoutOrgs = async () => {
	if($('select').val()==='none'){
		errorToast("부서를 선택해주세요.")
		return;
	}
	let org_cd = $('select').val()
	let data = {
		org_pk : `${equipment.equipment_id}-${org_cd}`,
		org_cd : org_cd,
		equipment_id: equipment.equipment_id,
	}
	await axios.post(`/layout/org/save`,data)
	getEquipment()
}

const deleteLayoutOrgs = async (org_cd) => {
	const org_pk = `${equipment.equipment_id}-${org_cd}`
  await axios.delete(`/layout/org/delete?orgPk=${org_pk}`)
  getEquipment()
}


