package PhoneBooks.service;


import java.io.*;
import java.util.*;

import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;
import PhoneBooks.exceptions.WriteInFileException;

public class CSVWrite {
	private static String SEPARATOR = ";";
	
	public void writeContactToFile(String path, List<Contact> contactList){
		File file = new File(path);
		if (file.isDirectory()){
			throw new WriteInFileException("Wrong file path!!!");
		}
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path)))){
			
			pw.println(convertContactToCsv(contactList));
			
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
		
	}
	
	private String convertContactToCsv(List<Contact> contactList){
		StringBuilder sb = new StringBuilder();
		
		sb.append("First Name").append(SEPARATOR)
			.append("Last Name").append(SEPARATOR)
			.append(PhoneType.MOBILE1).append(SEPARATOR)
			.append(PhoneType.MOBILE2).append(SEPARATOR)
			.append(PhoneType.HOME).append(SEPARATOR)
			.append(PhoneType.WORK).append(SEPARATOR)
			.append("Email").append(SEPARATOR)
			.append("Birthday").append(SEPARATOR)
			.append("Addres").append(SEPARATOR)
			.append("\n")
			;
		
		for (Contact contact : contactList) {
			sb.append(contact.getFirstName()).append(SEPARATOR)
				.append(contact.getLastName()).append(SEPARATOR)
				.append(mapPhoneNumberToString(contact.getPhoneNumbers()))
				.append(contact.getEmail()).append(SEPARATOR)
				.append(contact.getBirthday()).append(SEPARATOR)
				.append(contact.getAddres()).append(SEPARATOR)
				.append("\n");
			
		}
		
		return sb.toString();
	}
	
	private String mapPhoneNumberToString(Map<PhoneType, String> stringMap){
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<PhoneType, String> map : stringMap.entrySet()) {
			
			if (map.getValue().toString()==null){
				sb.append("emptyNumber"+SEPARATOR);
				System.out.println("sb");
			}
			else{
			sb.append(map.getValue()+SEPARATOR);
			}
		}
		return sb.toString();
	}
	
	
	
}
