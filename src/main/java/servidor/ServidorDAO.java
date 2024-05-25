package servidor;

import jakarta.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.List;

public class ServidorDAO {
    private static Transaction tx = null;
    private static Session session = null;

    public static Object create(Object object, boolean autocommit) throws Exception {
        try {
            if (autocommit == true) {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                session.persist(object);
                tx.commit();
                session.close();
            } else {
                session.persist(object);
            }
            return object;
        } catch (HibernateException he) {
//            he.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            throw he;
        }
    }

    public static Object update(Object object, Boolean autocommit) throws Exception {
        try {
            if (autocommit == true) {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                object = session.merge(object);
                tx.commit();
                session.close();
            } else {
                object = session.merge(object);
            }
            return object;
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            throw he;
        }
    }

    public static Object update(Object object) throws Exception {
        return update(object, true);
    }
    public static List<Object> readList(Object hql) throws Exception {
        return readList(hql,true);
    }
    public static List<Object> readList(Object hql,boolean autocommit) throws Exception{
        List<Object> results=null;
        Query<Object> query=null;
        try {
            if (autocommit == true) {
                try(Session session = HibernateUtil.getSessionFactory().openSession()){
                    query = session.createQuery((String) hql, Object.class);
                    results = query.getResultList();
                }
            }else{
                query = session.createQuery((String) hql, Object.class);
                results = query.getResultList();
            }
            return results;
        } catch (NoResultException exception) {
//            exception.printStackTrace();
            return null;
        }catch (Exception he){
            throw he;
        }
    }

    public static Object readObject(Object hql) throws Exception {
        return readObject(hql,true);
    }
    public static Object readObject(Object hql,boolean autocommit) throws Exception{
        Object results=null;
        Query<Object> query=null;
        try{
            if (autocommit == true) {
                Session session = HibernateUtil.getSessionFactory().openSession();
                query = session.createQuery((String) hql, Object.class);
                results = query.getSingleResult();
                session.close();
            }
            else{
                query = session.createQuery((String) hql, Object.class);
                results = query.getSingleResult();
            }
            return results;
        } catch (NoResultException exception) {
            return null;
        }catch (Exception he){
            throw he;
        }
    }
//
//    public static void remove(Object object) throws Exception {
//        Transaction tx = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            tx = session.beginTransaction();
//            session.remove(user);
//            tx.commit();
//        } catch (HibernateException he) {
//            throw new Exception("Error en hibernate al eliminar el usuario");
//        } catch (Exception ex) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            throw new Exception("No se ha podido insertar el usuario " + user.getName());
//        }
//    }

//    public static ArrayList<String> listClassNames(final File folder) {
//        ArrayList<String> clases = new ArrayList<>();
//        for (final File fileEntry : folder.listFiles()) {
//            if (fileEntry.isFile() && fileEntry.getName().endsWith(".java")) {
//                String className = fileEntry.getName();
//                // Elimina la extensión ".class" para obtener el nombre de la clase
//                className = className.substring(0, className.length() - 5);
//                clases.add(className);
//            }
//        }
//        return clases;
//    }

    public static void transaction() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            throw he;
        }
    }

    public static void commit() throws Exception {
        try {
            tx.commit();
            session.close();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            throw he;
        }
    }

    public static void delete(Object object, Boolean autocommit) {
        try {
            if (autocommit == true) {
                session = HibernateUtil.getSessionFactory().openSession();
                tx = session.beginTransaction();
                session.remove(object);
                tx.commit();
                session.close();
            } else {
                session.remove(object);
            }
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            session.close();
            throw he;
        }
    }

    public static void delete(Object object) throws Exception {
        delete(object, true);
    }
}
