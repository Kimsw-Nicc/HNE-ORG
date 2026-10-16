<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:eval expression="@basic['menu.acl']" var='acl'></spring:eval>
<spring:eval expression="@basic['menu.layout']" var='layout'></spring:eval>
<spring:eval expression="@basic['menu.tour']" var='tour'></spring:eval>
<spring:eval expression="@basic['menu.equipment']" var='equipment'></spring:eval>
<spring:eval expression="@basic['menu.sync']" var='sync'></spring:eval>
<ul class="navbar-nav mr-auto">
  <li class="nav-item" id="dashboard-menu"><a class="nav-link" href="/dashboard/main">
      <i class="xi-home mr-2"></i>Main
      <span class="sr-only">(current)</span>
    </a></li>
  <li class="nav-item" id="user-menu"><a href="/user/list" class="nav-link">
      <i class="xi-key mr-2"></i>계정관리
    </a></li>
  <li class="nav-item" id="com-menu"><a href="/com/manage" class="nav-link">
      <i class="xi-users mr-2"></i>조직관리
    </a></li>
  <li class="nav-item dropdown" id="gallery-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <i class="xi-image mr-2"></i>포토갤러리
    </a>
    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
      <a class="dropdown-item" href="/gallery/list">포토갤러리조회</a>
      <a class="dropdown-item" href="/gallery/insert">포토갤러리등록</a>
    </div></li>
  <li class="nav-item dropdown" id="notice-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <i class="xi-document mr-2"></i>공지사항
    </a>
    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
      <a class="dropdown-item" href="/notice/list">공지사항조회</a>
      <a class="dropdown-item" href="/notice/insert">공지사항등록</a>
    </div></li>
  <li class="nav-item dropdown" id="extention-menu"><a class="nav-link dropdown-toggle" href="#" id="CreateDropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <i class="xi-package mr-2"></i>확장시스템
    </a>
    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
      <a class="dropdown-item" href="/basicsetting/manage">
        기본설정 관리<i class="xi-check-circle text-primary"></i>
      </a>
<!--       <a class="dropdown-item" href="/layout/list"> -->
<!--         의회배치도<i class="xi-check-circle text-primary"></i> -->
<!--       </a> -->
      <hr class="dropdown-divider" />
      <a class="dropdown-item" href="/guide/main?buildingId=0">
        청사안내도관리 <i class="xi-check-circle text-primary"></i>
      </a>
      <c:if test="${layout}">
        <a class="dropdown-item" href="/layout/list">
          좌석배치도관리 <i class="xi-check-circle text-primary"></i>
        </a>
      </c:if>
      <a class="dropdown-item" href="/alert/list">
        알리미관리 <i class="xi-check-circle text-primary"></i>
      </a>
      <a class="dropdown-item" href="/background/list">
        배경이미지관리 <i class="xi-check-circle text-primary"></i>
      </a>
      <c:if test="${tour}">
        <a class="dropdown-item" href="/tour/list">
          관광안내 <i class="xi-check-circle text-primary"></i>
        </a>
      </c:if>
      <a class="dropdown-item" href="/movie/list">홍보콘텐츠</a>
      <hr class="dropdown-divider" />
      <c:if test="${sync}">
        <a class="dropdown-item" href="/sync/scheduler">
          동기화 관리<i class="xi-check-circle text-primary"></i>
        </a>
      </c:if>
      <a class="dropdown-item" href="/patch/list">
        패치파일관리<i class="xi-check-circle text-primary"></i>
      </a>
    </div></li>
</ul>