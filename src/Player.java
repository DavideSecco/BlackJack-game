public class Player implements Participant {
    private String name;
    private int wins;
    private Hand hand;

    @Override
    public void pickCard(CardsDeck cardsDeck) {
        hand.addCard(cardsDeck);
    }
}
