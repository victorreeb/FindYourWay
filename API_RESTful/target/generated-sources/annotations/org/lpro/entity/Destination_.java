package org.lpro.entity;

import java.util.ArrayList;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Destination.class)
public abstract class Destination_ {

	public static volatile SingularAttribute<Destination, ArrayList> indices;
	public static volatile SingularAttribute<Destination, Double> lng;
	public static volatile SingularAttribute<Destination, Partie> partie;
	public static volatile SingularAttribute<Destination, String> description;
	public static volatile SingularAttribute<Destination, String> id;
	public static volatile SingularAttribute<Destination, Double> lat;
	public static volatile SingularAttribute<Destination, String> lieu;

}

