package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    Kassapaate kp;
    Maksukortti mk;
    
    @Before
    public void setUp() {
        kp = new Kassapaate();
        mk = new Maksukortti(0);
    }

    // Itse luodut testit
    @Test
    public void kassapaateLuotuOikein() {
        assertTrue(kp.kassassaRahaa() == 100000 && kp.edullisiaLounaitaMyyty()+kp.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void edullinenOstoToimiiKateisella1() {
        //Tasaraha
        int vaihtoraha = kp.syoEdullisesti(240);
        
        assertTrue(vaihtoraha == 0 && kp.kassassaRahaa() == 100240 && kp.edullisiaLounaitaMyyty() == 1);
    }
        
    @Test
    public void edullinenOstoToimiiKateisella2() {
        // Annetaan enemmän rahaa kuin on tarpeellista
        int vaihtoraha = kp.syoEdullisesti(480);
        
        assertTrue(vaihtoraha == 240 && kp.kassassaRahaa() == 100240 && kp.edullisiaLounaitaMyyty() == 1);
    }
            
    @Test
    public void edullinenOstoToimiiKateisella3() {
        // Annetaan vähemmän rahaa kuin on tarpeellista
        int vaihtoraha = kp.syoEdullisesti(200);
        
        assertTrue(vaihtoraha == 200 && kp.kassassaRahaa() == 100000 && kp.edullisiaLounaitaMyyty() == 0);
    }
                
    @Test
    public void maukasOstoToimiiKateisella1() {
        // Annetaan riittävä rahamäärä
        int vaihtoraha = kp.syoMaukkaasti(400);
        
        assertTrue(vaihtoraha == 0 && kp.kassassaRahaa() == 100400 && kp.maukkaitaLounaitaMyyty() == 1);
    }
                
    @Test
    public void maukasOstoToimiiKateisella2() {
        // Annetaan enemmän rahaa kuin on tarpeellista
        int vaihtoraha = kp.syoMaukkaasti(500);
        
        assertTrue(vaihtoraha == 100 && kp.kassassaRahaa() == 100400 && kp.maukkaitaLounaitaMyyty() == 1);
    }
                
    @Test
    public void maukasOstoToimiiKateisella3() {
        // Annetaan vähemmän rahaa kuin on tarpeellista
        int vaihtoraha = kp.syoMaukkaasti(200);
        
        assertTrue(vaihtoraha == 200 && kp.kassassaRahaa() == 100000 && kp.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void edullinenOstoToimiiKortilla1() {
        mk.lataaRahaa(250);
        boolean ostoOnnistui = kp.syoEdullisesti(mk);
        
        assertTrue(ostoOnnistui && kp.edullisiaLounaitaMyyty() == 1);
    }
        
    @Test
    public void edullinenOstoToimiiKortilla2() {
        mk.lataaRahaa(200);
        boolean ostoEiOnnistunut = !kp.syoEdullisesti(mk);
        
        assertTrue(ostoEiOnnistunut && kp.edullisiaLounaitaMyyty() == 0);
    }
        
    @Test
    public void maukasOstoToimiiKortilla1() {
        mk.lataaRahaa(410);
        boolean ostoOnnistui = kp.syoMaukkaasti(mk);
        
        assertTrue(ostoOnnistui && kp.maukkaitaLounaitaMyyty() == 1);
    }
        
    @Test
    public void maukasOstoToimiiKortilla2() {
        mk.lataaRahaa(200);
        boolean ostoEiOnnistunut = !kp.syoMaukkaasti(mk);
        
        assertTrue(ostoEiOnnistunut && kp.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortinLatausKassallaToimii1() {
        kp.lataaRahaaKortille(mk, 200);
        
        assertTrue(mk.saldo() == 200 && kp.kassassaRahaa() == 100200);
    }
        
    @Test
    public void kortinLatausKassallaToimii2() {
        // Tarkistetaan ettei voi ladata negatiivinen summa
        kp.lataaRahaaKortille(mk, -200);
        
        assertTrue(mk.saldo() == 0 && kp.kassassaRahaa() == 100000);
    }

}
