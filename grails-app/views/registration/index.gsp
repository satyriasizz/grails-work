<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
    <p>Registration: </p>

    <g:form action="registration">

        <g:if test="${flash.message}">
            <div class="error">
                ${flash.message}
            </div>
        </g:if>

        <dl>
            <dt>User name: </dt>
            <dd><g:textField name="username" /></dd>
            <dt>Password: </dt>
            <dd><g:passwordField name="password" /></dd>
            <dt>Password: </dt>
            <dd><g:passwordField name="passwordRepeat" /></dd>
            <dt>E-mail: </dt>
            <dd><g:textField name="email" /></dd>
            <dt>Phone: </dt>
            <dd><g:textField name="phone" /></dd>

            <dt>Type: </dt>
            <dd>
                Worker: <g:radio name="type" value="Worker" checked="${true}">Worker</g:radio> <br/>
                Employer: <g:radio name="type" value="Employer">Employer</g:radio>
            </dd>

            <dt>
                <g:submitButton name="registration" value="Registration" />
            </dt>

        </dl>

    </g:form>

</body>
</html>