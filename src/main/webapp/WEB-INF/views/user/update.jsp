<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<script src="/resources/js/user/update.js"></script>
<section class="container">
	<div id="app">
		<details>
			<summary class="col-md">
				<span class="small float-right text-underline-bold">계정관리 > 계정편집</span>
				<span class="text-title h2">
					계정편집 <i class="xi-info-o xi-1x text-light"></i>
				</span>
			</summary>
			<div class="media bg-light p-3">
				<i class="xi-info-o xi-4x text-light"></i>
				<div class="ml-3 media-body">
					<h5 class="mt-0">이용안내</h5>
					등록된 관리자 정보를 수정할 수 있습니다. 계정ID를 제외한 정보가 수정이 가능합니다. <br />
					<span class="small">
						\※ 비밀번호는 bcrypt 알고리즘을 사용하여 저장됩니다. (<a href="https://en.wikipedia.org/wiki/Bcrypt" target="_blank"> bcrypt 설명보기<i class="xi-share ml-1"></i>
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
						<!-- org Select -->
						<div class="form-group">
							<label class="text-lb"> 관리부서선택 <i class="xi-check-min ml-1 input-need"></i>
							</label> <select id="user-org">
							</select>
						</div>
						<div class="form-group">
							<label class="text-lb">권한(자동선택)</label><br />
							<div class="form-check form-check-inline">
								<input type="radio" name="user-role" id="user-role-admin" value="ADMIN_ROLE" disabled /> <label for="user-role-admin">전체관리자 </label>
							</div>
							<div class="form-check form-check-inline">
								<input type="radio" name="user-role" id="user-role-manager" value="MANAGER_ROLE" disabled /> <label for="user-role-manager">부서관리자 </label>
							</div>
							<div class="form-check form-check-inline">
								<input type="radio" name="user-role" id="user-role-user" value="USER_ROLE" disabled /> <label for="user-role-user">기타관리자</label>
							</div>
						</div>
						<div class="form-group">
							<label class="text-lb"> 이름 <i class="xi-check-min ml-1 input-need"></i> <span class="small ml-1">부서명을 입력하시면 구분이 편리합니다.</span>
							</label> <input type="text" class="form-control" placeholder="이름" id="user-name" maxlength="15" oninput="checkText(event)" autocomplete="off" />
							<div id="text-info">
								<span class="small">최대 10자 이내 (특수문자 제외)</span>
							</div>
						</div>
						<!-- org-cd -->
						<div class="form-group">
							<label class="text-lb"> 아이디 <span class="small input-need">*변경불가</span>
							</label> <input type="text" class="form-control" placeholder="ID" id="user-id" readonly="readonly" autocomplete="off" />
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4"></div>
			<div class="col-md-12">
				<div class="form-group text-center">
					<button type="button" class="btn btn-primary" onclick="updateUser()">
						<i class="xi-check mr-1"></i> 수정
					</button>
					<a href="/user/list" class="btn btn-secondary"> <i class="xi-close mr-1"></i>취소
					</a>
				</div>
			</div>
		</div>
	</div>
	<!-- end row -->
</section>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
