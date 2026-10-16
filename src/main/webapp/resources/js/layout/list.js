/* jshint esversion : 6 */
let treeOpenFlag = false;
let currentOrgCd = 0;
$(()=>{
  getTreeOrgs();
  
  $("#search-tree").on("propertychange change keyup paste input", function() {
    $("#tree").jstree("search", this.value);
  });
  
  $('.tree-refresh').click(function(){
    $('#tree').jstree(true).refresh();
  });
  
  $("#update-background-file").change(function() {
    handleFileChange(this, '#update-background-filename', ["jpg", "png"], '#upload-msg', 5 * 1024 * 1024, '#update-background-origin-filename', true, {width: 1920, height: 1080});
  });
})

// CONTROL TREE 
const treeAll = () =>{
  treeOpenFlag = ! treeOpenFlag
  $('#tree').jstree(treeOpenFlag?'close_all':'open_all')
}

// GET TREE
const getTreeOrgs = async () => {
  let {data : orgs} = await axios.get("/org/tree/parliament");
  orgs = orgs.filter(item => item !== null);
  
  if(!Array.isArray(orgs)) location.href="/";
  
  $('#tree').jstree({
    core : {
      data : orgs,
      check_callback: true,
      multiple: false
    },
    plugins:['dnd', 'contextmenu', 'search', 'themes', 'wholerow', 'type'],
    contextmenu:{
      items : function(node) {
        var items = $.jstree.defaults.contextmenu.items();
        items.create = false;
        items.rename = false;
        items.remove = false;
        items.ccp = false;
        return items;
      }
    }
  }).bind('loaded.jstree', (e,d)=>{
    // AFTER LOAD ROOT NODE
    let firstNode = $('#tree').jstree().element[0].children[0].children[0].id
    $('#tree').jstree('select_node', firstNode);
    $('#tree').jstree('open_node', firstNode)
  }).bind('ready.jstree', (e,d)=>{
    // AFTER LOAD ALL NODE
    
  }).bind('select_node.jstree', (e,d)=>{
    // AFTER SELECT NODE
    let orgCd = d.node.id;
    getLayout(orgCd);
    currentOrgCd = orgCd;
  })
  if($('#login-roe').val()=='MANAGER_ROLE'){
    $('#tree').off('contextmenu.jstree','.jstree-anchor');
  }
}

// GET ORG LAYOUT
const getLayout = async (orgCd) => {
  let params = {
    orgCd : orgCd
  }
  // GET BACKGROUND
  try{
    let {data : background} = await axios.get('/layout/background', {params});
    $('#layout-box').css('background-image', `url(/resources/upload/layout/${background.backgroundFilename})`);
    $('#layout-box').css('background-size', 'cover');
  }catch{
    $('#layout-box').css('background-image', `url(/resources/assets/images/layout-default1.png)`);
    $('#layout-box').css('background-size', 'cover');

  }
  // GET OBJECTS
//  let {data : objects} = await axios.get('/layout/object', {params});
//  $('#layout-box').empty();
//  for(let object of objects){
//    $('#layout-box').append(makeObjecttDiv(object));
//  }
}

const makeObjecttDiv = (object) => {
  let objectDiv = '';
  object.objectXAxis = object.objectXAxis*0.5;
  object.objectYAxis = object.objectYAxis*0.5;
  if(object.objectIsTeam){
    // MAKE TEAM DIV 114×33
    let style = `style="
      left:${object.objectXAxis}px;
      top:${object.objectYAxis}px;"
    `
    objectDiv += `<div class="team-div" ${style}>
      ${object.objectTeamName}
    </div>`
  }else{
    // MAKE STAFF DIV 71×39
    let style = `style="
      left:${object.objectXAxis}px;
      top:${object.objectYAxis}px;"
    `
    objectDiv += `<div class="staff-div" ${style}>
      ${object.objectStaffName}
    </div>`
  }
  return objectDiv;
}

// BACKGROUND
// OPEN BACKGROUND
const openBackgroundModal = () => {
  $('#update-background-sort').val('')
  $('#update-background-filename').val('')
  $('#update-background-origin-filename').val('')
  $('#update-background-file').val('')
  $('#update-background-modal').modal('show');
}

const updateBackground = async () => {
  if(!emptyCheck('#update-background-file', '이미지파일을 첨부해주세요')) return false;
  let filename = {
    filename : null,
    originalFilename : null
  }
  try{
    filename = await saveFile('#update-background-file','layout');
  }catch{
    errorToast('파일저장에 실패하였습니다.')
    return;
  }
  let data = {
    backgroundFilename : filename.filename,
    backgroundOriginFilename : filename.originalFilename,
    orgCd : currentOrgCd,
  }
  await axios.post('/layout/background/insert',data);
  getLayout(currentOrgCd);
  $('#update-background-modal').modal('hide');
}