package com.jorkyin.myapp;

import android.content.Context;

import com.jorkyin.myapp.xml.SAXParseHandler;
import com.jorkyin.myapp.xml.WebURL;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by YinJian on 2016/4/14.
 */
public class XMLParse {
    private Context mContext;

    public XMLParse(Context context) {
        mContext = context;
    }

    private void testSAXParse() throws ParserConfigurationException, SAXException, IOException {
        //创建工厂
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        //创建解析器
        SAXParser saxParser = saxParserFactory.newSAXParser();
        //从解析器中读取Readr
        XMLReader xmlReader = saxParser.getXMLReader();

        SAXParseHandler saxParseHandler = new SAXParseHandler();
        xmlReader.setContentHandler(saxParseHandler);

        InputStream inputStream = mContext.getResources().openRawResource(R.raw.test);
        InputSource inputSource = new InputSource(inputStream);

        xmlReader.parse(inputSource);
        List<WebURL> webURLs = saxParseHandler.getXMLList();
        if (webURLs.size() == 0) {
            System.out.println("没有解析到数据");
        }
        for (int i = 0; i < webURLs.size(); i++) {
            System.out.println(webURLs.get(i).getID() + "  " + webURLs.get(i).getUrl() + "  " + webURLs.get(i).getContent());
        }

        //简单写法
   /* XMLReader xmlReader1Test= SAXParserFactory.newInstance().newSAXParser().getXMLReader();
    xmlReader1Test.setContentHandler(new SAXParseHandler());
    xmlReader1Test.parse(new InputSource(getResources().openRawResource(R.raw.test)));*/


    }

}
