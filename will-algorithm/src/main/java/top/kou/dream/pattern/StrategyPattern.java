package top.kou.dream.pattern;

/**
 * Created by ZXL on 2017/8/3.
 */
public class StrategyPattern {
    interface  Context {
        Object getData();
        void compute(ComputeStrategy strategy);
    }
    interface ComputeStrategy {
        void compute();
        Context getContext();
    }

    static class SimpleComputerStrategy implements ComputeStrategy {
        private Context context;

        @Override
        public void compute() {
            System.out.println("SimpleComputerStrategy - compute");
        }

        @Override
        public Context getContext() {
            return context;
        }
    }

    static class ComplexComputerStrategy implements ComputeStrategy {
        private Context context;

        @Override
        public void compute() {
            System.out.println("ComplexComputerStrategy - compute");
        }

        @Override
        public Context getContext() {
            return context;
        }
    }

    public static void main(String[] args) {
        Context context = new Context() {

            @Override
            public Object getData() {
                return new Object();
            }

            @Override
            public void compute(ComputeStrategy strategy) {
                strategy.compute();
            }
        };

        context.compute(new SimpleComputerStrategy());
        context.compute(new ComplexComputerStrategy());


    }

}
