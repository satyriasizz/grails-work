<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <g:if test="${flash.message}">
        <div class="error">
            ${flash.message}
        </div>
    </g:if>

    <g:each in="${resumes}" status="i" var="resume">
        <tr>
            <td>${resume.purpose}</td>
            <td>${resume.edu}</td>
            <td>${resume.exp}</td>
        </tr>
    </g:each>

</body>
</html>