package edu.gatech.chai.omopv5.dba.service;

import org.springframework.stereotype.Service;

import edu.gatech.chai.omopv5.jpa.dao.ConceptRelationshipDao;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationship;
import edu.gatech.chai.omopv5.model.entity.ConceptRelationshipPK;

@Service
public class ConceptRelationshipServiceImp extends BaseEntityServiceImp<ConceptRelationship, ConceptRelationshipDao>
		implements ConceptRelationshipService {

	public ConceptRelationshipServiceImp() {
		super(ConceptRelationship.class);
	}

	@Override
	public ConceptRelationship findById(ConceptRelationshipPK conceptRelationshipPk) {
		return getEntityDao().findById(getEntityClass(), conceptRelationshipPk);
	}

	@Override
	public void removeById(ConceptRelationshipPK conceptRelationshipPk) {
		getEntityDao().delete(getEntityClass(), conceptRelationshipPk);
	}
	
}
