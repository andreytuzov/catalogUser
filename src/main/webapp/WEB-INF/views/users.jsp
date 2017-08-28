<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Список пользователей</title>
	<style>
		<%@include file="/resources/css/bootstrap-theme.css" %>
		<%@include file="/resources/css/bootstrap.css" %>
	</style>
	<style>
		table td {
			vertical-align: middle !important;
		}
		table {
			margin-top: 10px;
		}
		
	</style>
</head>
<body>
	<!-- Header -->
	<%@include file="/WEB-INF/jspf/header.jspf" %>
	
	<!-- Таблица с данными пользователей -->					
	<div class="table-responsive col-md-10 col-md-offset-1" style="margin-top: 60px; margin-bottom: 15px;">
	
		<div id="info" class="alert fade in">
			<button class="close" onclick="display_block_info(0);" type="button">x</button>
			<p>${param.info}</p>
		</div>
		
		<c:if test="${userList.size() != 0}">
			<table class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>ID</th>
						<th>Имя</th>
						<th>Почта</th>
						<th>Возраст</th>
						<th>Пол</th>
						<th>Страна</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${userList}">
						<tr data-id="${user.id}">
							<td>
								<div class="checkbox">
									<input type="checkbox"/>
									${user.id}
								</div>
							</td>
							<td>${user.username}</td>
							<td>${user.email}</td>
							<td>${user.age}</td>
							<td>${user.sex}</td>
							<td>${user.country}</td>
							<td>
								<a class="btn" href="users/update?id=${user.id}">Изменить</a>
								<a class="btn" onclick="del(${user.id})">Удалить</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>	
		
	<!-- Footer -->
	<%@include file="/WEB-INF/jspf/footer.jspf" %>
	
	<!-- Scripts -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.1.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootbox.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/dynamic.js"/>"></script>
	
	
</body>
</html>