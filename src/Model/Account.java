package Model;

public class Account {
	private String username;
	private String password;
	private String IdPREFIX;
	private int IdCanBo;
	public Account(String username, String password, String idPREFIX, int idCanBo) {
		super();
		this.username = username;
		this.password = password;
		IdPREFIX = idPREFIX;
		IdCanBo = idCanBo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIdPREFIX() {
		return IdPREFIX;
	}
	public void setIdPREFIX(String idPREFIX) {
		IdPREFIX = idPREFIX;
	}
	public int getIdCanBo() {
		return IdCanBo;
	}
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", IdPREFIX=" + IdPREFIX + ", IdCanBo="
				+ IdCanBo + "]";
	}
	

}
