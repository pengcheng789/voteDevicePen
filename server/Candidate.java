/**
 * 
 */
package server;

import java.io.Serializable;

/**
 * @author chance
 *
 */
public class Candidate implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	private String name;
	private int age;
	private String sex;
	private String detail;
	
	public Candidate(String name, int age, String sex, String detail){
		this.count = 10;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.detail = detail;
	}
	
	public synchronized void setCount(boolean isAdd){
		if(isAdd)
			this.count ++;
		else
			this.count = 0;
	}
	public int getCount(){
		return this.count;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getAge( ){
		return this.age;
	}
	
	public String getsex(){
		return this.sex;
	}
	
	public String getdetail(){
		return this.detail;
	}
	
	public String toString(){
		return "这是" + this.name + "；";
	}
}
