package emaillist.vo;

public class EmaillistVo {
	private Long no;
	private String email;
	private String firstName;
	private String lastName;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fisrtName) {
		this.firstName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "EmaillistVo [no=" + no + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
