package PhoneBooks.service;

import java.time.LocalDate;
import java.util.*;

import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;
 

public class PhoneBookService {

	private Scanner scanInt = new Scanner(System.in);
	private Scanner scanLine = new Scanner(System.in);

	public static List <Contact> contactsList = new ArrayList<>();
	
	
	public void createNewContact(){
		
		contactsList.add(new Contact());
	}

	
	public void editContactFirstName(Contact contact, String name){
		contact.setFirstName(name);
			
	}
	
	public void editContactLastName(Contact contact, String name){
		contact.setLastName(name);
			
	}
	
	public void editContactBirthDay(Contact contact, LocalDate date){
		contact.setBirthday(date);
			
	}
	public LocalDate setContactBirthdayManualy(){


			System.out.println("Enter year of birthday");
			 int year = scanInt.nextInt();
			for (;;){
				if (year<0||year>9999){
					System.out.println("Year incorrect, try again");
					year = scanInt.nextInt();
				}else{
					break;
				}
			}
			System.out.println("Enter month (int) of birthday");
			int month = scanInt.nextInt();
			for (;;){
				if (month<1||month>12){
					System.out.println("Month incorrect, try again");
					month = scanInt.nextInt();
				}else{
					break;
				}
			}



		int countTry;
		LocalDate tmpDate = LocalDate.of(0000, 1, 1);

		do {

			System.out.println("Enter day of birthday");
			int day = scanInt.nextInt();

			countTry = 0;
			try {
				tmpDate = LocalDate.of(year, month, day);
			}catch (Exception e){
//			e.printStackTrace();
				System.out.println("Day incorrect, try again");
				countTry++;

			}


		}while (countTry!=0);

		return tmpDate;
	}
	
	public void editContactEmail(Contact contact, String email){
		contact.setEmail(email);
			
	}
	
	public void editContactAdress(Contact contact, String addres){
		contact.setAddres(addres);
			
	}
	

	public List<Contact> getALLContactsList() {
		return contactsList;
	}

	
	public void setContactsList(List<Contact> contactsList) {
		PhoneBookService.contactsList = contactsList;
	}


	public boolean deleteContact(Contact contact){
		
		boolean deleteContact = false;
		do{
			if (contactsList.remove(contact)){
				deleteContact = true;
			}

		}while (contactsList.contains(contact));

		return deleteContact;
	}
	
	public Contact searchByName(String name){
		
		for (int i = 0; i < contactsList.size(); i++) {
			if (name.equals(contactsList.get(i).getFirstName())
					||name.equals(contactsList.get(i).getLastName())){
				return contactsList.get(i);
			}
		}
		
		return null;
	}
	
	public List <Contact> searchByPartName(String partName){
		partName = partName.toLowerCase();
		String strTmp;
		List <Contact> contactListTmp = new ArrayList<>();
		
		for (int i = 0; i < contactsList.size(); i++) {
			strTmp = (contactsList.get(i).getFirstName()
					+ contactsList.get(i).getLastName())
					.toLowerCase();
			
			if (strTmp.contains(partName)){
				contactListTmp.add(contactsList.get(i));
			}
		}
		
		
		return contactListTmp;
	}

	public List<Contact> searchByPartNumber(String number){
		String strTmp = "";
		List <Contact> contactListTmp = new ArrayList<>();

		for (int i = 0; i < contactsList.size(); i++) {

			for (Map.Entry <PhoneType, String> entry: contactsList.get(i).getPhoneNumbers().entrySet() ){
				strTmp+= entry.getValue()+";";
				
			}

			strTmp = strTmp.replaceAll("-", "");
			number = number.replaceAll("-", "");

			if (strTmp.contains(number)){
				contactListTmp.add(contactsList.get(i));
				
			}
			strTmp = "";
			
		}

		return contactListTmp;
	}

}
