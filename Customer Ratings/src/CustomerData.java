public class CustomerData {
    //This class contains information about a customer
    private String name,surname,country,city,occupation;

    //No parameter constructor
    public CustomerData(){}

    //Constructor that uses all variables
    public CustomerData(String name, String surname, String country, String city, String occupation) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.occupation = occupation;
    }

    // Copy constructor
    public CustomerData(CustomerData other){
        this.name = other.getName();
        this.surname = other.getSurname();
        this.country = other.getCountry();
        this.city = other.getCity();
        this.occupation = other.getOccupation();
    }

    //toString method
    @Override
    public String toString() {
        return "Name:" + this.getName() +
                "\t\tSurname:" + this.getSurname() +
                "\t\tCountry:" + this.getCountry() +
                "\t\tCity:" + this.getCity() +
                "\t\tOccupation:" + this.getOccupation();
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
