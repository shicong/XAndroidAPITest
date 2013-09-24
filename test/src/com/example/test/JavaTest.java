package com.example.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaTest
{
    /**
     * 测试Java数据
     */
    public static void testJava()
    {
        test_LinkList();
        test_jason_create();
        test_json_decode();
    }

    private static void test_LinkList()
    {
        LinkedList<String> m1 = new LinkedList<String>();
        m1.add("a");
        m1.add("b");
        m1.add("c");
        
        LinkedList<String> m2 = new LinkedList<String>();
        m2.add("d");
        m2.add("e");
        m2.add("f");

        LinkedList<String> m3 = m1;
        m3.addAll(m2);
        
        m3.remove(0);
        m3.remove(3);
        
        m3.add("g");
        m3.add("h");
        m3.add(0,"a");
    }
    
    private static void test_jason_create()
    {
        String curr_use = "aaaaaaaaaaaa";
        
        ArrayList<String> already_use_list = new ArrayList<String>();
//        already_use_list.add("bbbbbbbbbbb");
//        already_use_list.add("ccccccccccc");
        
        ArrayList<String> never_use_list = new ArrayList<String>();
        never_use_list.add("ccccccccccc");
        never_use_list.add("ddddddddddd");
        
        LinkedHashSet<String> list = new LinkedHashSet<String>();
//        list.add("eeeeee");
//        list.add("fffff");
        
        JSONArray already_used_array = new JSONArray(already_use_list);
        JSONArray never_used_array = new JSONArray(never_use_list);
        JSONArray list_array = new JSONArray(list);
        
        
        JSONObject themeListMapJsonObject = new JSONObject();
        String xString = null;
        try
        {
            themeListMapJsonObject.put("current_use", curr_use);
            themeListMapJsonObject.put("already_used", already_used_array);
            themeListMapJsonObject.put("never_used", never_used_array);
            themeListMapJsonObject.put("list", list_array);
            
            JSONObject themeMainJsonObject = new JSONObject();
            themeMainJsonObject.put("themelist", themeListMapJsonObject);
            
            xString = themeMainJsonObject.toString();
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void test_json_decode()
    {
        String tmpString = "{\"themelist\":{\"aa\":\"namea\",\"bb\":\"nameb\",\"cc\":\"namec\",\"dd\":\"namea\",\"ee\":\"nameb\",\"ff\":\"namec\"},\"xx\":3}";
        try
        {
            JSONObject obj = new JSONObject(tmpString);
            JSONObject tmpArray = obj.getJSONObject("themelist");
            
            JSONArray aa = tmpArray.names();
            
            HashMap<String, String> aHashMap;
            
            int i = 0;
            i++;
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * Java的反射测试
     * @author shicong
     *
     * 2013-3-21
     */
    private void testJava_FS(){
        
        Person aPerson = new Person();
        
        Class<?> aDemoClass = null;
        try {
            aDemoClass = Class.forName(aPerson.getClass().getName());
            
             Constructor<?>[] xx = aDemoClass.getConstructors();
            
            Person bPerson = (Person) xx[0].newInstance(this);
            Person cPerson = (Person) xx[1].newInstance(this,20);
            Person dPerson = (Person) xx[2].newInstance(this,"shicong");
            

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private class Person{
        String mName;
        int mAge;

        public Person() {
            // TODO Auto-generated constructor stub
        }
        
        public Person(int aAge)
        {
            mAge = aAge;
        }
        
        public Person(String aName)
        {
            mName = aName;
        }
        
    }
}
