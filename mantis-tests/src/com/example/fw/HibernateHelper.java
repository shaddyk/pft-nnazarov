package com.example.fw;

import com.example.tests.UserObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public ArrayList<UserObject> getUsers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new ArrayList<UserObject>(
              (ArrayList<UserObject>) session.createQuery("from UserObject").list());
		} finally {
          trans.commit();
		}
	}

    public void removeUsersExcludeAdmin() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();

        ArrayList<UserObject> userObjects =
                (ArrayList<UserObject>) session.createQuery("from UserObject").list();
        //Удаляем из списка админскую учетку - всегда первая
        userObjects.remove(0);

        try {
            for (UserObject userObject : userObjects) {
                session.delete(userObject);
            }
        } finally {
            trans.commit();
        }
    }

}
