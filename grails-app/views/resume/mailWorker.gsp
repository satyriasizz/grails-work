<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <style type="text/css">
        span {
            margin: 20px 5px 5px 5px;
        }
    </style>
</head>

<body>

    <g:form action="sendEmail">
        <dl>
        <dt><span>Mail to: </span></dt><dd> <g:textField name="email" value="${email}" /></dd>

        <dt><span>Subject: </span></dt><dd> <g:textField name="subject" value="Work offer"/></dd>

        <dt><span>Body: </span></dt><dd> <g:textArea name="boby" value="Print your offer where"/></dd>

        <g:submitButton name="sendEmail" value="Send" />
        </dl>
    </g:form>

</body>
</html>