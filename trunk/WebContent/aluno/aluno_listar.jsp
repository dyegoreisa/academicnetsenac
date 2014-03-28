<hr>
Mensagem Scriptlet: <%=request.getAttribute("mensagem") %> <br>
Mensagem EL: ${mensagem } <br>
Mensagem JSTL: <c:out value="${mensagem}"/> <br>
<hr>
<form>
 <input type="text" name="texto">
 <input type="submit" value="Filtrar">
</form>
<table border="1">
	<tr>
		<td>Id</td>
		<td>Nome</td>
		<td>Sobrenome</td>
		<td>E-mail</td>
		<td>Nascimento</td>
		<td colspan="2">Aç&otilde;es</td>
	</tr>
	
	<c:forEach var="item" items="${listaAlunos}">
		<tr>
			<td>${item.id}</td>
			<td>${item.nome}</td>
			<td>${item.sobrenome}</td>
			<td>${item.email}</td>
			<td>${item.dataNascimento}</td>
			<td><a href="AlunoServlet?id=${item.id}">[ A ]</a></td>
			<td><a href="AlunoServlet?id=${item.id}&acao=3">[ X ]</a></td>
		</tr>
	</c:forEach>
	
</table>