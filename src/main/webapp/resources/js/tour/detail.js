/* jshint esversion : 6 */
let tourCd = urlParams.get('tourCd');
$(()=>{
	getTour();

	$('#update').click(()=>{
		$(location).attr('href', `/tour/update?tourCd=${tourCd}`);
	})
})

const getTour = async () => {
	let loginOrgCd = $('#login-org-cd').val();
	let {data : tour} = await axios.get(`/tour/${tourCd}`);
	
	$('h3').html(`${tour.tourName}`);
	$('#tour-file').attr("src", `/resources/upload/tour/${tour.tourFilename}`);
}

const goUpdate = () => {
	$(location).attr('href', `/tour/update?tourCd=${tourCd}`);
}

const openDeleteTour = async (tourCd) => {
	if(!confirm('삭제하시겠습니까?')) return false;
	let params = {
		tourCd : urlParams.get('tourCd')
	}
  await axios.delete(`/tour/delete`,{params});
  $(location).attr('href', `/tour/list`);
}