/*******************************************************************************
  * Copyright (c) 2019 Georgia Tech Research Institute
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *     http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  *******************************************************************************/
 package edu.gatech.chai.omopv5.model.entity;

 import java.util.Date;

 import javax.persistence.Access;
 import javax.persistence.AccessType;
 import javax.persistence.CascadeType;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.FetchType;
 import javax.persistence.Id;
 import javax.persistence.JoinColumn;
 import javax.persistence.ManyToOne;
 import javax.persistence.Table;
 import javax.persistence.Temporal;
 import javax.persistence.TemporalType;

 import net.jcip.annotations.Immutable;

 @Entity
 @Immutable
 @Table(name="f_immunization_view")
 public class FImmunizationView extends BaseEntity {
 	@Id
 	@Column(name="immunization_id")
 	@Access(AccessType.PROPERTY)
 	private Long id;

 	@ManyToOne(cascade={CascadeType.MERGE})
 	@JoinColumn(name = "person_id", nullable = false)
 	private FPerson fPerson;

 	@ManyToOne(cascade = { CascadeType.MERGE })
 	@JoinColumn(name = "immunization_concept_id", nullable = false)
 	private Concept immunizationConcept;

 	@Column(name = "immunization_date", nullable = false)
 	@Temporal(TemporalType.DATE)
 	private Date immunizationDate;

 	@Column(name = "immunization_datetime")
 	@Temporal(TemporalType.TIMESTAMP)
 	private Date immunizationDatetime;

 	@ManyToOne(cascade = { CascadeType.MERGE })
 	@JoinColumn(name = "immunization_type_concept_id", nullable = false)
 	private Concept immunizationTypeConcept;

 	@Column(name = "immunization_status")
 	private String immunizationStatus;

 	@ManyToOne(cascade = { CascadeType.MERGE }, fetch=FetchType.LAZY)
 	@JoinColumn(name = "provider_id")
 	private Provider provider;

 	@ManyToOne(cascade = { CascadeType.MERGE }, fetch=FetchType.LAZY)
 	@JoinColumn(name = "visit_occurrence_id")
 	private VisitOccurrence visitOccurrence;

 	@Column(name = "lot_number")
 	private String lotNumber;

 	@ManyToOne(cascade = { CascadeType.MERGE }, fetch=FetchType.LAZY)
 	@JoinColumn(name = "route_concept_id")
 	private Concept routeConcept;

 	@Column(name = "quantity")
 	private Double quantity;

 	@Column(name = "immunization_note")
 	private String immunizationNote;

 	public Long getId() {
 		return id;
 	}

 	public void setId(Long id) {
 		this.id = id;
 	}

 	public FPerson getFPerson() {
 		return fPerson;
 	}

 	public void setFPerson(FPerson person) {
 		this.fPerson = person;
 	}

 	public Concept getImmunizationConcept() {
 		return immunizationConcept;
 	}

 	public void setImmunizationConcept(Concept immunizationConcept) {
 		this.immunizationConcept = immunizationConcept;
 	}

 	public Date getImmunizationDate() {
 		return immunizationDate;
 	}

 	public void setImmunizationDate(Date immunizationDate) {
 		this.immunizationDate = immunizationDate;
 	}

 	public Date getImmunizationDatetime() {
 		return immunizationDatetime;
 	}

 	public void setImmunizationDatetime(Date immunizationDatetime) {
 		this.immunizationDatetime = immunizationDatetime;
 	}

 	public Concept getImmunizationTypeConcept() {
 		return immunizationTypeConcept;
 	}

 	public void setImmunizationTypeConcept(Concept immunizationTypeConcept) {
 		this.immunizationTypeConcept = immunizationTypeConcept;
 	}

 	public String getImmunizationStatus () {
 		return immunizationStatus;
 	}

 	public void setImmunizationStatus (String immunizationStatus) {
 		this.immunizationStatus = immunizationStatus;
 	}

 	public Provider getProvider() {
 		return provider;
 	}

 	public void setProvider(Provider provider) {
 		this.provider = provider;
 	}

 	public VisitOccurrence getVisitOccurrence() {
 		return visitOccurrence;
 	}

 	public void setVisitOccurrence(VisitOccurrence visitOccurrence) {
 		this.visitOccurrence = visitOccurrence;
 	}

 	public String getLotNumber() {
 		return lotNumber;
 	}

 	public void setLotNumber(String lotNumber) {
 		this.lotNumber = lotNumber;
 	}

 	public Concept getRouteConcept () {
 		return routeConcept;
 	}

 	public void setRouteConcept (Concept routeConcept) {
 		this.routeConcept = routeConcept;
 	}

 	public Double getQuantity() {
 		return quantity;
 	}

 	public void setQuantity(Double quantity) {
 		this.quantity = quantity;
 	}

 	public String getImmunizationNote() {
 		return immunizationNote;
 	}

 	public void setImmunizationNote(String immunizationNote) {
 		this.immunizationNote = immunizationNote;
 	}

 	@Override
 	public Long getIdAsLong() {
 		return getId();
 	}


 }