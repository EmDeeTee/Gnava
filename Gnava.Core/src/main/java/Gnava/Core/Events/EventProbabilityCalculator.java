package Gnava.Core.Events;

import org.springframework.stereotype.Service;

// NOTE: I imagine this being used in some kind of debug UI if I ever make it
// So the user will see when to expect a certain event to happen in a nice JTable
@Service
public final class EventProbabilityCalculator {
    public int calculateExpectedOnDay(float eventWeight, float totalWeight) {
        return Math.round(totalWeight / eventWeight);
    }
}
