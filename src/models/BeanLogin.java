package models;

public class BeanLogin {

	private String user = "";
	private String password = "";
	private int[] error = {0,0};
	
	public String getUser(){
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUser(String user){
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setError(int[] error) {
		this.error = error;
	}
	
	public int[] getError() {
		return error;
	}
	
	public boolean isComplete() {
	    return(hasValue(getUser()) && hasValue(getPassword()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	
	public void updateError(int index, int err) {
		this.error[index] = err;
	}

	
}
