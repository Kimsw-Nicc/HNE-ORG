<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/user/updateOrgPassword.js"></script>
<!-- Main content -->
<section class="container">
  <div id="app">
    <details>
      <summary class="col-md">
        <span class="small float-right text-underline-bold">계정관리 > 계정 비밀번호 변경</span>
        <span class="text-title h2">
          계정 비밀번호 변경<i class="xi-info-o xi-1x text-light"></i>
        </span>
      </summary>
      <div class="media bg-light p-3">
        <i class="xi-info-o xi-4x text-light"></i>
        <div class="ml-3 media-body">
          <h5 class="mt-0">이용안내</h5>
          등록된 계정의 비밀번호를 변경 할 수 있습니다.<br /> <span class="small"> ※ 비밀번호는 bcrypt 알고리즘을 사용하여 저장됩니다. (<a href="https://en.wikipedia.org/wiki/Bcrypt" target="_blank">
              bcrypt 설명보기<i class="xi-share ml-1"></i>
            </a>)
          </span>
        </div>
      </div>
    </details>
    <hr class="dropdown-divider mb-3" />

    <div class="border rounded p-3">
      <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
          <div class="form-horizontal" role="form">
            <!-- section Select -->

            <!-- section_id -->
            <div class="form-group">
              <label class="text-lb">아이디 <span class="small text-danger">*변경불가</span></label>
              <input type="text" class="form-control" placeholder="ID" id="user-id" readonly="readonly" />
            </div>

            <!-- section_pass_ok -->
            <div class="form-group">
              <label class="text-lb">기존 비밀번호<i class="xi-check-min ml-1 input-need"></i></label>
              <input type="password" class="form-control" placeholder="기존 비밀번호 입력" id="user-old-password" required autocomplete="new-password">
              <div id="checkPasswordOldMsg"></div>
            </div>
            <div class="form-group">
              <label class="text-lb">비밀번호 변경<i class="xi-check-min ml-1 input-need"></i></label>
              <input type="password" class="form-control" placeholder="변경할 비밀번호 입력" id="user-password" autocomplete="off" required />
              <div id="user-password-msg">
                <span class="small">영문, 숫자, 특수문자(!@#$%^&amp;*+=-)를 조합한 8자 이상</span>
              </div>
            </div>
            <div class="form-group">
              <label class="text-lb">비밀번호 확인<i class="xi-check-min ml-1 input-need"></i></label>
              <input type="password" class="form-control" placeholder="비밀번호 확인" id="user-check-password" autocomplete="off" required />
              <div id="user-check-password-msg">
                <span class="small"></span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-md-4"></div>
      <div class="col-md-12">
        <div class="form-group text-center">
          <button type="button" class="btn btn-primary" onclick="updatePassword()">
            <i class="xi-check mr-1"></i>수정
          </button>
          <a href="/user/list" class="btn btn-secondary">
            <i class="xi-close mr-1"></i>취소
          </a>
        </div>
      </div>
    </div>
  </div>
</section>

<%@include file="/WEB-INF/views/include/footer.jsp"%>