package de.georghenkel.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ENTITY")
public class EntityWithRelation implements Serializable {
	@Id
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ENTITY_RELATION_ID", insertable = false, updatable = false)
	private EntityRelation entityRelation;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public EntityRelation getEntityRelation() {
		return entityRelation;
	}
	
	public void setEntityRelation(EntityRelation entityRelation) {
		this.entityRelation = entityRelation;
	}
}	