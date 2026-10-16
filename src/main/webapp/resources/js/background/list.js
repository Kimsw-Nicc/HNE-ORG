let currentPage = 0;
$(()=>{
  currentPage = Number(urlParams.get("page"));
  getBackgrounds();
  getBackgroundPaging();
});

const getBackgrounds = async () => {
	let pageSize = 6;
  let params = {
    size : pageSize,
    page : currentPage
  }
  setPushState(params);
  let backgrounds;
  try{
  	({data : backgrounds} = await axios.get(`/background/all`,{params}));
  } catch (e) {
  	console.log(e)
		alert("예기치 않은 오류 발생, 관리자에게 문의 바랍니다.");
		return;
  }
  $("#background-container").empty();
  for(const background of backgrounds){
    $("#background-container").append(getBackgroundDiv(background));
  }
}

const getBackgroundDiv = (background) => {
  let backgroundDiv = `<div class="col-sm-6 col-md-4">
	  <div class="border rounded">
	    <a href="/background/detail?backgroundCd=${background.backgroundCd}">
	      <img width="100%" height="240px" src="/resources/upload/backGround/${background.backgroundFilename}" class="thumb-img" onerror="this.src='/resources/assets/images/properties/1.jpg'" />
	    </a>
	    <div class="p-2">
	      <div class="text-center">
	        <h5 class="text-m mb-3">
	          ${background.backgroundName}
	        </h5>
	        <a href="/background/update?backgroundCd=${background.backgroundCd}" class="btn btn-sm btn-outline-primary"><i class="xi-pen mr-1"></i>수정</a>
	        <button class="btn btn-sm btn-outline-danger" onclick="openDeleteBackground(${background.backgroundCd})"><i class="xi-trash mr-1"></i>삭제</button>
	        <a href="/background/detail?backgroundCd=${background.backgroundCd}" class="btn btn-sm btn-outline-dark"> <i class="xi-search mr-1"></i>상세보기 </a>
	      </div>
	    </div>
	  </div>
	</div>`;
	return backgroundDiv;
}

const getBackgroundPaging = async () => {
  getPaging('/background/count',{},6,currentPage);
}

const movePage = (page) => {
	currentPage = page;
	getBackgrounds();
	getPaging('/background/count',{},6,currentPage);
}

const openDeleteBackground = async (backgroundCd) => {
	if(!confirm('삭제하시겠습니까?')) return false;
	let params = {
		backgroundCd : backgroundCd
	}
  await axios.delete(`/background/delete`,{params});
  getBackgrounds();
  getBackgroundPaging();
}