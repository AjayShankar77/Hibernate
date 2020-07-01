package g3.hibernate.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	public static void main(String[] args) {
		User user = new User();
		user.setId(2);
		user.setName("Dhilip");
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();//session connection
		session.beginTransaction();//Ready for query
		session.save(user);//API
		session.getTransaction().commit();//Will affect the Backend

	}

}
