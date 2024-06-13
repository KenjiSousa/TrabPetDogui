package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Raca;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RacaRepository {
    
    private static final String INSERT = 
            "INSERT INTO RACA(DS_RACA) VALUES(?)";
    
    private static final String UPDATE = 
            "UPDATE raca SET ds_raca=? WHERE id=?";
    
    private static final String DELETE = 
            "DELETE FROM raca WHERE id=?";
    
    private static final String FIND_BY_ID = 
            "SELECT id, ds_raca " +
            "FROM raca WHERE id = ?";
    
    private static final String FIND_ALL =
            "SELECT id, ds_raca FROM raca";

    public Raca insert(Raca raca) throws SQLException {
        ResultSet rs = null;
        
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, raca.getDescricao());
            
            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            
            raca.setId(rs.getInt(1));
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return raca;
    }
    
    public Raca update(Raca raca) throws SQLException {
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE)
        ) {
            ps.setString(1, raca.getDescricao());
            ps.setInt(2, raca.getId());
            ps.executeUpdate();
        }

        return raca;
    }

    public Raca findById(int id) throws SQLException {
        ResultSet rs = null;
        Raca retorno = null;
        
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)
        ) {
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
               retorno = new Raca();
               retorno.setId(rs.getInt("ID"));
               retorno.setDescricao(rs.getString("DS_RACA"));
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return retorno;
    }

    public ArrayList<Raca> findAll() throws SQLException {
        ArrayList<Raca> retorno = new ArrayList<>();

        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Raca raca = new Raca();
                raca.setId(rs.getInt("ID"));
                raca.setDescricao(rs.getString("DS_RACA"));

                retorno.add(raca);
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
