/* jshint esversion : 6 */
$(()=>{
	getEquipments()
	$('.search-keyword').change(()=>{getEquipments()})
	$('#switch-input').change(()=>{getEquipments()})
})

const getEquipments = async () => {
	$('.equipment-container').empty()
	let type = $('.search-type').val()
	let keyword = $('.search-keyword').val()
	let isStatus = $('#switch-input').is(':checked')
	let equipments_data = await axios.get(`/equipment/all?type=${type}&keyword=${keyword}&isStatus=${isStatus}`)
	let equipments = equipments_data.data
	
	for(let equipment of equipments){
		let equipment_box = `<div class="equipment-box" data-id="${equipment.equipment_id}">
			<span class="equipment-box-name">${equipment.equipment_name}</span>
			<img class="equipment-box-status" alt="ON" class="mr-2" src="${equipment.equipment_status?'/resources/assets/images/ON.png':'/resources/assets/images/OFF.png'}">
			<span class="equipment-box-desc">${equipment.equipment_desc}</span>
			</div>`
			$('.equipment-container').append(equipment_box)		
	}
	$('.equipment-box').click((e) => {
    let equipment_id = $(e.currentTarget).data("id")
    location.href = `/equipment/detail?equipmentId=${equipment_id}`
	})
}
