package app.domain.models.Card;

public class ChanceCard implements ICard{

    @Override
    public void draw() {
        System.out.println("This is a chance card.");
    }
}
