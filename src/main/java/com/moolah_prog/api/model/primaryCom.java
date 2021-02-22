package com.moolah_prog.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "primaryCom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class primaryCom {
	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PC")
	 @GenericGenerator(
			 name = "PC", 
			 strategy = "com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator", 
			 parameters = {
	            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PC_"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	 
	 
	 String orgId;
	 @Column(name = "userId", nullable = false)
	 String userId;
	
	@Column (name="date_created",nullable=false)
	LocalDateTime date_created;
	
	@Column (name="endgoal_date",nullable=false)
	LocalDateTime endgoal_date;
	 @ Column(name="moolahDuration")
	 Period mDuration;
	 @Column(name = "moolahTitle", nullable = false)
	 String moolahTitle;
	 @Column(name = "moolahLimit", nullable = false)
	 long limit;
	 @Column(name = "currAmt")
	 long currAmt;
	 @Column(name = "numOfSecCom")
	 long no_OfSecCom;
	 
	 @Column(name= "status")
	 String status;
	


}
