package eylem;
import kisi.Musteri;
import yiyecek.Yemek;

import java.util.List;

/**
 * Garson tarafından alınan sipariş ile ilgili bilgiler içermektedir
 *
 */

public class GarsonSiparis {
    private Musteri musteri;
    private List<Yemek> siparisListesi;

    public GarsonSiparis(Musteri musteri, List<Yemek> siparisListesi) {
        this.musteri = musteri;
        this.siparisListesi = siparisListesi;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public List<Yemek> getSiparisListesi() {
        return siparisListesi;
    }

    public void setSiparisListesi(List<Yemek> siparisListesi) {
        this.siparisListesi = siparisListesi;
    }

    public int getYemekHazirlanmaSuresi(){
        int sure =0;
        for (Yemek yemek : siparisListesi) {
            sure+=yemek.getHazirlanmaSuresi();
        }

        return sure;
    }
}
