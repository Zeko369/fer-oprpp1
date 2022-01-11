package hr.fer.oprpp1.hw08.jnotepadpp.components;

import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.components.LocalizedJToolBar;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusBar extends LocalizedJToolBar {
    private final ILocalizationProvider lp;

    public StatusBar(MultipleDocumentModel mdm, ILocalizationProvider lp) {
        super("status", lp);

        this.lp = lp;

        this.setFloatable(false);
        this.setLayout(new GridLayout(0, 3));

        this.add(new JLabel("Length: 0"));
        this.add(new JLabel("Ln: 0, Col: 0, Sel: 0"));
        this.add(new JLabel(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        lp.addLocalizationListener(() -> this.render(mdm.getCurrentDocument().getTextComponent()));

        ActionListener timeListener = e -> this.updateTime();
        new Timer(1000, timeListener).start();
    }

    public void render(JTextArea editor) {
        int length = editor.getText().length();
        int col = -1;
        int sel = -1;
        int line = -1;

        try {
            line = editor.getLineOfOffset(editor.getCaret().getDot());
            col = editor.getCaret().getDot() - editor.getLineStartOffset(line);
            sel = Math.abs(editor.getCaret().getDot() - editor.getCaret().getMark());
        } catch (BadLocationException ignored) {
            // TODO: Log to file
            System.err.println("Bad location exception occured");
        } finally {
            this.setCompText(0, String.format("%s: %d", this.lp.getString("length"), length));
            this.setCompText(1, String.format("%s: %d %s: %d %s: %d", this.lp.getString("ln"), line + 1, this.lp.getString("col"), col, this.lp.getString("sel"), sel));
        }
    }

    private void setCompText(int index, String text) {
        ((JLabel) this.getComponent(index)).setText(text);
    }

    private void updateTime() {
        ((JLabel) this.getComponent(2)).setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }
}
