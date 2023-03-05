package qub;

public interface Transform2Tests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(Transform2.class, () ->
        {
            runner.test("create()", (Test test) ->
            {
                final MutableTransform2 transform = Transform2.create();
                test.assertNotNull(transform);
            });
        });
    }

    public static void test(TestRunner runner, Function0<? extends Transform2> creator)
    {
        runner.testGroup(Transform2.class, () ->
        {
            runner.testGroup("apply(Point2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final Transform2 transform = creator.run();
                    test.assertThrows(() -> transform.apply((Point2Integer)null),
                        new PreConditionFailure("point cannot be null."));
                });
            });

            runner.testGroup("applyAssign(MutablePoint2Integer)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final Transform2 transform = creator.run();
                    test.assertThrows(() -> transform.applyAssign((MutablePoint2Integer)null),
                        new PreConditionFailure("point cannot be null."));
                });
            });
        });
    }
}
