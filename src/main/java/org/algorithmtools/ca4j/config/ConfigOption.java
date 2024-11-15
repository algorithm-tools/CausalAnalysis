package org.algorithmtools.ca4j.config;


import java.util.Objects;

public class ConfigOption<T> {

    private final String key;

    private final T defaultValue;

    private final String description;

    public ConfigOption(String key, T defaultValue, String description) {
        this.key = key;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigOption<?> that = (ConfigOption<?>) o;
        return Objects.equals(key, that.key) && Objects.equals(defaultValue, that.defaultValue) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, defaultValue, description);
    }

    @Override
    public String toString() {
        return "ConfigOption{" +
                "key='" + key + '\'' +
                ", defaultValue=" + defaultValue +
                ", description='" + description + '\'' +
                '}';
    }
}
