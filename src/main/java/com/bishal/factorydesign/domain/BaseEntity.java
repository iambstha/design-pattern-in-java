package com.bishal.factorydesign.domain;

import java.sql.Timestamp;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
	@JsonIgnore
	@Column(name = "created_by", insertable = true, updatable = false)
	private Integer createdBy;
	@JsonIgnore
	@Column(name = "created_ts", insertable = true, updatable = false)
	private Timestamp createdTs;
	@JsonIgnore
	@Column(name = "modified_by", insertable = false, updatable = true)
	private Integer modifiedBy;
	@JsonIgnore
	@Column(name = "modified_ts", insertable = false, updatable = true)
	private Timestamp modifiedTs;

	@Override
	public int hashCode() {
		return Objects.hash(createdBy, createdTs, modifiedBy, modifiedTs);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(createdBy, other.createdBy) && Objects.equals(createdTs, other.createdTs)
				&& Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(modifiedTs, other.modifiedTs);
	}

}
