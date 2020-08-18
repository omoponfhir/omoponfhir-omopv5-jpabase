package edu.gatech.chai.omopv5.model.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="relationship")
public class Relationship extends BaseEntity {
	@Id
	@Column(name="relationship_id", nullable=false)
	@Access(AccessType.PROPERTY)
	private String id;

	@Column(name="relationship_name", nullable=false)
	private String relationshipName;

	@Column(name="is_hierarchical", nullable=false)
	private Character isHierarchical;
	
	@Column(name="defines_ancestry", nullable=false)
	private Character definesAncestry;

	@Column(name="reverse_relationship_id", nullable=false)
	private String reverseRelationshipId;

	@ManyToOne
	@JoinColumn(name="relationship_concept_id", nullable=false)
	private Concept relationshipConcept;
	
	public Relationship() {
		super();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRelationshipName() {
		return relationshipName;
	}

	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

	public Character getIsHierarchical() {
		return isHierarchical;
	}
	
	public void setIsHierarchical(Character isHierarchical) {
		this.isHierarchical = isHierarchical;
	}

	public Character getDefinesAncestry(){
		return definesAncestry;
	}
	public void setDefinesAncestry(Character definesAncestry) {
		this.definesAncestry = definesAncestry;
	}

	public String getReverseRelationshipId() {
		return reverseRelationshipId;
	}
	
	public void setReverseRelationshipId(String reverseRelationshipId) {
		this.reverseRelationshipId = reverseRelationshipId;
	}
	
	public Concept getRelationshipConcept() {
		return relationshipConcept;
	}
	
	public void setRelationshipConcept(Concept relationshipConcept) {
		this.relationshipConcept = relationshipConcept;
	}

}
