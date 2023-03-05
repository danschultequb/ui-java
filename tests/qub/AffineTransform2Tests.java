package qub;

public interface AffineTransform2Tests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(AffineTransform2.class, () ->
        {
            MutableTransform2Tests.test(runner, AffineTransform2::create);
        });
    }
}
