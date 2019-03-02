package poketwofx;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DatabaseConnections {

    public static Connection getConnections() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connection = "jdbc:sqlserver://localhost;databaseName=POKEMON";
        conn = DriverManager.getConnection(connection, "TestingUser", "1234");
        return conn;
    }

    public void createAccount(String username, String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        conn = DatabaseConnections.getConnections();
        String userid = "ply" + Integer.toString(checkRegisteredMembers());
        if (!validateExistance(username)) {
            String cmd = "INSERT INTO Accounts" + " VALUES ('" + userid + "', '" + username + "', '" + password + "', 'offline', 0,0)";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, " Account successfully created.");
        } else {
            JOptionPane.showMessageDialog(null, "An account using that username was already created.");
        }
    }

    public int checkRegisteredMembers() throws ClassNotFoundException, SQLException {
        int miembros = 0;
        String querycheck = "SELECT COUNT(*) as Cuantos from Accounts;";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            miembros = rs.getInt(1) + 1;
        }
        return miembros;
    }

    public int getMaxPokemon() throws ClassNotFoundException, SQLException {
        int poke = 0;
        String querycheck = "SELECT COUNT(*) as Cuantos from Pokemon;";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            poke = rs.getInt(1) + 1;
        }
        return poke;
    }

    public boolean validateExistance(String username) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        int miembros = 0;
        String querycheck = "SELECT COUNT(*) from Accounts where username='" + username + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            miembros = rs.getInt(1);
        }
        if (miembros > 0) {
            exists = true;
        }
        return exists;
    }

    public boolean validatePassword(String username, String password) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        int miembros = 0;
        String querycheck = "SELECT COUNT(*) from Accounts where username='" + username + "' AND password='" + password + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            miembros = rs.getInt(1);
        }
        if (miembros > 0) {
            exists = true;
        }
        return exists;
    }

    public void setStatus(boolean isConnected, String username) throws SQLException, ClassNotFoundException {
        String querycheck = "UPDATE Accounts set status= ? where username=?;";
        String elected;
        if (isConnected) {
            elected = "online";
        } else {
            elected = "offline";
        }
        PreparedStatement ps = getConnections().prepareStatement(querycheck);
        ps.setString(1, elected);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
    }

    public void addWin(String username) throws SQLException, ClassNotFoundException {
        String querycheck = "UPDATE Accounts set victories=victories+1 where username=?;";
        PreparedStatement ps = getConnections().prepareStatement(querycheck);
        ps.setString(1, username);
        ps.executeUpdate();
        ps.close();
    }

    public void addLoss(String username) throws SQLException, ClassNotFoundException {
        String querycheck = "UPDATE Accounts set defeats=defeats+1 where username=?;";
        PreparedStatement ps = getConnections().prepareStatement(querycheck);
        ps.setString(1, username);
        ps.executeUpdate();
        ps.close();
    }

    public int getWins(String username) throws SQLException, ClassNotFoundException {
        int wins = 0;
        String querycheck = "SELECT victories from Accounts where username='" + username + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            wins = rs.getInt(1);
        }
        return wins;
    }

    public int getDefeats(String username) throws SQLException, ClassNotFoundException {
        int defeats = 0;
        String querycheck = "SELECT defeats from Accounts where username='" + username + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            defeats = rs.getInt(1);
        }
        return defeats;
    }

    public ArrayList getPokemonNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "SELECT name from Pokemon;";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("name"));
        }
        return as;
    }

    public String getRandomPokemon(int index) throws SQLException, ClassNotFoundException {
        String as = "";
        String querycheck = "Select * from Pokemon;";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        int x = 1;
        while (rs.next()) {
            if (x == index) {
                as = rs.getString("name");
                break;
            }
            x++;
        }
        return as;
    }

    public ArrayList getTeamNames(String username) throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "Select * from Teams where owner='" + username + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("teamname"));
        }
        return as;
    }

    public ArrayList getPokemonMoves(String name) throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "Select * from Pokemon where name='" + name + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("move1"));
            as.add(rs.getString("move2"));
            as.add(rs.getString("move3"));
            as.add(rs.getString("move4"));
        }
        return as;
    }

    public ArrayList<String> getTeamMembers(String username, String teamname) throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "select * from Teams where teamname='" + teamname + "' and owner='" + username + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("pokemon1"));
            as.add(rs.getString("pokemon2"));
            as.add(rs.getString("pokemon3"));
            as.add(rs.getString("pokemon4"));
            as.add(rs.getString("pokemon5"));
            as.add(rs.getString("pokemon6"));
        }
        return as;
    }

    public ArrayList<String> getPokemonStats(String pokemon) throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "Select * from Pokemon where name='" + pokemon + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("pokemonid"));
            as.add(rs.getString("name"));
            as.add(rs.getString("type1"));
            as.add(rs.getString("type2"));
            as.add(rs.getString("move1"));
            as.add(rs.getString("move2"));
            as.add(rs.getString("move3"));
            as.add(rs.getString("move4"));
            as.add(Integer.toString(rs.getInt("speed")));
            as.add(Integer.toString(rs.getInt("accuracy")));
            as.add(rs.getString("evolution"));
            as.add(Integer.toString(rs.getInt("phAttack")));
            as.add(Integer.toString(rs.getInt("phDefense")));
            as.add(Integer.toString(rs.getInt("spAttack")));
            as.add(Integer.toString(rs.getInt("spDefense")));
            as.add(Integer.toString(rs.getInt("healthPoints")));
            as.add(Integer.toString(rs.getInt("height")));
            as.add(Integer.toString(rs.getInt("weight")));
        }
        return as;
    }

    public ArrayList<String> getMoveStats(String move) throws SQLException, ClassNotFoundException {
        ArrayList<String> as = new ArrayList<String>();
        String querycheck = "Select * from Moves where name='" + move + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            as.add(rs.getString("moveid"));
            as.add(rs.getString("name"));
            as.add(rs.getString("type"));
            as.add(Integer.toString(rs.getInt("accuracy")));
            as.add(rs.getString("movtype"));
        }
        return as;
    }

    public int checkRegisteredTeams(String owner) throws ClassNotFoundException, SQLException {
        int teams = 0;
        String querycheck = "SELECT COUNT(*) as Cuantos from Teams where owner='" + owner + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            teams = rs.getInt(1) + 1;
        }
        return teams;
    }

    public void createTeam(String teamname, String username, ArrayList<String> pkmn) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        conn = DatabaseConnections.getConnections();
        String teamid = username + Integer.toString(checkRegisteredTeams(username));
        if (!validateExistanceTeam(teamname)) {
            String cmd = "INSERT INTO Teams" + " VALUES ('" + teamid + "', '" + pkmn.get(0) + "', '" + pkmn.get(1) + "', '" + pkmn.get(2) + "', '" + pkmn.get(3) + "', '" + pkmn.get(4) + "', '" + pkmn.get(5) + "', '" + username + "', '" + teamname + "')";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(cmd);
            JOptionPane.showMessageDialog(null, " Team successfully created.");
        } else {
            JOptionPane.showMessageDialog(null, " Duplicated teamname.");
        }
    }

    public boolean validateExistanceTeam(String teamname) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        int miembros = 0;
        String querycheck = "SELECT COUNT(*) from Teams where teamname='" + teamname + "';";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(querycheck);
        while (rs.next()) {
            miembros = rs.getInt(1);
        }
        if (miembros > 0) {
            exists = true;
        }
        return exists;
    }

    public void deleteTeam(String teamid) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Teams " + "WHERE teamid ='" + teamid + "'";
        Statement stmt = getConnections().createStatement();
        stmt.executeUpdate(sql);
    }

    public String getTeamID(String username, String teamname) throws SQLException, ClassNotFoundException {
        String result = "";
        String sql = "SELECT * from Teams " + "WHERE teamname ='" + teamname + "' and owner='" + username + "'";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            result = rs.getString("teamid");
        }
        return result;
    }

    public String getTrainerID(String username) throws SQLException, ClassNotFoundException {
        String result = "";
        String sql = "SELECT * from Accounts " + "WHERE username ='" + username + "'";
        Statement stmt = getConnections().createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            result = rs.getString("userid");
        }
        return result;
    }
}
