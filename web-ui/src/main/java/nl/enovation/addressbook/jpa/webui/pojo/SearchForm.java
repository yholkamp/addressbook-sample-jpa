package nl.enovation.addressbook.jpa.webui.pojo;

/**
 * Plain Old Java Object providing a simple way to pass around information regarding a generic search query in the
 * application.
 * 
 * @author Maarten van Meijeren
 */
public class SearchForm {

    private String searchValue;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
}
