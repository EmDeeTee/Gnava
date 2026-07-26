package Gnava.Core.Mod.Context;

import Gnava.ModApi.IGameDayListener;
import Gnava.ModApi.IGameTimeApi;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public final class GameTimeApi implements IGameTimeApi {
    private final List<IGameDayListener> dayAdvancedListeners = new ArrayList<>();

    @Override
    public void onDayAdvanced(IGameDayListener listener) {
        dayAdvancedListeners.add(Objects.requireNonNull(listener, "listener"));
    }

    public void publishDayAdvanced(int currentDay) {
        for (IGameDayListener listener : dayAdvancedListeners) {
            listener.onDayAdvanced(currentDay);
        }
    }
}
