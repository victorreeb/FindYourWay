package org.lpro.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, String> mail;
	public static volatile ListAttribute<Utilisateur, Partie> parties;
	public static volatile SingularAttribute<Utilisateur, String> id;
	public static volatile SingularAttribute<Utilisateur, Integer> type;
	public static volatile SingularAttribute<Utilisateur, String> nom;
	public static volatile SingularAttribute<Utilisateur, String> prenom;

}

