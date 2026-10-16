<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/user/insert.js"></script>
<style>
.select2-container {
	display: block;
	width: 100% !important;
}
</style>
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">계정관리 > 계정생성</span>
				<span class="text-title h2">
					계정생성 <i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					관리자 계정을 입력합니다. 부서별 관리자는 아래 부서 목록에서 해당 부서를 선택 후 이름, 아이디, 비밀번호를 입력해주세요. <br> 아이디는 입력 후 반드시 <b>"중복확인"</b> 을 하여야 합니다. ( <i class="xi-check-min ml-1 input-need"></i> 표시가 된 부분은 필수 입력) <br> 권한부여는 '전체관리자'의 경우 모든 기능에 대한 관리권한이 부여되며, '부서관리자' 선택의 경우 상단 메뉴의 <i class="xi-check-circle text-primary"></i>
					표시를 제외한 항목에 대해서만 관리가 가능합니다. <br>
					<span class="small"> ※ 비밀번호는 bcrypt 알고리즘을 사용하여 저장됩니다. </span>
				</div>
			</div>
		</details>
		<hr class="dropdown-divider mb-3" />
		<div class="row">
			<div class="col-md-3">
				<div class="border rounded p-3">
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<label class="text-lb"> 1. 관리부서선택 <i class="xi-check-min ml-1 input-need"></i>
							</label> <select id="user-org">
							</select>
						</div>
						<div class="form-group">
							<label class="text-lb">권한(자동선택)</label>
							<div class="radio radio-warning">
								<input type="radio" name="user-role" id="user-role-admin" value="ADMIN_ROLE" checked disabled> <label for="user-role-admin">전체관리자</label>
							</div>
							<div class="radio radio-success">
								<input type="radio" name="user-role" id="user-role-manager" value="MANAGER_ROLE" disabled> <label for="user-role-manager">부서관리자</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="border rounded p-3">
					<!-- org_name -->
					<div class="form-group">
						<label class="text-lb"> 2. 이름 <i class="xi-check-min ml-1 input-need"></i> <span class="small ml-1">부서명을 입력하시면 구분이 편리합니다.</span>
						</label> <input type="text" class="form-control" placeholder="Name" id="user-name" maxlength="15" oninput="checkText(event)" autocomplete="off">
						<div>
							<span class="small">최대 10자 이내 (특수문자 제외)</span>
						</div>
					</div>
					<!-- id -->
					<div class="form-group">
						<label class="text-lb"> 3. 아이디 <i class="xi-check-min ml-1 input-need"></i>
						</label>
						<div class="input-group">
							<input type="text" class="form-control" placeholder="ID" id="user-id" maxlength="50" autocomplete="off">
							<div class="input-group-prepend">
								<button class="btn btn-dark" id="user-id-check">
									<i class="xi-check-min mr-1"></i> 중복확인
								</button>
							</div>
						</div>
						<div id="user-id-msg">
							<span class="small">최소 4자리 ~ 50자리 이하, 영문 대/소문자, 숫자만 입력</span>
						</div>
					</div>
					<!-- org_pass_ok -->
					<div class="form-group">
						<label class="text-lb"> 4. 비밀번호 <i class="xi-check-min ml-1 input-need"></i>
						</label> <input type="password" class="form-control" placeholder="비밀번호" id="user-password" maxlength="30" autocomplete="new-password">
						<div id="user-password-msg">
							<span class="small">영문, 숫자, 특수문자(!@#$%^&amp;*+=-)를 조합한 8자 이상</span>
						</div>
					</div>
					<div class="form-group">
						<label class="text-lb"> 5. 비밀번호확인 <i class="xi-check-min ml-1 input-need"></i>
						</label> <input type="password" class="form-control" placeholder="비밀번호 확인" id="user-check-password" maxlength="30" autocomplete="off">
						<div id="user-check-password-msg"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<div class="form-group text-center">
					<button class="btn btn-primary" onclick="insertUser()">
						<i class="xi-check mr-1"></i> 생성
					</button>
					<a href="/user/list" class="btn btn-secondary"> <i class="xi-close mr-1"></i>취소
					</a>
				</div>
			</div>
		</div>
	</div>
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
