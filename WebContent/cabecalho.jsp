<%@ page import="br.com.senac.model.Aluno"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Academic Net Senac</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript">
	var excluir = function(mensagem) {
		if (mensagem === undefined) {
			mensagem = 'Deseja apagar?';
		}
		
		if (confirm(mensagem)) {
			return true;
		} else {
			return false;
		}
	}
</script>
<style type="text/css">

  /* Sticky footer styles
  -------------------------------------------------- */

  html,
  body {
    height: 100%;
    /* The html and body elements cannot have any padding or margin. */
  }

  /* Wrapper for page content to push down footer */
  #wrap {
    min-height: 100%;
    height: auto !important;
    height: 100%;
    /* Negative indent footer by it's height */
    margin: 0 auto -60px;
  }

  /* Set the fixed height of the footer here */
  #push,
  #footer {
    height: 60px;
  }
  #footer {
    background-color: #f5f5f5;
  }

  /* Lastly, apply responsive CSS fixes as necessary */
  @media (max-width: 767px) {
    #footer {
      margin-left: -20px;
      margin-right: -20px;
      padding-left: 20px;
      padding-right: 20px;
    }
  }



  /* Custom page CSS
  -------------------------------------------------- */
  /* Not required for template or sticky footer method. */

  .container {
    width: auto;
    max-width: 680px;
  }
  .container .credit {
    margin: 20px 0;
  }

</style>
</head>
<body>
<div id="wrap">
<c:if test="${sessionScope.usuarioLogado ne null }">

<div class="btn-toolbar" role="toolbar" style="margin: 0;">
  <div class="btn-group">
    <a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp">
    	&nbsp;<span class="glyphicon glyphicon-home"></span>&nbsp;
    </a>
  </div>
  <div class="btn-group">
    <div class="btn-group">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        Cadastro
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
        <li><a href="${pageContext.request.contextPath}/AlunoServlet">Aluno</a></li>
	    <li><a href="${pageContext.request.contextPath}/CursoServlet">Curso</a></li>
        <li><a href="${pageContext.request.contextPath}/TurmaServlet">Turma</a></li>
        <li><a href="${pageContext.request.contextPath}/DisciplinaServlet">Disciplina</a></li>
      </ul>
    </div>
  </div>
  <div class="btn-group">
    <div class="btn-group">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        Gest&atilde;o
        <span class="caret"></span>
      </button>
      <ul class="dropdown-menu">
        <li><a href="${pageContext.request.contextPath}/MatriculaServlet?acao=4">Matricular Aluno</a></li>
        <!-- <li><a href="${pageContext.request.contextPath}/DisciplinaCursoServlet?acao=4">Incluir Disciplina</a></li>  -->
      </ul>
    </div>
  </div>  
  <div class="btn-group">
    <a class="btn btn-default" href="${pageContext.request.contextPath}/LogoutServlet">
    	&nbsp;<span class="glyphicon glyphicon-log-out"></span>&nbsp;
    </a>
  </div>
</div>

<hr>
</c:if>