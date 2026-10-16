const getPaging = async (url, params, contentsPerPage, currentPage, selector=".paging") => {
  // 1. 데이터 가져오기
  const { data: contentsCount } = await axios.get(url, { params });
  const pagesPerGroup = 10;
  const totalPages = Math.ceil(contentsCount / contentsPerPage);

  // 2. 현재 페이지 그룹의 첫 페이지와 마지막 페이지 계산
  const currentGroup = Math.ceil((currentPage + 1) / pagesPerGroup);
  const firstPageInGroup = (currentGroup - 1) * pagesPerGroup + 1;
  const lastPageInGroup = Math.min(currentGroup * pagesPerGroup, totalPages);

  // 3. 이전 및 다음 그룹의 시작 페이지 계산
  const hasPrevGroup = firstPageInGroup > 1;
  const hasNextGroup = lastPageInGroup < totalPages;

  // 4. HTML 버튼 생성 함수
  const createPagingButton = (page, label = page) => {
    const pageIndex = page - 1;
    const isActive = pageIndex === currentPage;
    return `
      <button class="btn btn-outline-secondary" onclick="movePage(${pageIndex})" 
        ${isActive ? 'disabled style="font-weight: bold; color: #202124; background-color: #EBEDED; border-color: #CBD0D3;"' : ""}>
        ${label}
      </button>`;
  };

  // 5. 페이지 버튼 HTML 조립
  let paginationHTML = "";
  if (hasPrevGroup) paginationHTML += createPagingButton(firstPageInGroup - 1, "<");

  for (let page = firstPageInGroup; page <= lastPageInGroup; page++) {
    paginationHTML += createPagingButton(page);
  }

  if (hasNextGroup) paginationHTML += createPagingButton(lastPageInGroup + 1, ">");

  // 6. DOM 업데이트
  $(selector).html(paginationHTML);
};

const setPushState = (params) => {
  let currentUrl = window.location.origin + window.location.pathname;
  let searchParams = new URLSearchParams(params);
  let historyUrl = `${currentUrl}?${searchParams.toString()}`;
  history.pushState(params, "", historyUrl);
};
