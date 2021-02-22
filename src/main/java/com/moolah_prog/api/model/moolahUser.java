package com.moolah_prog.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "moolahUsers")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class moolahUser {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "moolah_user")
	@GenericGenerator(
			name = "moolah_user", 
			strategy = "com.moolah_prog.api.idGenerator.StringPrefixedSequenceIdGenerator", 
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "user_"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	private String userId;


	private String firstName;
	private String lastName;
	private String emailId;
	private String favColor;

	
	private long currAmt;
	private String userName;
	private String password;
	private String type;


}
