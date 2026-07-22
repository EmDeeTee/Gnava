package Gnava.Core.Server.Endpoints.Registered;

import Gnava.Core.Server.Endpoints.AbstractServerEndpoint;
import Gnava.Core.Server.Endpoints.Responces.StatisticsResponse;
import Gnava.Core.Statistics.Records.WorldStatistics;
import Gnava.Core.Statistics.SpellStatisticsProvider;
import Gnava.Core.Statistics.WorldStatisticsProvider;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.stereotype.Component;

@Component
public final class StatisticsEndpoint extends AbstractServerEndpoint {
    private final WorldStatisticsProvider worldStatisticsProvider;
    private final SpellStatisticsProvider spellStatisticsProvider;

    public StatisticsEndpoint(WorldStatisticsProvider worldStatisticsProvider, SpellStatisticsProvider spellStatisticsProvider) {
        this.worldStatisticsProvider = worldStatisticsProvider;
        this.spellStatisticsProvider = spellStatisticsProvider;
    }

    @Override
    public String getPath() {
        return "/statistics";
    }

    @Override
    protected Object buildResponse(HttpExchange exchange) {
        WorldStatistics worldStatistics = worldStatisticsProvider.getWorldStatistics();

        return new StatisticsResponse(
            worldStatistics.population(),
            worldStatistics.playerBodyCount(),
            worldStatistics.settlementCount(),
            spellStatisticsProvider.getSpellStatistics().spellsCasted()
        );
    }
}
