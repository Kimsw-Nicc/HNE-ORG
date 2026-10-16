<%@page pageEncoding="UTF-8"%>
<div class="tab-pane fade show active" id="contents" role="contents" aria-labelledby="home-tab">
  <div class="mb-0">
    <table class="table table-hover table-bordered shadow-sm text-center">
      <tr>
        <th>부서명<i class="xi-check input-need"></i>
        </th>
        <td><input type="text" id="org-name" class="form-control" /></td>
      </tr>
      <tr>
        <th>코드<i class="xi-check input-need"></i>
        </th>
        <td><input type="text" id="org-code" class="form-control" readonly /></td>
      </tr>
      <tr>
        <th>상위부서</th>
        <td><input type="text" id="org-upr-code" class="form-control" readonly /></td>
      </tr>
      <tr>
        <th>순서<i class="xi-check input-need"></i>
        </th>
        <td><input type="number" id="org-sort" class="form-control" pattern="[0-9]+" /></td>
      </tr>
      <tr>
        <th>전체명</th>
        <td><input type="text" id="org-fullname" class="form-control" readonly /></td>
      </tr>
      <tr>
        <th>부서위치</th>
        <td><input type="text" id="org-location" class="form-control" /></td>
      </tr>
    </table>
    <div class="row mt-3" style="">
      <div class="col-md-12">
        <button class="btn btn-outline-primary float-right ml-3" onclick="updateOrg()">
          <i class="xi-pen mr-2"></i>수정
        </button>
      </div>
    </div>
  </div>
</div>