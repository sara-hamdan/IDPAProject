package com.example.idpaproject1.other;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import javax.xml.parsers.ParserConfigurationException;
import java.net.StandardSocketOptions;
import java.util.ArrayList;

public class TestParser {
    public static void main(String[] args) throws ParserConfigurationException {

        NiermanJagadishAlgorithm nandj = new NiermanJagadishAlgorithm();
        Document doc = nandj.parseXML("src/testFile.xml");


        System.out.println();
        DocumentTraversal traversal = (DocumentTraversal) doc;
        TreeWalker treeWalker = traversal.createTreeWalker(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        Document doc2 = nandj.parseXML("src/testFile2.xml");
        boolean containedIn = false;
        System.out.println();
        nandj.isContainedIn(containedIn, doc2.getDocumentElement().getChildNodes().item(1), doc, treeWalker);
        System.out.println(containedIn);

        



    }
}
