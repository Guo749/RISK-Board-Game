package edu.duke.group1.server;
import edu.duke.group1.shared.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
public class Database {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            if(sessionFactory == null){
                Configuration cfg = new Configuration()
                        .configure();
                cfg.addAnnotatedClass(PlayerInfo.class);
                cfg.addAnnotatedClass(Room.class);
                org.hibernate.boot.registry.StandardServiceRegistryBuilder builder = new org.hibernate.boot.registry.StandardServiceRegistryBuilder().applySettings(
                        cfg.getProperties());
                SessionFactory factory = cfg.buildSessionFactory(builder.build());
                return factory;
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void addPlayerInfo(PlayerInfo playerInfo){
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            session.save(playerInfo);
            tx.commit();
            session.close();
        }catch (Exception e){

        }
    }

    public static void addRoom(Room room){
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            session.save(room);
            tx.commit();
            session.close();
        }catch (Exception e){

        }
    }

    public static void updatePlayerInfo(PlayerInfo playerInfo){
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(playerInfo);
            tx.commit();
            session.close();
        }catch (Exception e){

        }
    }

    public static void updateRoom(Room room){
        try {
            Session session = sessionFactory.openSession();
            org.hibernate.Transaction tx = session.beginTransaction();
            session.update(room);
            tx.commit();
            session.close();
        }catch (Exception e){

        }
    }

    public static List<PlayerInfo> getPlayerInfos() {
        try {
            Session session = sessionFactory.openSession();
            List<PlayerInfo> playerInfos = session.createQuery("SELECT a FROM PlayerInfo a", PlayerInfo.class).getResultList();
            session.close();
            return playerInfos;
        }catch (Exception e){
            return new java.util.ArrayList();
        }
    }

    public static List<Room> getRooms() {
        try {
            Session session = sessionFactory.openSession();
            List<Room> rooms = session.createQuery("SELECT a FROM Room a", Room.class).getResultList();
            session.close();
            return rooms;
        }catch (Exception e){
            return new java.util.ArrayList();
        }
    }
}
