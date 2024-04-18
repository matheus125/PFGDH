package view.com.raven.model;

import javax.swing.Icon;

public class ModelCard {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ModelCard(String title, Icon icon) {
        this.title = title;
        this.icon = icon;
    }

    public ModelCard() {
    }

    private String title;
    private Icon icon;
}
