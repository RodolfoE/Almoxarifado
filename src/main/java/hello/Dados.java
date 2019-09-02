package hello;
import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Driver;  
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Dados {
    //conexão com bd e executador de queries
    private Connection conn;
    private Statement stmt;


    //Itens de conexão com o Postgres
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "postgres"; 
    private String driver = "org.postgresql.Driver";  

    /**
    Inicializa conexão com o Postgre
    */
    private void ObterConexao(){
        try {
            //caso não haja conexão criada, criar uma!
            if (conn == null){
                conn = DriverManager.getConnection(url, user, password);
                stmt = conn.createStatement();
            }            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
        Obter tupla da relação Ordem_Manutencao pela PK Id
    */
    public OrdemManutencao ObterOrdemManutencaoPelaId(int id){
        try{
            ObterConexao();           
            OrdemManutencao om;
            ResultSet rset = stmt.executeQuery("select * from Ordem_Manutencao where Id=" + id);
            //se houver tupla
            if (rset.next()){
                om = new OrdemManutencao(rset.getInt("Id"), rset.getInt("Id_equipamento"), rset.getString("prev_manutencao"));
                return om;
            }
            return null;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            desconectar();   
        }
    }
    
    /**
        Obter todas as tuplas da relação Ordem_Manutencao
    */
    public List<OrdemManutencao> ObterListaOrdemManutencao(){
        try{
            ObterConexao();            
            List<OrdemManutencao> listOm = new ArrayList<OrdemManutencao>();
            ResultSet rset = stmt.executeQuery("Select * from Ordem_Manutencao");
            while (rset.next()){
                listOm.add(new OrdemManutencao(rset.getInt("Id"),
                     rset.getInt("Id_equipamento"), 
                     rset.getString("prev_manutencao")));
            }
            return listOm;
        } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
        } finally {
            desconectar();
        }
    }

    /**
        Cria tupla na relação Ordem_Manutencao.
        attributos: Id_equipamento (FK de Equipamento), prev_manutencao
        @param om: objeto modelo da relação Ordem_Manutencao
     */
    public void InserirOrdemManutencao(OrdemManutencao om) throws Exception {
        ObterConexao();
        stmt.executeUpdate("insert into Ordem_Manutencao(Id_equipamento, prev_manutencao) values(" + om.getIdEquipamento() + " ,'" + om.getPrevManutencao() + "')");
        desconectar();
    }

    /**
        Altera tupla na relação Ordem_Manutencao.
        attributos:Id (PK), Id_equipamento (FK de Equipamento), prev_manutencao
        @param om: objeto modelo da relação Ordem_Manutencao
     */
    public void AlterarOrdemManutencao(OrdemManutencao om) throws Exception{
        ObterConexao();
        stmt.executeUpdate("update Ordem_Manutencao set Id_equipamento=" 
            + om.getIdEquipamento() + ", prev_manutencao='" + om.getPrevManutencao() 
            + "' where Id=" + om.getId());
        desconectar();
    }

    /**
    Insere uma tupla na relação Equipamento
    @param nomeEquip: dado do atributo nome na relação Equipamento
    */
    public void InserirEquipamento(String nomeEquip) throws Exception{
        ObterConexao();            
        stmt.executeUpdate("insert into Equipamento(nome) values('" + nomeEquip + "')");
        desconectar();
    }

    /**
        Obter lista de Equipamentos 
    */
    public List<Equipamento> ObterListaEquipamentos(){
        try{
            ObterConexao();            
            List<Equipamento> listEquipamentos = new ArrayList<Equipamento>();
            ResultSet rset = stmt.executeQuery("Select * from Equipamento");
            while (rset.next()){
                listEquipamentos.add(new Equipamento(rset.getInt("Id"), rset.getString("nome")));
            }
            return listEquipamentos;
        } catch (SQLException e){
           System.out.println(e.getMessage());
           return null;
        } finally {
            desconectar();
        }
    }

    /**
        Obter tupla da relação Equipamento pela PK Id
    */
    public Equipamento ObterEquipamentoPelaId(int id){
        try{
            ObterConexao();           
            Equipamento equip;
            ResultSet rset = stmt.executeQuery("select * from Equipamento where Id=" + id);
            //se houver tupla
            if (rset.next()){
                equip = new Equipamento(rset.getInt("Id"), rset.getString("nome"));
                return equip;
            }
            return null;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        } finally {
            desconectar();   
        }
    }

    /**
        Fecha conexão com banco
        @param con: Conexão com o banco
        @param stmt: executador queries
     */
    private void desconectar(){
        //gerando erro, ver dps pq
        //conn.close();
        //conn = null;
        //stmt.close();
        //stmt = null;
    }
}