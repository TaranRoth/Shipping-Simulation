package src;

public class Address {
    private String streetNumber;
    private String streetName;
    private String apartmentNumber;
    private String city;
    private String state;
    private String zipCode;

    public Address(String strNum, String strName, String ct, String st, String zipCd) {
        streetNumber = strNum;
        streetName = strName;
        city = ct;
        state = st;
        zipCode = zipCd;
        apartmentNumber = "";
    }

    public Address(String strNum, String strName, String ct, String st, String zipCd, String appNum) {
        streetNumber = strNum;
        streetName = strName;
        city = ct;
        state = st;
        zipCode = zipCd;
        apartmentNumber = appNum;
    }

    public Address(Address address) {
        streetNumber = address.getStreetNumber();
        streetName = address.getStreetName();
        apartmentNumber = address.getApartmentNumber();
        city = address.getCity();
        state = address.getState();
        zipCode = address.getZipCode();
    }

    public Address(String addressStr) {
        String remainingStr = addressStr;
        int divideIndex = remainingStr.indexOf(" ");
        streetNumber = remainingStr.substring(0, divideIndex);
        remainingStr = remainingStr.substring(divideIndex + 1);
        int firstSpaceIndex = remainingStr.indexOf(" ");
        String tempStr = remainingStr.substring(firstSpaceIndex + 1);
        if (tempStr.indexOf(",") > tempStr.indexOf(" ")) {
            divideIndex = tempStr.indexOf(" ");
            streetName = remainingStr.substring(0, divideIndex + firstSpaceIndex + 1);
            remainingStr = remainingStr.substring(divideIndex + firstSpaceIndex + 2);
            divideIndex = remainingStr.indexOf(",");
            apartmentNumber = remainingStr.substring(0, divideIndex);
            remainingStr = remainingStr.substring(divideIndex + 2);
        } else {
            apartmentNumber = "";
            divideIndex = remainingStr.indexOf(",");
            streetName = remainingStr.substring(0, divideIndex);
            remainingStr = remainingStr.substring(divideIndex + 2);
        }
        divideIndex = remainingStr.indexOf(",");
        city = remainingStr.substring(0, divideIndex);
        remainingStr = remainingStr.substring(divideIndex + 2);
        divideIndex = remainingStr.indexOf(" ");  
        state = remainingStr.substring(0, divideIndex);
        remainingStr = remainingStr.substring(divideIndex + 1);
        zipCode = remainingStr;
    }

    public String getStreetNumber() {
        return streetNumber;
    }
    
    public String getStreetName() {
        return streetName;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String toString() {
        String returnStr = "";
        returnStr += streetNumber + " " + streetName;
        if (!apartmentNumber.equals("")) {
            returnStr += " " + apartmentNumber;
        }
        returnStr += ", " + city + ", " + state + " " + zipCode;
        return returnStr;
    }

    public boolean compareAddress(Address address) {
        return this.toString().equals(address.toString());
    }
    

}