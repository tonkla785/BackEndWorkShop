package com.pcc.backend.DTO;

import java.util.Date;

public class UserRequestDTO {
    private String firstname;
    private String lastname;
    private Date birtday;
    private Integer age;
    private String gender;

    public UserRequestDTO(String firstname, String lastname, Date birtday, Integer age, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birtday = birtday;
        this.age = age;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirtday() {
        return birtday;
    }

    public void setBirtday(Date birtday) {
        this.birtday = birtday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
