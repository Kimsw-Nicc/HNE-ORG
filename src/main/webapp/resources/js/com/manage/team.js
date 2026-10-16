/* jshint esversion : 6 */
const getTeams = async (orgCd) => {
  let {data : teams} = await axios.get(`/team/all?orgCd=${orgCd}`);
  $('#team-tbody').empty();
  for (const team of teams){
    $('#team-tbody').append(getTeamTr(team));
  }
}

// TEAM CRUD
const getTeamTr = (team) => {
  let teamTr = "";
  // 표시
  teamTr += createTd(createSwitch(`${team.teamCd}-visible`, team.teamVisible, `updateTeamVisible(event,'${team.teamCd}')`));
  // 명칭
  teamTr += createTd(team.teamName, "cursor", '');
  // 코드
  teamTr += createTd(team.teamCd, "cursor", '');
  // 순서
  teamTr += createTd(team.teamSort, "cursor", '');
  // 수정
  teamTr += createTd(`<button class="btn btn-outline-warning btn-sm" onclick="openUpdateTeam('${team.teamCd}')"><i class="xi-pen"></i></button>`);
  // 삭제
  teamTr += createTd(`<button class="btn btn-sm btn-outline-danger" onclick="deleteTeam('${team.teamCd}')"><i class="xi-trash"></i></button>`);
  
  return `<tr>${teamTr}</tr>`;
}

const openInsertTeam = async ()=>{
  $('#insert-team-modal').modal('show');
  $('#insert-team-code').val(currentOrgCd);
  $('#insert-team-name').val('');
  $('#insert-team-sort').val('');
  $('#insert-team-info').val('');

}

const insertTeam = async () => {
  if(!emptyCheck('#insert-team-name', '팀이름을 등록해주세요.')) return false;
  if(!emptyCheck('#insert-team-code', '팀코드를 등록해주세요.')) return false;
  if(!emptyCheck('#insert-team-sort', '팀순서를 등록해주세요.')) return false;

  let data = {
    teamName : $('#insert-team-name').val(),
    teamCd : $('#insert-team-code').val(),
    teamSort : $('#insert-team-sort').val(),
    teamInfo : $('#insert-team-info').val(),
    teamVisible: true,
    orgCd : currentOrgCd
  }
  try{
  	await axios.post('/team/insert',data);
  }catch{
  	errorToast('에러가 발생하였습니다.</br>팀코드를 확인해주세요.');
  	return;
  }
  getTeams(currentOrgCd);
  $('#insert-team-modal').modal('hide');
}

const openUpdateTeam = async (teamCd) =>{
  $('#update-team-modal').modal('show');
  let {data : team} = await axios.get(`/team/${teamCd}`);
  $('#update-team-name').val(team.teamName);
  $('#update-team-code').val(team.teamCd);
  $('#update-team-sort').val(team.teamSort);
  $('#update-team-info').val(team.teamInfo);
}

const updateTeam = async ()=>{
  let data = {
    teamCd : $('#update-team-code').val(),
    teamName : $('#update-team-name').val(),
    teamSort : $('#update-team-sort').val(),
    teamInfo : $('#update-team-info').val(),
  }
  await axios.put('/team/update',data);
  getTeams(currentOrgCd);
  $('#update-team-modal').modal('hide')
}

const updateTeamVisible = async (event, teamCd) =>{
	let data = {
		teamCd : teamCd,
		teamVisible : event.target.checked
	}
  await axios.put(`/team/update/visible`, data);
}

const updateTeamSync = async (event, teamCd) => {
	let data = {
		teamCd : teamCd,
		staffSync : event.target.checked?'sync':'async'
	}
  await axios.put(`/team/update/sync`, data);
}

const deleteTeam = async (teamCd) =>{
  if(confirm('삭제하시겠습니까?')){
    await axios.delete(`/team/delete/${teamCd}`);
    getTeams(currentOrgCd);
  }
}

const sortTeam = async () => {
	await axios.put(`/team/sort?orgCd=${currentOrgCd}`);
	await getTeams(currentOrgCd);
	successToast('정렬되었습니다.');
}

