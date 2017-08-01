package top.kou.dream.serialize;

import org.springframework.util.SerializationUtils;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZXL on 2017/7/31.
 */
public class Externalization implements Externalizable {
    private Set<String> container = new HashSet<>(Arrays.asList("God", "Devil"));

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal");
    }

    public Collection<String> getContainer() {
        return new HashSet<>(container);
    }

    public static void main(String[] args) {
        Externalization origin = new Externalization();

        byte[] bytes = SerializationUtils.serialize(origin);
        Externalizable target =SerializeUtils.deserialize(bytes);

        assert origin.equals(bytes);
    }
}
