package models;

public class BeanUser {

	private String user = "";
	private String mail = "";
	private String name = "";
	private String surname = "";
	private String passwd = "";
	private String passwd2 = "";
	private String bday = "";

	private String surname2 = "";
	private int gender = 0; // 0 Not defined 1 Man 2 Woman
	private String description = "";
	private String likes = "";

	private int[] error = { 0, 0 };


	/* Setters */
	// Només user i email tenen comprovacions al servidor (per saber si estan a
	// BD). Els altres per jQuery en el mateix form.
	public void setUser(String user) {
		this.user = user;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public void setError(int[] error) {
		this.error = error;
	}
	
	public void updateError(int index, int err) {
		this.error[index] = err;
	}
	

	/* Getters */
	public String getUser() {
		return user;
	}

	public String getMail() {
		return mail;
	}

	public String getName() {
		return name;
	}

	public int[] getError() {
		return error;
	}

	public String getSurname() {
		return surname;
	}

	public String getBday() {
		return bday;
	}

	public String getSurname2() {
		return surname2;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getPasswd2() {
		return passwd2;
	}

	public int getGender() {
		return gender;
	}

	public String getDescription() {
		return description;
	}

	public String getLikes() {
		return likes;
	}

	/* Logic Functions */
	public boolean isComplete() {
		// hasValue(getBirthday().toString()
		return (hasValue(getUser()) && hasValue(getMail()) && hasValue(getName()) && hasValue(getSurname())
				&& hasValue(getPasswd()) && hasValue(getPasswd2()) && hasValue(getBday()));
	}

	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
	}
}
