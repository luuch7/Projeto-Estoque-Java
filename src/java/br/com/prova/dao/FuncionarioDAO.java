package br.com.prova.dao;

import br.com.prova.model.Funcionario;
import br.com.prova.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements GenericDAO{
    private Connection conexao;
    
    public FuncionarioDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        Boolean retorno = false;
        if(oFuncionario.getIdFuncionario()==0){
            retorno = this.inserir(oFuncionario);
        }else{
            retorno = this.alterar(oFuncionario);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        PreparedStatement stmt = null;
        String sql = "insert into Funcionario(nomeFuncionario) values(?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncionario.getNomeFuncionario());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao cadastrar Funcionario! Erro: "+ ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.err.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean alterar(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        PreparedStatement stmt = null;
        String sql = "update Funcionario set nomeFuncionario=? where idFuncionario=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncionario.getNomeFuncionario());
            stmt.setInt(2, oFuncionario.getIdFuncionario());
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            try {
                System.out.println("Problemas ao alterar Funcionario! Erro: "+ ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Eroo: "+ e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idFuncionario = numero;
        PreparedStatement stmt = null;
        String sql = "delete from Funcionario where idFuncionario=?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            stmt.execute();
            conexao.commit();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir Funcionario! Erro: "+ex.getMessage());
            try {
                conexao.rollback();
            } catch (SQLException e) {
                System.out.println("Erro rollback "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Object carregar(int numero) {
        int idFuncionario = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario oFuncionario = null;
        String sql = "select * from Funcionario where idFuncionario=?";
        
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            rs = stmt.executeQuery();
            while(rs.next()){
                oFuncionario = new Funcionario();
                oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
            }
            return oFuncionario;
        } catch (Exception e) {
            System.out.println("Problemas ao carregar Funcionari! Erro: "+e.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select * from Funcionario order by idFuncionario";
        try {
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                Funcionario oFuncionario = new Funcionario();
                oFuncionario.setIdFuncionario(rs.getInt("idFuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                resultado.add(oFuncionario);
            }
        } catch (SQLException e) {
            System.out.println("Problemas ao listar Funcionario! Erro: "+ e.getMessage());
        }
        return resultado;
    }
    
}
