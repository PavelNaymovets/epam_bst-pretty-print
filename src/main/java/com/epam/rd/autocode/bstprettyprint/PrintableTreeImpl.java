package com.epam.rd.autocode.bstprettyprint;

import java.util.HashSet;
import java.util.Set;

public class PrintableTreeImpl implements PrintableTree{
    String s = "";
    Set<Integer> present;
    Node node;

    public PrintableTreeImpl() {
        node = new Node();
        present = new HashSet<>();
    }

    @Override
    public void add(int i) {
        if (present.contains(i)) {
            return;
        }
        present.add(i);
        node.insertToTree(i, "");
    }

    @Override
    public String prettyPrint() {
        try {
            node.printTree();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public class Node {
        Integer value;
        String addInt = "";
        Node left, right;

        public void insertToTree(Integer i, String addInt) {
            this.addInt = addInt;
            if (value == null) {
                value = i;
                return;
            }
            if (i.compareTo(value) < 0) {
                if (right == null) {
                    right = new Node();
                }
                right.insertToTree(i, getAddInt());
            } else {
                if (left == null) {
                    left = new Node();
                }
                left.insertToTree(i, getAddInt());
            }
        }

        public void printTree(){
            if (right != null) {
                right.printTree(true, "");
            }
            printNodeValue();
            if (left != null) {
                left.printTree(false, "");
            }

        }
        private void printNodeValue(){
            String valString;
            if (value == null) {
                valString = "<null>";
            } else {
                valString = value.toString();
            }
            s += valString;
            if (left == null && right == null) {
                s += "\n";
            } else if (right == null) {
                s += "┐\n";
            } else if (left == null) {
                s += "┘\n";
            } else {
                s += "┤\n";
            }
        }

        private void printTree(boolean isRight, String indent) {
            if (right != null) {
                right.printTree(true, indent + (isRight ? "  " + addInt : (addInt + " │")));
            }
            s += indent;
            if (isRight) {
                s += addInt + " ┌";
            } else {
                s += addInt + " └";
            }
            s += "";
            printNodeValue();
            if (left != null) {
                left.printTree(false, indent + (isRight ? addInt + " │" : "  " + addInt));
            }
        }

        private String getAddInt() {
            String valString;
            StringBuilder addInt = new StringBuilder();
            if (value == null) {
                valString = "<null>";
            } else {
                valString = value.toString();
            }
            addInt.append(" ".repeat(Math.max(0, valString.length() - 1)));
            return addInt.toString();
        }
    }
}
