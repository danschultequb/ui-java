package qub;

public interface BasicMutableDynamicSize2IntegerTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(BasicMutableDynamicSize2Integer.class, () ->
        {
            MutableDynamicSize2IntegerTests.test(runner, () -> BasicMutableDynamicSize2Integer.create(1, 2));
        });
    }
}
