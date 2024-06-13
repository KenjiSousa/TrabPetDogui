/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Cor;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author andersonbosing
 */
public class CorRepository {
    
    private static final String INSERT = 
            "INSERT INTO COR(DS_COR) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE cor SET ds_cor=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM cor WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, ds_cor " +
            "FROM cor WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, ds_cor FROM cor";
    
    //Insert de uma nova cor
    public Cor insert(Cor cor) throws SQLException {
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(INSERT, 
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, cor.getDescricao());
            
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            rs.next();
            
            cor.setId(rs.getInt(1));
            
        } finally {
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
        }
        
        return cor;
    }
    
    //Update de cor
    public Cor update(Cor cor) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(UPDATE);
            ps.setString(1, cor.getDescricao());
            ps.setInt(2, cor.getId());
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return cor;
    }
    
    //Busca uma unica cor pela pk 
    public Cor findById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Cor retorno = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            pstmt = conn.prepareStatement(FIND_BY_ID);
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
               retorno = new Cor();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricao(rs.getString("DS_COR"));
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null)
                conn.close();
            
        }
        
        return retorno;
        
    }
    
    //Busca todas as Cores cadastradas no banco
    public ArrayList<Cor> findAll() throws SQLException {
        
        ArrayList<Cor> retorno = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            
            conn = new ConnectionFactory().getConnection();
            
            pstmt = conn.prepareStatement(FIND_ALL);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                Cor cor = new Cor();
                cor.setId(rs.getInt("ID"));
                cor.setDescricao(rs.getString("DS_COR"));
                
                retorno.add(cor);
                
            }
            
        } finally {
            
            if (rs != null)
                rs.close();
            
            if (pstmt != null)
                pstmt.close();
            
            if (conn != null) 
                conn.close();
        }
        
        return retorno;
    }
    
    public void delete(int id) throws SQLException {

        Connection conn = null;
        PreparedStatement ps = null;
                
        try {

            conn = new ConnectionFactory().getConnection();

            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, id);
            ps.execute();

        } finally {
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

    }
}
