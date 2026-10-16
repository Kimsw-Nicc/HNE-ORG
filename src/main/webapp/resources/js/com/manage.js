/* jshint esversion : 6 */
let treeOpenFlag = false;
var sel; // 검색, 이름변경 해야한다
var ref; // 검색, 이름변경 해야한다

let currentOrgCd = 0;
$(()=>{
	getTreeOrgs();
	
  $("#search-tree").on("propertychange change keyup paste input", function() {
    $("#tree").jstree("search", this.value);
  });

  $('#tree-refresh').click(function(){
    $('#tree').jstree(true).refresh();
  });

  
});

// tree 컨트롤 기능
const treeAll = () =>{
  treeOpenFlag = ! treeOpenFlag 
  $('#tree').jstree(treeOpenFlag?'close_all':'open_all')
}

const getJstreeInfo = (obj) => {
  const ref = $.jstree.reference(obj.reference);
  const sel = ref.get_selected();
  return { ref, sel };
};

const ensureSelection = (sel) => {
  if (!sel.length) {
    errorToast("선택된 항목이 없습니다.");
    return false;
  }
  return true;
};

const createOrg = async (obj) => {
  const { ref, sel } = getJstreeInfo(obj);
  if (!ensureSelection(sel)) return false;

  $("#insert-org-upr-name").val($("#tree").jstree("get_selected", true)[0].text);
  $("#insert-org-upr-cd").val(sel);

  openInsertOrg();
};

const deleteOrg = async (obj) => {
  const { ref, sel } = getJstreeInfo(obj);
  if (!ensureSelection(sel)) return false;

  let { data: isDeleted } = await axios.delete(`/org/delete/${sel[0]}`);
  if (isDeleted) {
    getTreeOrgs();
  } else {
    errorToast("하위에 팀 혹은 직원이 남아있습니다. 확인 바랍니다.");
  }
};

const renameOrg = async (obj) => {
  const { ref, sel } = getJstreeInfo(obj);
  if (!ensureSelection(sel)) return false;

  ref.edit(sel);
};

// 데이터 호출 및 트리 생성 메소드
const getTreeOrgs = async () => {
  $("#tree").jstree("destroy");
  let { data: orgs } = await axios.get("/org/tree");
  orgs = orgs.filter((item) => item !== null);

  $("#tree")
    .jstree({
      core: {
        data: orgs,
        check_callback: true,
        multiple: false,
      },
      plugins: ["dnd", "contextmenu", "search", "themes", "wholerow", "type"],
      contextmenu: {
        items: {
          create: {
            label: "생성",
            action: createOrg,
          },
          remove: {
            label: "삭제",
            action: deleteOrg,
          },
          rename: {
            label: "이름 변경",
            action: renameOrg,
          },
        },
      },
    })
    .bind("loaded.jstree", (e, d) => {
      // AFTER ROOT NODE LOAD
      currentOrgCd = urlParams.get("orgCd");
      let nodeToSelect;
      if (currentOrgCd == null) {
        nodeToSelect = d.instance.get_node("#").children[0];
      } else {
        nodeToSelect = d.instance.get_node(currentOrgCd);
      }
      if (nodeToSelect) {
        d.instance.select_node(nodeToSelect);
        d.instance.open_node(nodeToSelect);
        try {
          currentStaffPage = Number(urlParams.get("page"));
          $("#search-staff-input").val(urlParams.get("keyword"));
        } catch {}
      }
    })
    .bind("ready.jstree", (e, d) => {
      // 모든 노드가 load 되고 난 후
    })
    .bind("select_node.jstree", async (e, d) => {
      let orgCd = d.node.id;
      $("#search-staff-input").val("");
      currentStaffPage = 0;
      $("#search-type").val("org");
      await getOrg(orgCd);
      await getTeams(orgCd);
      await getStaffs(orgCd);
      currentOrgCd = d.node.id;
    })
    .bind('move_node.jstree', async (e,d)=>{
      if(d.parent == '#') {
        errorToast('최상위 부서 생성은 등록을 통해서만 가능합니다.');
        $("#tree").jstree(true).refresh();
      }else {
        let data = {
          orgCd : d.node.id,
          orgUprCd : d.parent
        }
        await axios.put('/org/update/upr', data);
      }
    })
    .bind("rename_node.jstree", async (e, d) => {
      let data = {
        orgCd: d.node.id,
        orgSort: d.node.original.sort,
        orgName: d.text,
      };
      await axios.put("/org/update", data);
      getTreeOrgs();
    });
  if ($("#login-role").val() == "MANAGER_ROLE") {
    $("#tree").off("contextmenu.jstree", ".jstree-anchor");
  }
};


