package top.kou.dream.pattern.director;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.io.UnsupportedEncodingException;

/**
 * Created by ZXL on 2017/8/1.
 */
public class X {

    static abstract class Message {
        abstract String message();
    }

    static class TextMessage extends Message {
        String message;
        TextMessage(String message) {
            this.message = message;
        }

        @Override
        String message() {
            return message;
        }
    }

    static abstract class MessageDecorator extends Message {
        Message message;
        MessageDecorator(Message message) {
            this.message = message;
        }
    }

    static class DecryptMessageDecorator extends MessageDecorator {
        DecryptMessageDecorator(Message message) {
            super(message);
        }

        @Override
        String message() {
            try {
                return new String(HexBin.decode(message.message()), "utf8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    static class EncryptMessageDecorator extends MessageDecorator {
        EncryptMessageDecorator(Message message) {
            super(message);
        }

        @Override
        String message() {
            return HexBin.encode(message.message().getBytes());
        }
    }

    public static void main(String[] args) {
        String encrypted = new EncryptMessageDecorator(new TextMessage("God")).message();
        String decrypted = new DecryptMessageDecorator(new TextMessage(encrypted)).message();
        System.out.println(encrypted + " - " + decrypted);
    }
}
