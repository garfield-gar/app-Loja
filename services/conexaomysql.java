
import java.sql.*;

public class conexaomysql {
    // dados da conexão

    public static String URL ="jdbc:mysql://localhost:3306/detudoumpouco";
    public static String USER = "root";
    public static String PWD="";

    // objetos de conexão 

    private Connection dbconn= null;
    private Statement sqlmgr= null;
    public ResultSet result = null;

    // Atributos last 
    //mostra o ultimo comando executado e o ultimo erro reportado 

    public String Lastsql = "";
    public String lastError= "";
    public int lastCount = -1;
    public boolean conectado=false;

    public void AbrirConexao(){
        try{
            dbconn= DriverManager.getConnection(URL, USER, PWD);
            System.out.println("conecta em " + URL);

            sqlmgr=dbconn.createStatement();
            conectado=true;
        }catch(Exception Error){
            lastError=Error.getMessage();
            conectado=false;
        }
    }
        public void FechaConexao() throws SQLException{
            if(conectado){
                sqlmgr.close();
                dbconn.close();
                conectado=false;
            }
        }

        //Executa um qry de Insert/Update/Delete
        public int ExecutadaQry(String sql){
            lastError="";Lastsql=sql; lastCount=-1;
            if(conectado == false) AbrirConexao();
            try{
                lastCount=sqlmgr.executeUpdate(sql);
            }catch(Exception Error){
                lastError = Error.getMessage();
            }
            return lastCount;
        }
}
