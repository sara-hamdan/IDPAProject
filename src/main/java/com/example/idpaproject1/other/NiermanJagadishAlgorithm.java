package com.example.idpaproject1.other;

import org.w3c.dom.*;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.min;

public class NiermanJagadishAlgorithm {

    Document A;
    Document B;

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

    public int NiermanJagadishExecute(Node nodeA, Node nodeB, Document documentA, Document documentB) {

        Node parentNodeA = nodeA;
        Node parentNodeB = nodeB;
        int degreeA = getDegree(parentNodeA, documentA);
        int degreeB = getDegree(parentNodeB, documentB);

        int[][] editDistanceMatrix = new int[degreeA+1][degreeB+1];
        editDistanceMatrix[0][0] = costUpdate(parentNodeA, parentNodeB);


        ArrayList<Node> levelSubtreesArrayA = getLevelSubtrees(parentNodeA, documentA);
        ArrayList<Node> levelSubtreesArrayB = getLevelSubtrees(parentNodeB, documentB);
        getLevelSubtrees(parentNodeB, documentB);
        for (int i = 1; i <= degreeA; i++) {

            for (int j = 0; j < levelSubtreesArrayA.size() ; j++) {
                System.out.println(levelSubtreesArrayA.get(j));
            }
            editDistanceMatrix[i][0] = editDistanceMatrix[i-1][0] + getCost(levelSubtreesArrayA.get(i-1), documentB);

        }

        for (int j = 1; j <= degreeB; j++) {


            editDistanceMatrix[0][j] = editDistanceMatrix[0][j-1] + getCost(levelSubtreesArrayB.get(j-1), documentA);
        }

        for (int i = 1; i <= degreeA; i++) {
            for (int j = 1; j <= degreeB; j++) {
                editDistanceMatrix[i][j] = min(
                        min(editDistanceMatrix[i-1][j-1] + NiermanJagadishExecute(levelSubtreesArrayA.get(i-1), levelSubtreesArrayB.get(j-1), documentA, documentB),
                                editDistanceMatrix[i-1][j]+ getCost(levelSubtreesArrayA.get(i-1), documentB)),
                                editDistanceMatrix[i][j-1] + getCost(levelSubtreesArrayB.get(j-1), documentA));
            }
        }


        return editDistanceMatrix[degreeA][degreeB];

    }


    public int costUpdate(Node nodeA, Node nodeB) {

        if (String.valueOf(nodeA).equals(String.valueOf(nodeB))) return 0;

        else return 1;

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

            for (int j = 0; j < min(subtreesListDoc1.get(i).length, arrayOfNodes.length); j++) {

                if (!String.valueOf(arrayOfNodes[j]).equals(String.valueOf(subtreesListDoc1.get(i)[j]))) {
                    isContained = false;
                    break;
                }
            }


        }

        return isContained;

    }


    public int getCost(Node node, Document document) {


        if (!node.hasChildNodes()) return 1;

        ArrayList<Node[]> arrayList = new ArrayList<>();
        DocumentTraversal traversal = (DocumentTraversal) document;
        TreeWalker treeWalker = traversal.createTreeWalker(document.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        arrayList = getSubtrees(document, arrayList, treeWalker);
        if (isContainedIn(node, arrayList)) return 1;
        else {

            int cost = 1 + node.getChildNodes().getLength();

            return cost;
        }
    }


    public ArrayList<Node[]> getSubtrees(Document document, ArrayList<Node[]> subtreesList, TreeWalker treeWalker) {

        Node parent = treeWalker.getCurrentNode();

        for (Node n = treeWalker.firstChild(); n != null; n = treeWalker.nextSibling()) {

            NodeList nodeList = n.getChildNodes();
            Node[] arrayOfNodes = new Node[nodeList.getLength() + 1];
            arrayOfNodes[0] = n;
            for (int i = 0; i < nodeList.getLength(); i++) {
                arrayOfNodes[i + 1] = nodeList.item(i);
            }
            subtreesList.add(arrayOfNodes);
            getSubtrees(document, subtreesList, treeWalker);
        }
        treeWalker.setCurrentNode(parent);


        return subtreesList;
    }

    public int getDegree(Node node, Document document) {

        if (node == null) return 1;
        DocumentTraversal traversal = (DocumentTraversal) document;
        TreeWalker treeWalker = traversal.createTreeWalker(document.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        treeWalker.setCurrentNode(node);
        int countLevelTrees = 0;
        for (Node n = treeWalker.firstChild(); n != null; n = treeWalker.nextSibling()) {
            countLevelTrees++;
        }
        return countLevelTrees;
    }

    public ArrayList<Node> getLevelSubtrees(Node node, Document document) {

        DocumentTraversal traversal = (DocumentTraversal) document;
        TreeWalker treeWalker = traversal.createTreeWalker(document.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        treeWalker.setCurrentNode(node);
        ArrayList<Node> levelSubtrees = new ArrayList<>();

        for (Node n = treeWalker.firstChild(); n != null; n = treeWalker.nextSibling()) {
            levelSubtrees.add(n);
        }

        return levelSubtrees;
    }
}








