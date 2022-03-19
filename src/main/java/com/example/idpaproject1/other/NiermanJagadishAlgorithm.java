package com.example.idpaproject1.other;

import org.w3c.dom.*;
import org.w3c.dom.traversal.TreeWalker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.min;

public class NiermanJagadishAlgorithm {


    String inputFile;


    public Document parseXML(String inputFile) {


        JDOMParser parser = new JDOMParser(inputFile);
        Document document = parser.DOMParser();
        return document;
    }

    public int getHeight(Node node) {

        int numElements = 0, numAttributes = 0, numChars = 0, height;


        int type = node.getNodeType();

        if (type == Node.TEXT_NODE) {
            // method getLenth() belongs to the Text interface
            numChars += ((Text) node).getLength();
            return 0;
        }

        if (type == Node.ELEMENT_NODE) {
            numElements++;
            numAttributes += node.getAttributes().getLength();
            NodeList children = node.getChildNodes();
            int outdegree = children.getLength();
            int h = 0;
            int hMax = 0;
            boolean leaf = true;
            for (int i = 0; i < outdegree; i++) {
                h = getHeight(children.item(i));
                if (h > hMax) {
                    hMax = h;
                }
                if (children.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    leaf = false;
                }
            }
            if (leaf) {
                return 0;
            } else {
                return (1 + hMax);
            }
        }
        // in all other cases
        return 0;
    }


    public int getHeightDocument(Document document) {


        return getHeight(document.getDocumentElement());

    }

    public void NiermanJagadishExecute(Document A, Document B) {

        int heightA = getHeightDocument(A);
        int heightB = getHeightDocument(B);

        int[][] editDistanceMatrix = new int[heightA][heightB];
        editDistanceMatrix[0][0] = costUpdate(A.getElementById("OrdinaryIssuePage"), B.getElementById("OrdinaryIssuePage"));

        for (int i = 1; i <= heightA; i++) {

            // editDistanceMatrix[i][0] = editDistanceMatrix[i-1][0]

        }


    }


    public int costUpdate(Node nodeA, Node nodeB) {

        if (nodeA == nodeB) return 0;

        else
            return 1;


    }



    public boolean isContainedIn(Node testNode, ArrayList<Node[]> subtreesListDoc1) {
        boolean isContained = true;

            NodeList nodeList = testNode.getChildNodes();
            Node[] arrayOfNodes = new Node[nodeList.getLength() + 1];
            arrayOfNodes[0] = testNode;
            for (int i = 0; i < nodeList.getLength(); i++) {
                arrayOfNodes[i + 1] = nodeList.item(i);

            }


            for (int i = 0; i < subtreesListDoc1.size(); i++) {

                  for (int j = 0; j< min(subtreesListDoc1.get(i).length, arrayOfNodes.length); j++) {

                      if (!String.valueOf(arrayOfNodes[j]).equals(String.valueOf(subtreesListDoc1.get(i)[j]))) {
                          isContained = false;
                          break;
                      }
                  }

                  isContained = true;

            }

            return isContained;

        }









    public void getSubtrees(Document document, ArrayList<Node[]> subtreesList, TreeWalker treeWalker) {

        Node parent = treeWalker.getCurrentNode();

        for (Node n = treeWalker.firstChild(); n != null; n = treeWalker.nextSibling()) {

            NodeList nodeList = n.getChildNodes();
            Node[] arrayOfNodes = new Node[nodeList.getLength()+1];
            arrayOfNodes[0] = n;
            for (int i = 0; i < nodeList.getLength(); i++) {
                arrayOfNodes[i+1] = nodeList.item(i);
            }
             subtreesList.add(arrayOfNodes);
           getSubtrees(document, subtreesList, treeWalker);
        }
        treeWalker.setCurrentNode(parent);



    }
}









