package hr.fer.oprpp1.hw08.jnotepadpp.actions.File;

import hr.fer.oprpp1.hw08.jnotepadpp.actions.BaseAction;
import hr.fer.oprpp1.hw08.jnotepadpp.i18n.ILocalizationProvider;
import hr.fer.oprpp1.hw08.jnotepadpp.model.MultipleDocumentModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StatsDocumentAction extends BaseAction {
    private final JFrame app;

    public StatsDocumentAction(MultipleDocumentModel mdm, ILocalizationProvider lp, JFrame app) {
        super(mdm, lp, "stats");
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea textArea = mdm.getCurrentDocument().getTextComponent();

        JOptionPane.showMessageDialog(
                app,
                String.format(this.lp.getString("stats_message"),
                        textArea.getText().length(),
                        textArea.getText().replaceAll("\\s+", "").length(),
                        textArea.getDocument().getDefaultRootElement().getElementCount()
                )
        );
    }
}
