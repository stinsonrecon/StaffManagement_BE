package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DBConnect;
import Model.Account;

public class AccountDAO {
	public Account Login(String username, String password) {
		Connection connection = DBConnect.getConnection();
		String login = "select * from Account where username = '" + username + "' and password = '" + password + "'";
		
		PreparedStatement pe;
		try {
			pe = connection.prepareStatement(login);
			ResultSet result = pe.executeQuery();
			while(result.next()) {				
				String userName = result.getString("username");
				String passWord = result.getString("password");
				String IdPREFIX = result.getString("IdPREFIX");
				int IdCanBo = result.getInt("IdCanBo");
				Account a = new Account(userName, passWord,IdPREFIX,IdCanBo);
				System.out.println(a.toString());
				return a;			
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	
	public boolean UpdateAccount(String username, String password, String IdPREFIX, int IdCanBo) {
		Connection connection = DBConnect.getConnection();
		String update = "update Account set username = " + username + " , password = " + password + " , IdPREFX = " + IdPREFIX + " where IdCanBo = " + IdCanBo;
		
		PreparedStatement pe;
		try {
			pe = connection.prepareStatement(update);
			pe.execute();
			return true;
		} catch (SQLException e) {
			
			return false;
		}
	}
	
	public void DeleteAccount(int IdCanBo) {
		Connection connection = DBConnect.getConnection();
		String delete = "delete from Account where IdCanBo = " + IdCanBo;
		try {
			PreparedStatement pe = connection.prepareStatement(delete);
			pe.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Create(String userName, String passWord,String IDPREFIX, int IdCanbo) throws SQLException{
		Connection connection = DBConnect.getConnection();
		String insert = "insert into Account(username, password, IdPREFIX, IdCanBo)" +
				" value ('" + userName + "', '" + passWord + "', '" + IDPREFIX + "' ," +
				" (select IdCanBo from CanBo where IdCanBo = " + IdCanbo + "));";
		PreparedStatement pe = connection.prepareStatement(insert);
		pe.execute();
	}
	
	public boolean CheckAdmin(String username) {
		Connection connection = DBConnect.getConnection();
		String select = "select * from Admin where username = '" + username + "'";
		try {
		PreparedStatement pe = connection.prepareStatement(select);
		ResultSet re = pe.executeQuery();
		
		if(re.next()) {
			return true;
		}
		return false;
		}
		catch(SQLException e) {
			return false;
		}
	}
}
