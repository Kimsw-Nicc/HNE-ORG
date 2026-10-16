let movieCd = urlParams.get('movieCd');
$(() => {
	getMovie();

	$("select").select2({
		placeholder: "검색",
		theme: 'bootstrap-5'
	});

	// 파일 추가시 정합성 검사
	$("#movie-file").change(function() {
		handleFileChange(this, '#movie-filename', ["mp4", "png", "jpg"], '#upload-msg', 350 * 1024 * 1024, '#movie-origin-filename');
	});
});

const getMovie = async () => {
	await getOrgs('select');

	let { data: movie } = await axios.get(`/movie/${movieCd}`);

	$('select').val(movie.orgCd ?? 'none').trigger('change');
	$('#movie-org').val(movie.orgCd ?? 'none');
	$('#movie-org-name').val(movie.orgFullname ?? '전체');
	$('#movie-name').val(movie.movieName);
	$('#movie-filename').val(movie.movieFilename);
	$('#movie-thumbnail').val(movie.movieThumbnail);
	$('#movie-origin-filename').val(movie.movieOriginFilename);
	$('#movie-sort').val(movie.movieSort);
}

const updateMovie = async () => {
	let movieName = $('#movie-name').val();
	let movieSort = $('#movie-sort').val();
	let orgCd = $('#movie-org').val();

	if (!emptyCheck('#movie-filename', '파일을 등록해주세요.')) return false;
	if (!emptyCheck('#movie-name', '이름을 입력해주세요.')) return false;

	await openProcessModal('파일 업로드 중...');

	let filename = {
		filename: null,
		originalFilename: null,
		thumbnail: null
	}

	try {
		filename = await saveFile('#movie-file', 'movie');
	} catch (e) {
		if (e.response.status == 500) {
			// jcodec 관련 에러 발생
			errorToast('encoding 문제로 파일 저장에 실패하였습니다. 관리자에 문의 바랍니다.');
			await closeProcessModal();
			return
		} else if (e.response.status == 400) {
			// 파일을 안 올린거니까 아무것도 안 함
		}
	}

	let data = {
		movieCd: movieCd,
		movieName: movieName,
		movieOriginFilename: filename.originalFilename ?? $('#movie-origin-filename').val(),
		movieFilename: filename.filename ?? $('#movie-filename').val(),
		movieThumbnail: filename.thumbnail ?? $('#movie-thumbnail').val(),
		movieSort: movieSort,
		orgCd: orgCd
	}

	try {
		await axios.put('/movie/update', data);
	} catch {
		await closeProcessModal();
		return;
	}

	$(location).attr('href', '/movie/list');
}