package com.arch.api.restful.boot.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysema.query.annotations.QueryEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by SRINIVASULU on 04/11/16.
 */

//import org.springframework.data.annotation.Id;

@QueryEntity
@Document(collection = "customers")
@CompoundIndexes({
        @CompoundIndex(name = "email_pan", unique = true, def = "{'email' : 1, 'pan': 1}")
                })
public class Customer {

    /*@Id
    public String id;*/

    private String firstName;
    private String lastName;

    @Indexed
    private String pan;

    private String company;

    private String address1;

    private String address2;

    private String city;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @ApiModelProperty(notes = "Nearest Locality")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public int getAssociationSinceFrom() {
        return associationSinceFrom;
    }

    public void setAssociationSinceFrom(int associationSinceFrom) {
        this.associationSinceFrom = associationSinceFrom;
    }


    @Indexed
    //@JsonIgnore
    public String passwd;


    //@JsonProperty("password")
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    private String area;

    private String pincode;

    private int associationSinceFrom;

    private String mobile1;

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    private String mobile2;

    private String verifyStatus;

    private boolean isSuspended;

    @ApiModelProperty(notes = "Value could be TaxPayer/TaxConsultant/InstitutionManager/InstitutionDelegate")
    private String type;

    @ApiModelProperty(notes = "Value could be TaxPayer/TaxConsultant/InstitutionManager/InstitutionDelegate")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
//Sha1

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }




    @Id
    private String id;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String nickname;
    @Indexed(direction = IndexDirection.ASCENDING)
    private Integer age;


    @Indexed
    private String email;



    public Customer() {
    }

    /*@PersistenceConstructor
    public Customer(final String name, @Value("#root.age ?: 0") final Integer age, final EmailAddress emailAddress) {
        this.name = name;
        this.age = age;
        this.email = email;
    }*/

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @ApiModelProperty(notes = "Display Nick Name")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    @JsonProperty(required = true)
    @ApiModelProperty(notes = "email id of the user", required = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @JsonIgnore
    private Integer yearOfBirth;

    @JsonProperty("yearOfBirth")
    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(final Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date lastUpdated;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;

    private String state;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s',nickname='%s',email='%s',pan='%s'," +
                        "company='%s',city='%s',mobile1='%s',mobile2='%s,verifyStatus='%s',lastUpdated='%s',state='%s']",
                id, firstName, lastName,nickname,email,pan,company,city,mobile1,mobile2,verifyStatus,lastUpdated,state);
    }

}