package top.kou.dream.serialize;

import java.io.*;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

/**
 * Created by ZXL on 2017/7/31.
 */
public class Serialization implements Serializable {
private long serialVersionID = 0L;
    private Integer id = 100;
    private String name = "GOD";
    private transient NonSerialization nonSerialization = new NonSerialization();
    private List<String> container = Arrays.asList("God", "Devil");

    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("stream data required");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject");
        in.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        System.out.println("writeObject");
        out.defaultWriteObject();
    }

    public Object readResolve() throws ObjectStreamException {
        System.out.println("readResolve");
        return this;
    }

    private Object writeReplace() throws ObjectStreamException {
        System.out.println("writeReplace");
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContainer() {
        return container;
    }

    public void setContainer(List<String> container) {
        this.container = container;
    }

    public NonSerialization getNonSerialization() {
        return nonSerialization;
    }

    public void setNonSerialization(NonSerialization nonSerialization) {
        this.nonSerialization = nonSerialization;
    }
}
