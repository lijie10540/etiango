package com.yitian.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.ClassLoader;

/**
 *  对象序列化工具
 */
public class SerializableObject
    implements java.io.Serializable {
    private static final long serialVersionUID=1L;
  /**
   *  Description of the Field
   */
  public byte data[];

  /**
   *  保存一个对象
   *
   *@param  obj  Description of Parameter
   *@return      Description of the Returned Value
   */
  public boolean SaveObject(Object obj) {
    if (obj == null) {
      return true;
    }
    Exception saveException = null;

    if (Serializable.class.isInstance(obj)) {
      try {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.writeObject(obj);
        stream.close();
        this.data = out.toByteArray();
        out.close();
      }
      catch (Exception e) {
        saveException = e;
      }
    }
    else {
      saveException = new Exception(obj.getClass().getName() +
                                    "  not implements java.io.Serializable");
    }

    if (saveException != null) {
      throw new RuntimeException("Serialize Error",saveException);
    }
    else {
      return true;
    }
  }

  /**
   *  取得一个对象
   *
   *@return    Description of the Returned Value
   */
  public Object LoadObject() {
    return LoadObject(null);
  }

  /**
   *  用指定的ClassLoader调入一个对象
   *
   *@param  loader  Description of Parameter
   *@return         Description of the Returned Value
   */
  public Object LoadObject(ClassLoader loader) {
    if ( (this.data == null) || (this.data.length == 0)) {
      return null;
    }
    Object obj = null;
    ByteArrayInputStream in = null;
    ObjectInputStream stream = null;
    if (data != null) {
      try {
        in = new ByteArrayInputStream(data);
        stream = new ObjectInputStream(in);
        obj = stream.readObject();
        stream.close();
        in.close();
      }
      catch (Exception e) {
     throw new RuntimeException("LoadObject",e);
      }
    }
    return obj;
  }


  /**
   *  复制一个对象
   *
   *@param  obj  输入对象
   *@return      输出对象
   */
  public static Object clone(Object obj) {
    if (obj == null) {
      return null;
    }
    SerializableObject sol = new SerializableObject();
    sol.SaveObject(obj);
    return sol.LoadObject();
  }

  /**
   *  把一个可序列对象变为二进制数组
   *
   *@param  obj  对象
   *@return      Description of the Returned Value
   */
  public static byte[] toByteArray(Object obj) {
    SerializableObject sol = new SerializableObject();
    if (obj != null) {
      sol.SaveObject(obj);
    }
    return sol.data;
  }

  /**
   *  把二进制数组变为对象
   *
   *@param  bs  二进制数组
   *@return     值对象
   */
  public static Object toObject(byte[] bs) {
    SerializableObject sol = new SerializableObject();
    sol.data = bs;
    return sol.LoadObject();
  }

}
