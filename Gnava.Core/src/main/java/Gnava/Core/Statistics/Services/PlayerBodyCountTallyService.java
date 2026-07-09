package Gnava.Core.Statistics.Services;

import org.springframework.stereotype.Service;

@Service
public final class PlayerBodyCountTallyService {
    private int playerBodyCount = 0;

    public int getPlayerBodyCount() {
        return playerBodyCount;
    }

    public void incrementPlayerBodyCount() {
        this.playerBodyCount++;
    }

    public void incrementPlayerBodyCount(int by) {
        this.playerBodyCount += by;
    }
}
