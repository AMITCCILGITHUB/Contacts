package org.apandey.data;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Phone {

	private static List<Phone> phones;

	private SimpleStringProperty phoneType;
	private SimpleStringProperty countryCode;
	private SimpleStringProperty cityCode;
	private SimpleStringProperty localNumber;
	private SimpleStringProperty extension;

	private Phone(String phoneType) {

		this.phoneType = new SimpleStringProperty(phoneType);
		this.countryCode = new SimpleStringProperty("");
		this.cityCode = new SimpleStringProperty("");
		this.localNumber = new SimpleStringProperty("");
		this.extension = new SimpleStringProperty("");
	}

	public static synchronized Phone getInstance(String phoneType) {

		int index = -1;

		if (phones == null) {
			phones = new ArrayList<>();
			Phone phone = new Phone(phoneType);
			phones.add(phone);
			index = 0;
		} else {
			int counter = 0;
			for (Phone phone : phones) {
				if (phone.getPhoneType().equals(phoneType)) {
					index = counter;
					break;
				}
				counter++;
			}
			if (index == -1) {
				Phone phone = new Phone(phoneType);
				phones.add(phone);
				index = counter;
			}
		}

		return phones.get(index);
	}

	public static int getCount() {

		return phones.size();
	}

	public SimpleStringProperty phoneTypeProperty() {

		return phoneType;
	}

	public String getPhoneType() {

		return phoneType.get();
	}

	public void setPhoneType(String phoneType) {

		this.phoneType.set(phoneType);
	}

	public SimpleStringProperty countryCodeProperty() {

		return countryCode;
	}

	public String getCountryCode() {

		return countryCode.get();
	}

	public void setCountryCode(String countryCode) {

		this.countryCode.set(countryCode);
	}

	public SimpleStringProperty cityCodeProperty() {

		return cityCode;
	}

	public String getCityCode() {

		return cityCode.get();
	}

	public void setCityCode(String cityCode) {

		this.cityCode.set(cityCode);
	}

	public SimpleStringProperty localNumberProperty() {

		return localNumber;
	}

	public String getLocalNumber() {

		return localNumber.get();
	}

	public void setLocalNumber(String localNumber) {

		this.localNumber.set(localNumber);
	}

	public SimpleStringProperty extensionProperty() {

		return extension;
	}

	public String getExtension() {

		return extension.get();
	}

	public void setExtension(String extension) {

		this.extension.set(extension);
	}

	public String getNumber() {

		StringBuilder contactNumber = new StringBuilder();
		contactNumber.append(getCountryCode() + " (" + getCityCode() + ") "
				+ getLocalNumber() + " - " + getExtension());
		return contactNumber.toString();
	}
}
