<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<title>Изменение пользователя</title>
	<style>
		<%@include file="/resources/css/bootstrap.css" %>
		<%@include file="/resources/css/bootstrap-theme.css" %>
		<%@include file="/resources/css/style.css" %>
	</style>
</head>
<body>
	
	<%@include file="/WEB-INF/jspf/header.jspf" %>
	
	<form:form id="signUp" action="update" method="post" modelAttribute="user" style="margin-top: 60px; margin-bottom: 45px;">
		<form:hidden path="id" value="${user.id}"/>
		<h1>${params.title}</h1>
	
		<fieldset>
			<legend><span class="number">1</span>Базовая информация</legend>
			<p>
				<label class="field_name">Ник:</label>
				<form:errors path="username" cssClass="error"/>
				<form:input path="username"/>
			</p>
			<p>
				<label>Пол:</label>
				<form:errors path="sex" cssClass="error"/>
				<form:radiobuttons path="sex" items="${sexOptions}"/>
			</p>
			<p>
				<label>Возраст:</label>
				<form:errors path="age" cssClass="error"/>
				<form:input path="age"/>
			</p>
			<p>
				<label>Страна:</label>
				<form:errors path="country" cssClass="error"/>
				<form:select path="country">
					<form:options items="${countryOptions}"/>
				</form:select>
			</p>
			<legend><span class="number">2</span>Ваш профиль</legend>
			<p>
				<label class="field_name">Пароль:</label>
				<form:errors path="password" cssClass="error"/>
				<form:input path="password"/>
			</p>
			<p>
				<label class="field_name">Почта:</label>
				<form:errors path="email" cssClass="error"/>
				<form:input path="email"/>
			</p>
		</fieldset>
		<button type="submit">Подтвердить изменения</button>
	</form:form>
	
		

	<%@include file="/WEB-INF/jspf/footer.jspf" %>
	
</body>
</html>