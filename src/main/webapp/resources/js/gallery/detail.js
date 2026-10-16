$(()=>{
	getGallery()
	
	$('#update').click(()=>{
		$(location).attr('href', `/gallery/update?galleryCd=${urlParams.get('galleryCd')}`)
	})
})

const getGallery = async () => {
	let loginOrgCd = $('#login-org-cd').val()
	let galleryCd = urlParams.get('galleryCd')
	let {data : gallery} = await axios.get(`/gallery/${galleryCd}`)
	
	$('#gallery-name').html(`[${gallery.orgName??'공통'}] ${gallery.galleryName}`)
	$('#gallery-file').attr("src", `/resources/upload/gallery/${gallery.galleryFilename}`);
	if(loginOrgCd === '' || loginOrgCd === gallery.orgCd){
		$('#gallery-buttons').prepend(`<a class="btn btn-primary" href="/gallery/update?galleryCd=${gallery.galleryCd}"><i class="xi-pen mr-1">수정</i></a>
		<button class="btn btn-danger" onclick="openDeleteGallery()"><i class="xi-trash mr-1">삭제</i></button>`)
	}
}

const openDeleteGallery = async () => {
	if(!confirm('삭제하시겠습니까?')) return false;
	let params = {
		galleryCd : urlParams.get('galleryCd')
	}
	await axios.delete(`/gallery/delete`, {params});
	$(location).attr('href', document.referrer);
}