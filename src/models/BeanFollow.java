package models;

public class BeanFollow {

	private int iduser = 0;
	private String name = "";
	private int idfollowed = 0;
	private String since = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getIdfollowed() {
		return idfollowed;
	}

	public void setIdfollowed(int idfollowed) {
		this.idfollowed = idfollowed;
	}

	public String getSince() {
		return since;
	}

	public void setSince(String since) {
		this.since = since;
	}
}
