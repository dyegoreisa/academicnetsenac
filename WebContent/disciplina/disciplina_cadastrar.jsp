<form class="form-horizontal" action="DisciplinaServlet" method="post">
<fieldset>

<!-- Form Name -->
<legend>Cadastrar Disciplina</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nome">Nome:</label>  
  <div class="col-md-4">
				<input id="nome" name="nome" type="text" placeholder=""
					class="form-control input-md" required=""
					value="${DisciplinaEditar.nome}">

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
      <c:when test="${DisciplinaEditar.id eq null}">
      	<input type="hidden" name="acao" value="1">
      </c:when>
      <c:otherwise>
	    <input type="hidden" name="id" value="${DisciplinaEditar.id }">
        <input type="hidden" name="acao" value="2">
      </c:otherwise>
</c:choose>

</fieldset>
</form>