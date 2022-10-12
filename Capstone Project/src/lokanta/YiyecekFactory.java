package lokanta;
import yiyecek.Icecek;
import yiyecek.Yemek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Yemek ve içecek listesi ile bunlar arasından rastgele seçilmesi için komutlar içermektedir.
 *
 */

public class YiyecekFactory {
    static List<Yemek> yemekler = Collections.synchronizedList(new ArrayList<>());
    static List<Yemek> icecekler = Collections.synchronizedList(new ArrayList<>());

    static {
        yemekler.add(new Yemek("Mantı",10,20));
        yemekler.add(new Yemek("Köfte",10,20));
        yemekler.add(new Yemek("Döner",10,20));
        yemekler.add(new Yemek("Pilav",5,10));
        yemekler.add(new Yemek("Patates",5,10));


        icecekler.add(new Icecek("Kola",5));
        icecekler.add(new Icecek("Fanta",5));
        icecekler.add(new Icecek("Su",2));
        icecekler.add(new Icecek("Ayran",3));
    }

    public static Yemek getRastgeleYemek(){
        Random r = new Random();
        int i = r.nextInt(yemekler.size());

        return yemekler.get(i);
    }

    public static Yemek getRastgeleIcecek(){
        Random r = new Random();
        int i = r.nextInt(icecekler.size());

        return icecekler.get(i);
    }
}
