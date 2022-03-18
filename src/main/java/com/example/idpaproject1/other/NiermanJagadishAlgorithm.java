package com.example.idpaproject1.other;

import org.w3c.dom.*;
import org.w3c.dom.traversal.TreeWalker;

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

    public Node getChild(Node node, int height) {

        Node childNode = node.getFirstChild();

        return childNode;
    }

    public Node getSibling(Node node, int height) {
        Node siblingNode = node.getNextSibling();


        return siblingNode;
    }

    public void getSubtrees(Node rootNode, int height) {

        if (height == 1) {
            return;
        }

        int tempHeight = height;
        Node firstChild = rootNode.getFirstChild();

        while (tempHeight != 1)

            if (firstChild.hasChildNodes()) {

                Node child = getChild(firstChild, tempHeight - 1);

            }


    }


    public void isContainedIn(boolean containedIn, Node node, Document document, TreeWalker treeWalker) {





        Node parent = treeWalker.getCurrentNode();



        for (Node n = treeWalker.firstChild(); n != null; n = treeWalker.nextSibling()) {
            if (!n.isEqualNode(node)) {
                containedIn = true;

                break;

            }


                isContainedIn(containedIn, node, document, treeWalker);


            }


            treeWalker.setCurrentNode(parent);
        }


    }


    /* public ArrayList<Document> getSubtrees(ArrayList<Document> subtrees, Document document, Node rootNode, int height) throws ParserConfigurationException {


        if (height == 2)
        {
            return subtrees;
        }

        else {

            // get the first child of the root Node
            Node childNode = rootNode.getFirstChild();

            Document doc = null;
            while (childNode != null) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = dbf.newDocumentBuilder();
                doc = builder.newDocument();


                doc.importNode(rootNode, true);
                Element element = doc.createElement(rootNode.getNodeName());
                System.out.println(doc);
                System.out.println(element);
                NodeList children = childNode.getChildNodes();


                for (int i = 0; i < children.getLength(); i++) {

                    element.appendChild(doc.importNode(children.item(i).cloneNode(true), true));

                }

                subtrees.add(doc);

                childNode = childNode.getNextSibling();

            }
            return getSubtrees(subtrees, doc, childNode, height-1);
            }


    } */






