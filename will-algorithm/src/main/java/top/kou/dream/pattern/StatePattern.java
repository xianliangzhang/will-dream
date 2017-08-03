package top.kou.dream.pattern;

import sun.rmi.transport.tcp.TCPChannel;
import sun.rmi.transport.tcp.TCPConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by ZXL on 2017/8/3.
 */
public class StatePattern {
    static abstract class TcpConnection {
        abstract void open();

        abstract void close();

        abstract void transfer(Object data);

        protected void setState(TcpState tcpState) {
            this.tcpState = tcpState;
        }

        protected TcpState getState() {
            return tcpState;
        }

        protected TcpState tcpState;
    }

    static abstract class TcpState extends TcpConnection {
        protected TcpConnection tcpConnection;

        protected TcpState(TcpConnection t) {
            this.tcpConnection = t;
        }

        protected void updateState(TcpState tcpState) {
            tcpConnection.setState(tcpState);
        }
    }

    static class TcpEstablishedState extends TcpState {

        protected TcpEstablishedState(TcpConnection t) {
            super(t);
        }

        @Override
        void open() {
            System.out.println("TcpEstablishedState - open");
        }

        @Override
        void close() {
            System.out.println("TcpEstablishedState - close");
        }

        @Override
        void transfer(Object data) {
            System.out.println("TcpEstablishedState - transfer");
        }
    }

    static class TcpListeningState extends TcpState {

        protected TcpListeningState(TcpConnection t) {
            super(t);
        }

        @Override
        void open() {
            System.out.println("TcpListeningState - open");
        }

        @Override
        void close() {
            System.out.println("TcpListeningState - close");
        }

        @Override
        void transfer(Object data) {
            System.out.println("TcpListeningState - transfer");
        }
    }

    static class TcpClosedState extends TcpState {

        protected TcpClosedState(TcpConnection t) {
            super(t);
        }

        @Override
        void open() {
            System.out.println("TcpClosedState - open");
        }

        @Override
        void close() {
            System.out.println("TcpClosedState - close");
        }

        @Override
        void transfer(Object data) {
            System.out.println("TcpClosedState - transfer");
        }
    }

    static class SimpleTcpConnection extends TcpConnection {

        @Override
        void open() {

        }

        @Override
        void close() {

        }

        @Override
        void transfer(Object data) {

        }
    }


}
