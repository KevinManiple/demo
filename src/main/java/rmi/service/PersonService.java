package rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import rmi.model.PersonEntity;

// 此为远程对象调用的接口，必须继承Remote类
public interface PersonService extends Remote {
	List<PersonEntity> GetList() throws RemoteException;
}
