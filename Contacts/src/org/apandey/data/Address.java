package org.apandey.data;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Address {

	private static List<Address> addresses;

	private SimpleStringProperty addressType;
	private SimpleStringProperty streetName;
	private SimpleStringProperty cityName;
	private SimpleStringProperty stateName;
	private SimpleStringProperty zipCode;
	private SimpleStringProperty country;

	private Address(String addressType) {

		this.addressType = new SimpleStringProperty(addressType);
		this.streetName = new SimpleStringProperty("");
		this.cityName = new SimpleStringProperty("");
		this.stateName = new SimpleStringProperty("");
		this.zipCode = new SimpleStringProperty("");
		this.country = new SimpleStringProperty("");
	}

	public static synchronized Address getInstance(String addressType) {

		int index = -1;

		if (addresses == null) {
			addresses = new ArrayList<>();
			Address address = new Address(addressType);
			addresses.add(address);
			index = 0;
		} else {
			int counter = 0;
			for (Address address : addresses) {
				if (address.getAddressType().equals(addressType)) {
					index = counter;
					break;
				}
				counter++;
			}
			if (index == -1) {
				Address address = new Address(addressType);
				addresses.add(address);
				index = counter;
			}
		}

		return addresses.get(index);
	}

	public static int getCount() {

		return addresses.size();
	}

	public SimpleStringProperty addressTypeProperty() {

		return addressType;
	}

	public String getAddressType() {

		return addressType.get();
	}

	public void setAddressType(String addressType) {

		this.addressType.set(addressType);
	}

	public SimpleStringProperty streetNameProperty() {

		return streetName;
	}

	public String getStreetName() {

		return streetName.get();
	}

	public void setStreetName(String streetName) {

		this.streetName.set(streetName);
	}

	public SimpleStringProperty cityNameProperty() {

		return cityName;
	}

	public String getCityName() {

		return cityName.get();
	}

	public void setCityName(String cityName) {

		this.cityName.set(cityName);
	}

	public SimpleStringProperty stateNameProperty() {

		return stateName;
	}

	public String getStateName() {

		return stateName.get();
	}

	public void setStateName(String stateName) {

		this.stateName.set(stateName);
	}

	public SimpleStringProperty zipCodeProperty() {

		return zipCode;
	}

	public String getZipCode() {

		return zipCode.get();
	}

	public void setZipCode(String zipCode) {

		this.zipCode.set(zipCode);
	}

	public SimpleStringProperty countryProperty() {

		return country;
	}

	public String getCountry() {

		return country.get();
	}

	public void setCountry(String country) {

		this.country.set(country);
	}

	public String getAddressDetail() {

		StringBuilder addressDetail = new StringBuilder();
		addressDetail.append(getStreetName() + "\n" + getCityName() + ", "
				+ getStateName() + "\n" + getCountry() + "\nPin: "
				+ getZipCode());
		return addressDetail.toString();
	}
}
