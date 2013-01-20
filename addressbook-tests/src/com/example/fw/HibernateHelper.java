package com.example.fw;

import com.example.tests.ContactObject;
import com.example.tests.GroupObject;
import com.example.utils.SortedListOf;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateHelper extends HelperBase {

	public HibernateHelper(ApplicationManager manager) {
	  super(manager);
	}

	public List<GroupObject> getGroups() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction trans = session.beginTransaction();
		try {
          return new SortedListOf<GroupObject>(
              (List<GroupObject>) session.createQuery("from GroupObject").list());
		} finally {
          trans.commit();
		}
	}

    public List<ContactObject> getContacts() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction trans = session.beginTransaction();
        try {
            return new SortedListOf<ContactObject>(
                    (List<ContactObject>) session.createQuery("from ContactObject").list());
        } finally {
            trans.commit();
        }
    }
}
