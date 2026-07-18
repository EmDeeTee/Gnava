package Gnava.Desktop.Interface.Frames.MainFrame.Components.GameEventsPanel.Options;

import org.springframework.stereotype.Service;

@Service
public final class FilterOptionsManager {
    private boolean showMajorEventsOnly = false;

    public void setShowMajorEventsOnly(boolean showMajorEventsOnly) {
        this.showMajorEventsOnly = showMajorEventsOnly;
    }

    public FilterOptions filterOptions() {
        return new FilterOptions(showMajorEventsOnly);
    }
}
