$(()=>{
  getMovie();
  
})

const getMovie = async () => {
  let loginOrgCd = $('#login-org-cd').val();
  let movieCd = urlParams.get('movieCd');
  let {data : movie} = await axios.get(`/movie/${movieCd}`);
  
  $('#movie-title').html(`<h3>[${movie.orgName??'공통'}] ${movie.movieName} </h3>`);
  if(movie.movieFilename.indexOf('.mp4') !== -1){
    $('#movie-content').html(`<iframe class="embed-responsive-item" src="/resources/upload/movie/${movie.movieFilename}"></iframe>`);
  }else{
    $('#movie-content').html(`<img src="/resources/upload/movie/${movie.movieFilename}" width="100%" onerror="this.src='/resources/assets/images/users/unknow.png'" />`);
    $('#movie-content').removeClass('embed-responsive-16by9');
  }
  if(loginOrgCd === '' || loginOrgCd === movie.orgCd){
    $('#movie-buttons').prepend(
    `<a href="/movie/update?movieCd=${movie.movieCd}" class="btn btn-primary"><i class="xi-pen mr-1">수정</i></a>
    <button class="btn btn-danger" onclick="openDeleteMovie()"><i class="xi-trash mr-1">삭제</i></button>`)
  }
}

const openDeleteMovie = async () => {
  if(!confirm('삭제하시겠습니까?')) return false;
  let params = {
      movieCd : urlParams.get('movieCd')
  }
  await axios.delete(`/movie/delete`, {params})
  $(location).attr('href', '/movie/list')
}