<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Smart Ogarnization Chart Management System">
<meta name="author" content="NICC">
<spring:eval expression="@basic['area']" var="area"></spring:eval>
<spring:eval expression="@basic['version']" var="version"></spring:eval>
<title>한눈에조직도 - ${area}</title>
<link rel="shortcut icon" href="/resources/assets/images/favicon.ico">
<script src="/resources/assets/js/moment.min.js"></script>
<link href="/resources/assets/css/bootstrap-nicc.css" rel="stylesheet" type="text/css" />
<link href="/resources/assets/css/fonts.css" rel="stylesheet" type="text/css" />
<link href="/resources/plugins/xeicon/xeicon.min.css" rel="stylesheet" type="text/css" />
<script src="/resources/assets/js/axios.min.js"></script>
<script src="/resources/assets/js/jquery.3.2.1.min.js"></script>
<script src="/resources/assets/js/popper.min.js"></script>
<script src="/resources/assets/js/btsrap.min.js"></script>
<script src="/resources/assets/js/util.js"></script>
<script src="/resources/js/login.js"></script>
<style>
body {
  /* height: 100%vh; */
  background-color: #f5f5f5;
}

.loginBox {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100vh;
}

.loginItem {
  width: 400px;
  background-color: #ffffff;
  padding: 40px;
  border-radius: 15px;
}

.titleName {
  background-color: #1E202D;
  border-radius: 10px;
}

.titleName>.areaName {
  background-color: #21BCB9;
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
  border-bottom-left-radius: 9px;
  border-bottom-right-radius: 9px;
  font-size: 15pt;
}
</style>
</head>
<body>
  <div class="loginBox">
    <div class="loginItem shadow-lg">
      <div class="titleName shadow text-center" style="">
        <img src="/resources/assets/hne-oc-logo-light.png" alt="" height="25" style="margin-right: 10px;" class="rounded p-4 img-fluid">
        <div class="text-center areaName">
          <p class="" style="padding: 3px; color: #fff;">
            <b>
              ${area}
            </b>
          </p>
        </div>
      </div>
      <hr>
      <div class="form-group ">
        <div class="col-xs-12">
          <input id="user-id" class="form-control" type="text" placeholder="아이디" value="" onkeydown="doEnter()" required autofocus>
          <div id="check-id-msg"></div>
        </div>
      </div>
      <input style="display: none" aria-hidden="true">
      <input type="password" style="display: none" aria-hidden="true">
      <div class="form-group">
        <div class="col-xs-12">
          <input id="user-pw" class="form-control" type="password" placeholder="비밀번호" value="" onkeydown="doEnter()" autocomplete="new-password" required>
          <div id="check-pw-msg"></div>
        </div>
      </div>
      <div class="form-group">
        <div class=" icheck text-center">
          <span class="small">비밀번호 분실 시 시스템 관리자에게 문의하십시오.</span>
        </div>
      </div>
      <div class="form-group account-btn text-center m-t-10">
        <div class="col-xs-12">
          <button class="btn shadow-sm btn-lg btn-block btn-dark" type="button" onclick="return doLogin()">로그인</button>
        </div>
      </div>
      <span class="float-right small">
        v
        ${version}
      </span>
    </div>
  </div>
</body>
</html>