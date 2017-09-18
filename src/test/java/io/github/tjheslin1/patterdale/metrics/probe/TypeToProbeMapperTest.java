package io.github.tjheslin1.patterdale.metrics.probe;

import io.github.tjheslin1.patterdale.database.DBConnectionPool;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.slf4j.Logger;
import testutil.WithMockito;

import static io.github.tjheslin1.patterdale.metrics.probe.Probe.probe;

public class TypeToProbeMapperTest implements WithAssertions, WithMockito {

    private static final Probe EXIST_PROBE_DEFINITION = probe("SQL", "exists", "metricName", "metricLabel");

    private final DBConnectionPool dbConnectionPool = mock(DBConnectionPool.class);
    private final Logger logger = mock(Logger.class);

    @Test
    public void mapsKnownTypeToSqlProbeClass() throws Exception {
        OracleSQLProbe oracleSQLProbe = new TypeToProbeMapper(dbConnectionPool, logger).createProbe(EXIST_PROBE_DEFINITION);

        assertThat(oracleSQLProbe).isExactlyInstanceOf(ExistsOracleSQLProbe.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void blowsUpForUnknownTypeParameter() throws Exception {
        new TypeToProbeMapper(dbConnectionPool, logger).createProbe(probe("", "none", "", ""));
    }
}