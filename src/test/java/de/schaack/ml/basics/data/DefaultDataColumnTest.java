package de.schaack.ml.basics.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.schaack.ml.basics.data.implementation.DefaultDataColumn;
import de.schaack.ml.basics.data.interfaces.Statistics;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDataColumnTest {

    private DefaultDataColumn dataColumn;
    private double[] features;

    @BeforeEach
    void setUp() {
        // Initialize the dataset for tests
        features = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        dataColumn = new DefaultDataColumn(features, "AttributeName", "AttributeDescription");
    }

    @Test
    void testConstructorWithFeatures() {
        DefaultDataColumn column = new DefaultDataColumn(features);
        assertArrayEquals(features, column.getFeatures());
        assertFalse(column.hasAttributeName());
        assertFalse(column.hasAttributeDescription());
    }

    @Test
    void testConstructorWithFeaturesAndName() {
        DefaultDataColumn column = new DefaultDataColumn(features, "AttributeName");
        assertArrayEquals(features, column.getFeatures());
        assertTrue(column.hasAttributeName());
        assertFalse(column.hasAttributeDescription());
        assertEquals("AttributeName", column.attributeName());
    }

    @Test
    void testConstructorWithFeaturesNameAndDescription() {
        assertArrayEquals(features, dataColumn.getFeatures());
        assertTrue(dataColumn.hasAttributeName());
        assertTrue(dataColumn.hasAttributeDescription());
        assertEquals("AttributeName", dataColumn.attributeName());
        assertEquals("AttributeDescription", dataColumn.attributeDescription());
    }

    @Test
    void testHasAttributeName() {
        assertTrue(dataColumn.hasAttributeName());
        DefaultDataColumn column = new DefaultDataColumn(features);
        assertFalse(column.hasAttributeName());
    }

    @Test
    void testAttributeName() {
        assertEquals("AttributeName", dataColumn.attributeName());
    }

    @Test
    void testAttributeName_ThrowsException() {
        DefaultDataColumn column = new DefaultDataColumn(features);
        assertThrows(IllegalStateException.class, column::attributeName);
    }

    @Test
    void testHasAttributeDescription() {
        assertTrue(dataColumn.hasAttributeDescription());
        DefaultDataColumn column = new DefaultDataColumn(features, "AttributeName");
        assertFalse(column.hasAttributeDescription());
    }

    @Test
    void testAttributeDescription() {
        assertEquals("AttributeDescription", dataColumn.attributeDescription());
    }

    @Test
    void testAttributeDescription_ThrowsException() {
        DefaultDataColumn column = new DefaultDataColumn(features, "AttributeName");
        assertThrows(IllegalStateException.class, column::attributeDescription);
    }

    @Test
    void testGetFeatures() {
        assertArrayEquals(features, dataColumn.getFeatures());
    }

    @Test
    void testGetFeature() {
        assertEquals(3.0, dataColumn.getFeature(2));
    }

    @Test
    void testGetFeature_ThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> dataColumn.getFeature(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> dataColumn.getFeature(5));
    }

    @Test
    void testSubsetWithIndices() {
        double[] subset = dataColumn.subset(new int[]{0, 2, 4});
        assertArrayEquals(new double[]{1.0, 3.0, 5.0}, subset);
    }

    @Test
    void testSubsetWithIndices_ThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> dataColumn.subset(new int[]{0, 5}));
    }

    @Test
    void testSubsetWithRange() {
        double[] subset = dataColumn.subset(1, 3);
        assertArrayEquals(new double[]{2.0, 3.0}, subset);
    }

    @Test
    void testSubsetWithRange_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> dataColumn.subset(3, 1));
    }

    @Test
    void testSubsetWithRange_ThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> dataColumn.subset(0, 6));
    }

    @Test
    void testGetStatistics() {
        Statistics stats = dataColumn.getStatistics();
        assertEquals(features.length, stats.getCount());
        assertEquals(3.0, stats.getMean(), 1e-9);
    }
}
