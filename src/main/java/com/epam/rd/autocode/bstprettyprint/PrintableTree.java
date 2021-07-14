package com.epam.rd.autocode.bstprettyprint;

public interface PrintableTree {

    void add(int i);
    String prettyPrint();

    static PrintableTree getInstance() {
        try{
            return new PrintableTreeImpl();
        } catch (Exception e){
            throw new UnsupportedOperationException();
        }
    }
}
