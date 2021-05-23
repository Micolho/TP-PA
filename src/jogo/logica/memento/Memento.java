package jogo.logica.memento;

import java.io.Serializable;

public class Memento implements Serializable {
    private static final long serialVersionUID = 1L;
    private byte[] snapshot;

    public Memento(byte[] snapshot){
        this.snapshot = snapshot;
    }

    public byte[] getSnapshot(){
        return snapshot;
    }
}
