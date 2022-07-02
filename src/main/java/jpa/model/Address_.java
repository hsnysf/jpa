package jpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Address.class)
public class Address_ {
	
	public static volatile SingularAttribute<Address, Integer> id;
	public static volatile SingularAttribute<Address, Integer> building;
	public static volatile SingularAttribute<Address, Integer> road;
	public static volatile SingularAttribute<Address, Integer> block;
	public static volatile SingularAttribute<Address, String> city;
}