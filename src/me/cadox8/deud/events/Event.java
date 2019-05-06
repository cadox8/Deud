package me.cadox8.deud.events;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.cadox8.deud.api.GameAPI;

@RequiredArgsConstructor
public abstract class Event {

    @Getter protected final @NonNull GameAPI gameAPI;

    @Getter @Setter private boolean cancelled = false;

    protected abstract void onEvent();

    public void performEvent() {
        if (isCancelled()) return;
        onEvent();
    }

    public String getEventName() {
        return getClass().getSimpleName();
    }
}
