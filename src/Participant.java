/**
 * Interfaccia partecipante per quelle che saranno le classi concrete Giocatore e Banco
 * requestCard() --> metodo che agguinge una carta a quelle del partecipante
 *
 */

public interface Participant {
    void hitting(CardsDeck cardsDeck);
    void standing();
}
