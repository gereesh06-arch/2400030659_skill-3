package com.klef.hql;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.*;

public class MainApp
{
public static void main(String[] args)
{
SessionFactory sf=new Configuration().configure().buildSessionFactory();
Session session=sf.openSession();
Transaction tx=session.beginTransaction();


// Insert Records
session.save(new Product(1,"Laptop","Electronics",50000,5));
session.save(new Product(2,"Mouse","Electronics",500,50));
session.save(new Product(3,"Chair","Furniture",3000,10));
session.save(new Product(4,"Table","Furniture",7000,3));
session.save(new Product(5,"Mobile","Electronics",20000,8));
session.save(new Product(6,"Pen","Stationary",20,100));

tx.commit();


// 🔥 Sorting Asc
Query q1=session.createQuery("from Product order by price asc");
List<Product> l1=q1.list();
l1.forEach(p->System.out.println(p.getName()+" "+p.getPrice()));


// 🔥 Sorting Desc
Query q2=session.createQuery("from Product order by price desc");


// 🔥 Quantity Highest First
Query q3=session.createQuery("from Product order by quantity desc");


// 🔥 Pagination First 3
Query q4=session.createQuery("from Product");
q4.setFirstResult(0);
q4.setMaxResults(3);


// 🔥 Pagination Next 3
Query q5=session.createQuery("from Product");
q5.setFirstResult(3);
q5.setMaxResults(3);


// 🔥 Aggregate
Query q6=session.createQuery("select count(*) from Product");


// 🔥 Min Max Price
Query q7=session.createQuery("select min(price),max(price) from Product");


// 🔥 Group By
Query q8=session.createQuery("select description,count(*) from Product group by description");


// 🔥 Price Range
Query q9=session.createQuery("from Product where price between 1000 and 30000");


// 🔥 LIKE
Query q10=session.createQuery("from Product where name like 'M%'");

session.close();
sf.close();
}
}