const furnitures_types = {
	'furniture-big-table' : 'big_table', 
	'furniture-small-table' : 'small_table',
	'furniture-flower-pot' : 'flowerPot',
	'furniture-print' : 'print',
	'furniture-water' : 'water',
}
$(document).ready(()=>{
	
	for(let furniture of Object.keys(furnitures_types)) {
		addFurniture(furniture);
	}
	
	$(document).on('dblclick', '.furniture-layout', (e) => {
    $(e.currentTarget).remove()
  });
})

const addFurniture = (class_name) => {
	$(`.${class_name}.furniture-ready`).click(()=>{
    let child = `<div class="furniture furniture-layout ${class_name}"></div>`
    $('#staff-layout-box').append(child)
  });
}

const getLayoutFurniture = async (org_pk) => {
	let furnitures_data = await axios.get(`/layout/furnitures?orgPk=${org_pk}`)
	let furnitures = furnitures_data.data
	for(let furniture of furnitures){
		let x = furniture.furniture_br_x
		let y = furniture.furniture_br_y
		let furniture_type
		for(const [key, value] of Object.entries(furnitures_types)){
			if(furniture.furniture_type === value){
				furniture_type = key
			}
		}
		let furniture_div = `<div class="furniture furniture-layout ${furniture_type}" data-x="${x}" data-y="${y}" style="transform: translate(${x}px, ${y}px);">
		</div>`
		$('#staff-layout-box').append(furniture_div)
	}
}

const saveFurnitures = async (org_pk) => {
	let furniture_datas = []
	let layout_furnitures = $('#staff-layout-box').find('.furniture')
	for(const layout_furniture of layout_furnitures){
		let furniture_data = {
			furniture_br_x : $(layout_furniture).data('x'),
			furniture_br_y : $(layout_furniture).data('y'),
			org_pk : org_pk
		}
		for(const [key, value] of Object.entries(furnitures_types)){
			if(layout_furniture.classList.contains(key)){
				furniture_data.furniture_type = value
			}
		}

		furniture_datas.push(furniture_data)
	}
	await axios.post('/layout/furniture/save',furniture_datas)
}