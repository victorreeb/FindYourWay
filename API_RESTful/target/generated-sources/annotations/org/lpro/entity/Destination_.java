package org.lpro.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Destination.class)
public abstract class Destination_ {

	public static volatile ListAttribute<Destination, Indice> indices;
	public static volatile SingularAttribute<Destination, String> lng;
	public static volatile SingularAttribute<Destination, Partie> partie;
	public static volatile SingularAttribute<Destination, String> description;
	public static volatile SingularAttribute<Destination, String> id;
	public static volatile SingularAttribute<Destination, String> lat;
	public static volatile SingularAttribute<Destination, String> lieu;

}

