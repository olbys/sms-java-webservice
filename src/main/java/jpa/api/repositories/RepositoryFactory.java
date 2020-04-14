package jpa.api.repositories;

import jpa.api.api.IGenericDao;

public class RepositoryFactory {

    private static RepositoryFactory INSTANCE;

    private RepositoryFactory() {
        INSTANCE = new RepositoryFactory();
    }


    public static RepositoryFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryFactory();
        }
        return INSTANCE;
    }


    public static IGenericDao getRepository(Class modelRepository){
        try {
            Class repository = Class.forName(modelRepository.getName());
            Object objectClass = repository.newInstance();
            IGenericDao iGenericDao = (IGenericDao) repository.cast(objectClass);
            return iGenericDao;
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return null;
    }

}
