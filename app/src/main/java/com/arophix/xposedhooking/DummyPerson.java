package com.arophix.xposedhooking;

public class DummyPerson {
    
    public String getFullName(String surname, String givenName) {
        return surname + " " + givenName;
    }
    
    public String getFullName() {
        return "default name";
    }
}
