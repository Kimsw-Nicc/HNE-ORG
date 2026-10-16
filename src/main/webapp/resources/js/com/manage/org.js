/* jshint esversion : 6 */
const getOrg = async (orgCd) => {
  let {data : org} = await axios.get(`/org/${orgCd}`);
  $('#org-name').val(org.orgName);
  $('#org-code').val(org.orgCd);
  $('#org-upr-code').val(org.orgUprCd);
  $('#org-upr-name').val(org.orgUprName);
  $('#org-sort').val(org.orgSort);
  $('#org-fullname').val(org.orgFullname);
  $('#org-location').val(org.orgLocation);
}
// ORG CRUD
const updateOrg = async () =>{
  if(!confirm('수정하시겠습니까?')) return;
  let data = {
    orgCd : $('#org-code').val(),
    orgSort : $('#org-sort').val(),
    orgName : $('#org-name').val(),
    orgLocation : $('#org-location').val()
  }
  await axios.put('/org/update',data)
  location.reload();
}
const openInsertOrg = async () =>{
  $('#insert-org-modal').modal('show')
  $('#insert-org-name').val('')
  $('#insert-org-cd').val('')
  $('#insert-org-sort').val('')

}
const insertOrg = async () => {
  let {data : orgs} = await axios.get(`/org/all`);
  for (const org of orgs) {
    if(org.orgCd == $('#insert-org-cd').val()){
      errorToast("중복되는 부서코드가 존재합니다", '#insert-org-cd');
      return false;
    }
  }

  if(!emptyCheck('#insert-org-name',"부서 이름을 입력해주세요")) return false;
  if(!emptyCheck('#insert-org-cd',"부서 코드를 입력해주세요")) return false;  
  if(!emptyCheck('#insert-org-sort',"부서 순서를 입력해주세요")) return false;

  let data = {
    orgCd : $('#insert-org-cd').val(),
    orgUprCd : $('#insert-org-upr-cd').val(),
    orgName : $('#insert-org-name').val(),
    orgSort : $('#insert-org-sort').val(),
    orgRank : $('#insert-org-sort').val(),
    orgFullname : $('#insert-org-full-name').val()
  }
  await axios.post('/org/insert',data);
  $('#insert-org-modal').modal('hide')
  location.reload();
}
