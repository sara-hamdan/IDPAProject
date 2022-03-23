package com.example.idpaproject1.other;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import javax.xml.parsers.ParserConfigurationException;
import java.net.StandardSocketOptions;
import java.util.ArrayList;

public class TestParser {
    public static void main(String[] args) throws ParserConfigurationException {
        //
        NiermanJagadishAlgorithm nandj = new NiermanJagadishAlgorithm();
        Document doc = nandj.parseXML("src/SampleDoc2 (original) Refined.xml");


        System.out.println();
        DocumentTraversal traversal = (DocumentTraversal) doc;
        TreeWalker treeWalker = traversal.createTreeWalker(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        Document doc2 = nandj.parseXML("src/SampleDoc2 (original) V1 Refined.xml");

        StringBuilder stringBuilder = new StringBuilder();
        Object[] TED  =(nandj.NiermanJagadishExecute(doc.getDocumentElement(), doc2.getDocumentElement(), doc, doc2));
        System.out.print("Edit distance = " + TED[0] + "\n" + "Edit Script = " + TED[1]);



    }




}
