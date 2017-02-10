package org.lpro.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Partie.class)
public abstract class Partie_ {

	public static volatile SingularAttribute<Partie, Utilisateur> utilisateur;
	public static volatile ListAttribute<Partie, Destination> destination;
	public static volatile SingularAttribute<Partie, String> description;
	public static volatile SingularAttribute<Partie, String> id;
	public static volatile SingularAttribute<Partie, String> nom;
	public static volatile ListAttribute<Partie, Point> points;

}

