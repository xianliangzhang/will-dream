package top.kou.dream.pattern;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ZXL on 2017/8/1.
 */
public class ResponsibilityPattern {
    static class Request {
        Integer serialId;
    }

    interface RequestHandler {
        boolean accept(Request request);
        boolean handleRequest(Request request);
    }

    static class MinimumSerialRequestHandler implements RequestHandler {

        @Override
        public boolean accept(Request request) {
            return request.serialId != null && request.serialId <= 10;
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println("MinimumSerialRequestHandler - handleRequest");
            return true;
        }
    }

    static class MediumSerialRequestHandler implements RequestHandler {
        @Override
        public boolean accept(Request request) {
            return request.serialId != null && request.serialId >= 10 && request.serialId < 100;
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println("MediumSerialRequestHandler - handleRequest");
            return true;
        }
    }

    static class MaximumSerialRequestHandler implements RequestHandler {
        @Override
        public boolean accept(Request request) {
            return request.serialId != null && request.serialId >= 100;
        }

        @Override
        public boolean handleRequest(Request request) {
            System.out.println("MaximumSerialRequestHandler - handleRequest");
            return true;
        }
    }

    static class Client implements RequestHandler {
        private Set<RequestHandler> requestHandlers = new LinkedHashSet<>(Arrays.asList(
           new MinimumSerialRequestHandler(), new MediumSerialRequestHandler(), new MaximumSerialRequestHandler()
        ));


        @Override
        public boolean accept(Request request) {
            return true;
        }

        @Override
        public boolean handleRequest(Request request) {
            for (RequestHandler handler : requestHandlers) {
                if (handler.accept(request) && handler.handleRequest(request)) {
                    return true;
                }
            }
            return false;
        }
    }
}
