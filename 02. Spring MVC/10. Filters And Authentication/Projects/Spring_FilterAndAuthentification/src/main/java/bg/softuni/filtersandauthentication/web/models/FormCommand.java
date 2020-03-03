package bg.softuni.filtersandauthentication.web.models;

public class FormCommand {
    private String[] selectedValues = new String[]{
            "Monday", "Tuesday", "Wednesday", "Thursday"
    };

    public String[] getSelectedValues() {
        return selectedValues;
    }

    public void setSelectedValues(String[] selectedValues) {
        this.selectedValues = selectedValues;
    }
}
