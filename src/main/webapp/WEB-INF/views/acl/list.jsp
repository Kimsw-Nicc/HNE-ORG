<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/acl/list.js"></script>
<script src="/resources/assets/js/sockjs-0.3.min.js"></script>
<script src="/resources/assets/js/stomp.min.js"></script>
<section class="container">
  <div id="app">
    <div id="title">
      <div class="row">
        <div class="col-md">
          <span class="small float-right text-underline-bold">서버접근권한 관리 > 서버접근권한 조회</span>
          <h2 class="text-title">서버접근권한 관리</h2>
        </div>
      </div>
    </div>
    <!-- end page title end breadcrumb -->
    <div class="row">
      <div class="col">
        <div class="media bg-light p-3">
          <i class="xi-info-o xi-4x text-light"></i>
          <div class="ml-3 media-body">
            <h5 class="mt-0">이용안내</h5>
            서버접근권한을 조회, 등록, 삭제가 가능한 페이지입니다.
            <br>
            서버접근권한 등록 시 우측 상단의 "서버접근권한 등록" 버튼을 클릭하여 서버접근권한을 등록할 수 있습니다.
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-3" id="allSection1">
        <select id="ip-used" class="form-control">
          <option value="1">접근 허용</option>
          <option value="0">접근 제한</option>
        </select>
      </div>
      <div class="col">
        <button onclick="openInsertAcl()" class="float-right btn btn-dark">
          <i class="xi-plus mr-1"></i>서버접근권한 등록
        </button>
        <%-- <input type='hidden' name='page' value="${cri.page}">
				<input type='hidden' name='perPageNum' value="${cri.perPageNum}"> --%>
      </div>
    </div>
    <div class="col-md-12">
      <div class="tab-content">
        <div class="form-group">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th style="text-align: center">등록 정보</th>
                  <th style="text-align: center">부서/담당</th>
                  <th style="text-align: center">구분</th>
                  <th style="text-align: center">수정</th>
                  <th style="text-align: center">삭제</th>
                </tr>
              </thead>
              <tbody>

              </tbody>
              <tfoot>
                <tr>
                  <td colspan="5"></td>
                </tr>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="">
          <ul class="pagination justify-content-center">
          </ul>
        </div>
      </div>
    </div>
  </div>
</section>
<div class="modal fade" id="insert-acl-modal" tabindex="-1" role="dialog" aria-labelledby="imageModalModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="imageModalLabel">서버접근권한 등록</h4>
        <%-- data-dismiss="modal" --%>
        <button type="button" class="close" data-dismiss="modal">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
        <div style="margin: 10px;">
          <div>
            <label class="text-lb">구분</label>
          </div>
          <div class="form-check form-check-inline custom-checkbox">
            <input type="radio" class="form-check-input insert-acl-type" id="insert-acl-typ-manager" name="insert-acl-type" value="manager" checked>
            <label class="form-check-label" for="insert-acl-type-manager">관리자</label>
          </div>
          <div class="form-check form-check-inline custom-checkbox">
            <input type="radio" class="form-check-input insert-acl-type" id="insert-acl-type-equipment" name="insert-acl-type" value="equipment">
            <label class="form-check-label" for="insert-acl-type-equipment">장비</label>
          </div>
        </div>
        <div class="col-md-12">
          <label class="text-lb">ip</label>
          <div class="input-group">
            <input id="insert-acl-ip" class="form-control" />
          </div>
        </div>
        <div class="col-md-12">
          <label class="text-lb">부서/담당</label>
          <div class="input-group">
            <input id="insert-acl-name" class="form-control" />
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="insertAcl()">
          <i class="xi-share mr-1"></i>ip 등록
        </button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="update-acl-modal" tabindex="-1" role="dialog" aria-labelledby="imageModalModalLabel" aria-hidden="true">
  <input type="hidden" class="form-check-input" id="update-acl-id">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="imageModalLabel">서버접근권한 수정</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
        <div style="margin: 10px;">
          <div>
            <label class="text-lb">구분</label>
          </div>
          <div class="form-check form-check-inline custom-checkbox">
            <input type="radio" class="form-check-input update-acl-type" id="update-acl-type-manager" name="update-acl-type" value="manager">
            <label class="form-check-label" for="update-acl-type-manager">관리자</label>
          </div>
          <div class="form-check form-check-inline custom-checkbox">
            <input type="radio" class="form-check-input update-acl-type" id="update-acl-type-equipment" name="update-acl-type" value="equipment">
            <label class="form-check-label" for="update-acl-type-equipment">장비</label>
          </div>
        </div>
        <div class="col-md-12">
          <label class="text-lb">ip</label>
          <div class="input-group">
            <input id="update-acl-ip" class="form-control" />
          </div>
        </div>
        <div class="col-md-12">
          <label class="text-lb">부서/담당</label>
          <div class="input-group">
            <input id="update-acl-name" class="form-control" />
          </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="updateAcl()">
          <i class="xi-share mr-1"></i>ip 수정
        </button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          <i class="xi-close mr-1"></i>취소
        </button>
      </div>
    </div>
  </div>
</div>
<%@include file="/WEB-INF/views/include/footer.jsp"%>