<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@include file="/WEB-INF/views/include/header.jsp"%>
<style>
#table, th, td{
	border: 1px solid black
}
th{
	width: auto
}
td{
	padding-left: 7px
}
</style>
<section>
  <table id="table">
    <tr>
        <th onclick="sortTable(0)">부서명</th>
        <th onclick="sortTable(1)">부서 풀네임</th>
    </tr>
    <c:forEach items="${orgList}" var="org">
      <tr>
        <td>${org.orgName}</td>
        <td>${org.orgFullname}</td>
      </tr>
    </c:forEach>
  </table>
</section>


<%@include file="/WEB-INF/views/include/footer.jsp"%>

<script>
const sortTable = (n) => {
  const table = document.getElementById('table');
  const rows = Array.from(table.getElementsByTagName('tr')).slice(1); // Ignore header row
  let dir = table.getAttribute('data-sort-dir') === 'asc' ? 'desc' : 'asc';

  rows.sort((a, b) => {
    const x = a.getElementsByTagName('td')[n].textContent;
    const y = b.getElementsByTagName('td')[n].textContent;

    if (dir === 'asc') {
      return x.localeCompare(y);
    } else {
      return y.localeCompare(x);
    }
  });

  // Toggle the sort direction
  table.setAttribute('data-sort-dir', dir);

  // Reorder the rows in the table
  rows.forEach(row => table.appendChild(row));
};

</script>