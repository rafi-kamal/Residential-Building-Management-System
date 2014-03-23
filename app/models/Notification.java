package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import models.enums.NotificationStatus;
import play.data.validation.Constraints.Required;
import play.db.ebean.*;

@Entity
public class Notification extends play.db.ebean.Model {

	private static final long serialVersionUID = -3698872425235311089L;

	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	public UserAccount receiver;
	
	@Column(insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date issueDate;
	
	@Required
	@Enumerated(EnumType.STRING)
	public NotificationStatus status;
	
}
