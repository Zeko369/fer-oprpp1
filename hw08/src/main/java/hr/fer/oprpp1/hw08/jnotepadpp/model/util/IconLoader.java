package hr.fer.oprpp1.hw08.jnotepadpp.model.util;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class IconLoader {
    public ImageIcon loadIcon(String path) {
        InputStream is = this.getClass().getResourceAsStream(path);
        if (is == null) {
            throw new RuntimeException("Error loading icon");
        }

        try {
            byte[] bytes = is.readAllBytes();
            is.close();
            return new ImageIcon(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Error loading icon");
        }
    }
}
