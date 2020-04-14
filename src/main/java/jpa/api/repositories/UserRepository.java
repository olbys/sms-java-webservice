package jpa.api.repositories;


import jpa.api.api.IGenericDaoImpl;
import jpa.model.Utilisateur;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class UserRepository extends IGenericDaoImpl<Utilisateur> {

    public Collection<Utilisateur> findByLogin(String login) {
        CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        CriteriaQuery<Utilisateur> criteriaQuery =
                builder.createQuery(this.getClazz());
        Root<Utilisateur> root = criteriaQuery.from(this.getClazz());
        criteriaQuery.select(root)
                .where(builder.like(
                        root.get("login"), "%" + login + "%"));
        Query query = this.manager.createQuery(criteriaQuery);
        List<Utilisateur> resultat = query.getResultList();
        return  resultat;

    }
}
