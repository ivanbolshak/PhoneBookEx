package PhoneBooks.vizualization;

import static PhoneBooks.service.PhoneBookService.*;

import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;
import PhoneBooks.service.CSVRead;
import PhoneBooks.service.CSVWrite;
import PhoneBooks.service.ContactSorter;
import PhoneBooks.service.PhoneBookService;


public class UIConsole {

	private Scanner scan = new Scanner(System.in);
	private Scanner scanInt = new Scanner(System.in);
	public void startUIConsole(){
		PhoneBookService serviceionals = new PhoneBookService();
				
		String action = "0";
		do {

		System.out.println("Enter number of the action ");
		System.out.println("11 - Load contacts");
		System.out.println("22 - Save contacts");
		System.out.println("1 - Show all contacts ");
		System.out.println("2 - Create new contact ");
		System.out.println("3 - Edit contact ");
		System.out.println("4 - Search contact by name");
		System.out.println("5 - Search contact by phone number");
		System.out.println("6 - Remove contact ");
		System.out.println("7 - Sorting contacts ");
		System.out.println("0 - Exit");
		action = scan.nextLine();
		
		switch (action){
		case "1": 
			showFormatedContactList(contactsList);
			
			break;
		case "2": CreateNewContact(serviceionals);
			break; 
		case "3":EditAnyContact(serviceionals);
			break;
		case "4":
			showFormatedContactList(SearchContactByName(serviceionals));
			
			break;
		case "5": 
			showFormatedContactList(SearchContactByPhoneNumber(serviceionals));
			
			break;
		case "6": removeContact(serviceionals);
				break;
		case "7": sortingContacts();
				break;	
		case "22":
			saveContacts ("src/PhoneBooks/saveFiles/myPhoneBook.csv", contactsList);
			break;
		case "11":
			loadContacts(serviceionals, "src/PhoneBooks/saveFiles/myPhoneBook.csv"); 
			break;
		
		case "0":
			System.out.println("The work is over!!!");
			action = "999";
			break;
			
		default: System.out.println("Incorrect entered number!!!");
			break;
		}
		
		}while (action != "999");
//		System.out.println("The work is over!!!");

	}
	

//	1
	public void showFormatedContactList(List<Contact> contactsListPrint ){
		
		ContactFormatPrinter printContacts = new ContactFormatPrinter();
		printContacts.printTable(contactsListPrint);
		
	}
	
	
//	2
	private void CreateNewContact(PhoneBookService service){
		service.createNewContact();

		System.out.println("Enter first name"); 
		service.editContactFirstName(contactsList.get(contactsList.size()-1), scan.nextLine());

		System.out.println("Enter last name");
		service.editContactLastName(contactsList.get(contactsList.size()-1), scan.nextLine());

		service.editContactBirthDay(contactsList.get(contactsList.size()-1), service.setContactBirthdayManualy());

		System.out.println("Enter FIRST phone mobile number");
		contactsList.get(contactsList.size()-1).setPhoneNumber(PhoneType.MOBILE1, scan.nextLine());

		System.out.println("Enter second phone mobile number");
		contactsList.get(contactsList.size()-1).setPhoneNumber(PhoneType.MOBILE2, scan.nextLine());
		System.out.println("Enter home phone number");
		contactsList.get(contactsList.size()-1).setPhoneNumber(PhoneType.HOME, scan.nextLine());
		System.out.println("Enter work phone number");
		contactsList.get(contactsList.size()-1).setPhoneNumber(PhoneType.WORK, scan.nextLine());
	}
	
	//3
	private void EditAnyContact(PhoneBookService service){
		List <Contact> editContactList = new ArrayList<>();
		Contact editContact = new Contact();
		
		System.out.println("Enter name contact for edit: ");
		editContactList = service.searchByPartName(scan.nextLine());
		
		if (editContactList.isEmpty()){
			System.out.println("Not found eny contact");
			return;
		}

		
		showFormatedContactList(editContactList);

		if (editContactList.size()>1){
			System.out.println("Enter number position for edit");
			editContact = editContactList.get(scanInt.nextInt()-1);
//			System.out.println("Edit contact: "+"\n"+editContact);
			
		}else{
			editContact = editContactList.get(0);
		}
		
		String count = "999";
		do{
		System.out.println("Edit contact: "+"\n"+editContact);

		System.out.println("Enter number of edit line ");
		System.out.println("1 - First name");
		System.out.println("2 - Second name");
		System.out.println("3 - Birtday");
		System.out.println("4 - First mobile number");
		System.out.println("5 - Second mobile number");
		System.out.println("6 - Home phone number");
		System.out.println("7 - Work phone number");
		System.out.println("8 - E-mail");
		System.out.println("9 - Adres");
		System.out.println("0 - Exit from the edit contact");
		
		count = scan.nextLine();
		switch (count){
		case "1":
			
			System.out.println("Enter new first name: ");
			editContact.setFirstName(scan.nextLine());
			
			break;
		case "2":
			
			System.out.println("Enter new last name: ");
			
			editContact.setLastName(scan.nextLine());
			break;
		case "3":

			editContact.setBirthday(service.setContactBirthdayManualy());
			break;
		case "4":
			
			System.out.println("Enter new first mobile number: ");
			editContact.setPhoneNumber(PhoneType.MOBILE1, scan.nextLine());
			
			break;
		case "5":
			
			System.out.println("Enter new second mobile number: ");
			editContact.setPhoneNumber(PhoneType.MOBILE2, scan.nextLine());
			
			break;
		case "6":
			
			System.out.println("Enter new home phone number: ");
			editContact.setPhoneNumber(PhoneType.HOME, scan.nextLine());
			
			break;
		case "7":
			
			System.out.println("Enter new work phone number: ");
			editContact.setPhoneNumber(PhoneType.WORK, scan.nextLine());
			
			break;
			
		case "8":
			
			System.out.println("Enter new E-mail: ");
			editContact.setEmail(scan.nextLine());
			
			break;
			
		case "9":
			
			System.out.println("Enter new Adres: ");
			editContact.setAddres(scan.nextLine());
			
			break;
			
		
		case "0":
			System.out.println("Exit from edit contact");
			count = "999";
			break;
		
		}


		}while(count != "999");
		
	}
	
	//4
	private List<Contact> SearchContactByName(PhoneBookService service){
		System.out.println("Enter name: ");
		return service.searchByPartName(scan.nextLine());
					
	}
	
	//5
	private List<Contact> SearchContactByPhoneNumber(PhoneBookService service){
		
		
		System.out.println("Enter phone number: ");
		
		
		return service.searchByPartNumber(scan.nextLine());
	}

	//6
	public void removeContact(PhoneBookService service){

		Contact contactRemoved = new Contact();
		
		System.out.println("Removed contact: ");
		List<Contact> contactListTmp = SearchContactByName(service);
		showFormatedContactList(contactListTmp);
		if (contactListTmp.size()>1){

			System.out.println("Enter number position for remove");
			contactRemoved = contactListTmp.get(scanInt.nextInt()-1);
			
		}else{
			contactRemoved = contactListTmp.get(0);
		}
		

		service.deleteContact(contactRemoved);
	}
	
//	7 - Sorting contacts
	public void sortingContacts(){
		
		
		String choose = "0";
		ContactSorter sorter = new ContactSorter();
		
		
			System.out.println("Enter of sorting line ");
			
			System.out.println("1 - First name");
			System.out.println("2 - Second name");
			System.out.println("3 - Birtday");
			System.out.println("4 - First mobile number");
			System.out.println("5 - Second mobile number");
			System.out.println("6 - Home phone number");
			System.out.println("7 - Work phone number");
			System.out.println("8 - E-mail");
			System.out.println("9 - Adres");
			System.out.println("0 - Exit from the edit contact");
			
			choose = scan.nextLine();
		
		switch (choose){
		case "1":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.FIRST_NAME );
			break;
		case "2":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.LAST_NAME);
			break;	
		case "3":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.MOBILE1);
			break;	
		case "4":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.MOBILE2);
			break;
		case "5":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.HOME);
			break;	
		case "6":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.WORK);
			break;
		case "7":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.EMAIL);
			break;	
		case "8":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.BIRTHDAY);
			break;
		case "9":
			sorter.sort(contactsList, ContactSorter.Direction.ASC, ContactSorter.Column.ADDRES);
			break;
		}
		
		showFormatedContactList(contactsList);
	}
	
//11	11 - Save contacts
	
	private void saveContacts (String path, List<Contact> contactListWrite){
		CSVWrite csvWrite = new CSVWrite();
		
		csvWrite.writeContactToFile(path, contactListWrite);
	}
	
//	22 - Load contacts

	private void loadContacts(PhoneBookService service, String pathToFile){
		CSVRead csvRead = new CSVRead();
		List<Contact> loadList = csvRead.readCSV(pathToFile);
		
		service.setContactsList(loadList);
	}
	
}
