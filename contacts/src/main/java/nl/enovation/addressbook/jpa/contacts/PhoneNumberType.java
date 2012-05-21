package nl.enovation.addressbook.jpa.contacts;

public enum PhoneNumberType {
    HOME("Home"), 
    WORK("Work"), 
    FAX("Fax"), 
    OTHER("Other");
    
    private String description;

    private PhoneNumberType(String description) {
        this.description = description;
    }

    public String getValue() {
        return name();
    }

    public void setValue(String value) {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
