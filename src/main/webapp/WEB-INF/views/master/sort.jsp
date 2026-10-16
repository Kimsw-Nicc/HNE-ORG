<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>

<section>
  <button onclick="sortStaff()">직원 정렬</button>
  <button onclick="sortTeam()">팀 정렬</button>
  <button onclick="sortOrg()">부서 정렬</button>
</section>

<script>
const sortStaff = async () =>{
  let data = await axios.put("/master/sort/staff")
}

const sortTeam = async () =>{
  let data = await axios.put("/master/sort/team")
}

const sortOrg = async () =>{
  let data = await axios.put("/master/sort/org")
}
</script>