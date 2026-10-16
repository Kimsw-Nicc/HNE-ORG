<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>

	<script type="text/javascript">
		(function() {
			var result = '${fn:escapeXml(msg)}'
			if (result) {
				alert(result)
			}
		}());
		//해당 경로로 이동하는 것은 LoginInterceptor에서 처리해준다
		self.location = "/";
	</script>
</body>
</html>

