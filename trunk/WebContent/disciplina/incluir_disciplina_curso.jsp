<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<form class="form-horizontal" action="MatriculaServlet" method="post">
<fieldset>

<!-- Form Name -->
<legend>Incluir Discplina no Curso</legend>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="turma">Curso:</label>
  <div class="col-md-4">
    <select id="turma" name="turma" class="form-control">
      <c:forEach var="item" items="${listaCursos}">
	      <option value="${item.id}">${item.nome}</option>
      </c:forEach>

    </select>
  </div>
</div>

<!-- Select Multiple -->
<div class="form-group">
  <label class="col-md-4 control-label" for="alunos">Disciplinas</label>
  <div class="col-md-4">
    <select id="alunos" name="alunos" class="form-control" multiple="multiple">
      <c:forEach var="item" items="${listaDisciplinas}">
      	<option value="${item.id}">${item.nome}</option>
      </c:forEach>
    </select>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for=""></label>
  <div class="col-md-4">
    <button id="" name="" class="btn btn-primary">Salvar</button>
  </div>
</div>

<input type="hidden" name="acao" value="1"/>

</fieldset>
</form>
