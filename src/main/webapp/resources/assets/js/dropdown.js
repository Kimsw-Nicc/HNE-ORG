$(document).ready(function(){
	$('#org-fullname').focusin(()=>{
	  aTags = $('#org-fullname-dropdown div').css("display","block")
	})
	$('#org-fullname').focusout(()=>{
	  setTimeout(()=>{
	  	aTags = $('#org-fullname-dropdown div').css("display","none")
	  },100)
	})
})

function changeOrgCd(orgCd,orgFullname){
  $('#org-cd').val(orgCd)
  $('#org-fullname').val(orgFullname)
  if (orgCd == "none") {
      $("#auth_1").prop("checked", true);
      $("#realAuth").val($("#auth_1").val());
  } else if (orgCd == "conf") {
      $("#auth_3").prop("checked", true);
      $("#realAuth").val($("#auth_3").val());
  } else if (orgCd == "food") {
      $("#auth_3").prop("checked", true);
      $("#realAuth").val($("#auth_3").val());
  } else {
      $("#auth_2").prop("checked", true);
      $("#realAuth").val($("#auth_2").val());
  }
}

function filterFunction(){
  let input, filter, ul, li, a, i
  input = $('#org-fullname')
  filter = input.val()
  div = $('#org-fullname-dropdown')
  divTags = $('#org-fullname-dropdown div')
  for(const divTag of divTags){
    let textValue = divTag.textContent || divTag.innerText
    if(textValue.indexOf(filter) > -1){
      divTag.style.display = "block";
    }else{
      divTag.style.display = "none";
    }
  }
}