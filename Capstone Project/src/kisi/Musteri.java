package kisi;
import yiyecek.Yemek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Müşteri tarafından yenecek yemekler listesi, tüketme süresi ve sipariş verip vermediği ile ilgili komutlar içermektedir.
 *
 */

public class Musteri {
    private List<Yemek> yenecekYemeklerListesi = Collections.synchronizedList(new ArrayList<>());
    private boolean siparisVerdimi;

    public void yemeklerGeldi(List<Yemek> yenecekYemeklerListesi) {
        this.yenecekYemeklerListesi = yenecekYemeklerListesi;
    }

    public int getYemekBitirmeSuresi() {
        int sure = 0;
        for (Yemek yemek : yenecekYemeklerListesi) {
            sure += yemek.getTuketilmeSuresi();
        }

        return sure;
    }

    public List<Yemek> getYenecekYemeklerListesi() {
        return yenecekYemeklerListesi;
    }

    public void setYenecekYemeklerListesi(List<Yemek> yenecekYemeklerListesi) {
        this.yenecekYemeklerListesi = yenecekYemeklerListesi;
    }

    public boolean isSiparisVerdimi() {
        return siparisVerdimi;
    }

    public void setSiparisVerdimi(boolean siparisVerdimi) {
        this.siparisVerdimi = siparisVerdimi;
    }
}
