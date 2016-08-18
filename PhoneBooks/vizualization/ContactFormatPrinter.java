package PhoneBooks.vizualization;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;


public class ContactFormatPrinter {

	private static DateTimeFormatter dateTimeTemplate = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public void printTable (List<Contact> contactList){
		StringBuilder sb = new StringBuilder();
		MaxColmnsSizeHolder holder = calculateColumnsSiazeStat(contactList);

			sb.append("| ")
				.append(spaceAdder("First Name", holder.contactFirstNameMaxSize))
					.append(" | ")
				.append(spaceAdder("Last Name", holder.contactLastNameMaxSize))
					.append(" | ")
				.append(spaceAdder("Mobile1", holder.contactPhoneMapMaxSize))
					.append(" | ")
				.append(spaceAdder("Mobile2", holder.contactPhoneMapMaxSize))
					.append(" | ")
				.append(spaceAdder("Home phone", holder.contactPhoneMapMaxSize))
					.append(" | ")
				.append(spaceAdder("Work phone", holder.contactPhoneMapMaxSize))
					.append(" | ")
				.append(spaceAdder("e-mail", holder.contactEmailMaxSize))
					.append(" | ")
				.append(spaceAdder("Birthday", holder.contactBirthdayMaxSize))
					.append(" | ")
				.append(spaceAdder("Address", holder.contactAdressMaxSize))
					.append(" |\n");


		for (Contact contact : contactList ){
			sb.append("| ")
			.append(spaceAdder(contact.getFirstName().toString(),holder.contactFirstNameMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getLastName().toString(),holder.contactLastNameMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getPhoneNumber(PhoneType.MOBILE1).toString(),holder.contactPhoneMapMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getPhoneNumber(PhoneType.MOBILE2).toString(),holder.contactPhoneMapMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getPhoneNumber(PhoneType.HOME).toString(),holder.contactPhoneMapMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getPhoneNumber(PhoneType.WORK).toString(),holder.contactPhoneMapMaxSize))
			.append(" | ")
			.append(spaceAdder(contact.getEmail().toString(),holder.contactEmailMaxSize))
			.append(" | ");
			
			if (contact.getBirthday()==null){
				sb.append(spaceAdder("", holder.contactBirthdayMaxSize));
			}else{
				sb.append(spaceAdder(contact.getBirthday().format(dateTimeTemplate),holder.contactBirthdayMaxSize));
			}
			
			sb.append(" | ")
			.append(spaceAdder(contact.getAddres().toString(),holder.contactAdressMaxSize))
						
			.append(" |\n");
		}

		System.out.println(sb.toString());
	}
	
	
	private String spaceAdder (String output, int outputSize){
		
		StringBuilder sb = new StringBuilder(output);
		while (sb.length() < outputSize) {
			sb.append(" ");
		}
		return sb.toString();
	}

	private static MaxColmnsSizeHolder calculateColumnsSiazeStat(List<Contact> contactList){
		MaxColmnsSizeHolder holder = new MaxColmnsSizeHolder();
		for (Contact contact : contactList){
			if (contact.getFirstName()!=null && contact.getFirstName().length() > holder.contactFirstNameMaxSize){
				holder.contactFirstNameMaxSize = contact.getFirstName().length();
			}
			if (contact.getLastName()!=null && contact.getLastName().length() > holder.contactLastNameMaxSize){
				holder.contactLastNameMaxSize = contact.getLastName().length();
			}
			if (contact.getEmail()!=null && contact.getEmail().length() > holder.contactEmailMaxSize){
				holder.contactEmailMaxSize = contact.getEmail().length();
			}
			if (contact.getAddres()!=null && contact.getAddres().length()> holder.contactAdressMaxSize){
				holder.contactAdressMaxSize = contact.getAddres().length();
			}
			if (contact.getBirthday()!=null && contact.getBirthday().format(dateTimeTemplate).length() > holder.contactBirthdayMaxSize){
				holder.contactBirthdayMaxSize = contact.getBirthday().format(dateTimeTemplate).length();
			}

				Map<PhoneType, String> stringMap = contact.getPhoneNumbers();
				for (Map.Entry<PhoneType, String> map : stringMap.entrySet()) {
				
					if (map.getValue().toString().length()>holder.contactPhoneMapMaxSize)
					{
						holder.contactPhoneMapMaxSize = map.getValue().toString().length(); 
					}
				}
						 
		}
		return holder;
	}

	private static class MaxColmnsSizeHolder {
		private int contactFirstNameMaxSize = 10;
		private int contactLastNameMaxSize = 10;
		private int contactEmailMaxSize = 7;
		private int contactAdressMaxSize = 7;
		private int contactBirthdayMaxSize = 7;
		private int contactPhoneMapMaxSize = 7;
		
	}

}


