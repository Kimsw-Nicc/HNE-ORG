<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<spring:eval expression="@basic['com.type']" var="type"></spring:eval>

<ul class="navbar-nav mr-auto">
	<li class="nav-item" id="dashboard-menu"><a class="nav-link" href="/dashboard/main"><i class="xi-home mr-2"></i>Main <span class="sr-only">(current)</span></a></li>


	<li class="nav-item" id="com-menu"><a href="/com/manage" class="nav-link"><i class="xi-users mr-2"></i>조직관리</a></li>

	<li class="nav-item dropdown" id="gallery-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="xi-image mr-2"></i>포토갤러리
	</a>
		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			<a class="dropdown-item" href="/gallery/list">포토갤러리조회</a> <a class="dropdown-item" href="/gallery/insert">포토갤러리등록</a>
		</div></li>

	<li class="nav-item dropdown" id="notice-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="xi-document mr-2"></i>공지사항
	</a>
		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			<a class="dropdown-item" href="/notice/list">공지사항조회</a> <a class="dropdown-item" href="/notice/insert">공지사항등록</a>
		</div></li>
	<li class="nav-item dropdown" id="extention-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="xi-package mr-2"></i>확장시스템
	</a>
		<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			<a class="dropdown-item" href="/movie/list">홍보콘텐츠</a>
		</div></li>
</ul>
