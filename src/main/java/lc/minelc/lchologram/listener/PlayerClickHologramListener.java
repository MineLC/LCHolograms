package lc.minelc.lchologram.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import lc.minelc.lchologram.lines.HologramLine;
import lc.minelc.lchologram.storage.Hologram;
import lc.minelc.lchologram.storage.HologramStorage;
import lc.minelc.lchologram.storage.ModifiableHologram;

import lc.lcspigot.events.PreInteractEntityEvent;
import lc.lcspigot.listeners.EventListener;
import lc.lcspigot.listeners.ListenerData;

public final class PlayerClickHologramListener implements EventListener {

    @ListenerData(event = PreInteractEntityEvent.class)
    public void handle(Event defaultEvent) {
        final PreInteractEntityEvent event = (PreInteractEntityEvent)defaultEvent;
        final Hologram hologram = HologramStorage.getStorage().getHologramsPerEntity().get(event.getEntityID());
        if (hologram != null) {
            event.setCancelled(true);
            for (final HologramLine line : hologram.getLines()) {
                if (line.hashCode() == event.getEntityID()) {
                    Bukkit.broadcastMessage("CLICKEADO : " + line.toString());
                }
            }
            if (hologram instanceof ModifiableHologram modifiableHologram) {
                modifiableHologram.getListener().handle(event);
            }
        }
    }
}