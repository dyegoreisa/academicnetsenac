<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aluno</title>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<form class="form-horizontal" action="AlunoServlet" method="post">
<fieldset>

<!-- Form Name -->
<legend>Cadastrar Aluno</legend>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="nome">Nome:</label>  
  <div class="col-md-4">
  <input id="nome" name="nome" type="text" placeholder="" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="sobrenome">Sobrenome:</label>  
  <div class="col-md-4">
  <input id="sobrenome" name="sobrenome" type="text" placeholder="" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="email">E-mail</label>  
  <div class="col-md-4">
  <input id="email" name="email" type="text" placeholder="" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Text input-->
<div class="form-group">
  <label class="col-md-4 control-label" for="data_nascimento">Data de Nascimento:</label>  
  <div class="col-md-4">
  <input id="data_nascimento" name="data_nascimento" type="date" placeholder="" class="form-control input-md" required="">
    
  </div>
</div>

<!-- Multiple Radios (inline) -->
<div class="form-group">
  <label class="col-md-4 control-label" for="sexo">Sexo:</label>
  <div class="col-md-4"> 
    <label class="radio-inline" for="sexo-0">
      <input type="radio" name="sexo" id="sexo-0" value="M" checked="checked">
      Masculino
    </label> 
    <label class="radio-inline" for="sexo-1">
      <input type="radio" name="sexo" id="sexo-1" value="F">
      Feminino
    </label>
  </div>
</div>

<!-- Button -->
<div class="form-group">
  <label class="col-md-4 control-label" for=""></label>
  <div class="col-md-4">
  	<input type="hidden" name="acao" value="1">
    <button id="" name="" class="btn btn-primary">Salvar</button>
  </div>
</div>

</fieldset>
</form>

<script src="bootstrap/js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>