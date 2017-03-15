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
		
		//Mongo类代表与MongoDB服务器的连接，有多种构造函数。无参构造函数默认连接localhost:27017. 
		Mongo connection = new Mongo("localhost:27017");
		//DB类代表数据库，如果当前服务器上没有该数据库，会默认创建一个 
		DB db = connection.getDB("admin");
		// DBCollection代表集合，如果数据库中没有该集合，会默认创建一个 
		DBCollection users = db.getCollection("users");
		
		 /** 
         * DBObject代表文档，这是一个接口，java中提供了多种实现，最简单的就是BasicDBObject了 
         */  
        DBObject user = new BasicDBObject();  
        user.put("name", "jimmy");  
        user.put("age", "34");  
        DBObject address = new BasicDBObject();  
        address.put("city", "bj");  
        address.put("street", "bq road");  
        address.put("mail", "ufpark 68#");  
        /** 
         * 对于内嵌文档，我们需要先将内嵌文档填充后，再填充到外层文档中！ 
         */  
        user.put("address", address);  
        // 将该文档插入到集合中  
        users.insert(user);  
        // 从集合中查询数据，我们就查询一条，调用findOne即可  
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
		//Mongo类代表与MongoDB服务器的连接，有多种构造函数。无参构造函数默认连接localhost:27017. 
		Mongo connection = new Mongo("localhost:27017");
		//DB类代表数据库，如果当前服务器上没有该数据库，会默认创建一个 
		DB db = connection.getDB("admin");
		// DBCollection代表集合，如果数据库中没有该集合，会默认创建一个 
		DBCollection users = db.getCollection("users");
		
		/** 
		*  创建水果店文档对象 
		*/  
		DBObject shop1 = new BasicDBObject();  
		 shop1.put("name", "The Fruit King");  
		 /** 
		*  水果店内水果保存在一个内嵌文档的数组中，格式为： 
		*  [{"name" : "apple", "quality" : "good", "price" : "5.6"},  
		*   {"name" : "orange", "quality" : "normal", "price" : "1.5"}, 
		*   ......] 
		*/  
		// 数组通过List表示  
		List<DBObject> fruits = new ArrayList<DBObject>();  
		// 数组中的每一个文档，我们通过BasicDBObjectBuilder来构造  
		fruits.add(BasicDBObjectBuilder.start().add("name", "apple").add("quality", "good").add("price", "5.6").get());  
		fruits.add(BasicDBObjectBuilder.start().add("name", "orange").add("quality", "normal").add("price", "1.5").get());  
		shop1.put("fruits", fruits);  
		   
		users.insert(shop1);  
	}
}
