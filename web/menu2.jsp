<nav class="navbar navbar-expand-lg navbar-light">
  <!-- Container wrapper -->
    <link href="css/modelo.css" rel="stylesheet" type="text/css"/>
  <div class="container-fluid">
    <!-- Toggle button -->
    <button
      class="navbar-toggler"
      type="button"
      data-mdb-toggle="collapse"
      data-mdb-target="#navbarCenteredExample"
      aria-controls="navbarCenteredExample"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <i class="fas fa-bars"></i>
    </button>

    <!-- Collapsible wrapper -->
    <div
      class="collapse navbar-collapse justify-content-center"
      id="navbarCenteredExample"
    >
      <!-- Left links -->
      <ul class="navbar-nav mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">APLESTOQUE</a>
        </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/ProdutoListar">PRODUTO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/FuncionarioListar">FUNCIONARIO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/ProdutoListar">PRODUTO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/MovimentoEstoqueListar">MOVIMENTO ESTOQUE</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/TipoProdutoListar">TIPO DE PRODUTO</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/UnidadeMedidaListar">UNIDADE DE MEDIDA</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/TipoMovimentoListar">TIPO MOVIMENTO</a>
            </li>
      </ul>
    </div>
  </div>
</nav>
