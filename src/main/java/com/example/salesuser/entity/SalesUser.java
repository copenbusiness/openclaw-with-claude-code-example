package com.example.salesuser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "SALES_USER")
@NamedQuery(name = "SalesUser.findAll", query = "SELECT u FROM SalesUser u")
public class SalesUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @NotBlank(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    @Column(name = "USER_NAME", unique = true, nullable = false, length = 50)
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Column(name = "PHONE", length = 20)
    private String phone;

    @Column(name = "PERSON_TYPE", length = 20)
    private String personType; // "Employee" or other values

    @Column(name = "BUSINESS_UNIT", length = 50)
    private String businessUnit;

    // Constructors
    public SalesUser() {
    }

    public SalesUser(String userName, String password, String email, String phone, String personType, String businessUnit) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.personType = personType;
        this.businessUnit = businessUnit;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    @Override
    public String toString() {
        return "SalesUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", personType='" + personType + '\'' +
                ", businessUnit='" + businessUnit + '\'' +
                '}';
    }
}