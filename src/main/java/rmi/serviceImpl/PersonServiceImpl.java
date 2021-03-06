package rmi.serviceImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import rmi.model.PersonEntity;
import rmi.service.*;

// 此为远程对象的实现类，须继承UnicastRemoteObject
public class PersonServiceImpl extends UnicastRemoteObject implements PersonService {

	private static final long	serialVersionUID	= -7407879898651578373L;

	public PersonServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public List<PersonEntity> GetList() throws RemoteException {
		System.out.println("Get Person Start!");
		List<PersonEntity> personList = new LinkedList<PersonEntity>();

		PersonEntity person1 = new PersonEntity();
		person1.setAge(25);
		person1.setId(0);
		person1.setName("Leslie");
		personList.add(person1);

		PersonEntity person2 = new PersonEntity();
		person2.setAge(25);
		person2.setId(1);
		person2.setName("Rose");
		personList.add(person2);

		return personList;
	}
}
