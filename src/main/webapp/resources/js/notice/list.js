let currentPage = 0;
$(() => {
	getOrgs('#org-cd')
		.then(() => {
			currentPage = Number(urlParams.get("page"));
			$('#search-input').val(urlParams.get("keyword") ?? '');
			let orgCd = urlParams.get('orgCd');
			$('#org-cd').val(orgCd ? orgCd : 'none').trigger('change');
		}).then(() => {
			getNotices();
		})

	$('#org-cd').select2({
		placeholder: "검색",
		theme: 'bootstrap-5'
	});
	$('#org-cd').change(() => {
		currentPage = 0;
		getNotices();
	})
	$('#search-input').on("keyup", (key) => {
		if (key.keyCode == 13) {
			currentPage = 0;
			getNotices()
		}
	});
	$('#search-btn').click(() => {
		currentPage = 0;
		getNotices()
	})
});

const getNotices = async () => {
	let pageSize = 10;
	let keyword = $('#search-input').val();
	let params = {
		size: pageSize,
		page: currentPage,
		keyword: keyword,
		orgCd: $('#org-cd').val() ?? 'none'
	}
	setPushState(params);
	let notices;
	try {
		({ data: notices } = await axios.get(`/notice/all`, { params }));
	} catch (e) {
		console.log(e);
		alert("예기치 않은 오류 발생, 관리자에게 문의 바랍니다.");
		return;
	}

	$("tbody").empty()
	for (const notice of notices) {
		$("tbody").append(getNoticeTr(notice));
	}
	getNoticePaging();
}

const getNoticeTr = (notice) => {
	let noticeTr = `<tr class="">
    <td class="text-center">
    <a href="/notice/update?noticeCd=${notice.noticeCd}" class="btn btn-sm btn-outline-secondary">
      <i class="xi-pen mr-1"></i>수정
    </a>
    </td>
    <td class="text-center">${notice.orgName ?? '전체부서'}</td>
    <td class="text-center"><a href="/notice/detail?noticeCd=${notice.noticeCd}" class="text-m">${notice.noticeName}</a></td>
    <td class="text-center">${notice.noticeSort}</td>
    <td class="text-center">
      ${moment(notice.noticeStartDate).format('YYYY-MM-DD')} ~ ${moment(notice.noticeEndDate).format('YYYY-MM-DD')} 
    </td>
    <td class="text-center">${moment(notice.noticeNdt).format('YYYY-MM-DD')}</td>
    <td class="text-center">
    <button class="btn btn-sm btn-outline-danger" onclick="openDeleteNotice(${notice.noticeCd})"><i class="xi-trash"></i></button>
    </td>
  `
	return noticeTr
}

const getNoticeEditButtonByAccess = (notice) => {
	if (notice.access === null || notice.access === 'my_access' || notice.access === 'lower_access') {
		return `<a href="/notice/update?noticeCd=${notice.noticeCd}" class="btn btn-sm btn-outline-secondary">
      <i class="xi-pen mr-1"></i>수정
    </a>`
	} else if (notice.access === 'upper_access' || notice.access === 'public_access') {
		return ''
	}
}

const getNoticeDeleteButtonByAccess = (notice) => {
	if (notice.access === null || notice.access === 'my_access' || notice.access === 'lower_access') {
		return `<button class="btn btn-sm btn-outline-danger" onclick="openDeleteNotice(${notice.noticeCd})"><i class="xi-trash"></i></button>`
	} else if (notice.access === 'upper_access' || notice.access === 'public_access') {
		return ''
	}
}

const getNoticePaging = async () => {
	let params = {
		keyword: $('#search-input').val(),
		orgCd: $('#org-cd').val()
	}
	getPaging('/notice/count', params, 10, currentPage);
}

const movePage = (page) => {
	currentPage = page;
	getNotices();
	getNoticePaging();
}

const openDeleteNotice = async (noticeCd) => {
	if (!confirm('삭제하시겠습니까?')) return false;
	let params = {
		noticeCd: noticeCd
	}
	await axios.delete(`/notice/delete`, { params });
	getNotices();
	getNoticePaging();
}