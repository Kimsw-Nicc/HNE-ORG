<%@ page import="egovframework.nicc.hne.auth.model.dto.AuthUserDTO"%>
<%@ page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@basic['area']" var='area'></spring:eval>
<spring:eval expression="@basic['areaBuilding']" var='areaBuilding'></spring:eval>
<spring:eval expression="@basic['version']" var="version"></spring:eval>
<spring:eval expression="@basic['menu.org']" var="org"></spring:eval>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Smart Ogarnization Chart Management System">
<meta name="author" content="NICC">
<link rel="shortcut icon" href="/resources/assets/images/favicon.ico">
<title>한눈에조직도 - ${area}</title>
<script src="/resources/assets/js/jquery.3.2.1.min.js"></script>
<link href="/resources/assets/css/fonts.css" rel="stylesheet" />
<link href="/resources/assets/css/bootstrap-nicc.css" rel="stylesheet" />
<link href="/resources/assets/css/fonts.css" rel="stylesheet" />
<link href="/resources/assets/css/dropdown.css" rel="stylesheet" />
<link href="/resources/plugins/toastr/toastr.min.css" rel="stylesheet" />
<link href="/resources/plugins/xeicon/xeicon.min.css" rel="stylesheet" />
<link href="/resources/plugins/switchery/switchery.min.css" rel="stylesheet">
<link href='/resources/plugins/bootstrap-icons/bootstrap-icons.min.css' />
<link href="/resources/plugins/magnific-popup/css/magnific-popup.css" rel="stylesheet" />
<link href="/resources/plugins/select2/css/select2.css" rel="stylesheet" />
<link href="/resources/plugins/select2/css/select2-bootstrap-5-theme.min.css" rel="stylesheet" />
<link href="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.css" rel="stylesheet" />
<link href="/resources/assets/css/file-input.css" rel="stylesheet">

<script src="/resources/assets/js/bootstrap.min.js"></script>
<script src="/resources/assets/js/popper.min.js"></script>
<script src="/resources/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/plugins/bootstrap-toggle/bootstrap-toggle.min.js"></script>
<script src="/resources/plugins/select2/js/select2.min.js"></script>
<script src="/resources/plugins/toastr/toastr.min.js"></script>
<script src="/resources/plugins/interact/interact.min.js"></script>
<script src="/resources/assets/js/datepicker.js"></script>
<script src="/resources/assets/js/modernizr.min.js"></script>
<script src="/resources/assets/js/dropdown.js"></script>
<script src="/resources/assets/js/fabric.js"></script>
<script src="/resources/assets/js/browserChecker.js"></script>
<script src="/resources/assets/js/inko.min.js"></script>
<script src="/resources/assets/js/axios.min.js"></script>
<script src="/resources/assets/js/moment.min.js"></script>
<script src="/resources/assets/js/util.js"></script>
<script src="/resources/assets/js/paging.js"></script>
<script src="/resources/assets/js/fileupload.js"></script>
<script src="/resources/assets/js/hne.app.js"></script>
<style>
  summary {
    list-style-type: none;
    padding-left: 0px !important;
  }
  summary::-webkit-details-marker {
    display: none;
  }
</style>
</head>
<script>
  function confirmLogout() {
    return confirm("로그아웃하시겠습니까?");
  }
</script>
<body>
<%@include file="./processModal.jsp"%>
<input class="d-none" value="${login.orgCd}" id="login-org-cd" />
<input class="d-none" value="${login.userRole}" id="login-role" />
<nav class="navbar navbar-expand-sm navbar-dark bg-header">
  <div class="container">
    <a class="navbar-brand" href="/dashboard/main">
      <img src="/resources/assets/hne-oc-logo-light.png" width="210px" class="mr-2" /><span class="mr-3" style="border-right: 2px solid #404460;"></span><b>${areaBuilding}</b>
    </a>

    <button class="navbar-toggler float-right" type="button" data-toggle="collapse" data-target="#topNav" aria-controls="topNav" aria-expanded="false" aria-label="Toggle navigation">+</button>

    <div class="float-right">
      <div class="collapse navbar-collapse text-white" id="topNav">
        <ul class="navbar-nav navbar-right">
          <li class="nav-item">
            <a class="nav-link" href='/user/updateOrgPassword?userId=${login.userId}'>
              <span class="small"><i class="xi-user mr-1"></i> ${login.userName} (${login.userId})</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-danger" href='/logout' onclick="return confirmLogout()">
              <span class="small"><i class="xi-log-out mr-1"></i> Logout</span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</nav>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
  <div class="container">
  	<button style="width: 100%" class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-nav" aria-controls="main-nav" aria-expanded="false" aria-label="Toggle navigation">
      <i><span class="font-weight-bold">Menu</span></i>
  	</button>
    <div class="collapse navbar-collapse" id="main-nav">
      <c:if test="${'ADMIN_ROLE' == login.userRole}">
        <%@include file="./header/headerAdmin.jsp"%>
      </c:if>
      <c:if test="${'MANAGER_ROLE' == login.userRole}">
        <%@include file="./header/headerManager.jsp"%>
      </c:if>
    </div>
  </div>
</nav>
<div class="container mt-4">	