package app.domain.models.Card;

public class TerritoryCard implements ICard{

    private int territoryId;
    @Override
    public void draw() {
        System.out.println("This is a territory card.");
    }

    public TerritoryCard(int territoryId) {
        this.territoryId = territoryId;
    }

    public int getTerritoryId() {
        return territoryId;
    }
}
