$(() => {
  $('#orgCd > option[value="${gallery.orgCd}"]').attr("selected", true);

  getGallery()
  
  $("select").select2({
    placeholder: "검색",
    theme: 'bootstrap-5'
  });
  
  $("#gallery-file").change(function() {
    handleFileChange(
      this,
      '#gallery-filename', 
      ["jpg", "png"], 
      '#upload-msg', 
      5 * 1024 * 1024, 
      '#gallery-origin-filename',
    );
  });
});

const getGallery = async () => {
	await getOrgs('select');
	let galleryCd = urlParams.get('galleryCd');
  let {data : gallery} = await axios.get(`/gallery/${galleryCd}`);
	
	$('select').val(gallery.orgCd??'none').trigger('change');
	$('#gallery-org').val(gallery.orgCd??'none');
	$('#gallery-org-name').val(gallery.orgFullname??'전체');
	$("#gallery-name").val(gallery.galleryName);
	$('#gallery-sort').val(gallery.gallerySort);
	$("#gallery-filename").val(gallery.galleryFilename);
	$("#gallery-origin-filename").val(gallery.galleryOriginFilename);
}


const upateGallery = async () => {
  let galleryName = $('#gallery-name').val();
	let gallerySort = $('#gallery-sort').val();
  let orgCd = $('#gallery-org').val()
  
  if(!emptyCheck('#gallery-name', '이름을 입력해주세요')) return false;
  if(!emptyCheck('#gallery-sort', '순서를 입력해주세요')) return false;
  if(!emptyCheck('#gallery-filename', '파일을 등록해주세요')) return false;

  let filename = {
  	filename : null,
  	originalFilename : null
  }
  
  try{
  	filename = await saveFile('#gallery-file', 'gallery')
  }catch{
  }
  
  data = {
  	galleryCd : urlParams.get('galleryCd'),
    galleryName : galleryName,
    gallerySort : gallerySort,
    galleryOriginFilename : filename.originalFilename??$('#gallery-origin-filename').val(),
    galleryFilename : filename.filename??$('#gallery-filename').val(),
    orgCd : orgCd
  }
  await axios.put('/gallery/update',data);
  $(location).attr('href', document.referrer);
}