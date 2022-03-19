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

        NiermanJagadishAlgorithm nandj = new NiermanJagadishAlgorithm();
        Document doc = nandj.parseXML("src/testFile.xml");


        System.out.println();
        DocumentTraversal traversal = (DocumentTraversal) doc;
        TreeWalker treeWalker = traversal.createTreeWalker(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        Document doc2 = nandj.parseXML("src/testFile2.xml");

        System.out.println();
        ArrayList<Node[]> arrayList = new ArrayList<>();
        nandj.getSubtrees(doc, arrayList, treeWalker);
        int count = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            Node[] array = arrayList.get(i);

            for (int j = 0; j < arrayList.get(i).length; j++) {
                System.out.print(array[j]+ " ");

            }
            System.out.println();



            count++;
        }
        System.out.println(count);

        System.out.println(nandj.isContainedIn(doc2.getDocumentElement().getChildNodes().item(1), arrayList));

    }



}
