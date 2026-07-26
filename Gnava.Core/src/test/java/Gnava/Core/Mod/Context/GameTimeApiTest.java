package Gnava.Core.Mod.Context;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTimeApiTest {
    @Test
    void dayAdvanced_notifiesRegisteredListeners() {
        GameTimeApi gameTime = new GameTimeApi();
        AtomicInteger receivedDay = new AtomicInteger();
        gameTime.onDayAdvanced(receivedDay::set);

        gameTime.publishDayAdvanced(7);

        assertEquals(7, receivedDay.get());
    }
}
