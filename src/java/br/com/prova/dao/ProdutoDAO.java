package br.com.prova.dao;

import br.com.prova.model.Produto;
import br.com.prova.model.TipoProduto;
import br.com.prova.model.UnidadeMedida;
import br.com.prova.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements GenericDAO{
    private Connection conexao;
    
    public ProdutoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        Boolean retorno = false;
        if(oProduto.getIdProduto()==0){
            retorno = this.inserir(oProduto);
        }else{
            retorno = this.alterar(oProduto);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into Produto(nomeProduto,ultimoPrecoPago,saldoAtual,idUnidadeMedida,idTipoProduto) values(?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setDouble(2, oProduto.getUltimoPrecoPago());
            stmt.setDouble(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.setInt(5, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar Produto! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Produto oProduto = (Produto) objeto;
        PreparedStatement stmt = null;
        String sql = "update Produto set nomeProduto=?,ultimoPrecoPago=?, saldoAtual=?, idTipoProduto=?, idUnidadeMedida=? where idProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oProduto.getNomeProduto());
            stmt.setInt(2, oProduto.getUltimoPrecoPago());
            stmt.setInt(3, oProduto.getSaldoAtual());
            stmt.setInt(4, oProduto.getTipoProduto().getIdTipoProduto());
            stmt.setInt(5, oProduto.getUnidadeMedida().getIdUnidadeMedida());
            stmt.setInt(6, oProduto.getIdProduto());
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception ex){
            try{
                System.out.println("Problemas ao alterar o Produto! Erro:");
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        
            String sql = "delete from Produto where idProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex){
            System.out.println("Problemas ao excluir o Produto! Erro: "
                    +ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e){
                System.out.println("Erro rollback"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idProduto = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Produto oProduto = null;
        String sql = "select * from Produto where idProduto=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idProduto);
            rs=stmt.executeQuery();
            while(rs.next()){
                oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setUltimoPrecoPago(rs.getInt("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getInt("saldoAtual"));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida")));
                
                TipoProdutoDAO oTipoProdutoDAO = new TipoProdutoDAO();
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idTipoProduto")));
            }
            return oProduto;
        } catch (Exception e) {
            System.out.println("Problemas ao carregar Produto! Erro: "+e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from Produto order by idProduto";
        try {
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while(rs.next()){
                Produto oProduto = new Produto();
                oProduto.setIdProduto(rs.getInt("idProduto"));
                oProduto.setNomeProduto(rs.getString("nomeProduto"));
                oProduto.setUltimoPrecoPago(rs.getInt("ultimoPrecoPago"));
                oProduto.setSaldoAtual(rs.getInt("saldoAtual"));
                
                UnidadeMedidaDAO oUnidadeMedidaDAO = null;
                TipoProdutoDAO oTipoProdutoDAO = null;
                try {
                    oUnidadeMedidaDAO = new UnidadeMedidaDAO();
                } catch (Exception ex) {
                    System.out.println("Erro em UnidadeMedidaDAO: "+ex.getMessage());
                    ex.printStackTrace();
                }
                oProduto.setUnidadeMedida((UnidadeMedida) oUnidadeMedidaDAO.carregar(rs.getInt("idUnidadeMedida")));
                try {
                    oTipoProdutoDAO = new TipoProdutoDAO();
                } catch (Exception e) {
                    System.out.println("Erro ao buscar as TipoProdutoDAO "+ e.getMessage());
                    e.printStackTrace();
                }//se der erro tirar os maiusculos
                oProduto.setTipoProduto((TipoProduto) oTipoProdutoDAO.carregar(rs.getInt("idTipoProduto")));
                resultado.add(oProduto);
            }
        } catch (SQLException e) {
            System.out.println("Problemas ao Listar Produto! Erro: "+e.getMessage());
            
        }
        return resultado;
    }
    
}
