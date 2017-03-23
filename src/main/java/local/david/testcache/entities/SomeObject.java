package local.david.testcache.entities;

import local.david.testcache.cache.Cachable;

import java.io.Serializable;

/**
 * Created by david on 23.03.17.
 */
public class SomeObject implements Cachable, Serializable {
    private String key;
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SomeObject object = (SomeObject) o;

        return key != null ? key.equals(object.key) : object.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "SomeObject{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
