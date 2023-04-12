package app.domain.models.Card;

public class MilitaryCard implements ICard{

    private MilitaryCardType type;

    @Override
    public void draw() {
        System.out.println("This is a military card.");
    }

    public MilitaryCard(MilitaryCardType type) {
        this.type = type;
    }
}
