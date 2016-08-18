package PhoneBooks.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;
import PhoneBooks.exceptions.NoDataToReadException;


public class CSVRead {
	private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public List<Contact> readCSV(String filePath){
		File file = new File(filePath);
		if (!file.isFile()){
			throw new NoDataToReadException("Can't read file " + file);
		}
		
		List<Contact> contactList = new ArrayList<>();
		
		try( FileReader fr = new FileReader(file);
				Scanner scan = new Scanner(fr)
			){
			scan.nextLine();
			while(scan.hasNext()){
				String tmpCsvLine = scan.nextLine();
				contactList.add(parseToContact(tmpCsvLine));
			}
			
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}{
			
		}
		
		return contactList;
	}
	
	private Contact parseToContact(String str){
		String [] parseStr = str.split(";", -1);
		
		Contact contactTmp = new Contact();
		
		contactTmp.setFirstName(parseStr[0]);
		contactTmp.setLastName(parseStr[1]);
		contactTmp.setPhoneNumber(PhoneType.MOBILE1, parseStr[2]);
		contactTmp.setPhoneNumber(PhoneType.MOBILE2, parseStr[3]);
		contactTmp.setPhoneNumber(PhoneType.HOME, parseStr[4]);
		contactTmp.setPhoneNumber(PhoneType.WORK, parseStr[5]);
		contactTmp.setEmail(parseStr[6]);
		contactTmp.setAddres(parseStr[8]);
		try{contactTmp.setBirthday(LocalDate.parse(parseStr[7], dateTimeFormat));
			}catch(Exception e){
			e.getStackTrace();
		}
		
		return contactTmp;
	}

}
