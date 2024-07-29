package de.schaack.ml.basics.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.data.implementation.DefaultStatistics;

class DefaultStatisticsTest {
    
    private DefaultStatistics stats;
    private double[] features;

    @BeforeEach
    void setUp() {
        // Initialize the dataset for tests
        features = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        stats = new DefaultStatistics(features);
    }

    @Test
    void testConstructor() {
        assertEquals(features.length, stats.getCount());
        assertEquals(1.0, stats.getMin());
        assertEquals(5.0, stats.getMax());
    }

    @Test
    void testGetCount() {
        assertEquals(5, stats.getCount());
    }

    @Test
    void testGetMean() {
        assertEquals(3.0, stats.getMean(), 1e-9);
    }

    @Test
    void testGetStandardDeviation() {
        assertEquals(Math.sqrt(2.0), stats.getStandardDeviation(), 1e-9);
    }

    @Test
    void testGetMin() {
        assertEquals(1.0, stats.getMin());
    }

    @Test
    void testGetMax() {
        assertEquals(5.0, stats.getMax());
    }

    @Test
    void testGetPercentile() {
        assertEquals(1.0, stats.getPercentile(0));
        assertEquals(1.0, stats.getPercentile(20));
        assertEquals(2.0, stats.getPercentile(40));
        assertEquals(3.0, stats.getPercentile(60));
        assertEquals(4.0, stats.getPercentile(80));
        assertEquals(5.0, stats.getPercentile(100));
    }

    @Test
    void testGetPercentile_OutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            stats.getPercentile(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            stats.getPercentile(101);
        });
    }

    @Test
    void testGetPercentile_EmptyArray() {
        DefaultStatistics emptyStats = new DefaultStatistics(new double[]{});
        assertThrows(IllegalArgumentException.class, () -> {
            emptyStats.getPercentile(50);
        });
    }

    @Test
    void testGetPercentiles_OutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            stats.getPercentiles(-1, 50, 101);
        });
    }

    @Test
    void testGetPercentiles_EmptyArray() {
        DefaultStatistics emptyStats = new DefaultStatistics(new double[]{});
        assertThrows(IllegalArgumentException.class, () -> {
            emptyStats.getPercentiles(50);
        });
    }
}
