package br.com.prova.dao;

import br.com.prova.model.TipoMovimento;
import br.com.prova.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoMovimentoDAO implements GenericDAO{
    private Connection conexao;
    
    public TipoMovimentoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
        TipoMovimento oTipoMovimento = (TipoMovimento) objeto;
        Boolean retorno = false;
        if(oTipoMovimento.getIdTipoMovimento()==0){
            retorno = this.inserir(oTipoMovimento);
        }else{
            retorno = this.alterar(oTipoMovimento);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        TipoMovimento oTipoMovimento = (TipoMovimento) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into TipoMovimento(descricao) values(?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoMovimento.getDescricao());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar TipoMovimento! Erro: "+ ex.getMessage());
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
        TipoMovimento oTipoMovimento = (TipoMovimento) objeto;
        PreparedStatement stmt = null;
        String sql = "update TipoMovimento set descricao=? where idTipoMovimento=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oTipoMovimento.getDescricao());
            stmt.setInt(2, oTipoMovimento.getIdTipoMovimento());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar TipoMovimento! Erro: "+ ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro: "+ e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idTipoMovimento = numero;
        PreparedStatement stmt = null;
        String sql = "delete from TipoMovimento where idTipoMovimento=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipoMovimento);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir TipoMovimento! Erro: "+ ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rollback "+ e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idTipoMovimento = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        TipoMovimento oTipoMovimento = null;
        String sql = "select * from TipoMovimento where idTipoMovimento=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idTipoMovimento);
            rs = stmt.executeQuery();
            while(rs.next()){
                oTipoMovimento = new TipoMovimento();
                oTipoMovimento.setIdTipoMovimento(rs.getInt("idTipoMovimento"));
                oTipoMovimento.setDescricao(rs.getString("descricao"));
            }
            return oTipoMovimento;
        } catch (Exception e) {
            System.out.println("Problemas ao carregar TipoMovimento! Erro: "+e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from TipoMovimento order by idTipoMovimento";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                TipoMovimento oTipoMovimento = new TipoMovimento();
                oTipoMovimento.setIdTipoMovimento(rs.getInt("idTipoMovimento"));
                oTipoMovimento.setDescricao(rs.getString("descricao"));
                resultado.add(oTipoMovimento);
            }
        } catch (SQLException e) {
            System.out.println("Problemas ao listar TipoMovimento! Erro: "+e.getMessage());
        }
        return resultado;
    }
    
    
}
