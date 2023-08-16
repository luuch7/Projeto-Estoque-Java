CREATE TABLE TipoProduto (
  idTipoProduto SERIAL PRIMARY KEY,
  descricao VARCHAR(80)
);

CREATE TABLE Produto (
  idProduto SERIAL PRIMARY KEY,
  nomeProduto VARCHAR(80),
  ultimoPrecoPago INTEGER,
  saldoAtual INTEGER,
  idUnidadeMedida INT,
  idTipoProduto INT,
  FOREIGN KEY (idTipoProduto) REFERENCES TipoProduto(idTipoProduto),
  FOREIGN KEY (idUnidadeMedida) REFERENCES UnidadeMedida(idUnidadeMedida)
);

CREATE TABLE UnidadeMedida (
  idUnidadeMedida SERIAL PRIMARY KEY,
  descricao VARCHAR(80),
  sigla VARCHAR(10)
);

CREATE TABLE MovimentoEstoque (
  idMovimento SERIAL PRIMARY KEY,
  entradaSaida VARCHAR(1),
  documento VARCHAR(80),
  data VARCHAR(10),
  quantidade INTEGER,
  valorMovimento INTEGER,
  idProduto INT,
  idTipoMovimento INT,
  idFuncionario INT,
  FOREIGN KEY (idProduto) REFERENCES Produto(idProduto),
  FOREIGN KEY (idTipoMovimento) REFERENCES TipoMovimento(idTipoMovimento),
  FOREIGN KEY (idFuncionario) REFERENCES Funcionario(idFuncionario)
);

CREATE TABLE TipoMovimento (
  idTipoMovimento SERIAL PRIMARY KEY,
  descricao VARCHAR(80)
);

CREATE TABLE Funcionario (
  idFuncionario SERIAL PRIMARY KEY,
  nomeFuncionario VARCHAR(80)
);

INSERT INTO TipoProduto (descricao) VALUES ('Eletrônico');

INSERT INTO UnidadeMedida (descricao, sigla) VALUES ('Quilograma', 'kg');

INSERT INTO Produto (nomeProduto, ultimoPrecoPago, saldoAtual, idUnidadeMedida) VALUES ('Celular', 1500.00, 10, 1);

INSERT INTO TipoMovimento (descricao) VALUES ('Venda');

INSERT INTO Funcionario (nomeFuncionario) VALUES ('Roberto da Silva');

INSERT INTO MovimentoEstoque (entradaSaida, documento, data, quantidade, valorMovimento, idProduto, idTipoMovimento, idFuncionario)
VALUES ('1', 'Nota Fiscal', '2001-09-11', 2, 1500.00, 1, 1, 1);


create or replace function atualizar_saldo_atual()
returns trigger as $$
begin
	update Produto
	set saldoAtual = saldoAtual + new.quantidade
	where idProduto = new.idProduto;
	return new;
end
$$ 
language plpgsql;

create trigger atualizar_saldo_trigger
after insert or update on MovimentoEstoque for each row
execute procedure atualizar_saldo_atual();