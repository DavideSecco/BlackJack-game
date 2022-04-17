public class TestApp {
    public static void main(String[] args) {
        CardsDeck cd1 = new CardsDeck();
        for( Card c : cd1.getCards()){
            System.out.println(c);
        }
    }
}
