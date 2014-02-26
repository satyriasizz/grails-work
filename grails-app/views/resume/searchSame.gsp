<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<table>
    <tr style="background-color: #999999">
        <td>Purpose</td>
        <td>Education</td>
        <td>Experience</td>
    </tr>
    <g:each in="${result}" status="i" var="resume">
    <tr>
        <td>${resume.purpose}</td>
        <td>${resume.edu}</td>
        <td>${resume.exp}</td>
    </tr>
    </g:each>
</table>
</body>
</html>