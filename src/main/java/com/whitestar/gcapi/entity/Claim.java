/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whitestar.gcapi.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "CLAIMS")
@NamedQueries({                        
    @NamedQuery(name = "Claim.findAll", query = "SELECT c FROM Claim c"),    
    @NamedQuery(name = "Claim.findByClaimId", query = "SELECT c FROM Claim c WHERE c.claimId = :claimId"),
    @NamedQuery(name = "Claim.findByClaimEventDescription", query = "SELECT c FROM Claim c WHERE c.claimEventDescription = :claimEventDescription"),
    @NamedQuery(name = "Claim.findByClaimOcurrenceDate", query = "SELECT c FROM Claim c WHERE c.claimOcurrenceDate = :claimOcurrenceDate"),
    @NamedQuery(name = "Claim.findByClaimReportDate", query = "SELECT c FROM Claim c WHERE c.claimReportDate = :claimReportDate"),
    @NamedQuery(name = "Claim.findByClaimRegistryDate", query = "SELECT c FROM Claim c WHERE c.claimRegistryDate = :claimRegistryDate"),
    @NamedQuery(name = "Claim.findByClaimClaimedAmount", query = "SELECT c FROM Claim c WHERE c.claimClaimedAmount = :claimClaimedAmount"),
    @NamedQuery(name = "Claim.findByClaimStatus", query = "SELECT c FROM Claim c WHERE c.claimStatus = :claimStatus")})
public class Claim implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLAIM_ID")    
    private Long claimId;
    
    @Column(name = "CLAIM_EVENT_DESCRIPTION")
    private String claimEventDescription;
    
    @Column(name = "CLAIM_OCURRENCE_DATE")
    @Temporal(TemporalType.DATE)
    private Date claimOcurrenceDate;
    
    @Column(name = "CLAIM_REPORT_DATE")
    @Temporal(TemporalType.DATE)
    private Date claimReportDate;
    
    @Column(name = "CLAIM_REGISTRY_DATE")
    @Temporal(TemporalType.DATE)
    private Date claimRegistryDate;
    
    @Column(name = "CLAIM_CLAIMED_AMOUNT")
    private Integer claimClaimedAmount;
    
    @Column(name = "CLAIM_STATUS")
    private String claimStatus;

   
    
    @JoinColumn(name = "CLAIM_PERSON_BENEFICIARY_ID", referencedColumnName = "PERSON_ID")
    @ManyToOne
    private Person claimPersonBeneficiaryId;
    
    @JoinColumn(name = "CLAIM_POLICY_NUMBER", referencedColumnName = "POLICY_NUMBER")
    @ManyToOne
    private Policy claimPolicyNumber;

    public Claim() {
    }

    public Claim(Long claimId) {
        this.claimId = claimId;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public String getClaimEventDescription() {
        return claimEventDescription;
    }

    public void setClaimEventDescription(String claimEventDescription) {
        this.claimEventDescription = claimEventDescription;
    }

    public Date getClaimOcurrenceDate() {
        return claimOcurrenceDate;
    }

    public void setClaimOcurrenceDate(Date claimOcurrenceDate) {
        this.claimOcurrenceDate = claimOcurrenceDate;
    }

    public Date getClaimReportDate() {
        return claimReportDate;
    }

    public void setClaimReportDate(Date claimReportDate) {
        this.claimReportDate = claimReportDate;
    }

    public Date getClaimRegistryDate() {
        return claimRegistryDate;
    }

    public void setClaimRegistryDate(Date claimRegistryDate) {
        this.claimRegistryDate = claimRegistryDate;
    }

    public Integer getClaimClaimedAmount() {
        return claimClaimedAmount;
    }

    public void setClaimClaimedAmount(Integer claimClaimedAmount) {
        this.claimClaimedAmount = claimClaimedAmount;
    }
    
    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public Person getClaimPersonBeneficiaryId() {
        return claimPersonBeneficiaryId;
    }

    public void setClaimPersonBeneficiaryId(Person claimPersonBeneficiaryId) {
        this.claimPersonBeneficiaryId = claimPersonBeneficiaryId;
    }

    public Policy getClaimPolicyNumber() {
        return claimPolicyNumber;
    }

    public void setClaimPolicyNumber(Policy claimPolicyNumber) {
        this.claimPolicyNumber = claimPolicyNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claimId != null ? claimId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Claim)) {
            return false;
        }
        Claim other = (Claim) object;
        if ((this.claimId == null && other.claimId != null) || (this.claimId != null && !this.claimId.equals(other.claimId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.whitestar.gcapi.Claim[ claimId=" + claimId + " ]";
    }
    
}
