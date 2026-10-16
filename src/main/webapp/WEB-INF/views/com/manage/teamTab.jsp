<%@page pageEncoding="UTF-8"%>
<div class="tab-pane fade" id="team" role="team" aria-labelledby="home-tab">
  <div>
    <button class="btn btn-outline-secondary float-right mb-3" onclick="sortTeam()" type="button">
      <i class="xi-sort-asc" style="padding-top: 0.2rem;"></i> 팀 정렬
    </button>
    <button class="btn btn-outline-secondary float-right mb-3" onclick="openInsertTeam()" type="button">
      <i class="xi-plus" style="padding-top: 0.2rem;"></i> 팀 추가
    </button>
    <table class="table table-hover table-bordered shadow-sm text-center">
      <thead>
        <tr class="bg-light">
          <th style="width: 5rem;">표시</th>
          <!--           <th style="width: 5rem;">동기화</th> -->
          <th style="width: 25rem;">명칭</th>
          <th style="width: 15rem;">코드</th>
          <th style="width: 4rem;">순서</th>
          <th style="width: 4rem;">수정</th>
          <th style="width: 4rem;">삭제</th>
        </tr>
      </thead>
      <tbody id="team-tbody" class="bg-white"></tbody>
    </table>
  </div>
</div>