package com.kylin.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongodbTest {
	
	public static void main(String[] args) throws UnknownHostException {
		
		//Mongo�������MongoDB�����������ӣ��ж��ֹ��캯�����޲ι��캯��Ĭ������localhost:27017. 
		Mongo connection = new Mongo("localhost:27017");
		//DB��������ݿ⣬�����ǰ��������û�и����ݿ⣬��Ĭ�ϴ���һ�� 
		DB db = connection.getDB("admin");
		// DBCollection�����ϣ�������ݿ���û�иü��ϣ���Ĭ�ϴ���һ�� 
		DBCollection users = db.getCollection("users");
		
		 /** 
         * DBObject�����ĵ�������һ���ӿڣ�java���ṩ�˶���ʵ�֣���򵥵ľ���BasicDBObject�� 
         */  
        DBObject user = new BasicDBObject();  
        user.put("name", "jimmy");  
        user.put("age", "34");  
        DBObject address = new BasicDBObject();  
        address.put("city", "bj");  
        address.put("street", "bq road");  
        address.put("mail", "ufpark 68#");  
        /** 
         * ������Ƕ�ĵ���������Ҫ�Ƚ���Ƕ�ĵ���������䵽����ĵ��У� 
         */  
        user.put("address", address);  
        // �����ĵ����뵽������  
        users.insert(user);  
        // �Ӽ����в�ѯ���ݣ����ǾͲ�ѯһ��������findOne����  
        DBObject dbUser = users.findOne();  
        System.out.println("name" + " : "  + dbUser.get("name") );  
        System.out.println("age" + " : "  + dbUser.get("age") );  
        DBObject dbAddress = (DBObject)user.get("address");  
        System.out.println("city" + " : "  + dbAddress.get("city") );  
        System.out.println("street" + " : "  + dbAddress.get("street") );  
        System.out.println("mail" + " : "  + dbAddress.get("mail") );  
          
	}
	
	@Test
	public void Test1() throws UnknownHostException{
		//Mongo�������MongoDB�����������ӣ��ж��ֹ��캯�����޲ι��캯��Ĭ������localhost:27017. 
		Mongo connection = new Mongo("localhost:27017");
		//DB��������ݿ⣬�����ǰ��������û�и����ݿ⣬��Ĭ�ϴ���һ�� 
		DB db = connection.getDB("admin");
		// DBCollection�����ϣ�������ݿ���û�иü��ϣ���Ĭ�ϴ���һ�� 
		DBCollection users = db.getCollection("users");
		
		/** 
		*  ����ˮ�����ĵ����� 
		*/  
		DBObject shop1 = new BasicDBObject();  
		 shop1.put("name", "The Fruit King");  
		 /** 
		*  ˮ������ˮ��������һ����Ƕ�ĵ��������У���ʽΪ�� 
		*  [{"name" : "apple", "quality" : "good", "price" : "5.6"},  
		*   {"name" : "orange", "quality" : "normal", "price" : "1.5"}, 
		*   ......] 
		*/  
		// ����ͨ��List��ʾ  
		List<DBObject> fruits = new ArrayList<DBObject>();  
		// �����е�ÿһ���ĵ�������ͨ��BasicDBObjectBuilder������  
		fruits.add(BasicDBObjectBuilder.start().add("name", "apple").add("quality", "good").add("price", "5.6").get());  
		fruits.add(BasicDBObjectBuilder.start().add("name", "orange").add("quality", "normal").add("price", "1.5").get());  
		shop1.put("fruits", fruits);  
		   
		users.insert(shop1);  
	}
}
