let currentPage = 0;
$(() => {
	getOrgs('#org-cd')
		.then(() => {
			currentPage = Number(urlParams.get("page"));
			$('#search-input').val(urlParams.get("keyword") ?? '');
			let orgCd = urlParams.get('orgCd');
			$('#org-cd').val(orgCd ? orgCd : 'none').trigger('change');
		}).then(() => {
			getMovies();
		})

	$('#org-cd').select2({
		placeholder: "검색",
		theme: 'bootstrap-5'
	});
	$('#org-cd').change(() => {
		currentPage = 0;
		getMovies();
	})
});

const getMovies = async () => {
	let pageSize = 6
	let params = {
		size: pageSize,
		page: currentPage,
		orgCd: $('#org-cd').val() ?? 'none'
	}
	setPushState(params);
	let { data: movies } = await axios.get(`/movie/all`, { params })
	$("#movie-container").empty()
	for (const movie of movies) {
		$("#movie-container").append(getMovieDiv(movie))
	}
	getMoviePaging();
}

const getMovieDiv = (movie) => {
	let movieDiv = `<div class="col-sm-6 col-md-4 d-flex">
    <div class="border rounded w-100">
      <a href="/movie/detail?movieCd=${movie.movieCd}">
      ${movie.movieFilename.indexOf('.mp4') !== -1 ? `<video width="100%">
          <source src="/resources/upload/movie/${movie.movieFilename}" type="video/mp4">
          <source src="movie.ogg" type="video/ogg">
        </video>`
			: `<img src="/resources/upload/movie/${movie.movieFilename}" alt="contentImg" title="contentImg" width="100%" style="max-height: 240px;" onerror="this.src='/resources/assets/images/users/unknow.png'"/>`}
      </a>
      <div class="p-3">
        <div class="text-center">
          <h5 class="text-m mb-3">
            <a href="/movie/detail?movieCd=${movie.movieCd}">
              <h5 class="text-center text-m">
                [${movie.orgName ?? '공통'}] ${movie.movieName}
              </h5>
            </a>
          </h5>
          <div class="d-flex justify-content-center">
            <a class="btn btn-sm btn-outline-primary" href="/movie/update?movieCd=${movie.movieCd}"><i class="xi-pen mr-1">수정</i></a>
            <button class="btn btn-sm btn-outline-danger" onclick="openDeleteMovie(${movie.movieCd})"><i class="xi-trash mr-1">삭제</i></button>
            <button class="btn btn-sm btn-outline-dark" onclick='location.href="/movie/detail?movieCd=${movie.movieCd}"'><i class="xi-search mr-1">상세</i></button>
          </div>
        </div>
      </div>
    </div>
  </div>`
	return movieDiv
}

const getMoviePaging = async () => {
	getPaging('/movie/count', { orgCd: $('#org-cd').val() }, 6, currentPage);
}

const movePage = (page) => {
	currentPage = page
	getMovies();
}

const openDeleteMovie = async (movieCd) => {
	if (!confirm('삭제하시겠습니까?')) return false;
	let params = {
		movieCd: movieCd
	}
	await axios.delete(`/movie/delete`, { params })
	getMovies()
}