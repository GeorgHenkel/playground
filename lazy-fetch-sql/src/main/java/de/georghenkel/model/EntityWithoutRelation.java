package de.georghenkel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ENTITY")
public class EntityWithoutRelation implements Serializable {
	@Id
	private Long id;
	
	@Column(name="ENTITY_RELATION_ID")
	private Long entityRelationId;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getEntityRelationId() {
		return entityRelationId;
	}
	
	public void setEntityRelationId(Long entityRelationId) {
		this.entityRelationId = entityRelationId;
	}
}	