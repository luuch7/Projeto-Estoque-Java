<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header2.jsp"/>
<jsp:include page="/menu2.jsp"/>
        <br/><br/>
        <h2>Tipo Movimento</h2>
        <table class="table table-hover table-dark">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Descrição</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="tipomovimento" items="${tipomovimentos}">
                <tr>
                    <td align="left">${tipomovimento.idTipoMovimento}</td>
                    <td align="left">${tipomovimento.descricao}</td>
                    <td align="center">
                    <a href="${pageContext.request.contextPath}/TipoMovimentoExcluir?idTipoMovimento=${tipomovimento.idTipoMovimento}">
                        Excluir</a></td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/TipoMovimentoCarregar?idTipoMovimento=${tipomovimento.idTipoMovimento}">
                        Alterar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/TipoMovimentoNovo">Novo</a>
               <a href="index.jsp">Voltar a pagina inicial</a>
        </div>
               
<script>
    $(document).ready(function (){
        console.log('entrei ready');
        //carregamo a database
        //$("#datable").DataTable({});
        $('#datatable').dataTable({
            "oLanguge":{
                "sProcessing":"Processando...",
                "sLengthMenu":"Mostrar _MENU_ registros",
                "sZeroRecords":"Nenhum registro encontrado",
                "sInfo":"Mostrando de _START_ ate _END_ de _TOTAL_ registros",
                "sInfoEmpty":"Mostrando de 0 ate 0 de 0 registros",
                "sInfoFiltered":"",
                "sInfoPostFix":"",
                "sSearch":"Buscar",
                "sUrl":"",
                "oPaginate":{
                    "sFirst": "Primeiro",
                    "sPrevius": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Ultimo"
                }
            }
        });
    });

</script>
<%@include file="/footer.jsp" %>
        