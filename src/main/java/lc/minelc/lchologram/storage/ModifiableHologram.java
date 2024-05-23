package lc.minelc.lchologram.storage;

import lc.minelc.lchologram.lines.HologramLine;
import lc.minelc.lchologram.objets.HologramClickListener;

public class ModifiableHologram implements Hologram {

    private HologramLine[] lines;
    private HologramClickListener listener;

    public ModifiableHologram(HologramLine[] lines) {
        this.lines = lines;
    }

    public void setLines(final HologramLine[] lines) {
        this.lines = lines;
    }

    public HologramLine[] getLines() {
        return lines;
    }

    public HologramClickListener getListener() {
        return listener;
    }

    public void setListener(final HologramClickListener listener) {
        this.listener = listener;
    }
}