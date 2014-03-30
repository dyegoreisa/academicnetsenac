<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="form-group">
  <div class="col-md-4">
    <a class="btn btn-default" href="${pageContext.request.contextPath}/TurmaServlet?acao=6">
    	&nbsp;<span class="glyphicon glyphicon-plus"></span>&nbsp;Novo
    </a>
  </div>
</div>

<form class="form-inline" action="TurmaServlet" method="post">
<fieldset>

<!-- Select Basic -->
<div class="form-group">
  <div class="col-md-4">
    <select id="curso" name="curso" class="form-control">
    	<option value="0">---</option>
	    <c:forEach var="item" items="${listaCursos }">
	      <option value="${item.id}" <c:if test="${cursoBusca.id eq item.id}">selected="true"</c:if> > ${item.nome}</option>
	    </c:forEach>
    </select>
  </div>
</div>

<!-- Search input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="conteudo"></label>
  <div class="col-md-4">
    <input id="conteudo" name="conteudo" type="search" placeholder="Turma" class="form-control input-md">
    
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for=""></label>
  <div class="col-md-4">
    <button id="" name="" class="btn btn-primary">Filtrar</button>
  </div>
</div>

<input type="hidden" name="acao" value="5">

</fieldset>
</form>
<hr>
<table class="table table-striped table-hover">
	<thead>
	<tr>
		<th>Turma</th>
		<th>Data Inicio</th>
		<th>Data Fim</th>
		<th>Previsão Termino</th>
		<th>Curso<th>
		<th colspan="2">Aç&otilde;es</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="item" items="${listaTurmas}">
		<tr>
			<td>${item.nome}</td>
			<td><fmt:formatDate value="${item.dataInicio}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${item.dataFim}" pattern="dd/MM/yyyy"/></td>
			<td><fmt:formatDate value="${item.previsaoTermino}" pattern="dd/MM/yyyy"/></td>
			<td>${item.curso.nome}</td>
			<td>
				<a href="TurmaServlet?id=${item.id}&acao=4">
					<span class="glyphicon glyphicon-edit"></span>
				</a>
			</td>
			<td>
				<a href="TurmaServlet?id=${item.id}&acao=3" onclick="return excluir();" >
					<span class="glyphicon glyphicon-remove-sign"></span>
				</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	
</table>