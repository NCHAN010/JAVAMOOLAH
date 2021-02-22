package com.moolah_prog.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "secondaryCom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class secondaryCom {

	
	@Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SC")
	 @GenericGenerator(
			 name = "SC", 
			 strategy = "com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator", 
			 parameters = {
	            @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SC_"),
	            @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	 
	 
	 String secondaryId;
	 @Column(name = "orgId") //primaryComponent orgId
	 String orgId;
	 @Column(name = "userId",nullable=false) //primaryComponent orgId
	 String userId;
	 @Column(name = "moolahTitle", nullable = false)
	 String moolahTitle;
	 @Column(name = "moolahLimit", nullable = false)
	 long limit;
	 @Column(name = "currAmt", nullable = false)
	 long currAmt;
	
}
