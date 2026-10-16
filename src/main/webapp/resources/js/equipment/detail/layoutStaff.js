/* jshint esversion : 6 */
$(()=>{
	$(document).on('click', '.staff-ready', (e) => {
    let child = $(e.currentTarget);
    let layoutBox = $('#staff-layout-box');

    child.appendTo(layoutBox);
    child.removeClass('staff-ready').addClass('staff-layout');
  });
	
	$(document).on('dblclick', '.staff-layout', (e) => {
    let child = $(e.currentTarget);
    let ready_box = $('#staff-ready-box');

    child.removeAttr('data-x');
    child.removeAttr('data-y');
    child.removeAttr('style');
    
    interact(child[0]).unset();
    
    child.appendTo(ready_box);
    child.removeClass('staff-layout').addClass('staff-ready');
  });
})

// 직원 가져오기
const getReadyStaff = async (team) => {
	let staffs_data = await axios.get(`/staff/all?teamCd=${team.team_cd}`)
	let staffs = staffs_data.data
	for(let staff of staffs){
		let flag = await axios.get(`/layout/staff?staffCd=${staff.staff_cd}&orgPk=${org_pk}`)
		if(flag.data) continue;
		let staff_div = IS_IMG?`<div class="staff-container staff-ready" data-staff-cd=${staff.staff_cd}>
			<div class="staff-box">
				<div class="staff-box-top-img">
					<div style="grid-row: 1 /span 2;padding: 5px;"><img class="staff-filename" src=${staff.staff_filename}></div>
					<div class="staff-position">${staff.staff_position ?? ''}</div>
					<div class="staff-name">${staff.staff_name}</div>
				</div>
				<div class="staff-duty text-center bg-light">${staff.staff_duty}</div>
			</div>
		</div>`:
		`<div class="staff-container staff-ready" data-staff-cd=${staff.staff_cd}>
				<div class="staff-box">
					<div class="staff-box-top-noimg">
						<div class="staff-position text-center">${staff.staff_position}</div>
						<div class="staff-name text-center">${staff.staff_name}</div>
					</div>
				<div class="staff-duty text-center bg-light">${staff.staff_duty}</div>
			</div>
		</div>`
		$('#staff-ready-box').append(staff_div)
	}
}

// 팀 가져오기
const getReadyTeam = async (team) => {
	let flag = await axios.get(`/layout/team?teamCd=${team.team_cd}&orgPk=${org_pk}`)
	if(flag.data) return;
	let team_div = `<div class="team-container staff-ready" data-team-cd=${team.team_cd}>
		${team.team_name}
	</div>`
	$('#staff-ready-box').append(team_div)
}

// 그려진 직원 가져오기
const getLayoutStaff = async () => {
	let staffs_data = await axios.get(`/layout/staffs?orgPk=${org_pk}`)
	let staffs = staffs_data.data
	for(let staff of staffs){
		const { staff_br_x: x, staff_br_y: y, staff_cd } = staff;
		let staff_div = IS_IMG?`<div class="staff-container staff-layout" data-staff-cd=${staff_cd} data-x=${x} data-y=${y} style="transform: translate(${x}px, ${y}px);">
			<div class="staff-box">
				<div class="staff-box-top-img">
					<div style="grid-row: 1 /span 2;padding: 5px;"><img class="staff-filename" src=${staff.staff_filename}></div>
					<div class="staff-position">${staff.staff_position ?? ''}</div>
					<div class="staff-name">${staff.staff_name}</div>
				</div>
				<div class="staff-duty text-center bg-light">${staff.staff_duty}</div>
			</div>
		</div>`:
		`<div class="staff-container staff-layout" data-staff-cd=${staff_cd} data-x=${x} data-y=${y} style="transform: translate(${x}px, ${y}px);">
				<div class="staff-box">
					<div class="staff-box-top-noimg">
						<div class="staff-position text-center">${staff.staff_position}</div>
						<div class="staff-name text-center">${staff.staff_name}</div>
					</div>
				<div class="staff-duty text-center bg-light">${staff.staff_duty}</div>
			</div>
		</div>`
		$('#staff-layout-box').append(staff_div)
	}
}

// 그려진 팀 가져오기
const getLayoutTeam = async () => {
	let teams_data = await axios.get(`/layout/teams?orgPk=${org_pk}`)
	let teams = teams_data.data
	for(let team of teams){
		const { team_br_x: x, team_br_y: y, team_cd } = team;
		let team_div = `<div class="team-container staff-layout" data-team-cd=${team_cd} data-x=${x} data-y=${y} style="transform: translate(${x}px, ${y}px);">
			${team.team_name}
		</div>`
		$('#staff-layout-box').append(team_div)
	}
}

const saveStaffs = async () => {
	let staff_datas = []
	let layout_staffs = $('#staff-layout-box').find('.staff-container')
	for(const layout_staff of layout_staffs){
		let staff_data = {
			staff_cd : $(layout_staff).data('staff-cd'),
			staff_br_x : $(layout_staff).data('x'),
			staff_br_y : $(layout_staff).data('y'),
			org_pk : org_pk
		}
		staff_datas.push(staff_data)
	}
	await axios.post('/layout/staff/save',staff_datas)
}

const saveTeams = async () => {
	let team_datas = []
	let layout_teams = $('#staff-layout-box').find('.team-container')
	for(const layout_team of layout_teams){
		let team_data = {
			team_cd : $(layout_team).data('team-cd'),
			team_br_x : $(layout_team).data('x'),
			team_br_y : $(layout_team).data('y'),
			org_pk : org_pk
		}
		team_datas.push(team_data)
	}
	await axios.post('/layout/team/save',team_datas)
}