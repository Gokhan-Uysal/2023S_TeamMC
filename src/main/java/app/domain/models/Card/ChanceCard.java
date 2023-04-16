package app.domain.models.Card;

import javax.swing.ImageIcon;

public class ChanceCard extends BaseCard {

    protected ChanceCard(String description, ImageIcon imageIcon) {
        super(ChanceCard.class.getName(), description, imageIcon);
    }

}
