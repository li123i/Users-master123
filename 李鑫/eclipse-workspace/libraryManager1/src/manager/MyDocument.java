package manager;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MyDocument extends PlainDocument {

    private int maxLength;
    private boolean dij;

    MyDocument(int newMaxLength) {
        super();
        dij = false;
        maxLength = newMaxLength;
    }
    MyDocument() {
        this(10);
    }
    MyDocument(int newMaxLength, boolean b) {
        super();
        dij = b;
        maxLength = newMaxLength;
    }
    public void insertString(int offset,String str,javax.swing.text.AttributeSet a) throws BadLocationException {

        if(getLength()+str.length()<=maxLength) {
            if(dij && isNotNumber(str)) return ;
            super.insertString(offset, str,a);
        }
    }

    private boolean isNotNumber(String str) {
        boolean is = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isDigit(ch)) {
                return is = true;
            }
        }
        return is = false;
    }
}
