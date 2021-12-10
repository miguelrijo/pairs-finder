package enums;

public enum Days {
    MO("Monday"),
    TU("Tuesday"),
    WE("Wednesday"),
    TH("Thursday"),
    FR("Friday"),
    SA("Saturday"),
    SU("Sunday");

    private String name;

    private Days( String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
