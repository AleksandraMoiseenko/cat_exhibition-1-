package cat_exhibition;

public class Human {
	private String name;
	private String last_name;
	private String address;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void printname() {
		System.out.println("Owner's name: " + this.getName());
	}

	public void printlast_name() {
		System.out.println("Owner's last name: " + this.getLast_name());
	}

	public void printaddress() {
		System.out.println("Owner and cat's address: " + this.getAddress());
	}

	public void printhumaninfo() {
		System.out.println("Name is: " + this.getName() + " Last name is: " + this.getLast_name() + " Address is: " + this.getAddress());
	}
	public Human(String name, String last_name, String address) {
		this.name = name;
		this.last_name = last_name;
		this.address = address;
	}

	public Human(String human_data) {
		String[] data=human_data.split(" ");
		name = data[0];
		last_name = data[1];
		address = data[2];
	}


}
