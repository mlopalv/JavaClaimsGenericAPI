/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whitestar.gcapi.entity;

import java.io.Serializable;
import java.util.Collection;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author MAURICIO
 */
@Entity
@Table(name = "COVERAGES")
@NamedQueries({
    @NamedQuery(name = "com.whitestar.gcapi.entity.Coverage.findAll", query = "SELECT c FROM Coverage c"),
    @NamedQuery(name = "com.whitestar.gcapi.entity.Coverage.findByCoverageId", query = "SELECT c FROM Coverage c WHERE c.coverageId = :coverageId"),
    @NamedQuery(name = "com.whitestar.gcapi.entity.Coverage.findByCoverageDescription", query = "SELECT c FROM Coverage c WHERE c.coverageDescription = :coverageDescription")})
public class Coverage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COVERAGE_ID")
    private Integer coverageId;
    @Column(name = "COVERAGE_DESCRIPTION")
    private String coverageDescription;
    @ManyToMany(mappedBy = "coveragesCollection")
    private Collection<Policy> policiesCollection;

    public Coverage() {
    }

    public Coverage(Integer coverageId) {
        this.coverageId = coverageId;
    }

    public Integer getCoverageId() {
        return coverageId;
    }

    public void setCoverageId(Integer coverageId) {
        this.coverageId = coverageId;
    }

    public String getCoverageDescription() {
        return coverageDescription;
    }

    public void setCoverageDescription(String coverageDescription) {
        this.coverageDescription = coverageDescription;
    }

    public Collection<Policy> getPoliciesCollection() {
        return policiesCollection;
    }

    public void setPoliciesCollection(Collection<Policy> policiesCollection) {
        this.policiesCollection = policiesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coverageId != null ? coverageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Coverage)) {
            return false;
        }
        Coverage other = (Coverage) object;
        if ((this.coverageId == null && other.coverageId != null) || (this.coverageId != null && !this.coverageId.equals(other.coverageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.whitestar.gcapi.Coverages[ coverageId=" + coverageId + " ]";
    }
    
}
