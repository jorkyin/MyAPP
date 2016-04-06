package com.jorkyin.myapp.xml;

import android.text.TextUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuifang on 2016/4/6.
 */
public class SAXParseHandler extends DefaultHandler {
    public static final String ITEM = "item";
    List<WebURL> mWebURLs  ;
    WebURL webURL= new WebURL();
    private WebURL mWebURL;

    //开始这个文件
    @Override
    public void startDocument() throws SAXException {

        super.startDocument();

        mWebURLs = new ArrayList<>();

    }
//结束这个文件
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
//开始这个元素
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        mWebURL = new WebURL();
        if (TextUtils.equals(localName, ITEM)){
             for (int i=0;i<attributes.getLength();i++){
                 if (TextUtils.equals(attributes.getLocalName(i),"id")){
                     mWebURL.setID(Integer.valueOf(attributes.getValue(i)));
                 }
             }
        }
    }
//结束这个元素
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }
//
    @Override
    public void  characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String content= String.valueOf(ch,start,length);
    }

    public List<WebURL> getXMLList() {
        return mWebURLs;
    }
}
