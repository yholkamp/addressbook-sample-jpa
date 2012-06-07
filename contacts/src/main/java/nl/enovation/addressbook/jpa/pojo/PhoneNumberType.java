package nl.enovation.addressbook.jpa.pojo;

/**
 * Simple enum containing the types of phone numbers we recognize in our sample application.
 * 
 * @author Yorick Holkamp
 */
public enum PhoneNumberType {
    HOME("Home"), WORK("Work"), FAX("Fax"), OTHER("Other");

    private String description;

    private PhoneNumberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return name();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
    }
}
