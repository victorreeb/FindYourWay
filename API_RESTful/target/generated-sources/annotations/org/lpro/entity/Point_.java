package org.lpro.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Point.class)
public abstract class Point_ {

	public static volatile SingularAttribute<Point, Double> lng;
	public static volatile SingularAttribute<Point, Partie> partie;
	public static volatile SingularAttribute<Point, String> id;
	public static volatile SingularAttribute<Point, String> appellation;
	public static volatile SingularAttribute<Point, Double> lat;

}

