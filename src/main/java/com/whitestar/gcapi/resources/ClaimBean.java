/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whitestar.gcapi.resources;

import com.whitestar.gcapi.entity.Claim;
import com.whitestar.gcapi.entity.Person;
import com.whitestar.gcapi.entity.Policy;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAURICIO
 */
@Stateless
@Path("claims-operations")
public class ClaimBean {

    private static final Logger logger = Logger.getLogger(ClaimBean.class.getName());

    @PersistenceContext(unitName = "claimsApiPU")
    private EntityManager em;

    /**
     * Creates a new Claim event in the database
     *
     * @param eventDescription
     * @param ocurrenceDate
     * @param reportedDate
     * @param registryDate
     * @param claimedAmount
     * @param beneficiaryId
     * @param policyNumber
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("create-fnol")
    public Long createFnol(
            @QueryParam("eventDescription") String eventDescription,
            @QueryParam("ocurrenceDate") String ocurrenceDate,
            @QueryParam("reportedDate") String reportedDate,
            @QueryParam("registryDate") String registryDate,
            @QueryParam("claimedAmount") int claimedAmount,
            @QueryParam("beneficiaryId") String beneficiaryId,
            @QueryParam("policyNumber") int policyNumber) {

        Date ocurrenceDateD;
        Date reportedDateD;
        Date registryDateD;

        logger.info("Creando FNOL con los siguientes parámetros: ");
        logger.log(Level.INFO, "eventDescription: {0}", eventDescription);
        logger.log(Level.INFO, "ocurrenceDate: {0}", ocurrenceDate);
        logger.log(Level.INFO, "reportedDate {0}", reportedDate);
        logger.log(Level.INFO, "registryDate {0}", registryDate);
        logger.log(Level.INFO, "claimedAmount {0}", claimedAmount);
        logger.log(Level.INFO, "beneficiaryId {0}", beneficiaryId);
        logger.log(Level.INFO, "policyNumber {0}", policyNumber);

        Claim claim = new Claim();
        Policy policy;
        Person person;     

        try {

            ocurrenceDateD = new SimpleDateFormat("dd/MM/yyyy").parse(ocurrenceDate);
            reportedDateD = new SimpleDateFormat("dd/MM/yyyy").parse(reportedDate);
            registryDateD = new SimpleDateFormat("dd/MM/yyyy").parse(registryDate);

            claim.setClaimEventDescription(eventDescription);
            claim.setClaimOcurrenceDate(ocurrenceDateD);
            claim.setClaimReportDate(reportedDateD);
            claim.setClaimRegistryDate(registryDateD);
            claim.setClaimClaimedAmount(claimedAmount);

            //Policy
            policy = (Policy) em.createNamedQuery("Policy.findByPolicyNumber")
                    .setParameter("policyNumber", policyNumber)
                    .getSingleResult();

            claim.setClaimPolicyNumber(policy);

            //Beneficiary
            person = (Person) em.createNamedQuery("Person.findByPersonId")
                    .setParameter("personId", beneficiaryId)
                    .getSingleResult();

            logger.log(Level.INFO, "Se obtiene la siguiente persona para registro del reclamo: {0}",
                    person.getPersonFirstName() + person.getPersonLastName());

            claim.setClaimPersonBeneficiaryId(person);
            claim.setClaimStatus("Open - New");
            //persist new object into database
            em.persist(claim);

        } catch (ParseException e) {
            logger.log(Level.INFO, "exception {0}", e.getLocalizedMessage());
           
        }


        return claim.getClaimId();
    }

    /**
     * Creates a new beneficiary of a claim payment in the database
     *
     * @param personId personal identification for the beneficiary
     * @param personIdType type of ID set for the new person
     * @param personName name of the beneficiary
     * @param personLastName last name of the beneficiary
     * @param personDateOfBirth
     *
     * @return
     */
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("create-person")
    public Person createPerson(@QueryParam("personId") String personId,
            @QueryParam("personIdType") String personIdType,
            @QueryParam("personName") String personName,
            @QueryParam("personLastName") String personLastName,
            @QueryParam("personDateOfBirth") String personDateOfBirth) {

        Person person = new Person();
        Date dateOfBirth;
      

        try {

            dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse(personDateOfBirth);

            person.setPersonId(personId);

            if (personIdType.equals("CC") || personIdType.equals("NIT")) {
                person.setPersonIdType(personIdType);
                person.setPersonFirstName(personName);
                person.setPersonLastName(personLastName);
                person.setPersonDateOfBirth(dateOfBirth);

                em.persist(person);

            } else {                
                
                logger.log(Level.INFO, "Incorrect type of person Id {0}", personIdType);               

            }

        } catch (ParseException e) {
            
            logger.log(Level.INFO, "exception {0}", e.getLocalizedMessage());
            
        }      

        return person;
    }

    /**
     * *
     *
     *
     * @param claimStatus
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("get-claims-by-status")
    public List<Claim> getClaimsByStatus(@QueryParam("claimStatus") String claimStatus) {

        List<Claim> claimsInStatus = null;
        
        logger.log(Level.INFO, "Getting all claims in status: {0}", claimStatus);

        try {

            claimsInStatus = (List<Claim>) em.createNamedQuery("Claim.findByClaimStatus")
                    .setParameter("claimStatus", claimStatus)
                    .getResultList();

            for (Claim c : claimsInStatus) {
                logger.log(Level.INFO, "Claim Id -> {0}", c.getClaimId());
                logger.log(Level.INFO, "Claim policy -> {0}", c.getClaimPolicyNumber().getPolicyNumber());
                logger.log(Level.INFO, "Beneficiario -> {0}", c.getClaimPersonBeneficiaryId().getPersonFirstName() + " " + c.getClaimPersonBeneficiaryId().getPersonLastName());

            }
        } catch (Exception e) {
            logger.log(Level.INFO, "exception {0}", e.getLocalizedMessage());            
            
        }

        return claimsInStatus;

    }

    /**
     * Gets all the claims available in the database
     *
     *
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("get-claims")
    public List<Claim> getAllClaims() {

        int claimCounter = 0;
        List<Claim> registeredClaims = (List<Claim>) em.createNamedQuery("Claim.findAll").getResultList();

        logger.info("Listing all claims registered in DB (only first 10) ");
        for (Claim c : registeredClaims) {
            if (claimCounter < 5) {
                logger.log(Level.INFO, "Claim Id -> {0}", c.getClaimId());
                logger.log(Level.INFO, "Claim policy -> {0}", c.getClaimPolicyNumber().getPolicyNumber());
                logger.log(Level.INFO, "Beneficiario -> {0}", c.getClaimPersonBeneficiaryId().getPersonFirstName() + " " + c.getClaimPersonBeneficiaryId().getPersonLastName());
                claimCounter += 1;
            } else {
                break;
            }
        }

        return registeredClaims;

    }

    /**
     * Get the assigned status of a Claim
     *
     *
     * @param claimId identifier for the claim that wants to be recovered
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("get-claim-by-id")
    public Claim getClaimById(@QueryParam("claimId") Long claimId) {
        Claim claim = null;

        logger.log(Level.INFO, "Retrieveng the claim : {0}", claimId);

        try {
            claim = (Claim) em.createNamedQuery("Claim.findByClaimId")
                    .setParameter("claimId", claimId)
                    .getSingleResult();

            logger.log(Level.INFO, "Claim Id -> {0}", claim.getClaimId());
            logger.log(Level.INFO, "Claim policy -> {0}", claim.getClaimPolicyNumber().getPolicyNumber());
            logger.log(Level.INFO, "Beneficiario -> {0}", claim.getClaimPersonBeneficiaryId().getPersonFirstName() + " " + claim.getClaimPersonBeneficiaryId().getPersonLastName());

        } catch (Exception e) {
            logger.log(Level.INFO, "exception {0}", e.getLocalizedMessage());
        }

        return claim;

    }

}
