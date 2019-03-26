package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    // Itse tehdyt testit
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        
        assertEquals(5, kortti.saldo());
    }
        
    @Test
    public void saldoEiMuutuJosEiOleTarpeeksiRahaa() {
        kortti.otaRahaa(11);
        
        assertEquals(10, kortti.saldo());
    }
        
    @Test
    public void rahanOttaminenPalauttaaTrueJosOnnistui() {
        assertTrue(kortti.otaRahaa(5));
    }
        
    @Test
    public void rahanOttaminenPalauttaaFalseJosOnnistui() {
        assertFalse(kortti.otaRahaa(11));
    }
}
