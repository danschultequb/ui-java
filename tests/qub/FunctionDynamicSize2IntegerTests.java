package qub;

public interface FunctionDynamicSize2IntegerTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(BasicMutableDynamicSize2Integer.class, () ->
        {
            final Function0<FunctionDynamicSize2Integer> creator = () ->
            {
                final MutableSize2Integer size = Size2.create(1, 2);
                final RunnableEvent1<SizeChange> event = Event1.create();
                return FunctionDynamicSize2Integer.create()
                    .setGetWidthFunction(size::getWidth)
                    .setGetHeightFunction(size::getHeight)
                    .setOnChangedFunction(event::subscribe);
            };
        });
    }
}
