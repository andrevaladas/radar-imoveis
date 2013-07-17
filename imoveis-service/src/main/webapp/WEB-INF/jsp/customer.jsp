<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>Profile Page</title>

		<!-- Bootstrap -->
    	<link href="/css/bootstrap.css" rel="stylesheet">
    	<style type="text/css">
	      body {
	        padding-top: 60px;
	        padding-bottom: 40px;
	      }
	    </style>
		<link href="/css/bootstrap-responsive.css" rel="stylesheet">
		<script type="javascript" src="/js/bootstrap.js"></script>
	</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Project name</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn btn-danger">Sign in</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

	<legend><spring:message code="label.title" /></legend>

	<form:form method="post" action="/customer/save" commandName="customer" modelAttribute="customer" class="form-horizontal">
		<div class="control-group">
			<form:label class="control-label" path="name">
				<spring:message code="label.name" />
			</form:label>
			<div class="controls">
				<input type="text" id="name" name="name" placeholder="<spring:message code="label.name" />">
			</div>
		</div>

		<div class="control-group">
    		<div class="controls">
				<input type="submit" class="btn btn-success" value="<spring:message code="label.save"/>" />
			</div>
		</div>
	</form:form>

	<legend>Profile List</legend>
	<c:if test="${!empty customerList}">
		<table class="table table-striped table-bordered table-hover" align="center" style="width: 50%">
			<tr>
				<th><spring:message code="label.id" /></th>
				<th><spring:message code="label.name" /></th>
				<th>&nbsp;</th>
			</tr>
		<c:forEach items="${customerList}" var="entity">
			<tr>
				<td>${entity.id}</td>
				<td>${entity.name}</td>
				<td><a href="delete/${entity.id}"><spring:message code="label.delete" /></a></td>
			</tr>
		</c:forEach>
		</table>
		<div class="pagination pagination-centered pagination-small">
              <ul>
                <li class="disabled"><a href="#">«</a></li>
                <li class="active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">»</a></li>
             </ul>
        </div>
	</c:if>
</body>
</html>
