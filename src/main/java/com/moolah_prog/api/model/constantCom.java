package com.moolah_prog.api.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "constantCom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class constantCom {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CC")
	 @GenericGenerator(
			 name = "CC", 
			 strategy = "com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator", 
			 parameters = {
	            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CC_"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	 
	 String orgId;
	 @Column(name = "userId", nullable = false)
	 String userId;
	
	
	@Column (name="M_interval")
	 @JsonFormat(pattern = "yyyy-MM-dd")
	Time duration;
	
	 @Column(name = "moolahTitle", nullable = false)
	 String moolahTitle;
	 
	 
	
	 
	 @Column(name="moolahState",nullable=false)
	 String state;
	 
	 @Transient
	 Integer dYear;
	 @Transient
	  Integer dMonth;
	 @Transient
	 Integer dDay;
	 
	 Long constantAmt;
	 

}
