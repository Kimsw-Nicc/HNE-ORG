let currentPage = 0;
$(()=>{
  currentPage = Number(urlParams.get("page"));
  getTours();
  getTourPaging();
});

const getTours = async () => {
  let pageSize = 6
  let params = {
    size : pageSize,
    page : currentPage
  }
  setPushState(params);
  let {data : tours} = await axios.get(`/tour/all`,{params});
  $("#tour-container").empty();
  for(const tour of tours){
    $("#tour-container").append(getTourDiv(tour));
  }
}

const getTourDiv = (tour) => {
  let loginOrgCd = $('#login-org-cd').val();
  let tourDiv = `<div class="col-sm-6 col-md-4">
    <div class="border rounded">
      <div class="text-center">
        <a href="/tour/detail?tourCd=${tour.tourCd}">
          <img src="/resources/upload/tour/${tour.tourFilename}" width="100%" height="240px" class="">
        </a>
        <h4 class="text-m mt-3">${tour.tourName}</h4>
        <span class="small">
          ${moment(tour.tourNdt).format('yyyy-MM-DD')}
        </span>
        <div class="p-3">
          ${(loginOrgCd == '' || loginOrgCd == tour.orgCd) ? `<a class="btn btn-sm btn-outline-primary" href='/tour/update?tourCd=${tour.tourCd}'><i class="xi-pen mr-1"></i>수정</a>
          <button class="btn btn-sm btn-outline-danger" onclick="openDeleteTour(${tour.tourCd})"><i class="xi-trash mr-1">삭제</i></button>`:''}
          <a class="btn btn-sm btn-outline-dark" href="/tour/detail?tourCd=${tour.tourCd}"><i class="xi-search mr-1">상세</i></a>
        </div>
      </div>
    </div>
  </div>`;
  return tourDiv;
}

const getTourPaging = async () => {
  getPaging('/tour/count',{},6,currentPage);
}

const movePage = (page) => {
  currentPage = page;
  getTours();
  getTourPaging();
}

const openDeleteTour = async (tourCd) => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
    tourCd : tourCd
  }
  await axios.delete(`/tour/delete`,{params});
  getTours();
  getTourPaging();
}