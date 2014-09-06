package com.yitian.base.utils;

import java.io.*;

/**
 *  Description of the Class
 */
public class FileUtil {
	  /**
	   * 取得较短的文件目录名 如/a/b/v/../x.doc 返回 /a/b/x.doc
	   *
	   *@param  fileName  Description of Parameter
	   *@return           The ShortFileName value
	   */
  public static String getShortFileName(String fileName) {
    StringBuffer sb = new StringBuffer();
    String[] sa = fileName.replace('\\', '/').split("/");
    for (int i = 0; i < sa.length; i++) {
      if (sa[i].equals(".")) {
        sa[i] = null;
      }
      if (sa[i].equals("..")) {
        sa[i] = null;
        if (i > 0) {
          sa[i - 1] = null;
        }
      }
    }
    ;
    for (int i = 0; i < sa.length; i++) {
      if (sa[i] != null) {
        sb.append("/");
        sb.append(sa[i]);
      }
    }
    return sb.toString();
  }

  /**
   *  读一个文件返回内容字符串
   *
   *@param  fileName         文件名
   *@return                  Description of the Returned Value
   *@exception  IOException  Description of Exception
   */
  public static String readStringFromFile(String fileName) throws IOException {
    return readStringFromFile(fileName, null);
  }

  public static byte[] readBytesFromFile(String fileName) throws
      IOException {
    FileInputStream fin = new FileInputStream(fileName);
    ByteArrayOutputStream outStream = new ByteArrayOutputStream(fin.
        available());
    streamCopy(fin, outStream);
    fin.close();
    return outStream.toByteArray();
  }

  public static String readFromFile(String fileName, String encoding) throws
      IOException {
    return readStringFromFile(fileName, encoding);
  }

  public static String readFromFile(String fileName) throws
      IOException {
    return readStringFromFile(fileName);
  }

  /**
   * 读文件返回字符，指定编码方式
   *
   *@param  fileName         文件名
   *@param  encoding         编码方式
   *@return                  返回字符串
   *@exception  IOException  Description of Exception
   */
  public static String readStringFromFile(String fileName, String encoding) throws
      IOException {
    byte[] data = readBytesFromFile(fileName);
    if (encoding != null) {
      return new String(data, encoding);
    }
    else {
      return new String(data);
    }
  }

  /**
   * 把字符串写到文件里
   *
   *@param  fileName         文件名
   *@param  content          要写的内容
   *@param  encoding         编码方式
   *@exception  IOException  Description of Exception
   */
  public static void writeToFile(String fileName, String content) throws
      IOException {
    writeToFile(fileName, new ByteArrayInputStream(content.getBytes()), true);
  }

  public static void writeToFile(String fileName, byte[] content
      ) throws IOException {
    writeToFile(fileName, new ByteArrayInputStream(content), true);
  }


  /**
   * 把字符串写到文件里
   *
   *@param  fileName         Description of Parameter
   *@param  content          Description of Parameter
   *@exception  IOException  Description of Exception
   */
  public static void writeToFile(String fileName, String content,
                                 String encoding) throws IOException {
    writeToFile(fileName, new ByteArrayInputStream(content.getBytes(encoding)), true);
  }

  /**
   *  把流的内容写到文件中
   *
   *@param  fileName         文件名
   *@param  iStream          输入流
   *@exception  IOException  Description of Exception
   */
  public static void writeToFile(String fileName, InputStream iStream) throws
      IOException {
    writeToFile(fileName, iStream, true);
  }

  /**
   *  把一个流复制到一个流中
   *
   *@param  in               输入流
   *@param  out              输出流
   *@exception  IOException  Description of Exception
   */
  public static long streamCopy(InputStream in, OutputStream out) throws
      IOException {
    long writeCount = 0;
    if ( (in == null) || (out == null)) {
      return 0;
    }
    byte[] buffer = new byte[32 * 1024];
    int bytesRead = 0;
    while ( (bytesRead = in.read(buffer)) != -1) {
      out.write(buffer, 0, bytesRead);
      writeCount += bytesRead;
    }
    return writeCount;
  }

  /**
   *  Writes InputStream to a given <code>fileName<code>.
   * And, if directory for this file does not exists,
   * if createDir is true, creates it, otherwice throws OMDIOexception.
   *
   *
   *
   *@param  fileName      - filename save to.
   *@param  iStream       - InputStream with data to read from.
   *@param  createDir     (false by default)
   *@throws  IOException  in case of any error.
   */
  public static void writeToFile(String fileName, InputStream iStream,
                                 boolean createDir) throws IOException {
    String me = "FileUtils.WriteToFile";
    File theFile = new File(fileName);
    if (createDir && theFile.getParentFile() != null) {
      theFile.getParentFile().mkdirs();
    }

    // Save InputStream to the file.
    FileOutputStream fOut = null;
    try {
      fOut = new FileOutputStream(theFile);
      streamCopy(iStream, fOut);
    }
    catch (Exception e) {
      throw new IOException(me + " failed, got: " + e.toString());
    }
    finally {
      close(iStream);
      close(fOut);
    }
  }

  /**
   *  Closes InputStream and/or OutputStream. It makes sure that both streams
   *  tried to be closed, even first throws an exception.
   *
   *@param  iStream  Description of Parameter
   *@throw           IOException if stream (is not null and) cannot be closed.
   */
  public static void close(InputStream iStream) {

    if (iStream != null) {
      try {
        iStream.close();
      }
      catch (Exception e) {
      }
    }
  }

  /**
   *  Description of the Method
   *
   *@param  oStream  Description of Parameter
   */
  public static void close(OutputStream oStream) {
    if (oStream != null) {
      try {
        oStream.close();
      }
      catch (Exception e) {
      }
    }

  }

  public static final String getCurrentDirectory() throws IOException {
    File currentDirectory = new File(".");
    return currentDirectory.getCanonicalPath();
  }

}
