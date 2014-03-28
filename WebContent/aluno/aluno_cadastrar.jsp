<form class="form-horizontal" action="AlunoServlet" method="post">
<fieldset>

<!-- Form Name -->
<legend>Cadastrar Aluno</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nome">Nome:</label>  
  <div class="col-md-4">
  <input id="nome" name="nome" type="text" placeholder="" class="form-control input-md" required="" value="${alunoEditar.nome}">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="sobrenome">Sobrenome:</label>  
  <div class="col-md-4">
  <input id="sobrenome" name="sobrenome" type="text" placeholder="" class="form-control input-md" required="" value="${alunoEditar.sobrenome}">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">E-mail</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="" class="form-control input-md" required="" value="${alunoEditar.email}">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="data_nascimento">Data de Nascimento:</label>  
  <div class="col-md-4">
  <input id="data_nascimento" name="data_nascimento" type="date" placeholder="" class="form-control input-md" required="" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${alunoEditar.dataNascimento}" />">
    
  </div>
</div>

<!-- Multiple Radios (inline) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="sexo">Sexo:</label>
  <div class="col-md-4"> 
    <label class="radio-inline" for="sexo-0">
    <c:choose>
	      <c:when test="${alunoEditar.sexo eq 'M'}">
	      	<input type="radio" name="sexo" id="sexo-0" value="M" checked="checked">
	      </c:when>
	      <c:otherwise>
	      	<input type="radio" name="sexo" id="sexo-0" value="M">
	      </c:otherwise>
	</c:choose>
      Masculino
    </label> 
    <label class="radio-inline" for="sexo-1">
    <c:choose>
	      <c:when test="${alunoEditar.sexo eq 'F'}">
	      	<input type="radio" name="sexo" id="sexo-1" value="F" checked="checked">
	      </c:when>
	      <c:otherwise>
	      	<input type="radio" name="sexo" id="sexo-1" value="F">
	      </c:otherwise>
	</c:choose>
      Feminino
    </label>
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
      <c:when test="${alunoEditar.id eq null}">
      	<input type="hidden" name="acao" value="1">
      </c:when>
      <c:otherwise>
	    <input type="hidden" name="id" value="${alunoEditar.id }">
        <input type="hidden" name="acao" value="2">
      </c:otherwise>
</c:choose>


</fieldset>
</form>