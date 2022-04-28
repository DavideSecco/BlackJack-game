public class Player implements Participant {
    private String name;
    private int wins;
    private Hand hand;

    @Override
    public void hitting(CardsDeck cardsDeck) {
        hand.addCard(cardsDeck);
    }

    public void standing(){

    }
}
