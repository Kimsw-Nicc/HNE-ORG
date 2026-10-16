<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/header.jsp"%>

<iframe id="iframe" src="${iframeSrc}" style="width:100%; overflow:auto; overflow-x:hidden;"></iframe>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
<script>
    function resizeIframe(iframe) {
        try {
            if (iframe.contentWindow.document.body) {
                iframe.style.height = iframe.contentWindow.document.body.scrollHeight + "px";
            }
        } catch (e) {
            console.warn("Cross-origin iframe height adjustment is restricted.");
        }
    }

    function setupIframe() {
        const iframe = document.getElementById("iframe");

        iframe.onload = function () {
            resizeIframe(iframe);
        };

        // 주기적으로 높이 업데이트 (내부 페이지가 동적으로 변하는 경우)
        setInterval(() => resizeIframe(iframe), 500);
    }

    window.onload = setupIframe;
</script>