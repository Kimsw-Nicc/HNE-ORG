<%@page pageEncoding="UTF-8"%>
<div class="tab-pane fade" id="staff" role="staff" aria-labelledby="home-tab">
  <div>
    <div class="input-group">
      <select class="form-control col-md-1" id="search-type">
        <option value="org">부서</option>
        <option value="all">전체</option>
      </select>
      <input type="text" id="search-staff-input" class="form-control" aria-label="Example text with button addon" placeholder="직원 이름을 입력해주세요" />
      <button class="btn btn-outline-secondary " type="button" id="search-staff-button">
        <i class="xi-search pt-1"></i>
      </button>
      <button class="btn btn-outline-secondary" onclick="openInsertStaff()" type="button">
        <i class="xi-plus pt-1"></i>
      </button>
      <button class="btn btn-outline-secondary" onclick="sortStaff()" type="button">
        <i class="xi-sort-asc pt-1"></i>직원 정렬
      </button>
    </div>
    <table class="table table-hover table-bordered shadow-sm text-center mt-3">
      <thead>
        <tr class="bg-light">
          <th style="width: 6%;">표시</th>
          <th style="width: 7%;">동기화</th>
          <th style="width: 7%;">사진</th>
          <th style="width: 7%;">순서</th>
          <th style="">부서</th>
          <th>팀 정보</th>
          <th>이름</th>
          <th>직위</th>
          <th style="width: 7%;">재직</th>
<!--           <th style="width: 7%;">재실</th> -->
<!--           <th>직원 번호</th> -->
<!--           <th>직원 이메일</th> -->
          <th style="width: 7%;">수정</th>
          <th style="width: 7%;">삭제</th>
        </tr>
      </thead>
      <tbody id="staff-tbody" class="bg-white"></tbody>
    </table>
  </div>
  <div class="col">
    <ul id="staff-paging" class="pagination justify-content-center"></ul>
  </div>
</div>