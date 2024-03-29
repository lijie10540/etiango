﻿package com.yitian.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class XmlUtils {
	private static byte [] emptXmlData="<?xml version='1.0' encoding='UTF-8'?>".getBytes();
	

  protected ErrorHandler getErrorHandler() {
    return new ErrorHandler() {
      public void warning(SAXParseException exception) throws SAXException {
      }

      public void error(SAXParseException exception) throws SAXException {
        exception.printStackTrace();
      }

      public void fatalError(SAXParseException exception) throws SAXException {
        exception.printStackTrace();
      }
    };
  }
  protected EntityResolver getIgnoreDTDEntityResolver(){
	  
	 return  new  EntityResolver() {
	   public InputSource resolveEntity(String publicId, String systemId)
	           throws SAXException, java.io.IOException
	    {
    	return new InputSource(new ByteArrayInputStream(
	    	emptXmlData));
	    }
	 };
  }

  private DocumentBuilderFactory documentBuilderFactory;

  /**
   * Not Thread Safe
   * @return javax.xml.parsers.DocumentBuilderFactory
   */
  public synchronized DocumentBuilderFactory getDocumentBuilderFactory(boolean
      validating, boolean namespaceAware) {
    if (documentBuilderFactory == null) {
      // configure the document builder factory
      documentBuilderFactory = DocumentBuilderFactory.newInstance();
      documentBuilderFactory.setValidating(validating);
      documentBuilderFactory.setNamespaceAware(namespaceAware);
    }
    return documentBuilderFactory;
  }

  private static XmlUtils __Instance;
  public static XmlUtils getInstance() {
    if (__Instance == null) {
      __Instance = new XmlUtils();
    }
    return __Instance;
  }

  /**
   * @param is an InputSource
   * @return org.w3c.dom.Document
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws org.xml.sax.SAXException
   * @throws java.io.IOException
   */
  private static DocumentBuilder getDocumentBuilder(boolean validating,
      boolean namespaceAware) throws ParserConfigurationException {
    DocumentBuilder builder =
        XmlUtils.getInstance().getDocumentBuilderFactory(validating,
        namespaceAware).newDocumentBuilder();
    // parse the damned document
    builder.setErrorHandler(XmlUtils.getInstance().getErrorHandler());
    if(!validating){
    	  builder.setEntityResolver(XmlUtils.getInstance().getIgnoreDTDEntityResolver());   	
    }
    return builder;
  }

  private static DocumentBuilder getDocumentBuilder() throws
      ParserConfigurationException {
    return getDocumentBuilder(false, false);
  }

  public static Document newDocument() throws
      ParserConfigurationException, SAXException, IOException {
    return getDocumentBuilder().newDocument();
  }

  public static Document parse(InputSource is) throws
      ParserConfigurationException, SAXException, IOException {
    return getDocumentBuilder().parse(is);
  }

  public static Document parse(InputStream is) throws
      ParserConfigurationException, SAXException, IOException {

    byte[] sign = new byte[1];
    while ( (is.available() > 0)) {
      is.read(sign);
      if (sign[0] == '<') {
        break;
      }
    }
    if ( (is.available() > 0)) {
      long skipCount = 0;
      try {
        skipCount = is.skip( -1);
      }
      catch (Throwable t) {
        skipCount = 0;
      }
      if (skipCount != -1) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(is.
            available());
        sign[0] = '<';
        outStream.write(sign);
        FileUtil.streamCopy(is, outStream);
        ByteArrayInputStream bs = new ByteArrayInputStream(outStream.
            toByteArray());
        is = bs;
      }
    }

    return getDocumentBuilder().parse(is);
  }

  public static Document parseText(String S, String encoding) throws
      ParserConfigurationException, SAXException, IOException {
    ByteArrayInputStream stream = new ByteArrayInputStream(S.getBytes(encoding));
    Document doc = getDocumentBuilder().parse(stream);
    stream.close();
    return doc;
  }

  public static Document parse(File f) throws ParserConfigurationException,
      SAXException, IOException {
    FileInputStream fs = null;

    try {
      fs = new FileInputStream(f);
      return getDocumentBuilder().parse(fs);

    }
    finally {
      if (fs != null) {
        fs.close();
      }
    }
  }

  public static Document parse(String filename) throws
      ParserConfigurationException, SAXException, IOException {
    return parse(new File(filename));
  }

  public static String getAttribute(Node node, String name) {
    return getAttribute(node, name, null);
  }

  public static String getAttribute(Node node, String name, String defVal) {
    NamedNodeMap map = node.getAttributes();
    if (map != null) {
      Attr attr = (Attr) map.getNamedItem(name);
      if (attr != null) {
        return attr.getValue();
      }
    }
    return defVal;
  }

  public static void setAttribute(Node node, String name, String val) {
    if (Element.class.isInstance(node)) {
      ( (Element) node).setAttribute(name, val);
    }
  }

  public static void removeAttribute(Node node, String name) {
    Attr attr = (Attr) node.getAttributes().getNamedItem(name);
    if (attr != null) {
      node.getAttributes().removeNamedItem(name);
    }
  }

  public static Node createChildNode(Node parent, String name) {
    Document doc;
    if (Document.class.isInstance(parent)) {
      doc = (Document) parent;
    }
    else {
      doc = parent.getOwnerDocument();
    }
    Node child = doc.createElement(name);
    parent.appendChild(child);
    return child;
  }

  public static Node createTextNode(Node parent, String text) {
    Document doc;
    if (Document.class.isInstance(parent)) {
      doc = (Document) parent;
    }
    else {
      doc = parent.getOwnerDocument();

    }
    Node node = doc.createTextNode(text);
    parent.appendChild(node);
    return node;
  }
  public static CDATASection  createCDATASection(Node parent, String text){
	  Document doc;
	    if (Document.class.isInstance(parent)) {
	      doc = (Document) parent;
	    }
	    else {
	      doc = parent.getOwnerDocument();

	    }
	    CDATASection section=doc.createCDATASection(text);
	    parent.appendChild(section);
	    return section;
  }

  public static NodeList getChildListByName(Node node, String nodeName,
                                            int nodeIndex) {
    SampleNodeList list = new SampleNodeList();
    node = node.getFirstChild();
    while (node != null) {
      if (node.getNodeName().equals(nodeName)) {
        if (nodeIndex != -1) {
          nodeIndex--;
          if (nodeIndex == 0) {
            list.add(node);
          }
          ;

        }
        else {
          list.add(node);
        }
      }
      node = node.getNextSibling();
    }

    return list;
  }

  public static Node getChildByName(Node node, String nodeName
                                    ) {
    return getChildByName(node, nodeName, 1);
  }

  public static Node getChildByName(Node node, String nodeName,
                                    int nodeIndex) {

    Node findNode = null;
    NodeList list = getChildListByName(node, nodeName, nodeIndex);
    if (list.getLength() > 0) {
      findNode = list.item(0);
      ( (SampleNodeList) list).clear();
    }
    return findNode;
  }

  public static String getXPath(Node node) {
    String xpath = new String(node.getNodeName());
    if (node.getParentNode() != null) {
      int index = 0;
      Node prec = node;
      while (prec != null) {
        if (prec.getNodeName().toString().equals(
            node.getNodeName().toString())) {
          index++;
        }
        prec = prec.getPreviousSibling();
      }
      if (node.getParentNode() instanceof Document) {
      }
      else {
        xpath = getXPath(node.getParentNode()) + "/" + xpath;
        if (index > 1) {
          xpath = xpath + "["
              + String.valueOf(index) + "]";
        }
      }

    }
    return xpath;
  }

  static public String XPathEvaluate(Node parentNode, String path) {
    String nodePath, attrName = null;
    int arrIndex = path.indexOf('@');
    if (arrIndex > 0) {
      nodePath = path.substring(0, arrIndex);
      attrName = path.substring(arrIndex + 1);
    }
    else {
      nodePath = path;

    }
    Node node = getNodebyXPath(parentNode, nodePath);
    if (node == null) {
      return null;
    }
    if (attrName == null) {
      return node.getFirstChild().getNodeValue();
    }
    return getAttribute(node, attrName);

  }

  static public String[] XPathEvaluateA(Node parentNode, String path) {
    String nodePath, attrName = null;
    int arrIndex = path.indexOf('@');
    if (arrIndex > 0) {
      nodePath = path.substring(0, arrIndex);
      attrName = path.substring(arrIndex + 1);
    }
    else {
      nodePath = path;

    }
    NodeList nodes = getNodeListbyXPath(parentNode, nodePath);
    if (nodes == null) {
      return null;
    }
    int valueLength = nodes.getLength();
    String[] values = new String[valueLength];
    for (int i = 0; i < valueLength; i++) {
      Node node = nodes.item(i);
      if (attrName == null) {
        values[i] = node.getFirstChild().getNodeValue();
      }
      values[i] = getAttribute(node, attrName);
    }
    return values;
  }

  static public Node getNodebyXPath(Node parentNode, String path) {
    NodeList list = getNodeListbyXPath(parentNode, path);
    if (list.getLength() > 0) {
      return list.item(0);
    }
    return null;
  }

  static public NodeList getNodeListbyXPath(Node parentNode, String path) {
    String[] nodePaths = path.split("/");
    SampleNodeList list = new SampleNodeList();
    Node node = parentNode.getFirstChild(), findNode = null;

    getNodeListbyXPath(node, list, nodePaths, 0);
    return list;
  }

  static public NodeList newNodeList() {
    return new SampleNodeList();
  }

  static class SampleNodeList
      extends ArrayList
      implements NodeList {
    public Node item(int index) {
      return (Node) get(index);
    }

    public int getLength() {
      return this.size();
    }

  }

  static private int getNodeListbyXPath(Node node, SampleNodeList list,
                                        String[] nodePaths,
                                        int level) {
    if (level > nodePaths.length - 1) {
      return 0;
    }
    String nodeName = nodePaths[level];
    if (nodeName.trim().length() == 0) {
      return getNodeListbyXPath(node, list, nodePaths, level + 1);
    }
    int nodeIndex = 1;

    int indexStart = nodeName.indexOf('[');
    int indexEnd = nodeName.indexOf(']');
    if ( (indexStart > 0) && (indexEnd > 0)) {
      String sNodeIndex = nodeName.substring(indexStart + 1, indexEnd);
      if (sNodeIndex.charAt(0) == '*') {
        nodeIndex = -1;
      }
      else {
        nodeIndex = StringUtils.parseInt(sNodeIndex, 1);
      }
      nodeName = nodeName.substring(0, indexStart);
    }
    while (node != null) {
      if (node.getNodeName().equals(nodeName)) {
        if (nodeIndex != -1) {
          level++;
          nodeIndex--;
          if (nodeIndex == 0) {
            if (level != nodePaths.length) {
              return getNodeListbyXPath(node.getFirstChild(), list, nodePaths,
                                        level);
            }
            else {
              list.add(node);
            }
          }
        }
        else {
          if (level + 1 == nodePaths.length) {
            list.add(node);
          }
          else {
            getNodeListbyXPath(node.getFirstChild(), list, nodePaths,
                               level + 1);
          }
        }

      }
      node = node.getNextSibling();
    }

    return level;
  }

  public static String getNodeText(Node node) {
    if (Text.class.isInstance(node)) {
      return ( (Text) node).getNodeValue();
    }
    StringBuffer buffer = new StringBuffer();
    NodeList nodeList = node.getChildNodes();
    int nodeLength = nodeList.getLength();
    for (int i = 0; i < nodeLength; i++) {
      node = nodeList.item(i);
      if (Text.class.isInstance(node)) {
        buffer.append( ( (Text) node).getNodeValue());
      }
    }
    return buffer.toString();
  }

  public static void setNodeValue(Node node, String value)
    {
        if(node == null)
            return;
        Node childNode = null;
        switch(node.getNodeType())
        {
        case Node.ELEMENT_NODE: // '\001'
            childNode = node.getFirstChild();
            if(childNode == null)
            {
                childNode = node.getOwnerDocument().createTextNode(value);
                node.appendChild(childNode);
            } else
            if(childNode.getNodeType() == Node.TEXT_NODE)
                childNode.setNodeValue(value);
            else
                node.appendChild(node.getOwnerDocument().createTextNode(value));
            return;

        case Node.TEXT_NODE: // '\003'
            node.setNodeValue(value);
            return;

        case Node.ATTRIBUTE_NODE: // '\002'
            node.setNodeValue(value);
            return;
        }
    }

    public static String  getNodeValue(Node node)
      {
          if(node == null)
              return null;
          switch(node.getNodeType())
          {
          case Node.ELEMENT_NODE: // '\001'
                  return getNodeText(node);
           case Node.TEXT_NODE: // '\003'
              return node.getNodeValue();
          case Node.ATTRIBUTE_NODE: // '\002'
              return node.getNodeValue();
          }
          return null;
    }
  public static Node findNodeByAttr(Node node, String attrname,
                                    String attrval) {
    return findNodeByAttr(node, attrname, attrval, true);
  }

  public static Node findNodeByAttr(Node node, String attrname,
                                    String attrval, boolean dept) {
    if (node == null || attrname == null || attrname.length() == 0
        || attrval == null || attrval.length() == 0) {
      return null;
    }
    String tmpvalue = null;
    NodeList list = node.getChildNodes();
    int nodeLength = list.getLength();
    for (int i = 0; i < nodeLength; i++) {
      Node n = list.item(i);
      tmpvalue = getAttribute(n, attrname);
      if (attrval.equals(tmpvalue)) {
        return n;
      }
    }
    if (dept) {
      for (int i = 0; i < nodeLength; i++) {
        Node n = findNodeByAttr(list.item(i), attrname, attrval, dept);
        if (n != null) {
          return n;
        }
      }
    }
    return null;
  }
/*
  static public void main(String[] args) throws Exception {
    Document doc = XmlUtils.parse("c:/b.xml");
    String sptah = "/Root/ItemTypeDetail[*]/Process/ProDetail[*]/@ChName";
    String path2[] = XPathEvaluateA(doc, sptah);
    for (int i = 0; i < path2.length; i++) {
      System.out.println(sptah + "  " + path2[i]);

    }
  }*/

}
