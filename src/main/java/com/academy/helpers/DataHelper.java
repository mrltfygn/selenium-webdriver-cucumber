package com.academy.helpers;

import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.function.Supplier;

public class DataHelper {
    /**
     * This function fills data for the fields that have not been defined in the initialdata at the gherkin level.
     * The data is not generated in this function itself, but by the supplier which should supply the new object with
     * generated faker data.
     *
     * Created by: Mazin Inaad
     *
     * @param initialData the initial data.
     * @param newDataSupplier the new daya supplier.
     * @param <T> the type.
     * @return object with generated fake data.
     */
    public static <T> T generateRemainingData(final T initialData, final Supplier<T> newDataSupplier) {
        final T result = newDataSupplier.get();
        final Field[] fields = initialData.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                if (f.get(initialData) != null) {
                    f.set(result, f.get(initialData));
                }
            }
        } catch (IllegalAccessException e) {
            Assert.fail("Error occured in generateRemainingData while trying to copy data from initial data to generated data");
        }
        return result;
    }
}
