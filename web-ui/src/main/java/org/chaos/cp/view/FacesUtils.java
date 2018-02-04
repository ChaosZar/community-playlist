package org.chaos.cp.view;

import javax.faces.context.FacesContext;
import java.io.IOException;

public class FacesUtils {
    public static void redirect(String path) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
