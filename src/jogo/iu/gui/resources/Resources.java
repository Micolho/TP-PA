package jogo.iu.gui.resources;

import java.io.InputStream;

public class Resources {
    private Resources() {}
    public static InputStream getResourceFileAsStream(String name){
        return jogo.iu.gui.resources.Resources.class.getResourceAsStream(name);
    }
    public static String getResourceFilename(String name){
        return jogo.iu.gui.resources.Resources.class.getResource(name).toExternalForm();
    }
}
