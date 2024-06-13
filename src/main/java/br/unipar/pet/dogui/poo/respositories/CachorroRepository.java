package br.unipar.pet.dogui.poo.respositories;

import br.unipar.pet.dogui.poo.domain.Cachorro;
import br.unipar.pet.dogui.poo.infraestructure.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;


public class CachorroRepository {
    
    private static final String INSERT = """
            INSERT INTO CACHORRO(NOME,
                                 VL_TAMANHO,
                                 ST_PERFUME,
                                 DT_NASCIMENTO,
                                 ID_RACA,
                                 ID_PELAGEM,
                                 ID_COR)
              VALUES(?, ?, ?, ?, ?, ?, ?)""";
    
    private static final String UPDATE = """
            UPDATE CACHORRO
               SET NOME = ?,
                   VL_TAMANHO = ?,
                   ST_PERFUME = ?,
                   DT_NASCIMENTO = ?,
                   ID_RACA = ?,
                   ID_PELAGEM = ?,
                   ID_COR = ?
             WHERE ID = ?""";
    
    private static final String DELETE = 
            "DELETE FROM cachorro WHERE id=?";
    
    private static final String FIND_BY_ID = """
            SELECT NOME,
                   VL_TAMANHO,
                   ST_PERFUME,
                   DT_NASCIMENTO,
                   ID_RACA,
                   ID_PELAGEM,
                   ID_COR
            FROM CACHORRO WHERE ID = ?""";
    
    private static final String FIND_ALL = """
            SELECT ID,
                   NOME,
                   VL_TAMANHO,
                   ST_PERFUME,
                   DT_NASCIMENTO,
                   ID_RACA,
                   ID_PELAGEM,
                   ID_COR
            FROM CACHORRO""";
    
    
    public Cachorro insert(Cachorro cachorro) throws SQLException {
        ResultSet rs = null;
        
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(INSERT,
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, cachorro.getNome());
            pstmt.setDouble(2, cachorro.getTamanho());
            pstmt.setBoolean(3, cachorro.isStPerfume());
            pstmt.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            pstmt.setInt(5, cachorro.getRaca().getId());
            pstmt.setInt(6, cachorro.getPelagem().getId());
            pstmt.setInt(7, cachorro.getCor().getId());

            pstmt.executeUpdate();
            
            rs = pstmt.getGeneratedKeys();
            
            rs.next();
            
            cachorro.setId(rs.getInt(1));
            
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return cachorro;
    }
    
    
    public Cachorro update(Cachorro cachorro) throws SQLException {
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement ps = conn.prepareStatement(UPDATE)
        ) {
            ps.setString(1, cachorro.getNome());
            ps.setDouble(2, cachorro.getTamanho());
            ps.setBoolean(3, cachorro.isStPerfume());
            ps.setDate(4, new java.sql.Date(cachorro.getDtNascimento().getTime()));
            ps.setInt(5, cachorro.getRaca().getId());
            ps.setInt(6, cachorro.getPelagem().getId());
            ps.setInt(7, cachorro.getCor().getId());
            ps.setInt(8, cachorro.getId());

            ps.executeUpdate();
        }

        return cachorro;
    }
    
     
    public Cachorro findById(int id) throws SQLException {
        ResultSet rs = null;
        Cachorro retorno = null;
        
        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_BY_ID)
        ) {
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                retorno = new Cachorro();
                retorno.setId(rs.getInt("ID"));
                retorno.setNome(rs.getString("NOME"));
                retorno.setTamanho(rs.getDouble("VL_TAMANHO"));
                retorno.setStPerfume(rs.getBoolean("ST_PERFUME"));
                retorno.setDtNascimento(rs.getDate("DT_NASCIMENTO"));
                retorno.setRaca(new RacaRepository().findById(rs.getInt("ID_RACA")));
                retorno.setPelagem(new PelagemRepository().findById(rs.getInt("ID_PELAGEM")));
                retorno.setCor(new CorRepository().findById(rs.getInt("ID_COR")));
            }
        } finally {
            if (rs != null)
                rs.close();
        }
        
        return retorno;
    }
    
    
    public ArrayList<Cachorro> findAll() throws SQLException {
        ArrayList<Cachorro> retorno = new ArrayList<>();

        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(FIND_ALL);
                ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {

                Cachorro cachorro = new Cachorro();
                cachorro.setId(rs.getInt("ID"));
                cachorro.setNome(rs.getString("NOME"));
                cachorro.setTamanho(rs.getDouble("VL_TAMANHO"));
                cachorro.setStPerfume(rs.getBoolean("ST_PERFUME"));
                cachorro.setDtNascimento(rs.getDate("DT_NASCIMENTO"));
                cachorro.setRaca(new RacaRepository().findById(rs.getInt("ID_RACA")));
                cachorro.setPelagem(new PelagemRepository().findById(rs.getInt("ID_PELAGEM")));
                cachorro.setCor(new CorRepository().findById(rs.getInt("ID_COR")));

                retorno.add(cachorro);
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
