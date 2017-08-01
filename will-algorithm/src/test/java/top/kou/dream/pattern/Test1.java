package top.kou.dream.pattern;

/**
 * Created by ZXL on 2017/8/1.
 */
public class Test1 {
    static class Source {
        Integer type;
    }

    static class CustomerSourceDirector extends Source {
        private Integer customerId;
        private Source source;

        CustomerSourceDirector(Source source) {
            this.source = source;
        }
    }

    static class MeasureDateSourceDirector extends Source {
        private Long measureDate;
        private Source source;
        MeasureDateSourceDirector(Source source) {
            this.source = source;
        }
    }
}
