package edu.gatech.chai.omopv5.dba.service;

import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK;

public interface ConceptRelationshipService extends IService<ConceptRelationship> {
	public ConceptRelationship findById(ConceptRelationshipPK conceptRelationshipPk);
	public void removeById(ConceptRelationshipPK conceptRelationshipPk);
}
