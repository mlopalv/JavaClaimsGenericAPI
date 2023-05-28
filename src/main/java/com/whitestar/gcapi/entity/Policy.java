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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "POLICIES")
@NamedQueries({
    @NamedQuery(name = "Policy.findAll", query = "SELECT p FROM Policy p"),
    @NamedQuery(name = "Policy.findByPolicyNumber", query = "SELECT p FROM Policy p WHERE p.policyNumber = :policyNumber"),
    @NamedQuery(name = "Policy.findByPolicyEffectiveDate", query = "SELECT p FROM Policy p WHERE p.policyEffectiveDate = :policyEffectiveDate"),
    @NamedQuery(name = "Policy.findByPolicyTermDate", query = "SELECT p FROM Policy p WHERE p.policyTermDate = :policyTermDate")})
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "POLICY_NUMBER")
    private Integer policyNumber;
    
    @Column(name = "POLICY_EFFECTIVE_DATE")
    @Temporal(TemporalType.DATE)
    private Date policyEffectiveDate;
    
    @Column(name = "POLICY_TERM_DATE")    
    @Temporal(TemporalType.DATE)
    private Date policyTermDate;
    
    @JsonbTransient
    @JoinTable(name = "POLICIES_COVERAGES", joinColumns = {
        @JoinColumn(name = "POLICY_NUMBER", referencedColumnName = "POLICY_NUMBER")}, inverseJoinColumns = {
        @JoinColumn(name = "COVERAGE_ID", referencedColumnName = "COVERAGE_ID")})    
    @ManyToMany
    private Collection<Coverage> coveragesCollection;
    
    @JsonbTransient
    @OneToMany(mappedBy = "claimPolicyNumber")
    private Collection<Claim> claimsCollection;
    
    @JsonbTransient
    @JoinColumn(name = "POLICY_PERSON_INSURED_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person policyPersonInsuredId;
    
    @JsonbTransient
    @JoinColumn(name = "POLICY_PERSON_HOLDER_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person policyPersonHolderId;

    public Policy() {
    }

    public Policy(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Integer getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(Integer policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Date getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(Date policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

    public Date getPolicyTermDate() {
        return policyTermDate;
    }

    public void setPolicyTermDate(Date policyTermDate) {
        this.policyTermDate = policyTermDate;
    }

    public Collection<Coverage> getCoveragesCollection() {
        return coveragesCollection;
    }

    public void setCoveragesCollection(Collection<Coverage> coveragesCollection) {
        this.coveragesCollection = coveragesCollection;
    }

    public Collection<Claim> getClaimsCollection() {
        return claimsCollection;
    }

    public void setClaimsCollection(Collection<Claim> claimsCollection) {
        this.claimsCollection = claimsCollection;
    }

    public Person getPolicyPersonInsuredId() {
        return policyPersonInsuredId;
    }

    public void setPolicyPersonInsuredId(Person policyPersonInsuredId) {
        this.policyPersonInsuredId = policyPersonInsuredId;
    }

    public Person getPolicyPersonHolderId() {
        return policyPersonHolderId;
    }

    public void setPolicyPersonHolderId(Person policyPersonHolderId) {
        this.policyPersonHolderId = policyPersonHolderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (policyNumber != null ? policyNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Policy)) {
            return false;
        }
        Policy other = (Policy) object;
        if ((this.policyNumber == null && other.policyNumber != null) || (this.policyNumber != null && !this.policyNumber.equals(other.policyNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.whitestar.gcapi.Policy[ policyNumber=" + policyNumber + " ]";
    }
    
}
