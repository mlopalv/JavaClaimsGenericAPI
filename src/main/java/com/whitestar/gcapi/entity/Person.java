/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whitestar.gcapi.entity;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "PERSONS")
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByPersonId", query = "SELECT p FROM Person p WHERE p.personId = :personId"),
    @NamedQuery(name = "Person.findByPersonIdType", query = "SELECT p FROM Person p WHERE p.personIdType = :personIdType"),
    @NamedQuery(name = "Person.findByPersonFirstName", query = "SELECT p FROM Person p WHERE p.personFirstName = :personFirstName"),
    @NamedQuery(name = "Person.findByPersonLastName", query = "SELECT p FROM Person p WHERE p.personLastName = :personLastName"),
    @NamedQuery(name = "Person.findByPersonDateOfBirth", query = "SELECT p FROM Person p WHERE p.personDateOfBirth = :personDateOfBirth")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "PERSON_ID")
    private String personId;
    
    @Column(name = "PERSON_ID_TYPE")
    private String personIdType;
    
    @Column(name = "PERSON_FIRST_NAME")
    private String personFirstName;
    
    @Column(name = "PERSON_LAST_NAME")
    private String personLastName;
    
    @Column(name = "PERSON_DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date personDateOfBirth;
    
    @JsonbTransient
    @OneToMany(mappedBy = "claimPersonBeneficiaryId")
    private Collection<Claim> claimsCollection;
    
    @JsonbTransient
    @OneToMany(mappedBy = "policyPersonInsuredId")
    private Collection<Policy> policiesCollection;
    
    @JsonbTransient
    @OneToMany(mappedBy = "policyPersonHolderId")
    private Collection<Policy> policiesCollection1;

    public Person() {
    }

    public Person(String personId) {
        this.personId = personId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonIdType() {
        return personIdType;
    }

    public void setPersonIdType(String personIdType) {
        this.personIdType = personIdType;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Date getPersonDateOfBirth() {
        return personDateOfBirth;
    }

    public void setPersonDateOfBirth(Date personDateOfBirth) {
        this.personDateOfBirth = personDateOfBirth;
    }

    public Collection<Claim> getClaimsCollection() {
        return claimsCollection;
    }

    public void setClaimsCollection(Collection<Claim> claimsCollection) {
        this.claimsCollection = claimsCollection;
    }

    public Collection<Policy> getPoliciesCollection() {
        return policiesCollection;
    }

    public void setPoliciesCollection(Collection<Policy> policiesCollection) {
        this.policiesCollection = policiesCollection;
    }

    public Collection<Policy> getPoliciesCollection1() {
        return policiesCollection1;
    }

    public void setPoliciesCollection1(Collection<Policy> policiesCollection1) {
        this.policiesCollection1 = policiesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.whitestar.gcapi.Person[ personId=" + personId + " ]";
    }
    
}
