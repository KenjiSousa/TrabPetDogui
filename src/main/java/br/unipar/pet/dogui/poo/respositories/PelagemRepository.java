package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Pelagem;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class PelagemRepository {
    
    private static final String INSERT = 
            "INSERT INTO PELAGEM(DS_PELAGEM) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE pelagem SET ds_pelagem=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM pelagem WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, ds_pelagem " +
            "FROM pelagem WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, ds_pelagem FROM pelagem";
    
    
    public Pelagem insert(Pelagem pelagem) throws SQLException {
        ResultSet rs = null;

        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, pelagem.getDescricao());
            
            pstmt.executeUpdate();
           
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            
            pelagem.setId(rs.getInt(1));
            
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return pelagem;
    }
    
    
    public Pelagem update(Pelagem pelagem) throws SQLException {
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE)
        ) {

            ps.setString(1, pelagem.getDescricao());
            ps.setInt(2, pelagem.getId());
            ps.executeUpdate();

        }

        return pelagem;
    }
    
     
    public Pelagem findById(int id) throws SQLException {
        ResultSet rs = null;
        Pelagem retorno = null;
        
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)
        ) {
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Pelagem();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricao(rs.getString("DS_PELAGEM"));
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return retorno;
    }
    
    
    public ArrayList<Pelagem> findAll() throws SQLException {
        ArrayList<Pelagem> retorno = new ArrayList<>();

        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {

                Pelagem pel = new Pelagem();
                pel.setId(rs.getInt("ID"));
                pel.setDescricao(rs.getString("DS_PELAGEM"));

                retorno.add(pel);
            }
        }
        
        return retorno;
    }
    
    public void delete(int id) throws SQLException {
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement ps = conn.prepareStatement(DELETE)
        ) {

            ps.setInt(1, id);
            ps.execute();
        }
    }
}
