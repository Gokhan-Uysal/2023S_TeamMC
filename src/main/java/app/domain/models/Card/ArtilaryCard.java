package app.domain.models.Card;

import javax.swing.ImageIcon;

public class ArtilaryCard extends ArmyCard {

    protected ArtilaryCard(ImageIcon imageIcon) {
        super(ArtilaryCard.class.getName(), imageIcon, 60);
        this.imageIcon = imageIcon;
    }

}
