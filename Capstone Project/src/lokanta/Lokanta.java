package lokanta;
import eylem.GarsonSiparis;
import kisi.Garson;
import kisi.Musteri;
import kisi.Sef;
import yiyecek.Yemek;

import java.util.*;

/**
 * Müşterinin lokantaya girmesinden ayrılmasına kadar olan süreci simüle etmektedir.
 *
 */

public class Lokanta {

    private int masaAdedi = 10;

    private List<Garson> garsonListesi;
    private List<Sef> sefListesi;
    private List<Musteri> musteriListesi;

    public Lokanta() {
        garsonListesi = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            garsonListesi.add(new Garson());
        }

        sefListesi = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            sefListesi.add(new Sef());
        }

        musteriListesi = Collections.synchronizedList(new ArrayList<>());

        musteriGelmeSimulasyonu();
        musteriSiparisVermeSimulasyonu();
        sefSiparisAlmaSimulasyonu();
        sefSiraIleYemekPisirmeSimulasyonu();
        yemekYemeSimulasyonu();
    }

    private void yemekYemeSimulasyonu() {
        Thread yemekYemeSimulasyonu = new Thread(() -> {
            while (true) {
                try {
                    for (Iterator<Musteri> iter = musteriListesi.iterator(); iter.hasNext(); ) {
                        Musteri musteri = iter.next();
                        if (musteri.isSiparisVerdimi() && musteri.getYenecekYemeklerListesi().size() > 0) {
                            System.out.println("Musteri yemegine basladi");
                            Thread.sleep(musteri.getYemekBitirmeSuresi() * 1000);
                            System.out.println("musteri yemegini bitirdi kalkiyor");
                            musteriListesi.remove(musteri);
                        }
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        yemekYemeSimulasyonu.start();
        System.out.println("yemek yeme similasyonu basladi");
    }

    private void sefSiraIleYemekPisirmeSimulasyonu() {
        Thread sefYemekPisirmeThread = new Thread(() -> {
            while (true) {
                try {
                    for (Iterator<Sef> iter1 = sefListesi.iterator(); iter1.hasNext(); ) {
                        Sef sef = iter1.next();
                        for (Iterator<GarsonSiparis> iter = sef.getSiparisler().iterator(); iter.hasNext(); ) {
                            GarsonSiparis garsonSiparis = iter.next();
                            System.out.println("Pisirilmeye baslanan yemekler :" + garsonSiparis.getSiparisListesi());
                            Thread.sleep(garsonSiparis.getYemekHazirlanmaSuresi() * 1000);

                            garsonSiparis.getMusteri().setYenecekYemeklerListesi(garsonSiparis.getSiparisListesi());
                            sef.getSiparisler().remove(garsonSiparis);
                        }
                    }

                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sefYemekPisirmeThread.start();
        System.out.println("sef yemek pisirme simulasyonu basladi");
    }

    private void sefSiparisAlmaSimulasyonu() {

        Thread sefSiparisThread = new Thread(() -> {
            while (true) {
                for (Garson garson : garsonListesi) {
                    if (garson.getSiparisler().size() > 0) {
                        Random r = new Random();
                        int rastgeleSefNo = r.nextInt(sefListesi.size());

                        Sef sef = sefListesi.get(rastgeleSefNo);
                        for (Iterator<GarsonSiparis> iter = garson.getSiparisler().iterator(); iter.hasNext(); ) {
                            GarsonSiparis garsonSiparis = iter.next();
                            sef.getSiparisler().add(garsonSiparis);
                            garsonSiparis.getSiparisListesi().remove(garsonSiparis);
                        }
                    }
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sefSiparisThread.start();
        System.out.println("sef siparis alma simulasyonu basladi");
    }

    private void musteriSiparisVermeSimulasyonu() {
        Thread musteriSiparisVermeThread = new Thread(() -> {

            while (true) {
                for (Iterator<Musteri> iter = musteriListesi.iterator(); iter.hasNext(); ) {
                    Musteri musteri = iter.next();
                    if (!musteri.isSiparisVerdimi()) {
                        musteri.setSiparisVerdimi(true);

                        Random r = new Random();
                        int siparisAdedi = r.nextInt(3) + 1;


                        int rastgeleGarson = r.nextInt(garsonListesi.size());

                        List<Yemek> yemekListesi = Collections.synchronizedList(new ArrayList<>());
                        if (siparisAdedi == 3) {
                            siparisAdedi = 2;
                            yemekListesi.add(YiyecekFactory.getRastgeleIcecek());
                        }
                        for (int i = 0; i < siparisAdedi; i++) {
                            yemekListesi.add(YiyecekFactory.getRastgeleYemek());
                        }

                        System.out.println("Musteri " + siparisAdedi + " adet yiyecek siparisini "
                                + rastgeleGarson + " numaralı garsona siparis edicek. Siparis Listesi: " + yemekListesi);

                        Garson garson = garsonListesi.get(rastgeleGarson);
                        garson.siparisAl(musteri, yemekListesi);

                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        musteriSiparisVermeThread.start();
        System.out.println("musteri siparis verme simulasyonu basladi");
    }

    private void musteriGelmeSimulasyonu() {

        Thread musteriGeldiThread = new Thread(() -> {
            while (true) {
                Musteri musteri = new Musteri();
                try {
                    while (musteriListesi.size() >= masaAdedi) {
                        System.out.println("cok fazla musteri var, musteri kuyrukta beklemesi gerekiyor");
                        Thread.sleep(3000);
                    }
                    musteriListesi.add(musteri);
                    System.out.println("Yeni Bir Musteri Geldi lokantadaki musteri sayisi: " + musteriListesi.size());


                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        musteriGeldiThread.start();
        System.out.println("musteri gelme simulasyonu basladi");
    }
}
