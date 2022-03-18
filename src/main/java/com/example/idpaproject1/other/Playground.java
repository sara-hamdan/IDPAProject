package com.example.idpaproject1.other;

import java.util.ArrayList;

public class Playground {
    public static void main(String[] args) {

        TreeNode<Integer> A1 = new TreeNode<Integer>(1);

        TreeNode<Integer> B1 = new TreeNode<Integer>(3);
        A1.addChild(B1);

        TreeNode<Integer> C1 = new TreeNode<Integer>(2);
        A1.addChild(C1);

        TreeNode<Integer> D1 = new TreeNode<Integer>(4);
        B1.addChild(D1);

        TreeNode<Integer> E1 = new TreeNode<Integer>(5);
        B1.addChild(E1);

        TreeNode<Integer> F1 = new TreeNode<Integer>(28);
        B1.addChild(F1);


        TreeNode<Integer> A2 = new TreeNode<Integer>(1);

        TreeNode<Integer> B2 = new TreeNode<Integer>(3);
        A2.addChild(B2);

        TreeNode<Integer> C2 = new TreeNode<Integer>(2);
        A2.addChild(C2);

        TreeNode<Integer> D2 = new TreeNode<Integer>(4);
        B2.addChild(D2);

        TreeNode<Integer> E2 = new TreeNode<Integer>(5);
        B2.addChild(E2);

        System.out.print(A1.equals(A2));

    }

    public static class TreeNode<T> {

        private T data;
        private ArrayList<TreeNode> children = new ArrayList<>();

        public TreeNode(T data) {
            this.data = data;

        }

        public void addChild(TreeNode child) {
            children.add(child);
        }

        public TreeNode getChild(int index) {
            return children.get(index);
        }

        public int getNumChildren() {
            return children.size();
        }

        public String toString() {
            return data.toString();
        }

        public boolean equals(TreeNode<T> other){
            if (this.data != other.data) return false;
            if (this.children.size() != other.children.size()) return false;

            for (int i=0; i < children.size(); i++){
                if (! this.children.get(i).equals(other.children.get(i))) return false;
            }

            return true;
        }

    }
}


