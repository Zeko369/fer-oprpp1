package hr.fer.oprpp1.hw08.jnotepadpp.shared;

import javax.swing.*;

public class SelectorHelpers {
    public static String get(JTextArea editor) {
        int len = Math.abs(editor.getCaret().getDot() - editor.getCaret().getMark());
        if (len == 0) {
            return null;
        }

        int tmpStartIndex = Math.min(editor.getCaret().getDot(), editor.getCaret().getMark());
        int tmpEndIndex = Math.max(editor.getCaret().getDot(), editor.getCaret().getMark());

        return editor.getText().substring(tmpStartIndex, tmpEndIndex);
    }
}
