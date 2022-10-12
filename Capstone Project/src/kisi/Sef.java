package kisi;
import eylem.GarsonSiparis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Şefin üzerinde olan siparişler ile ilgili bilgiler içermektedir.
 *
 */

public class Sef {
    List<GarsonSiparis> siparisler = Collections.synchronizedList(new ArrayList<>());

    public List<GarsonSiparis> getSiparisler() {
        return siparisler;
    }

    public void setSiparisler(List<GarsonSiparis> siparisler) {
        this.siparisler = siparisler;
    }
}
