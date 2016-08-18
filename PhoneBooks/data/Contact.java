package PhoneBooks.data;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.*;


public class Contact {
	
	private String firstName = "";
	private String lastName = "";
	private Map<PhoneType, String> phoneNumbers = new HashMap<>(); 
	private String email = "";
	private LocalDate birthday;
	private String addres = "";

	public Contact(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
//		iniciatePhoneMap();
		
	}
	
	public Contact() {
		iniciatePhoneMap();
	}
	
	public void iniciatePhoneMap(){
		String str = "0";
		phoneNumbers.put(PhoneType.MOBILE1, str);
		phoneNumbers.put(PhoneType.MOBILE2, str);
		phoneNumbers.put(PhoneType.HOME, str);
		phoneNumbers.put(PhoneType.WORK, str);
	}
	
	
public String getPhoneNumber(PhoneType phoneType){
		
		return phoneNumbers.get(phoneType);
	} 

	
public boolean setPhoneNumber(PhoneType phoneType, String number){
		
		switch (phoneType){
		case	MOBILE1: phoneNumbers.put(PhoneType.MOBILE1, number);
		return true;
		case	MOBILE2: phoneNumbers.put(PhoneType.MOBILE2, number);
		return true;
		case	HOME: phoneNumbers.put(PhoneType.HOME, number);
		return true;
		case	WORK: phoneNumbers.put(PhoneType.WORK, number);
		return true;
			
		default: System.out.println("Phone number cannot by ADD!!!");
		break;
					
		}
		return false;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Map<PhoneType, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

//	<<<<<<<<<<<<<<<<<<<<<<<<----------------------------
//	
	@Override 
	public String toString() {
		return "Name: " + firstName + " " + lastName + "; Phone Numbers: " + mapToString(phoneNumbers)
				+ "E-mail: " + email + "; Birthday: " + printData(birthday) + "; Addres: " + addres + " ";
	}
	
	private String mapToString(Map<PhoneType, String> stringMap){
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<PhoneType, String> map : stringMap.entrySet()) {
					
			if (map.getValue() != null){
				sb.append(map.getKey()+ ": " + map.getValue()+"; ");
				
			}
		}
		
		return sb.toString();
	}
	
	private String printData (LocalDate data){
		if (data == null) {
			return "empty";
		}
		
		return data.toString();
	}
	
//	
//	------------------------->>>>>>>>>>>>>>>>>>>>>
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((addres == null) ? 0 : addres.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (addres == null) {
			if (other.addres != null)
				return false;
		} else if (!addres.equals(other.addres))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (phoneNumbers == null) {
			if (other.phoneNumbers != null)
				return false;
		} else if (!phoneNumbers.equals(other.phoneNumbers))
			return false;
		return true;
	}

	


}
