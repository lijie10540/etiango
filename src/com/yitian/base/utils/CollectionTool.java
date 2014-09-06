package com.yitian.base.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionTool {

	private CollectionTool(){
		
	}
	
	/**
	 * ArrayList转String[]
	 * @param arraylist 
	 * @return 如果arraylist==null或者arraylist.size()<1返回null
	 */
	@SuppressWarnings("unchecked")
	public static String[] arrayListToStringArray(List arraylist){
		String[] array=null;
		if((arraylist!=null)&&(arraylist.size()>0)){
			int length=arraylist.size();
			array=new String[length];
			for(int i=0;i<length;i++){
				array[i]=(String)arraylist.get(i);
			}
		}
		return array;
	}
	
	/**
	 * ArrayList转Object[]
	 * @param arraylist
	 * @return 如果arraylist==null或者arraylist.size()<1返回null
	 */
	@SuppressWarnings("unchecked")
	public static Object[] arrayListToObjectArray(List arraylist){
		Object[] array=null;
		if((arraylist!=null)&&(arraylist.size()>0)){
			int length=arraylist.size();
			array=new Object[length];
			for(int i=0;i<length;i++){
				array[i]=arraylist.get(i);
			}
		}
		return array;
	}
	
	/**
	 * Object[][] 转 String[][]
	 * @param objects
	 * @return String[][]
	 */
	public static String[][] objectArrayToStringArray(Object[][] objects){
		String[][] array=null;
		if(objects==null){
			return null;
		}
		int length=objects.length;
		array=new String[length][2];
		for(int i=0;i<length;i++){
			array[i][0]=(String)objects[i][0];
			array[i][1]=(String)objects[i][1];
		}
		return array;
	}
	
	/**
	 * Object[] 转 String[]
	 * @param objects
	 * @return String[]
	 */
	public static String[] objectArrayToStringArray(Object[] objects){
		String[] array=null;
		if(objects==null){
			return null;
		}
		int length=objects.length;
		array=new String[length];
		for(int i=0;i<length;i++){
			array[i]=(String)objects[i];
		}
		return array;
	}
	
	/**
	 * String[] 转 ArrayList
	 * @param array
	 * @return 如果array==null或者array.size()<1返回null
	 */
	@SuppressWarnings("unchecked")
	public static List stringArrayToList(String[] array){
		List arraylist=null;
		if(array!=null){
			int length=array.length;
			arraylist=new ArrayList();
			for(int i=0;i<length;i++){
				arraylist.add(array[i]);
			}
		}
		return arraylist;
	}
	
	/**
	 * Object[] 转 ArrayList
	 * @param array
	 * @return 如果array==null或者array.size()<1返回null
	 */
	@SuppressWarnings("unchecked")
	public static List objectArrayToList(Object[] array){
		List arraylist=null;
		if(array!=null){
			int length=array.length;
			arraylist=new ArrayList();
			for(int i=0;i<length;i++){
				arraylist.add(array[i]);
			}
		}
		return arraylist;
	}
	
	/**
	 * 将Map中的key和value保存到Object[][]中，数组的第一位保存key，第二位保存value
	 * @param map
	 * @return Object[][] if(hashtable==null) return null;
	 */
	@SuppressWarnings("unchecked")
	public static Object[][] getMapKeyValue(Map map){
		Object[][] object=null;
		if ((map != null) && (!map.isEmpty())){
			int hashtableSize = map.size();
			object=new Object[hashtableSize][2];
			Iterator iterator = map.entrySet().iterator();
			for (int i = 0; i < hashtableSize; i++) {
				Map.Entry entry = (Map.Entry) iterator.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				object[i][0]=key;
				object[i][1]=value;
			}
		}
		return object;
	}
}
