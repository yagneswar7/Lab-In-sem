package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {

        SessionFactory sf = new Configuration()
                .configure()
                .addAnnotatedClass(Supplier.class)
                .buildSessionFactory();

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Supplier sup = new Supplier();
        sup.setName("Global Tech");
        sup.setDescription("IT Hardware");
        sup.setStatus("Active");
        sup.setDate("2023-10-27");

        s.save(sup);
        System.out.println("Inserted ID: " + sup.getId());

        Supplier updateSup = s.get(Supplier.class, sup.getId());

        if(updateSup != null) {
            updateSup.setName("Updated Name");
            updateSup.setStatus("Completed");
            s.update(updateSup);
        }

        t.commit();

        s.close();
        sf.close();
    }
}