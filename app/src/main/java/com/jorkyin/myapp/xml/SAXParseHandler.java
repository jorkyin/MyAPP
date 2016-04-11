package com.jorkyin.myapp.xml;

import android.text.TextUtils;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuifang on 2016/4/6.
 */
public class SAXParseHandler extends DefaultHandler {
    private static final String TAG = SAXParseHandler.class.getSimpleName();
    public static final String ITEM = "item";
    List<WebURL> mWebURLs;
    private WebURL mWebURL;
    private StringBuilder sb = new StringBuilder();

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
        //(3) 开始收集新的标签的数据时，先清空历史数据
        sb.setLength(0);

        mWebURL = new WebURL();
        if (TextUtils.equals(localName, ITEM)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                if (TextUtils.equals(attributes.getLocalName(i), "id")) {
                    mWebURL.setID(Integer.valueOf(attributes.getValue(i)));
                }
                if (TextUtils.equals(attributes.getLocalName(i), "url")) {
                    mWebURL.setUrl(attributes.getValue(i));
                }
            }
        }
    }

    //结束这个标签
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (TextUtils.equals(localName, ITEM)) {
            //(4)原来在characters中取值，现改在此取值
            mWebURL.setContent(sb.toString());
            mWebURLs.add(mWebURL);
        }
    }

    //
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //(2)不管在startElement到endElement的过程中，执行了多少次characters， 都会将内容添加到StringBuilder中，不会丢失内容
        sb.append(ch, start, length);
    }

    public List<WebURL> getXMLList() {
        return mWebURLs;
    }
}
