<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header2.jsp"/>
<jsp:include page="/menu2.jsp"/>


<form name="cadastrarproduto" action="ProdutoCadastrar"
      method="POST">
          
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Produto
                    
                </th>
            </tr>
            <tr>
                <th colspan="2" align="center">${mensagem}</th>
            </tr>
        </thead>
        <tbody>
            <tr><td>ID: </td>
            <td><input type="text" name="idproduto" id="idproduto" value="${produto.idProduto}" readonly="readonly"/></td></tr>
            <tr><td>Nome: </td>
                <td><input type="text" name="nomeproduto" id="nomeproduto" value="${produto.nomeProduto}" size="50" maxlength="50" /></td></tr>    
            <tr><td>Ultimo Preço: </td>
                <td><input type="text" name="ultimoprecopago" id="ultimoprecopago" value="${produto.ultimoPrecoPago}" size="50" maxlength="50" /></td></tr>    
            <tr><td>Saldo Atual: </td>
                <td><input type="text" name="saldoatual" id="saldoatual" value="${produto.saldoAtual}" size="50" maxlength="50" /></td></tr>    
            <tr>
                <td>Sigla: </td>
                <td>
                    <select name="idunidademedida" id="idunidademedida">
                        <option value="">Selecione</option><!-- comment -->
                       
                        <c:forEach var="unidademedida" items="${unidadeMedidas}">
                            <option value="${unidademedida.idUnidadeMedida}"
                                    ${produto.unidadeMedida.idUnidadeMedida == unidadeMedida.idUnidadeMedida ? "selected" : ""}>
                                    ${unidademedida.sigla}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>Tipo Produto: </td>
                <td>
                    <select name="idtipoproduto" id="idtipoproduto">
                        <option value="">Selecione</option><!-- comment -->
                       <c:forEach var="tipoProduto" items="${tipoProdutos}">
                            <option value="${tipoProduto.idTipoProduto}"
                                    ${produto.tipoProduto.idTipoProduto == tipoProduto.idTipoProduto ? "selected" : ""}>
                                    ${tipoProduto.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr><td colspan="2" align="center">
                    <button type="submit" class="btn btn-outline-success" name="cadastrar" id="cadastrar">Cadastrar</button>
                    <button type="reset" class="btn btn-outline-danger" name="limpar" id="limpar">Limpar</button>
                </td> </tr>
            <tr>
                <td align="center" colspan="2"><h5><a href="index.jsp">Voltar a Página Inicial</a></h5></td>
            </tr>
        </tbody>
    </table>
    
</form>
    <%@ include file="/footer.jsp"%>