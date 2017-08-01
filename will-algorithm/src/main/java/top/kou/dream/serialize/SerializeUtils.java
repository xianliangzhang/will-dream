package top.kou.dream.serialize;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;

/**
 * Created by ZXL on 2017/7/31.
 */
public class SerializeUtils {

    public static <T extends Serialization> byte[] serialize(T serializable) {
        try {
            ByteArrayOutputStream oos = new ByteArrayOutputStream();
            new ObjectOutputStream(oos).writeObject(serializable);
            oos.flush();
            return oos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static  <T> T deserialize(byte[] bytes) {
        try {
            ObjectInputStream ins = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return (T) ins.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Serialization origin = new Serialization();

        byte[] bytes = SerializeUtils.serialize(origin);
        Serialization target = SerializeUtils.deserialize(bytes);

        System.out.print(target);
    }

}
