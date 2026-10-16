/* jshint esversion : 6 */
let currentPage = 0;
$(() => {
	getOrgs('#org-cd')
		.then(() => {
			currentPage = Number(urlParams.get('page'));
			let orgCd = urlParams.get('orgCd');
			$('#org-cd').val(orgCd ? orgCd : 'none').trigger('change');
		}).then(() => {
			getGalleries();
		})

	$('#org-cd').select2({
		placeholder: "검색",
		theme: 'bootstrap-5'
	});
	$('#org-cd').change(() => {
		currentPage = 0;
		getGalleries();
	})
});
const getGalleries = async () => {
	let pageSize = 6;
	let params = {
		size: pageSize,
		page: currentPage,
		orgCd: $('#org-cd').val() ?? 'none'
	}
	setPushState(params);
	let galleries;
	try {
		({ data: galleries } = await axios.get(`/gallery/all`, { params }));
	} catch (e) {
		console.log(e.response.data);
		alert("예기치 않은 오류 발생, 관리자에게 문의 바랍니다.");
		return;
	}
	$("#gallery-container").empty()
	for (const gallery of galleries) {
		$("#gallery-container").append(getGalleryDiv(gallery));
	}
	getGalleryPaging();
}

const getGalleryDiv = (gallery) => {
	let galleryDiv = `<div class="col-sm-6 col-md-4">
	  <div class="border rounded">
	    <a href="/gallery/detail?galleryCd=${gallery.galleryCd}">
	      <img src="/resources/upload/gallery/${gallery.galleryFilename}" width="100%" height="240px" onerror="this.src='/resources/assets/images/properties/1.jpg'" />
	    </a>
	    <div class="p-2">
	      <div class="text-center">
	        <h5 class="text-m mb-3">
	          [${gallery.orgName ?? '공통'}] ${gallery.galleryName}
	        </h5>
	        <div class="d-flex justify-content-center">
	          <button class="btn btn-sm btn-outline-primary" onclick='location.href="/gallery/update?galleryCd=${gallery.galleryCd}"'><i class="xi-pen mr-1">수정</i></button>
	          <button class="btn btn-sm btn-outline-danger" onclick="openDeleteGallery(${gallery.galleryCd})"><i class="xi-trash mr-1">삭제</i></button>
	          <button class="btn btn-sm btn-outline-dark" onclick='location.href="/gallery/detail?galleryCd=${gallery.galleryCd}"'><i class="xi-search mr-1">상세</i></button>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>`;
	return galleryDiv;
}

const getGalleryButtonByAcces = (gallery) => {
	if (gallery.access === null || gallery.access === 'my_access' || gallery.access === 'lower_access') {
		return ;
	} else if (gallery.access === 'upper_access' || gallery.access === 'public_access') {
		return '';
	}
}

const getGalleryPaging = async () => {
	getPaging('/gallery/count', { orgCd: $('#org-cd').val() }, 6, currentPage);
}

const movePage = (page) => {
	currentPage = page;
	getGalleries();
	getGalleryPaging();
}

const openDeleteGallery = async (galleryCd) => {
	if (!confirm('삭제하시겠습니까?')) return false;
	let params = {
		galleryCd: galleryCd
	}
	await axios.delete(`/gallery/delete`, { params });
	getGalleries();
	getGalleryPaging();
}