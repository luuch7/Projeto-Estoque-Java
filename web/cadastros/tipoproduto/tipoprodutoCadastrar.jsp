<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="iso-8859-1" %>
<jsp:include page="/header2.jsp"/>
<jsp:include page="/menu2.jsp"/>

<form name="cadastrartipoproduto" action="TipoProdutoCadastrar" method="POST">
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">Cadastro de TipoProduto</th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID: </td>
            <td><input type="text" name="idtipoproduto" id="idtipoproduto" value="${tipoproduto.idTipoProduto}" readonly="readonly"/></td></tr>
            <tr><td>Descricao: </td>
            <td><input type="text" name="descricao" id="descricao" value="${tipoproduto.descricao}"
                    size="50" maxlength="50"/></td></tr>
            <tr><td colspan="2" align="center">
                    <button type="submit" class="btn btn-outline-success" name="cadastrar" id="cadastrar">Cadastrar</button>
                    <button type="reset" class="btn btn-outline-danger" name="limpar" id="limpar">Limpar</button>
                </td> </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar á Página Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
</form>
<%@include file="/footer.jsp" %>