package org.lpro.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Utilisateur.class)
public abstract class Utilisateur_ {

	public static volatile SingularAttribute<Utilisateur, String> password;
	public static volatile SingularAttribute<Utilisateur, Integer> user_type;
	public static volatile SingularAttribute<Utilisateur, String> fullName;
	public static volatile SingularAttribute<Utilisateur, String> id;
	public static volatile SingularAttribute<Utilisateur, String> email;

}

