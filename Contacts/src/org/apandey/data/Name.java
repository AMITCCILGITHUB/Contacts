package org.apandey.data;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Name {

	private static Name name;

	private SimpleStringProperty title;
	private SimpleStringProperty firstName;
	private SimpleStringProperty middleName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty suffix;

	private Name() {

		title = new SimpleStringProperty("");
		firstName = new SimpleStringProperty("");
		middleName = new SimpleStringProperty("");
		lastName = new SimpleStringProperty("");
		suffix = new SimpleStringProperty("");
	}

	public static synchronized Name getInstance() {

		if (name == null) {
			name = new Name();
		}

		return name;
	}

	public SimpleStringProperty titleProperty() {

		return title;
	}

	public String getTitle() {

		return title.get();
	}

	public void setTitle(String title) {

		this.title.set(title);
	}

	public SimpleStringProperty firstNameProperty() {

		return firstName;
	}

	public String getFirstName() {

		return firstName.get();
	}

	public void setFirstName(String firstName) {

		this.firstName.set(firstName);
	}

	public SimpleStringProperty middleNameProperty() {

		return middleName;
	}

	public String getMiddleName() {

		return middleName.get();
	}

	public void setMiddleName(String middleName) {

		this.middleName.set(middleName);
	}

	public SimpleStringProperty lastNameProperty() {

		return lastName;
	}

	public String getLastName() {

		return lastName.get();
	}

	public void setLastName(String lastName) {

		this.lastName.set(lastName);
	}

	public SimpleStringProperty suffixProperty() {

		return suffix;
	}

	public String getSuffix() {

		return suffix.get();
	}

	public void setSuffix(String suffix) {

		this.suffix.set(suffix);
	}

	public String toString() {

		return getTitle() + " " + getFirstName() + " " + getMiddleName() + " "
				+ getLastName() + " " + getSuffix();
	}

	public List<String> getNameCombinations() {

		List<String> nameComb = new ArrayList<String>();
		nameComb.add(getLastName() + ", " + getFirstName() + " "
				+ getMiddleName());
		nameComb.add(getFirstName() + " " + getMiddleName() + " "
				+ getLastName() + " " + getSuffix());

		return nameComb;
	}
}
