package PhoneBooks.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import PhoneBooks.data.Contact;
import PhoneBooks.data.PhoneType;


public class ContactSorter {

	public enum Direction {
		ASC(1), DESC(-1);
		private int k;
		Direction(int k) {
			this.k = k;
		}
		public int getK() {
			return k;
		}
	}

	public enum Column {
		FIRST_NAME, LAST_NAME, MOBILE1, MOBILE2, HOME, WORK, EMAIL, BIRTHDAY, ADDRES
	}

	public void sort (List<Contact> auctionLotList, Direction direction, Column column){
		Collections.sort(auctionLotList, new Comparator<Contact>() {
			@Override
			public int compare(Contact o1, Contact o2) {
				switch (column){
					case FIRST_NAME:return o1.getFirstName().compareTo(o2.getFirstName()) * direction.getK();
					case LAST_NAME:return o1.getLastName().compareTo(o2.getLastName()) * direction.getK();
					case MOBILE1: return o1.getPhoneNumber(PhoneType.MOBILE1).compareTo(o2.getPhoneNumber(PhoneType.MOBILE1)) * direction.getK();
					case MOBILE2: return o1.getPhoneNumber(PhoneType.MOBILE2).compareTo(o2.getPhoneNumber(PhoneType.MOBILE2)) * direction.getK();
					case HOME: return o1.getPhoneNumber(PhoneType.HOME).compareTo(o2.getPhoneNumber(PhoneType.HOME)) * direction.getK();
					case WORK: return o1.getPhoneNumber(PhoneType.WORK).compareTo(o2.getPhoneNumber(PhoneType.WORK)) * direction.getK();
					case EMAIL: return o1.getEmail().compareTo(o2.getEmail()) * direction.getK();
					case BIRTHDAY: return o1.getBirthday().compareTo(o2.getBirthday()) * direction.getK();
					case ADDRES: return o1.getAddres().compareTo(o2.getAddres()) * direction.getK();
					default: return 0;
				}
			}
			
		});
	}

}
