/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package midiplayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.midi.*;

/**
 * MidiPlayer
 * @author Manuel David Villalba Escamilla
 */
public class MidiPlayer implements MetaEventListener {
    
    File fichero;
    Sequencer player;
    
        
    private boolean loop;

    private boolean paused;
    
    public static final int END_OF_TRACK_MESSAGE = 47;

    public MidiPlayer(File fichero) throws MidiUnavailableException, InvalidMidiDataException, IOException{
        this.fichero = fichero;
                
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        synthesizer.unloadAllInstruments(synthesizer.getDefaultSoundbank());
        synthesizer.loadAllInstruments(MidiSystem.getSoundbank(new File("OmegaGMGS2.sf2")));

        this.player = MidiSystem.getSequencer(false);
//        this.player = MidiSystem.getSequencer();
        this.player.open();
        this.player.getTransmitter().setReceiver(synthesizer.getReceiver());
        this.player.addMetaEventListener(this);
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(File fichero) {
        this.fichero = fichero;
    }
    
    public void reproducir(Sequence sequence) {
      if (player != null && sequence != null && player.isOpen()) {
        try {
          player.setSequence(sequence);
          player.start();
          paused = false;
        } catch (InvalidMidiDataException ex) {
          ex.printStackTrace();
        }
      }
    }
        
   public void stop() {
     if (player != null && player.isOpen()) {
       player.stop();
       player.setMicrosecondPosition(0);
       paused = true;
     }
   }

   public void close() {
     if (player != null && player.isOpen()) {
       player.close();
     }
   }

    public Sequence getSequence(String filename) {
      try {
        return getSequence(new FileInputStream(filename));
      } catch (IOException ex) {
        ex.printStackTrace();
        return null;
      }
    }

    public Sequence getSequence(InputStream is) {
      try {
        if (!is.markSupported()) {
          is = new BufferedInputStream(is);
        }
        Sequence s = MidiSystem.getSequence(is);
        is.close();
        return s;
      } catch (InvalidMidiDataException ex) {
        ex.printStackTrace();
        return null;
      } catch (IOException ex) {
        ex.printStackTrace();
        return null;
      }
    }
    
    public boolean isPaused() {
      return paused;
    }
    
    @Override
    public void meta(MetaMessage meta) {
        if (meta.getType() == END_OF_TRACK_MESSAGE) {
          if (player != null && player.isOpen()) {
            player.start();
          }
        }
    }
    
}
