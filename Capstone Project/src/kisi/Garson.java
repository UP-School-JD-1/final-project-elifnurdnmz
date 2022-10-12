package kisi;
import eylem.GarsonSiparis;
import yiyecek.Yemek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Garson tarafından sipariş alınması ve üzerinde olan siparişler için komutlar içermektedir.
 *
 */

public class Garson {
    List<GarsonSiparis> siparisler = Collections.synchronizedList(new ArrayList<>());

    public void siparisAl(Musteri musteri, List<Yemek> yemekler) {
        GarsonSiparis garsonSiparis = new GarsonSiparis(musteri, yemekler);
        siparisler.add(garsonSiparis);
    }


    public List<GarsonSiparis> getSiparisler() {
        return siparisler;
    }

    public void setSiparisler(List<GarsonSiparis> siparisler) {
        this.siparisler = siparisler;
    }
}
