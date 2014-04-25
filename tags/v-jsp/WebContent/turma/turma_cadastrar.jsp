<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<form class="form-horizontal" action="TurmaServlet" method="post">
<fieldset>

<!-- Form Name -->
<legend>Cadastrar Turma</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nome">Nome:</label>  
  <div class="col-md-4">
  <input id="nome" name="nome" type="text" placeholder="" class="form-control input-md" required="" value="${turmaEditar.nome}">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="data_inicio">Data Inicio:</label>  
  <div class="col-md-4">
  <input id="data_inicio" name="data_inicio" type="text" placeholder="" class="form-control input-md" required="" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${turmaEditar.dataInicio}" />">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="data_fim">Data Fim:</label>  
  <div class="col-md-4">
  <input id="data_fim" name="data_fim" type="text" placeholder="" class="form-control input-md" required="" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${turmaEditar.dataFim}" />">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="previsao">Previsão de termino</label>  
  <div class="col-md-4">
  <input id="previsao" name="previsao" type="text" placeholder="" class="form-control input-md" required="" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${turmaEditar.previsaoTermino}" />">
    
  </div>
</div>

<!-- Select Basic -->
<div class="form-group">
  <label class="col-md-4 control-label" for="curso">Curso:</label>
  <div class="col-md-4">
    <select id="curso" name="curso" class="form-control">
	    <c:forEach var="item" items="${listaCursos }">
	      <option value="${item.id}" <c:if test="${turmaEditar.curso.id eq item.id}">selected="true"</c:if> > ${item.nome}</option>
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

 <c:choose>
    <c:when test="${turmaEditar.id eq null}">
    	<input type="hidden" name="acao" value="1">
    </c:when>
    <c:otherwise>
   <input type="hidden" name="id" value="${turmaEditar.id }">
      <input type="hidden" name="acao" value="2">
    </c:otherwise>
</c:choose>

</fieldset>
</form>